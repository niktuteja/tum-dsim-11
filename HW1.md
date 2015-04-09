# Introduction #

Goal: Discrete Event Simulator implementation & simple result evaluation


# Results #

## service time: 9 sec (const.) ##
### simulation time: 10⁴ sec ###
  * Min Queue Length: 0
  * Max Queue Length: 0
  * Queue Length on Termination: 0
### simulation time: 10⁵ sec ###
  * Min Queue Length: 0
  * Max Queue Length: 0
  * Queue Length on Termination: 0

## service time: 10 sec (const.) ##
### simulation time: 10⁴ sec ###
  * Min Queue Length: 0
  * Max Queue Length: 1
  * Queue Length on Termination: 0
### simulation time: 10⁵ sec ###
  * Min Queue Length: 0
  * Max Queue Length: 1
  * Queue Length on Termination: 0

## service time: `[`9..11`]` sec (rand.) ##
### simulation time: 10⁴ sec ###
  * Min Queue Length: 0
  * Max Queue Length: 3
  * Queue Length on Termination: 1
### simulation time: 10⁵ sec ###
  * Min Queue Length: 0
  * Max Queue Length: 5
  * Queue Length on Termination: 2

# Discussion #

For the first and second case it doesn't matter how long the simulation runs;
As for the former no customer is waiting at all since the service completion time is smaller than the time new customer arrivals. For the latter the customer arrival interval is exactly the same as the service completion time. So the new customer arrives just when the former is about to leave- This yields a maximum waiting queue length of 1.
In both cases we deal with a discrete and deterministic simulation due to no randomness being involved.

Contrary the last case is stochastic due to a randomized customer service time(but still discrete).
The randomness also influences the waiting queue: The max queue length is higher for longer executions times (3 vs. 5 - with 10⁶ even ~21), since it is more likely that you encounter a customer that needs to wait because there are X customers already waiting.

The minimum queue length is in all three examples zero since we're starting with an empty queue.