package util;

import java.util.HashSet;

/**
 * Created by gabriel on 26/05/2015.
 */
public abstract class EventSource<Handler> {
    private final HashSet<Handler> handlers = new HashSet<>();

    public void register(Handler handler) {
        handlers.add(handler);
    }

    public void unregister(Handler handler) {
        handlers.remove(handler);
    }

    protected void fireAll() {
        for(Handler h : handlers)
            fire(h);
    }

    abstract protected void fire(Handler h);
}
