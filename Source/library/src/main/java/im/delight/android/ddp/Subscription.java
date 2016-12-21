package im.delight.android.ddp;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by elloyg on 20/12/2016.
 */

public class Subscription {

	private Map<String, Object> data;
	private boolean ready = false;
	private final Queue<SubscriptionListener> mListeners;

	private final CallbackProxy callbackProxy;

	public Subscription(Map<String, Object> data, CallbackProxy callbackProxy) {
		this.data = data;
		this.ready = false;
		this.mListeners = new ConcurrentLinkedQueue<SubscriptionListener>();

		this.callbackProxy = callbackProxy;
	}


	public Map<String, Object> getData() {
		return data;
	}

	public String getId() {
		return (String)data.get(Protocol.Field.ID);
	}

	public String getName() {
		return (String)data.get(Protocol.Field.NAME);
	}

	public synchronized boolean isReady() {
		return ready;
	}

	public synchronized void setReady(boolean ready) {
		this.ready = ready;
		Iterator<SubscriptionListener> listenersIterator = this.mListeners.iterator();
		while (listenersIterator.hasNext()) {
			SubscriptionListener listener = listenersIterator.next();
			callbackProxy.forSubscriptionListener(listener).onSubscriptionReady(ready);
		}
	}

	public void addListener(SubscriptionListener listener) {
		this.mListeners.add(listener);
	}

	public void removeListener(SubscriptionListener listener) {
		this.mListeners.remove(listener);
	}

	public void removeListeners() {
		this.mListeners.clear();
	}

}
