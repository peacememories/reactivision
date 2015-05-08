-----------------------------------------------------------------------------
--
-- Module      :  Sound.OSC
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

module Sound.OSC
    ( Timestamp
    , OSC
        ( Bundle
        , Message
        )
    , Datum
        ( String
        , Int
        , Float
        , Blob
        )
) where

import Data.Word       (Word64)
import Data.Int        (Int32)
import Data.ByteString (ByteString)

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
