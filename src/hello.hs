module Main (main) where

import OSC
import Data.Attoparsec.ByteString.Lazy
import Network.Socket hiding (recv)
import Network.Socket.ByteString.Lazy
import qualified Data.ByteString.Lazy as S
import Control.Monad (unless)

main = do
    con <- socket AF_INET Datagram defaultProtocol
    addr <- inet_addr "127.0.0.1"
    bind con (SockAddrInet 3333 addr)
    handleInput con

handleInput :: Socket -> IO ()
handleInput con = do
    msg <- recv con 4096
    unless (S.null msg) $
        (putStrLn $ handleMessage msg) >> handleInput con

handleMessage :: S.ByteString -> String
handleMessage = show . (parse osc)
