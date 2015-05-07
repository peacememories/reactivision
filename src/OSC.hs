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

) where


import Data.Attoparsec.ByteString

type Time = Int -- This is very wrong

data OSC = Bundle Time [OSC]
         | Message [Datum]

data Datum = String String
           | Int    Int
           | Float  Float

