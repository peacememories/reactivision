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
        for(RandomAgent agent : RandomAgentStore.getStore().getAgents()) {
            PVector pos = agent.getPosition();
            ctxt.rectMode(ctxt.CENTER);
            ctxt.pushMatrix();
            ctxt.translate(pos.x, pos.y);
            ctxt.fill(210);
            ctxt.stroke(0);
            ctxt.ellipse(0, 0, 10, 10);
            ctxt.popMatrix();
        }
    }
}
