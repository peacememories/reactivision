package util;

/**
 * Created by gabriel on 26/05/2015.
 */
public class Dispatcher extends EventSource<Dispatcher.DispatchHandler> {
    private final static Dispatcher instance = new Dispatcher();

    private Object currentPayload;

    public static Dispatcher getInstance() {
        return instance;
    }

    public synchronized void dispatch(Object payload) {
        currentPayload = payload;
        fireAll();
    }

    @Override
    protected void fire(DispatchHandler h) {
        h.handle(currentPayload);
    }

    public static interface DispatchHandler {
        public void handle(Object payload);
    }
}
