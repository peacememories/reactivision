package util;

/**
 * Created by gabriel on 26/05/2015.
 */
public class Dispatcher extends EventSource<Dispatcher.DispatchHandler> {
    private final static Dispatcher instance = new Dispatcher();

    private Object currentPayload;
    private boolean dispatching = false;

    public static Dispatcher getInstance() {
        return instance;
    }

    private Dispatcher() {}

    public void dispatch(Object payload) {
        if(dispatching)
            throw new DispatchException("Dispatch is already running with event: " + currentPayload);
        synchronized (this) {
            dispatching = true;
            currentPayload = payload;
            fireAll();
            dispatching = false;
        }
    }

    @Override
    protected void fire(DispatchHandler h) {
        h.handle(currentPayload);
    }

    public static interface DispatchHandler {
        public void handle(Object payload);
    }

    public static class DispatchException extends RuntimeException {
        public DispatchException() {
        }

        public DispatchException(String message) {
            super(message);
        }

        public DispatchException(String message, Throwable cause) {
            super(message, cause);
        }

        public DispatchException(Throwable cause) {
            super(cause);
        }

        public DispatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
