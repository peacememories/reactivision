import TUIO.*;
import processing.core.PVector;
import processing.event.Event;
import processing.event.TouchEvent;
import util.Conf;
import util.Dispatcher;

/**
 * Created by moru on 5/28/2015.
 */
public class MyTuioListener implements TuioListener {
    @Override
    public void addTuioObject(TuioObject tobj) {
        //System.out.println("addTuioObject");
    }

    @Override
    public void updateTuioObject(TuioObject tobj) {
        //System.out.println("updateTuioObject");
    }

    @Override
    public void removeTuioObject(TuioObject tobj) {
        //System.out.println("removeTuioObject");
    }

    @Override
    public void addTuioCursor(TuioCursor tcur) {
        //System.out.println("addTuioCursor");
    }

    @Override
    public void updateTuioCursor(TuioCursor tcur) {
        //System.out.println("updateTuioCursor");
    }

    @Override
    public void removeTuioCursor(TuioCursor tcur) {
        TuioPoint tp = tcur.getPosition();
        PVector p = new PVector(tp.getX() * Conf.SCREEN_WIDTH, tp.getY() * Conf.SCREEN_HEIGHT);

        //System.out.println("removeTuioCursor at (" + p.x + ", " + p.y + ")");

        //TouchEvent(java.lang.Object nativeObject, long millis, int action, int modifiers)
        Dispatcher.getInstance().dispatch(new TouchEvent(p, 0, Event.TOUCH, Event.TOUCH));
    }

    @Override
    public void refresh(TuioTime ftime) {
        //System.out.println("refresh");
    }
}
