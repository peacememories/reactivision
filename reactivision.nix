{ mkDerivation, base, helm, stdenv }:
mkDerivation {
  pname = "reactivision";
  version = "0.1.0.0";
  src = ./.;
  isLibrary = false;
  isExecutable = true;
  buildDepends = [ base helm ];
  homepage = "http://github.com/peacememories/reactivision";
  description = "A haskell game for reactivision";
  license = stdenv.lib.licenses.mit;
}
