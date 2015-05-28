package util;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.TouchEvent;

/**
 * Created by gabriel on 26/05/2015.
 */
public class Widget implements Renderable, Dispatcher.DispatchHandler {
    private Renderable child;

    private float x, y;
    private float angle;
    private float scaleX = 1, scaleY = 1;

    public Widget() {
        Dispatcher.getInstance().register(this);
    }

    public Widget setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Widget setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public Widget setScale(float x, float y) {
        scaleX = x;
        scaleY = y;
        return this;
    }

    public Widget setChild(Renderable child) {
        this.child = child;
        return this;
    }

    @Override
    public void render(PApplet context) {
        if(child != null) {
            context.pushMatrix();
            context.translate(x, y);
            context.rotate(angle);
            context.scale(scaleX, scaleY);
            child.render(context);
            context.popMatrix();
        }
    }

    @Override
    public void handle(Object payload) {
        if(payload instanceof TouchEvent) {
            TouchEvent e = (TouchEvent) payload;
            Object ntv = e.getNative();
            if(ntv instanceof PVector) {
                PVector coords = (PVector) ntv;
                if(inWidget(coords)) {
                    this.handleTouch(e);
                }
            }
        }
    }

    private boolean inWidget(PVector p) {
        return this.x <= p.x && p.x <= this.x + this.getWidth() &&
               this.y <= p.y && p.y <= this.y + this.getHeight();
    }

    @Override
    public float getWidth() { return child.getWidth(); }

    @Override
    public float getHeight() { return child.getHeight(); }

    @Override
    public void handleTouch(TouchEvent e) { child.handleTouch(e); }
}
