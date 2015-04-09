# Ex1 #

| system | avg waiting time | avg waiting queue length | avg utilization |
|:-------|:-----------------|:-------------------------|:----------------|
| A        | 14.789756702265771| 1.3457173709888353| 0.4019980808039962|
| B        | 30.09549696229931| 2.743334411349833| 0.6896125894480629|
| C       | 50.81662633870892| 4.848532810952254| 0.8186634390105957|

## Observations ##
  * avg waiting queue length is about half of the number of dry waiting queue slots
  * avg utilization increases with the number of dry waiting queue slots
  * avg waiting time also increases with the number of dry waiting queue slots

## Answer ##
For all three systems customers are less willing to wait in the rain (50%) than inside in order to get their beer. Increasing the amount of dry waiting slots yields to more customers willing to wait and thus impacting the length of the waiting queue and of course the average waiting time.
Related to this the system is better utilized with more dry slots due to shorter inter arrival times.


# Ex2 #

| system | avg waiting time | avg waiting queue length | avg utilization | satisfied customers % |
|:-------|:-----------------|:-------------------------|:----------------|:----------------------|
| A        | 10635.591252853132| 1.105493537015276| 0.6124259674109652|100.0|
| B        | 5068.169948129948| 0.5207692307692308| 0.530910170578998|80.94867669678058|
| C        | 7256.7885071723285| 0.7334295699741681| 0.579158023740802|94.04958179186103|

## Observations ##
  * Deadlines yield to less satisfied customers (If they matter waiting)
  * Utilization of the system is less if we introduce deadlines
  * Also the avg waiting time & waiting queue length decreases

## Answer ##
Since Customers tend to step out of the waiting queue if they need to wait for a long time, the system has less customers to serve (less utilization) and the waiting times / queue length decrease.
For System C Customers are more satisfied because they are willing to wait longer than in System B.

# Ex3 #

| system | avg waiting time | avg waiting queue length | avg utilization | satisfied customers % |
|:-------|:-----------------|:-------------------------|:----------------|:----------------------|
| C (FIFO)      | 7256.7885071723285| 0.7334295699741681| 0.579158023740802|94.04958179186103|
| C (EDF)       | 8081.942106548586| 0.8248874211948364| 0.5914543202096728|95.06369748238477|

## Observations ##
  * With EDF we have longer avg waiting times and a longer queue size
  * Better satisfaction
  * Better utilization

## Answer ##
Taking the Customers with earlier deadlines out of the queue first, yields into more Customers being able to be served, since Customers who intent to leave the queue are served preferably. This increases the utilization and satisfaction.
Moreover Customers that are willing to wait longer stay longer in the system ==> longer waiting queue size + waiting times.