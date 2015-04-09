# Exercise 1

```
Exercise 1d: autocorrelations
Config A
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=1) = -0.000345
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=2) = -0.023245
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=3) = 0.013618
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=4) = 0.009607
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=5) = 0.033895
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=6) = 0.048647
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=7) = -0.009872
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=8) = -0.013410
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=9) = 0.027197
	Rng<a=16807, c=0, z_0=1, m=2147483647> autoCorrelation(lag=10) = -0.044695
Config B:
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=1) = 0.065444
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=2) = -0.026106
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=3) = -0.200174
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=4) = -0.010777
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=5) = -0.148246
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=6) = -0.043911
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=7) = -0.144806
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=8) = 0.039050
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=9) = 0.065441
	Rng<a=65539, c=1, z_0=0, m=201> autoCorrelation(lag=10) = 0.000437
Config C:
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=1) = 0.219221
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=2) = 0.089798
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=3) = 0.047284
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=4) = -0.101878
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=5) = 0.004081
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=6) = -0.033557
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=7) = 0.095628
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=8) = 0.012555
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=9) = -0.005459
	Rng<a=65539, c=19, z_0=0, m=201> autoCorrelation(lag=10) = -0.040798

```

# Exercise 2

| System | WQL mean | WQL min | WQL max |
|:-------|:---------|:--------|:--------|
| A         | 2.693  |0 | 41 |
| B         | 2.8264  |0 | 7 |
| C         | 15610.316  |0 | 31221 |

**Answer:**
Even though system A and B use the same configuration there is a relatively big difference in the maximum waiting queue length. This shows how much influence the RNG implementation has on the simulation results.

(The Erlang-k distribution for System C was probably not implemented correctly ;))

**System A**
```
E[inter-arrival time] = 0.125
E[service completion time] = 0.1
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 41
___________________________________________________________________
discrete counter

observed random variable: waiting time per customer
mean:                     0.33662396016544344
variance:                 0.12134971839455908
standard deviation:       0.34835286477156907
coefficient of variation: 1.034842750350751
minimum:                  0.0
maximum:                  4.02
number of samples: 80029349
discrete counter

observed random variable: service time per customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 80029349
discrete counter

observed random variable: customer interarrival time
mean:                     0.12495416059198139
variance:                 0.015639266937021355
standard deviation:       0.12505705472711787
coefficient of variation: 1.0008234550546298
minimum:                  0.0
maximum:                  2.2
number of samples: 80029349
discrete counter

observed random variable: blocking probability
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 80029349
discrete counter

observed random variable: unsatisfied customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 80029349
discrete counter

observed random variable: satisfied customer
mean:                     1.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  1.0
maximum:                  1.0
number of samples: 80029349
continuous counter

observed random variable: server utilization over time
mean:                     0.8052183856626206
variance:                 0.1568417370535038
standard deviation:       0.3960324949464422
coefficient of variation: 0.4918324047215389
minimum:                  0.0
maximum:                  1.0
interval length: 999999988
continuous counter

observed random variable: queue occupancy over time
mean:                     2.693979671327756
variance:                 10.471617132222518
standard deviation:       3.2359878139792984
coefficient of variation: 1.2011923654881953
minimum:                  0.0
maximum:                  41.0
interval length: 999999988
```

**System B**
```
E[inter-arrival time] = 0.125
E[service completion time] = 0.1
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 7
___________________________________________________________________
discrete counter

observed random variable: waiting time per customer
mean:                     0.3556922209837134
variance:                 0.049341442488697734
standard deviation:       0.22212933729856066
coefficient of variation: 0.624498721631395
minimum:                  0.0
maximum:                  1.02
number of samples: 79462103
discrete counter

observed random variable: service time per customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 79462103
discrete counter

observed random variable: customer interarrival time
mean:                     0.12584615354405243
variance:                 0.01813505409288122
standard deviation:       0.13466645496515167
coefficient of variation: 1.0700879698958117
minimum:                  0.0
maximum:                  0.66
number of samples: 79462103
discrete counter

observed random variable: blocking probability
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 79462103
discrete counter

observed random variable: unsatisfied customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 79462103
discrete counter

observed random variable: satisfied customer
mean:                     1.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  1.0
maximum:                  1.0
number of samples: 79462103
continuous counter

observed random variable: server utilization over time
mean:                     0.9217602913740823
variance:                 0.07211825662004923
standard deviation:       0.2685484250932208
coefficient of variation: 0.291343017925942
minimum:                  0.0
maximum:                  1.0
interval length: 999999992
continuous counter

observed random variable: queue occupancy over time
mean:                     2.826405212611242
variance:                 3.1752449874344917
standard deviation:       1.7819217119263382
coefficient of variation: 0.6304551463376574
minimum:                  0.0
maximum:                  7.0
interval length: 999999992
```

**System C**
```
E[inter-arrival time] = 11.11111111111111
E[service completion time] = 10.0
simulation duration = 10000000
minimum queue length = 0
maximum queue length = 31221
___________________________________________________________________
discrete counter

observed random variable: waiting time per customer
mean:                     174090.83037959263
variance:                 1.010238483900508E10
standard deviation:       100510.62052840526
coefficient of variation: 0.5773458619804904
minimum:                  0.0
maximum:                  348185.45
number of samples: 865454
discrete counter

observed random variable: service time per customer
mean:                     11.153837176788137
variance:                 13.976302898158066
standard deviation:       3.738489387193451
coefficient of variation: 0.3351751803382509
minimum:                  6.0
maximum:                  20.0
number of samples: 865454
discrete counter

observed random variable: customer interarrival time
mean:                     11.152311068819609
variance:                 7.3788895156821965
standard deviation:       2.7164111462888303
coefficient of variation: 0.24357383232284094
minimum:                  5.29
maximum:                  15.02
number of samples: 896676
discrete counter

observed random variable: blocking probability
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 896676
discrete counter

observed random variable: unsatisfied customer
mean:                     0.0
variance:                 0.0
standard deviation:       0.0
coefficient of variation: 0.0
minimum:                  0.0
maximum:                  4.9E-324
number of samples: 865454
discrete counter

observed random variable: satisfied customer
mean:                     1.675421224004973E-4
variance:                 1.6751424559421706E-4
standard deviation:       0.012942729449162456
coefficient of variation: 77.25059981169272
minimum:                  0.0
maximum:                  1.0
number of samples: 865454
continuous counter

observed random variable: server utilization over time
mean:                     0.9999991339996103
variance:                 8.659996397764047E-7
standard deviation:       9.305910163849663E-4
coefficient of variation: 9.305918222778471E-4
minimum:                  0.0
maximum:                  1.0
interval length: 999999550
continuous counter

observed random variable: queue occupancy over time
mean:                     15610.316964791635
variance:                 8.122590595576969E7
standard deviation:       9012.54159245713
coefficient of variation: 0.5773452014321369
minimum:                  0.0
maximum:                  31221.0
interval length: 999999550
```

# Exercise 3
| System | simtime | arrived customers | served customers |
|:-------|:--------|:------------------|:-----------------|
| A (10^-4)         |  100        |   780    |   777   |
| A (10^-5)         |    233     |     1874  |    1874  |
| A (10^-6)         |     2486    |    19869   |    19866 |
| B (10^-4)         |  100        |    794   |    789  |
| B (10^-5)         |  206       |    1633   |   1630   |
| B (10^-6)         |    1768     |    14054   |   14049   |