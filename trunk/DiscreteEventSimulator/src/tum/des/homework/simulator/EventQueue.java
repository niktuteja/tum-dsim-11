package tum.des.homework.simulator;

import java.util.PriorityQueue;

import tum.des.homework.simulator.events.EventBase;

public class EventQueue {

	private PriorityQueue<EventBase> queue = new PriorityQueue<EventBase>();

	public void enqueueEvent(EventBase evt) {
		queue.add(evt);
	}

	public EventBase dequeueNextEvent() {
		EventBase evt = queue.poll();
		System.out.println("dequeuing event " + evt);
		return evt;
	}

}
