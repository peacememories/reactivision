import processing.core.*;
import shiffman.box2d.*;

public class TestSketch extends PApplet {
 
  // code taken from:
  // https://processing.org/tutorials/eclipse/
  // (there's more there)

  public void setup() {
    //Box2DProcessing b = new Box2DProcessing(this);
    size(200,200);
    background(0);
  }

  public void draw() {
    stroke(255);
    if (mousePressed) {
      line(mouseX,mouseY,pmouseX,pmouseY);
    }
  }

  public static void main(String args[]) {
     PApplet.main(new String[] { "--present", "TestSketch" });
  }
}
