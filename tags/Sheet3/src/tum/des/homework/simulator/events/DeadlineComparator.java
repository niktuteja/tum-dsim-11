package tum.des.homework.simulator.events;

import java.util.Comparator;

public class DeadlineComparator implements Comparator<CustomerArrival> {

	@Override
	public int compare(CustomerArrival o1, CustomerArrival o2) {
		if (o1.customerStats.deadlineEvent.getExecutionTime() < o2.customerStats.deadlineEvent.getExecutionTime())
			return -1;

		if (o1.customerStats.deadlineEvent.getExecutionTime() > o2.customerStats.deadlineEvent.getExecutionTime())
			return 1;

		return 0;
	}

}
