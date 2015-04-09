# Exercise 1 #
**Answer for Exercise 1.a**:
| System   | Avg waiting queue length | Maximum queue length |
|:---------|:-------------------------|:---------------------|
| A        | 0 | 1 |
| B        | 0.96 |  18 |
|----------|
| C        | 0 |  1 |
| D        | 5.40 |  3 |
|----------|
| E        |  4.18 | 44 |
| F        |  2.84 | 41 |
|----------|
| G        | 0.19 |  9 |
| H        |  0.11 | 8 |

- In M/M systems a single but fast service unit yields smaller average and maximum waiting queue lengths.

- In M/D and D/M systems the ten (slow) service units perform slightly better than one singe fast unit.


**Answer for Exercise 1.b**:
| System   | Avg waiting times | Maximum waiting times |
|:---------|:------------------|:----------------------|
| A        | 0 |  0 |
| B        | 10.61 | 138 |
|----------|
| C        | 0 |  0  |
| D        | 5.93 |  9  |
|----------|
| E        | 45.93 |  479  |
| F        | 31.23 | 448  |
|----------|
| G        | 2.06 |  45  |
| H        | 0.12 |  44  |

- In M/M systems a single but fast service unit yields smaller average and maximum waiting times.

- In M/D and D/M systems the ten (slow) service units perform slightly better than one singe fast unit.


**Answer for Exercise 1.c**:
| System   | Avg waiting times | Maximum waiting times | Avg service times | Maximumg service times |
|:---------|:------------------|:----------------------|:------------------|:-----------------------|
| A        | 0 |  0 | 10 | 146 |
| B        | 10.61 | 138 | 100 | 1458 |
|----------|
| C        | 0 |  0  | 5 | 73 |
| D        | 5.93 |  9  | 50 | 729 |
|----------|
| E        | 45.93 |  479  | 10 | 146 |
| F        | 31.23 | 448  | 100 | 1458 |
|----------|
| G        | 2.06 |  45  | 5 | 5 |
| H        | 0.12 |  44  | 50 | 50 |

- In M/M systems a single but fast service unit yields much smaller average and maximum passthrough times.

- In M/D and D/M systems the ten (slow) service units yield smaller average and maximum waiting times but much slower service times


**Answer for Exercise 1.d**:

Best performance from...

... a customer's perspective:
  * If customers prefer shorter passthrough times then they prefer having a single, fast service unit.
  * If customers prefer shorter waiting times then they either prefer a single fast service unit in an M/M system or several slower service units in an M/D or D/M system.

... the shop owner's perspective
  * prefers a small passthrough time in order to process as many customers as possible. Therefore the shop owner prefers one fast service unit.


Data for Ex1:

Ex1A
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 1
discrete counter

observed random variable: waiting time per customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 900235
discrete counter

observed random variable: service time per customer
mean:                     9.996778618916172
variance:                 100.232797981408
standard deviation:       10.011633132581716
coefficient of variation: 1.001485930041247
minimum:                  0.0
maximum:                  146.0
number of samples: 900235
discrete counter
```

Ex1B
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 100.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 18
discrete counter

observed random variable: waiting time per customer
mean:                     10.610313597984513
variance:                 223.0015221503532
standard deviation:       14.933235488344554
coefficient of variation: 1.4074264017211708
minimum:                  0.0
maximum:                  138.0
number of samples: 900229
discrete counter

observed random variable: service time per customer
mean:                     100.0058262953093
variance:                 10006.199794319975
standard deviation:       100.0309941684075
coefficient of variation: 1.0002516640682901
minimum:                  0.0
maximum:                  1458.0
number of samples: 900229
discrete counter
```

Ex1C
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 5.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 1
discrete counter

observed random variable: waiting time per customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 900236
discrete counter

observed random variable: service time per customer
mean:                     4.991693289315246
variance:                 25.179885121571033
standard deviation:       5.017956269396041
coefficient of variation: 1.0052613368968424
minimum:                  0.0
maximum:                  73.0
number of samples: 900236
discrete counter
```

Ex1D
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 50.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 3
discrete counter

observed random variable: waiting time per customer
mean:                     5.931817424435977E-4
variance:                 0.0014592767406399205
standard deviation:       0.03820048089540131
coefficient of variation: 64.39928636042532
minimum:                  0.0
maximum:                  9.0
number of samples: 900230
discrete counter

observed random variable: service time per customer
mean:                     50.00244048743099
variance:                 2501.7707112726307
standard deviation:       50.01770397841779
coefficient of variation: 1.0003052549203202
minimum:                  0.0
maximum:                  729.0
number of samples: 900230
discrete counter
```

Ex1E
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 44
discrete counter

observed random variable: waiting time per customer
mean:                     45.93109035528975
variance:                 2951.654020463703
standard deviation:       54.329126814846774
coefficient of variation: 1.1828399107139822
minimum:                  0.0
maximum:                  479.0
number of samples: 909089
discrete counter

observed random variable: service time per customer
mean:                     9.994959789415558
variance:                 100.20073843869362
standard deviation:       10.010031889993838
coefficient of variation: 1.0015079701065173
minimum:                  0.0
maximum:                  146.0
number of samples: 909089
discrete counter
```

Ex1F
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 100.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 41
discrete counter

observed random variable: waiting time per customer
mean:                     31.228924939829543
variance:                 2464.659686709489
standard deviation:       49.64533902300889
coefficient of variation: 1.5897229609620966
minimum:                  0.0
maximum:                  448.0
number of samples: 909084
discrete counter

observed random variable: service time per customer
mean:                     99.98870511415886
variance:                 10003.75719491412
standard deviation:       100.01878421033781
coefficient of variation: 1.000300824939623
minimum:                  0.0
maximum:                  1458.0
number of samples: 909084
discrete counter
```

Ex1G
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 5.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 9
discrete counter

observed random variable: waiting time per customer
mean:                     2.0610828716025575
variance:                 11.351644974893718
standard deviation:       3.369220232471264
coefficient of variation: 1.6346845043894755
minimum:                  0.0
maximum:                  45.0
number of samples: 900236
discrete counter

observed random variable: service time per customer
mean:                     5.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  5.0
maximum:                  5.0
number of samples: 900236
discrete counter
```

Ex1H
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 50.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 8
discrete counter

observed random variable: waiting time per customer
mean:                     0.12454122937198411
variance:                 1.4690550931124686
standard deviation:       1.2120458296254597
coefficient of variation: 9.732084994964026
minimum:                  0.0
maximum:                  44.0
number of samples: 900232
discrete counter

observed random variable: service time per customer
mean:                     50.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  50.0
maximum:                  50.0
number of samples: 900232
discrete counter
```


# Exercise 2 #
| System   | avg cwt | max cwt | avg wql | max wql |
|:---------|:--------|:--------|:--------|:--------|
| J | 3.021582 | 24.940000 | 27.291424 | 229.000000 |
| K | 2.907523 | 24.910000 | 26.261191 | 227.000000 |
| L | 1.845778 | 18.920000 | 16.666663 | 180.000000 |
| M | 2.885423 | 24.640000 | 26.061497 | 227.000000 |
| N | 1.742308 | 18.880000 | 15.732460 | 188.000000 |


## Answers ##
Having slower inter arrival rates within a batch process reduces the waiting queue length and the waiting time of the customers (Compare System L/N vs System J, System K/M lies in between since customers are coming faster than for L/N).

But between the systems L/N and K/M, where we just distinguish between Exponential or Constant distribution (with same mean), there are no marginal differences. (The average waiting time/queue length seems to be slightly better, but we would need more measurements to verify this)

## J ##
```
E[batch-inter-arrival time] = 55.55555555555555
E[batch-size] = 5.0
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 229
discrete counter

observed random variable: waiting time per customer
mean:                     3.0215815503505863
variance:                 9.367313872887559
standard deviation:       3.060606781814279
coefficient of variation: 1.012915498328736
minimum:                  0.0
maximum:                  24.94
number of samples: 903215
continuous counter

observed random variable: queue occupancy over time
mean:                     27.291424195421133
variance:                 891.6439533774216
standard deviation:       29.86040778987155
coefficient of variation: 1.0941315328967491
minimum:                  0.0
maximum:                  229.0
interval length: 9999983
```

## K ##
```
E[batch-inter-arrival time] = 55.55555555555555
E[batch-size] = 5.0
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 227
discrete counter

observed random variable: waiting time per customer
mean:                     2.9075234467984594
variance:                 9.342108303784437
standard deviation:       3.056486267560258
coefficient of variation: 1.051233575063969
minimum:                  0.0
maximum:                  24.91
number of samples: 903215
continuous counter

observed random variable: queue occupancy over time
mean:                     26.26119052611905
variance:                 884.6965782855455
standard deviation:       29.743849419426958
coefficient of variation: 1.1326161847020644
minimum:                  0.0
maximum:                  227.0
interval length: 9999999
```

## L ##
```
E[batch-inter-arrival time] = 55.55555555555555
E[batch-size] = 5.0
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 180
discrete counter

observed random variable: waiting time per customer
mean:                     1.8457782626038302
variance:                 5.588485564331794
standard deviation:       2.3639977927933424
coefficient of variation: 1.2807593635101449
minimum:                  0.0
maximum:                  18.92
number of samples: 902960
continuous counter

observed random variable: queue occupancy over time
mean:                     16.666662533331266
variance:                 515.2800397288917
standard deviation:       22.69978060970836
coefficient of variation: 1.3619871743554897
minimum:                  0.0
maximum:                  180.0
interval length: 9999995
```

## M ##
```
E[batch-inter-arrival time] = 55.55555555555555
E[batch-size] = 5.0
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 227
discrete counter

observed random variable: waiting time per customer
mean:                     2.885423289327524
variance:                 9.306274436499978
standard deviation:       3.050618697330097
coefficient of variation: 1.0572517067473568
minimum:                  0.0
maximum:                  24.64
number of samples: 903212
continuous counter

observed random variable: queue occupancy over time
mean:                     26.061497218449166
variance:                 880.5362844541429
standard deviation:       29.673831644298026
coefficient of variation: 1.1386080928340394
minimum:                  0.0
maximum:                  227.0
interval length: 9999997
```

## N ##
```
E[batch-inter-arrival time] = 55.55555555555555
E[batch-size] = 5.0
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 188
discrete counter

observed random variable: waiting time per customer
mean:                     1.742307541522426
variance:                 5.664397999203403
standard deviation:       2.3799995796645432
coefficient of variation: 1.366004291977582
minimum:                  0.0
maximum:                  18.88
number of samples: 902961
continuous counter

observed random variable: queue occupancy over time
mean:                     15.732459566229783
variance:                 518.8266652654196
standard deviation:       22.7777669069077
coefficient of variation: 1.4478198282359416
minimum:                  0.0
maximum:                  188.0
interval length: 9999995
```