with (import <nixpkgs> {}).pkgs;
let myhelm = haskellngPackages.callPackage ./helm.nix {};
    pkg = haskellngPackages.callPackage ./reactivision.nix {helm = myhelm;};
in
  pkg.env
