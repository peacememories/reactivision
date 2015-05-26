package util;

import processing.core.PApplet;

/**
 * Created by gabriel on 26/05/2015.
 */
public class Widget implements Renderable {
    private Renderable child;

    private float x, y;
    private float angle;
    private float scaleX = 1, scaleY = 1;

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
}
