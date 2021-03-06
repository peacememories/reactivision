import TUIO.TuioClient;
import stores.AgentStore;
import stores.HappinessStore;
import util.Conf;
import processing.core.*;
import shiffman.box2d.*;
import util.Widget;
import widgets.HappinessGraph;
//import
//import

//import org.jbox2d.collision.shapes.

public class TestSketch extends PApplet {

  // A reference to our box2d world
  Box2DProcessing box2d;

  Ball ball;

  Widget graphWidget;

  public TestSketch() {
    super();
  }

	// A list we'll use to track fixed objects
	//ArrayList<Boundary> boundaries;
	// A list for all of our rectangles
	//ArrayList<Box> boxes;


  TuioClient tuio;
  private long lastFrame;

  public void setup() {
    size(Conf.SCREEN_WIDTH, Conf.SCREEN_HEIGHT);
    background(0);
    smooth();

    // ---------- Box2D-Processing Setup ----
    //box2d = new Box2DProcessing(this);
    //box2d.createWorld();
    // We are setting a custom gravity
    //box2d.setGravity(0, -20);

    // ---------- Creating Game Pieces ------
    //ball = new Ball(box2d, this);

    graphWidget = new Widget().setChild(new HappinessGraph(150, 100)).setPosition(Conf.SCREEN_WIDTH / 2 - 75, 10);


    tuio = new TuioClient();
    MyTuioListener tuioListener = new MyTuioListener();
    tuio.addTuioListener(tuioListener);
    tuio.connect();


    lastFrame = System.currentTimeMillis();
  }

  // ------- DEMO CIRCLE VARS ---------------
  public void draw() {
    long now = System.currentTimeMillis();
    float deltaSeconds = (now - lastFrame) / 1000.0f;

    // UPDATES ---------------
    // We must always step through time!
    //box2d.step(1.0f / 60, 10, 10);
    // TODO if there should be any shapes that can leave the screen: delete them now

    AgentStore.getStore().update(deltaSeconds);
    HappinessStore.getInstance().update(now);
    //stores.FacilitiesStore.getStore().update(deltaSeconds); //???

    //System.out.println(AgentStore.getStore().avgExcitation());


    // RENDERING -------------
    background(30);
    //ball.render(this);

    AgentRenderer.getRenderer().render(this);
    FacilityRenderer.getRenderer().render(this);

    graphWidget.render(this);

    lastFrame = now;
  }

  public static void main(String args[]) {
     PApplet.main(new String[] { "--present", "TestSketch" });
  }
}
