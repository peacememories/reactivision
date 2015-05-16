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
    rev = "3a964ade523f95986f2e3e3431523300e6e7d87d";
    sha256 = "0dz5rkwhxicdz9aa6b3n5r2z7rqdx2vsy8xdlcl05j7xlkszgij3";
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
