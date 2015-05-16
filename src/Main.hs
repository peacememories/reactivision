import FRP.Helm
import qualified FRP.Helm.Mouse as Mouse(position)
import qualified FRP.Helm.Window as Window(dimensions)

main = do
  run defaultConfig $ render <$> Window.dimensions <*> Mouse.position

render (w, h) (x, y) = collage w h [obj]
  where obj = move (fromIntegral x, fromIntegral y) (rect 10.0 10.0 |> filled red)
