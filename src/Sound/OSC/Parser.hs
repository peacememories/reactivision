-----------------------------------------------------------------------------
--
-- Module      :  Sound.OSC.Parser
-- Copyright   :  (c) Gabriel Pickl
-- License     :  BSD3
--
-- Maintainer  :  Gabriel Pickl <peacemotion@gmail.com>
-- Stability   :  unstable
-- Portability :
--
-- |
--
-----------------------------------------------------------------------------

module Sound.OSC.Parser (
    osc
) where

{-# LANGUAGE DoAndIfThenElse #-}


import Data.Attoparsec.ByteString as AP
import Data.Binary.Get
import Data.Binary.IEEE754
import Data.Word (Word64, Word8)
import Control.Applicative ((<$>), (<|>))
import Data.ByteString.Char8 (unpack, pack)
import Data.Int (Int32)
import qualified Data.ByteString as BS
import Data.ByteString.Lazy (fromStrict)
import Data.ByteString (ByteString)

import Sound.OSC

nest :: AP.Parser ByteString -> AP.Parser a -> AP.Parser a
nest bsp p = bsp >>= parse
    where
        parse bs = either fail return (parseOnly p bs)

int32 :: AP.Parser Int32
int32 = convert <$> AP.take 4
    where
        convert :: ByteString -> Int32
        convert bs = fromIntegral $ runGet getWord32be $ fromStrict bs

float32 :: AP.Parser Float
float32 = convert <$> AP.take 4
    where
        convert :: ByteString -> Float
        convert bs = runGet getFloat32be $ fromStrict bs

timestamp :: AP.Parser Timestamp
timestamp = convert <$> AP.take 8
    where
        convert :: ByteString -> Timestamp
        convert bs = runGet getWord64be $ fromStrict bs

oscString :: AP.Parser ByteString
oscString = do
    str <- AP.takeTill (==0)
    AP.take $ blockPadding (BS.length str)
    return str
    where
        blockPadding len = ((-len-1) `mod` 4)+1

tokenParser :: Word8 -> AP.Parser Datum
-- s
tokenParser 115 = String . unpack <$> oscString
-- i
tokenParser 105 = Int <$> int32
-- f
tokenParser 102 = Float <$> float32
-- b
tokenParser 98 = do
    size <- int32
    Blob <$> (AP.take $ fromIntegral size)
tokenParser t = fail $ "Could not parse type " ++ show t

typeTag :: Parser [Word8]
typeTag = do
    first <- AP.peekWord8
    case first of
        Just 44 -> tail . BS.unpack <$> oscString
        Just other -> fail $ "Unexpected token: " ++ show other
        Nothing -> return []

message :: AP.Parser OSC
message = do
    addr <- unpack <$> oscString
    types <- typeTag
    (Message addr) <$> (sequence $ map tokenParser types)

bundle :: AP.Parser OSC
bundle = do
    string $ pack "#bundle"
    word8 0
    time <- timestamp
    elems <- AP.manyTill bundleElement AP.endOfInput
    return $ Bundle time elems

bundleElement :: AP.Parser OSC
bundleElement = do
    length <- int32
    nest (AP.take $ fromIntegral length) osc

osc :: AP.Parser OSC
osc = do
    first <- AP.peekWord8
    case first of
        Just 35 -> bundle
        otherwise -> message
