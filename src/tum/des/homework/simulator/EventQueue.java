package tum.des.homework.simulator;

import java.util.PriorityQueue;
import java.util.Properties;

import tum.des.homework.simulator.events.EventBase;

public class EventQueue {

	private final PriorityQueue<EventBase> queue = new PriorityQueue<EventBase>();
	private long maxSize = Long.MAX_VALUE;

	public EventQueue(Properties p) {
		String maxSize = p.getProperty("eventQueue.maxSize");
		if (maxSize != null) {
			try {
				this.maxSize = Long.parseLong(maxSize);
			} catch (NumberFormatException e) {
				// swallow
			}
		}
	}

	public boolean enqueueEvent(EventBase evt) {

		if (queue.size() < this.maxSize) {
			queue.add(evt);
			return true;
		}

		return false;
	}

	public EventBase dequeueNextEvent() {
		EventBase evt = queue.poll();
		return evt;
	}

	public long size() {
		return queue.size();
	}

	public void removeEvent(EventBase event) {
		queue.remove(event);
	}

}
