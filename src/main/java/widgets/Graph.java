package widgets;

import processing.core.PApplet;
import util.ListTools;
import util.Renderable;

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
        context.noFill();
        context.beginShape();
        for(Point p: ListTools.map(new ListTools.Fun1<Point, Point>() {
            @Override
            public Point call(Point val) {
                return new Point(val.x*getWidth(), (getHeight()-val.y)/2);
            }
        }, getPoints())) {
            if(p.x > 0 && p.x < getWidth())
                context.vertex(p.x*getWidth(), getHeight()-p.y);
        }
        context.endShape();
        context.fill(50);
        context.rect(0, 0, getWidth(), getHeight());
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
