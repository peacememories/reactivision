package widgets;

import processing.core.PApplet;
import util.Renderable;

/**
 * Created by gabriel on 26/05/2015.
 */
public class Graph implements Renderable {
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

    }
}
