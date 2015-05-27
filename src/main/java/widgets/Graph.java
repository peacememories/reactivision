package widgets;

import processing.core.PApplet;
import processing.core.PVector;
import util.ListTools;
import util.Renderable;

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

        LinkedList<PVector> points = new LinkedList<>();
        for(Point p : getPoints()) {
            points.add(new PVector(
                    p.x * getWidth(),
                    getHeight() * (1 - p.y) / 2
            ));
        }

        context.stroke(0, 255, 255);

        // paint lower half ---------------

        context.beginShape();
        context.fill(80,180,80);
        context.vertex(0, height); // start in lower left corner
        context.vertex(0, points.getFirst().y);
        for(PVector p : points) {
 //           if(p.x >= 0 && p.x <= getWidth()) {
                context.vertex(p.x, p.y);
//            }
        }
        context.vertex(width, points.getLast().y);
        context.vertex(width, height);
        context.endShape();

        // paint upper half ---------------



        context.beginShape();
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

        context.noFill();
        context.rect(getWidth()/2, getHeight()/2, getWidth(), getHeight());

    }

    public abstract Iterable<Point> getPoints();

    public static class Point {
        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public final float x;
        public final float y;
    }
}
