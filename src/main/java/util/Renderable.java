package util;

import processing.core.PApplet;
import processing.event.TouchEvent;

/**
 * Created by gabriel on 26/05/2015.
 */
public interface Renderable {
    public void render(PApplet context);
    public float getWidth();
    public float getHeight();

    void handleTouch(TouchEvent e);
}
