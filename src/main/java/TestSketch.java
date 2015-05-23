import processing.core.*;
import shiffman.box2d.*;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
//import org.jbox2d.collision.shapes.

public class TestSketch extends PApplet {

  // A reference to our box2d world
  Box2DProcessing box2d;

  public TestSketch() {
    super();


    // ---------- Box2D-Processing Setup ----

    box2d = new Box2DProcessing(this);
    box2d.createWorld();
    // We are setting a custom gravity
    box2d.setGravity(0, -20);
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

    // ---------- INIT DEMO CIRCLE ---------

    BodyDef bd = new BodyDef();
    bd.position.set(box2d.coordPixelsToWorld(x,y));
    bd.type = BodyType.DYNAMIC;

    CircleShape circle = new CircleShape();
    circle.m_p.set(3.0f, 3.0f);
    circle.m_radius = 0.5f;

    FixtureDef fd = new FixtureDef();
    fd.shape = circle;
    fd.density = 0;
    fd.friction = 0.3f;
    fd.restitution = 0.5f;

    body = box2d.createBody(bd);
    body.createFixture(fd);

    // Give it some initial random velocity
    body.setLinearVelocity(new Vec2(random(-5, 5), random(2, 5)));
    body.setAngularVelocity(random(-5, 5));
  }

  // ------- DEMO CIRCLE VARS ---------------
  Body body;
  int x = 100;
  int y = 50;

  public void draw() {
    background(0);

    // We must always step through time!
    box2d.step(1.0f/60,10,10);

    // TODO if there should be any shapes that can leave the screen: delete them now

    // ------------- DRAW DEMO CIRCLE -------------------

    // We look at each body and get its screen position
    Vec2 pos = box2d.getBodyPixelCoord(body);
    System.out.println(pos);
    // Get its angle of rotation
    float a = body.getAngle();

    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    fill(175);
    stroke(0);
    ellipse(0, 0, 10, 10);
    popMatrix();
  }

  public static void main(String args[]) {
     PApplet.main(new String[] { "--present", "TestSketch" });
  }
}
