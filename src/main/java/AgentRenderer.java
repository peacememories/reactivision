import processing.core.PVector;
import util.Renderable;
import processing.core.PApplet;

/**
 * Created by moru on 5/26/2015.
 */
public class AgentRenderer implements Renderable {

    private static AgentRenderer renderer;
    public static AgentRenderer getRenderer() {
        if(renderer == null) {
            renderer = new AgentRenderer();
        }
        return renderer;
    }

    private AgentRenderer() {}

    public void render(PApplet ctxt) {
        for(Agent agent : AgentStore.getStore().getAgents()) {
            PVector pos = agent.getPosition();
            ctxt.rectMode(ctxt.CENTER);
            ctxt.pushMatrix();
            ctxt.translate(pos.x, pos.y);

            //float red = 255.0f;
            float red = (1.0f - agent.getHappiness()) / 2 * 255;
            float green = (1.0f + agent.getHappiness()) / 2 * 255;
            //float blue = (1.0f + agent.getHappiness()) / 2 * 180;
            float blue = 0.0f;
            ctxt.fill(red, green, blue);

            //System.out.println(agent.getHappiness() + " -> (" + red + ", " + green + ", " + blue + ")");

            ctxt.stroke(0);
            ctxt.ellipse(0, 0, 10, 10);
            ctxt.popMatrix();
        }
    }
}
