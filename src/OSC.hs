-----------------------------------------------------------------------------
--
-- Module      :  OSC
-- Copyright   :  (c) Simon Marlow
-- License     :  BSD3
--
-- Maintainer  :  Simon Marlow <marlowsd@gmail.com>
-- Stability   :  stable
-- Portability :
--
-- |
--
-----------------------------------------------------------------------------

module OSC (
    OSC,
    Datum,
    osc
) where

{-# LANGUAGE DoAndIfThenElse #-}


import Data.Attoparsec.ByteString.Lazy as AP
import Data.Binary.Get
import Data.Binary.IEEE754
import Data.Word (Word64, Word8)
import Control.Applicative ((<$>), (<|>))
import Data.ByteString.Char8 hiding (map, tail)
import Data.Int (Int32)
import Data.ByteString.Lazy (fromChunks)
import qualified Data.ByteString as BS

type Timestamp = Word64

data OSC = Bundle Timestamp [OSC]
         | Message String [Datum]
         deriving (Show)

-- Minimal type definition for OSC
data Datum = String String
           | Int    Int32
           | Float  Float
           | Blob   ByteString
           deriving (Show)

int32 :: AP.Parser Int32
int32 = convert <$> AP.take 4
    where
        convert :: ByteString -> Int32
        convert bs = fromIntegral $ runGet getWord32be $ fromChunks [bs]

float32 :: AP.Parser Float
float32 = convert <$> AP.take 4
    where
        convert :: ByteString -> Float
        convert bs = runGet getFloat32be $ fromChunks [bs]

timestamp :: AP.Parser Timestamp
timestamp = convert <$> AP.take 8
    where
        convert :: ByteString -> Timestamp
        convert bs = runGet getWord64be $ fromChunks [bs]

nest :: AP.Parser ByteString -> AP.Parser a -> AP.Parser a
nest bsp p = bsp >>= parse
    where
        parse bs = either fail return (parseOnly p bs)

bytestring :: AP.Parser ByteString
bytestring = do
    str <- takeTill (==0)
    AP.take $ remaining (BS.length str)
    return str
    where
        remaining len = 4 - ((len+1) `mod` 4)

stringParse :: AP.Parser String
stringParse = unpack <$> bytestring

tokenParser :: Char -> AP.Parser Datum
tokenParser 's' = String <$> stringParse
tokenParser 'i' = Int <$> int32
tokenParser 'f' = Float <$> float32
tokenParser 'b' = do
    size <- int32
    Blob <$> (AP.take $ fromIntegral size)
tokenParser t = fail $ "Could not parse type " ++ [t]

w8tokenParser :: Word8 -> AP.Parser Datum
w8tokenParser = tokenParser . toEnum . fromEnum -- This has got to be super slow :/

typeTag = do
    list <- stringParse
    if (isTypeTag list)
        then
            return $ tail list
        else
            fail $ "Invalid token list: " ++ list
    where
        isTypeTag (',':_) = True
        isTypeTag _ = False

fromChar :: Char -> Word8
fromChar = toEnum . fromEnum

message :: AP.Parser OSC
message = do
    addr <- stringParse
    types <- typeTag
    (Message addr) <$> (sequence $ map tokenParser types)

bundle :: AP.Parser OSC
bundle = do
    string $ pack "#bundle"
    word8 0
    time <- timestamp
    elems <- many1' bundleElement
    return $ Bundle time elems

bundleElement :: AP.Parser OSC
bundleElement = do
    length <- int32
    nest (AP.take $ fromIntegral length) message

osc = bundle
