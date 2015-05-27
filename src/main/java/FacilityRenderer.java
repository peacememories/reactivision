/**
 * Created by moru on 27/05/15.
 */

import facilities.Facility;
import processing.core.PVector;
import stores.FacilitiesStore;
import processing.core.PApplet;

public class FacilityRenderer {
    private static FacilityRenderer renderer;
    public static FacilityRenderer getRenderer() {
        if(renderer == null) {
            renderer = new FacilityRenderer();
        }
        return renderer;
    }

    private FacilityRenderer() {}

    public void render(PApplet ctxt) {
        for(Facility facility : FacilitiesStore.getStore().getFacilities()) {
            PVector pos = facility.position.get();
            ctxt.rectMode(ctxt.CENTER);
            ctxt.pushMatrix();
            ctxt.translate(pos.x, pos.y);
            switch (facility.type) {
                case SPORT:
                    ctxt.fill(0, 0, 200.0f, 120.5f);
                    break;
                case CLUB:
                    ctxt.fill(200.0f, 0, 200.0f, 120.5f);
                    break;
                default:
                    ctxt.fill(200.0f, 200.0f, 200.0f, 120.5f);
                    break;
            }
            ctxt.stroke(130);
            ctxt.ellipse(0, 0, facility.radius * 2, facility.radius * 2);
            ctxt.popMatrix();
        }
    }
}
