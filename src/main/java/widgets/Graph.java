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
        context.stroke(0, 255, 255);
        Iterable<Point> points = ListTools.map(new ListTools.Fun1<Point, Point>() {
            @Override
            public Point call(Point val) {
                return new Point(val.x*getWidth(), getHeight()*(1-val.y)/2);
            }
        }, getPoints());
        for(Point p: points) {
            if(p.x >= 0 && p.x <= getWidth())
                context.vertex(p.x, p.y);
        }
        context.endShape();
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
