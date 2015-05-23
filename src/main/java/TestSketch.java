import processing.core.*;
import shiffman.box2d.*;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
//import org.jbox2d.collision.shapes.

public class TestSketch extends PApplet {

  // A reference to our box2d world
  Box2DProcessing box2d;

  Ball ball;

  public TestSketch() {
    super();
  }

	// A list we'll use to track fixed objects
	//ArrayList<Boundary> boundaries;
	// A list for all of our rectangles
	//ArrayList<Box> boxes;

  // code taken from:
  // https://processing.org/tutorials/eclipse/
  // (there's more there)
  public void setup() {
    size(200, 200);
    background(0);
    smooth();

    // ---------- Box2D-Processing Setup ----
    box2d = new Box2DProcessing(this);
    box2d.createWorld();
    // We are setting a custom gravity
    box2d.setGravity(0, -20);

    // ---------- Creating Game Pieces ------
    ball = new Ball(box2d, this);
  }

  // ------- DEMO CIRCLE VARS ---------------
  public void draw() {
    background(30);

    // We must always step through time!
    box2d.step(1.0f/60,10,10);

    // TODO if there should be any shapes that can leave the screen: delete them now

    ball.render(this);
  }

  public static void main(String args[]) {
     PApplet.main(new String[] { "--present", "TestSketch" });
  }
}
