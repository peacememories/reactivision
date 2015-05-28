import agents.Agent;
import processing.core.PVector;
import processing.event.TouchEvent;
import stores.AgentStore;
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
            //float red = (1.0f - agent.getHappiness()) / 2 * 255;
            float red = agent.getHappiness() > 0 ? 0 : 255;
            //float green = (1.0f + agent.getHappiness()) / 2 * 255;
            float green = agent.getHappiness() > 0 ? 255 : 0;
            float blue = (1.0f + agent.getHappiness()) / 2 * 60;
            //float alpha = Math.abs(agent.getHappiness()) * 122 + 123;
            float alpha = Math.abs(agent.getHappiness()) * 255;

            //float blue = 0.0f;
            ctxt.fill(red, green, blue, alpha);

            //System.out.println(agent.getHappiness() + " -> (" + red + ", " + green + ", " + blue + ")");

            ctxt.stroke(130);
            ctxt.ellipse(0, 0, getWidth(), getHeight());
            ctxt.popMatrix();
        }
    }

    @Override
    public float getWidth() { return 10.0f; }

    @Override
    public float getHeight() { return 10.0f; }

    @Override
    public void handleTouch(TouchEvent e) {}
}
