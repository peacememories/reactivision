{ mkDerivation, base, cairo, containers, cpu, elerea, filepath
, HUnit, mtl, pango, random, sdl2, stdenv, test-framework
, test-framework-hunit, test-framework-quickcheck2, text, time
, transformers, fetchFromGitHub
}:
mkDerivation {
  pname = "helm";
  version = "0.7.1";
  src = fetchFromGitHub {
    owner = "peacememories";
    repo = "helm";
    rev = "3fafc398f95711d3015e6f61b01cc24defc03af4";
    sha256 = "052pb3gzgqw1rcvbbgjfqg8v3b8lrgn5zhkw6w53bqm1gmvwdhbq";
  };
  buildDepends = [
    base cairo containers cpu elerea filepath mtl pango random sdl2
    text time transformers
  ];
  testDepends = [
    base cairo containers elerea HUnit sdl2 test-framework
    test-framework-hunit test-framework-quickcheck2 time
  ];
  homepage = "http://github.com/switchface/helm";
  description = "A functionally reactive game engine";
  license = stdenv.lib.licenses.mit;
}
