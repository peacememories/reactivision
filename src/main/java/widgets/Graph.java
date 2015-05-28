package widgets;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.TouchEvent;
import util.Dispatcher;
import util.ListTools;
import util.Renderable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by gabriel on 26/05/2015.
 */
public abstract class Graph implements Renderable {
    private float width, height;

    public Graph(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }

    public void render(PApplet context) {

        LinkedList<PVector> points = new LinkedList(getPoints());


        // paint lower half ---------------

        context.beginShape();
        context.noStroke();
        context.fill(80,180,80);
        context.vertex(0, height); // start in lower left corner
        context.vertex(0, points.getFirst().y);
        for(PVector p : points) {
 //           if(p.x >= 0 && p.x <= getWidth()) {
                context.vertex(p.x*getWidth(), (1-p.y)*getHeight()/2);
//            }
        }
        context.vertex(width, points.getLast().y);
        context.vertex(width, height);
        context.endShape();

        // paint upper half ---------------
        context.beginShape();
        context.noStroke();
        context.fill(120,0,0);
        context.vertex(0, 0); // start in lower left corner
        context.vertex(0, points.getFirst().y);
        for(PVector p : points) {
            //           if(p.x >= 0 && p.x <= getWidth()) {
            context.vertex(p.x, p.y);
//            }
        }
        context.vertex(width, points.getLast().y);
        context.vertex(width, 0);
        context.endShape();


        // paint border

        context.stroke(0, 255, 255);
        context.noFill();
        context.rect(getWidth()/2, getHeight()/2, getWidth(), getHeight());

    }

    public abstract Collection<PVector> getPoints();

    public static class Point {
        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public final float x;
        public final float y;
    }
}
