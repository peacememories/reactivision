/**
 * Created by moru on 5/23/2015.
 */

import processing.core.*;
import shiffman.box2d.*;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class Ball {

    Body body;
    int x = 100;
    int y = 50;

    Box2DProcessing box2d;

    public Ball(Box2DProcessing box2dProcessing, PApplet p) {

        box2d = box2dProcessing;

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
        body.setLinearVelocity(new Vec2(p.random(-5, 5), p.random(2, 5)));
        body.setAngularVelocity(p.random(-5, 5));

        System.out.println(body);
    }

    /**
     * Renders the Ball onto <code>p</code>'s canvas.
     * @param p the PApplet to serve as canvas
     */
    public void render(PApplet p){
        // ------------- DRAW DEMO CIRCLE -------------------

        // We look at each body and get its screen position
        Vec2 pos = box2d.getBodyPixelCoord(body);
        // Get its angle of rotation
        //float a = body.getAngle();

        p.rectMode(p.CENTER);
        p.pushMatrix();
        p.translate(pos.x, pos.y);
        //p.rotate(-a);
        p.fill(210);
        p.stroke(0);
        p.ellipse(0, 0, 10, 10);
        p.popMatrix();
    }
}
