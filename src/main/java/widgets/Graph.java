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
    PVector playerMarker = null;
    private long markerTime;

    public Graph(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public float getWidth() { return width; }

    @Override
    public float getHeight() { return height; }

    /**
     * @param dataCoords (0-1) for time x (-1,+1) for value
     * @return pixel coordinates within the widget
     */
    private PVector toPixelCoords(PVector dataCoords) {
        return new PVector(dataCoords.x * getWidth(),
                (1 - dataCoords.y)*getHeight()/2);
    }

    public void render(PApplet context) {

        LinkedList<PVector> points = new LinkedList(getPoints());

        PVector firstCoords = toPixelCoords(points.getFirst());
        PVector lastCoords = toPixelCoords(points.getLast());


        float duration = points.getLast().x - points.getFirst().x;
        float timeSinceMarker = 1-(float)(System.currentTimeMillis() - markerTime)/duration;

        // paint lower half ---------------

        context.beginShape();
        context.noStroke();
        context.fill(80, 180, 80);
        context.vertex(0, height); // start in lower left corner
        context.vertex(0, firstCoords.y);
        for(PVector p : points) {
            PVector coords = toPixelCoords(p);
            context.vertex(coords.x/duration, coords.y);
        }
        context.vertex(width, lastCoords.y);
        context.vertex(width, height);
        context.endShape();

        // paint upper half ---------------
        context.beginShape();
        //context.noStroke();
        context.fill(80,0,0);
        context.vertex(0, 0); // start in lower left corner
        context.vertex(0, height); //TODO debug, remove me
        context.vertex(0, firstCoords.y);
        for(PVector p : points) {
            PVector coords = toPixelCoords(p);
            context.vertex(coords.x/duration, coords.y);
        }
        context.vertex(width, lastCoords.y);
        context.vertex(width, 0);
        //context.vertex(0, 0);
        context.endShape();


        // paint border
        context.stroke(0, 255, 255);
        context.noFill();
        context.rect(getWidth()/2, getHeight()/2, getWidth(), getHeight());

        context.line(0, getHeight()/2, getWidth(), getHeight()/2);
        if(timeSinceMarker>=0 && timeSinceMarker<= 1)
            context.line(getWidth()*timeSinceMarker, 0, getWidth()*timeSinceMarker, getHeight());

        // paint player marker
        if(playerMarker != null) {
            context.stroke(200,200,200);
            context.line(playerMarker.x, 0, playerMarker.x, getHeight());
            context.line(0, playerMarker.y, getWidth(), 0);

            System.out.println("Drawing player marker at " + playerMarker);

        }

    }

    @Override
    public void handleTouch(TouchEvent e) {
        markerTime = System.currentTimeMillis();
        System.out.println("Placed marker at y = " + markerTime);

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
