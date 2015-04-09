| System   | avg customer waiting time | avg customer processing time | avg customer retention time | avg waiting queue length | customer blocking probability | utilization      |
|:---------|:--------------------------|:-----------------------------|:----------------------------|:-------------------------|:------------------------------|:-----------------|
| -        | waitingTime.mean          | processingTime.mean          | retentionTime.mean          | waitingQueueLength.mean  | customerBlocked.mean          | utilization.mean |
| A        | 26397153.519298           | 9.509492                     | 26397162.777340             | 2930647.198665           | 0.513559                      | 0.999999         |
| B        | 15.242258                 | 9.509260                     | 24.751518                   | 1.591249                 | 0.519396                      | 0.808384         |
| C        | 52.718955                 | 9.509250                     | 62.228205                   | 5.546635                 | 0.515359                      | 0.931910         |
| D        | 0.0                       | 10.0                         | 10.0                        | NaN                      | 0.0                           | 0.90909089999999 |
| E        | 7.462226571439804E7       | 10.0                         | 7.46222749683248E7          | 8770115.776784122        | 0.5403003258424842            | 0.999999994      |
| F        | 30.333425737001942        | 9.509129780292152            | 39.84255565296178           | 3.157095067771892        | 0.4322639233437828            | 0.864466318593399|


**As customers we would prefers System D because it has the shortest waiting time and a comparably short processing time.**

SYSTEM A:
terminationTime=1e7
resolution=100
waitingQueue.maxSize=Inf
log.level=DEBUG
interArrivalTimes.dist=Exp
interArrivalTimes.seed=100
interArrivalTimes.mean=0.095
interArrivalTimes.scale=@resolution
serviceTimes.dist=Exp
serviceTimes.seed=200
serviceTimes.mean=0.1
serviceTimes.scale=@resolution

numCustomers = 111020201
waitingQueueLength = Counter<numSamples=111020140, sum=325360862286398.000000, min=0.000000, max=325360862286398.000000, mean=2930647.198665>
waitingTime = Counter<numSamples=105158021, sum=2775872424122615.000000, min=0.000000, max=2775872424122615.000000, mean=26397153.519298>
processingTime = Counter<numSamples=105158020, sum=999999391.000000, min=0.000000, max=999999391.000000, mean=9.509492>
retentionTime = Counter<numSamples=105158020, sum=2775873371282816.000000, min=0.000000, max=2775873371282816.000000, mean=26397162.777340>
customerBlocked = Counter<numSamples=216178161, sum=111020140.000000, min=0.000000, max=111020140.000000, mean=0.513559>
utilization = TDCounter<numSamples=105158020, sum=999999391.000000, min=0.000000, max=0.000000, firstSampleTime=0, lastSampleTime=999999980, totalDuration=999999980, mean=0.999999>

SYSTEM B:
terminationTime=1e7
resolution=100
waitingQueue.maxSize=3
log.level=DEBUG
interArrivalTimes.dist=Exp
interArrivalTimes.seed=100
interArrivalTimes.mean=0.095
interArrivalTimes.scale=@resolution
serviceTimes.dist=Exp
serviceTimes.seed=200
serviceTimes.mean=0.1
serviceTimes.scale=@resolution
numCustomers = 111020201
waitingQueueLength = Counter<numSamples=91871727, sum=146190822.000000, min=0.000000, max=146190822.000000, mean=1.591249>
waitingTime = Counter<numSamples=85010229, sum=1295747851.000000, min=0.000000, max=1295747851.000000, mean=15.242258>
processingTime = Counter<numSamples=85010228, sum=808384397.000000, min=0.000000, max=808384397.000000, mean=9.509260>
retentionTime = Counter<numSamples=85010228, sum=2104132210.000000, min=0.000000, max=2104132210.000000, mean=24.751518>
customerBlocked = Counter<numSamples=176881956, sum=91871727.000000, min=0.000000, max=91871727.000000, mean=0.519396>
utilization = TDCounter<numSamples=85010228, sum=808384397.000000, min=0.000000, max=0.000000, firstSampleTime=0, lastSampleTime=999999993, totalDuration=999999993, mean=0.808384>

SYSTEM C:
terminationTime=1e7
resolution=100
waitingQueue.maxSize=10
log.level=DEBUG
interArrivalTimes.dist=Exp
interArrivalTimes.seed=100
interArrivalTimes.mean=0.095
interArrivalTimes.scale=@resolution
serviceTimes.dist=Exp
serviceTimes.seed=200
serviceTimes.mean=0.1
serviceTimes.scale=@resolution
numCustomers = 111020201
waitingQueueLength = Counter<numSamples=104211798, sum=578024857.000000, min=0.000000, max=578024857.000000, mean=5.546635>
waitingTime = Counter<numSamples=98000379, sum=5166477582.000000, min=0.000000, max=5166477582.000000, mean=52.718955>
processingTime = Counter<numSamples=98000378, sum=931910115.000000, min=0.000000, max=931910115.000000, mean=9.509250>
retentionTime = Counter<numSamples=98000378, sum=6098387651.000000, min=0.000000, max=6098387651.000000, mean=62.228205>
customerBlocked = Counter<numSamples=202212177, sum=104211798.000000, min=0.000000, max=104211798.000000, mean=0.515359>
utilization = TDCounter<numSamples=98000378, sum=931910115.000000, min=0.000000, max=0.000000, firstSampleTime=0, lastSampleTime=999999973, totalDuration=999999973, mean=0.931910>

SYSTEM D:
terminationTime=1e7
resolution=100
waitingQueue.maxSize=Inf
log.level=DEBUG
interArrivalTimes.dist=Uni
interArrivalTimes.seed=100
interArrivalTimes.min=0.111111111
interArrivalTimes.max=0.111111111
interArrivalTimes.scale=@resolution
serviceTimes.dist=Uni
serviceTimes.seed=200
serviceTimes.min=0.1
serviceTimes.max=0.1
serviceTimes.scale=@resolution
numCustomers = 90909091
waitingQueueLength = Counter<numSamples=0, sum=0.000000, min=0.000000, max=0.000000, mean=NaN>
waitingTime = Counter<numSamples=90909090, sum=0.000000, min=0.000000, max=0.000000, mean=0.000000>
processingTime = Counter<numSamples=90909089, sum=909090890.000000, min=0.000000, max=909090890.000000, mean=10.000000>
retentionTime = Counter<numSamples=90909089, sum=909090890.000000, min=0.000000, max=909090890.000000, mean=10.000000>
customerBlocked = Counter<numSamples=90909090, sum=0.000000, min=0.000000, max=0.000000, mean=0.000000>
utilization = TDCounter<numSamples=90909089, sum=909090890.000000, min=0.000000, max=0.000000, firstSampleTime=0, lastSampleTime=999999989, totalDuration=999999989, mean=0.909091>

SYSTEM E:
terminationTime=1e7
resolution=100
waitingQueue.maxSize=Inf
log.level=DEBUG
interArrivalTimes.dist=Exp
interArrivalTimes.seed=100
interArrivalTimes.mean=0.09
interArrivalTimes.scale=@resolution
serviceTimes.dist=Uni
serviceTimes.seed=200
serviceTimes.min=0.1
serviceTimes.max=0.1
serviceTimes.scale=@resolution
numCustomers = 117533331
waitingQueueLength = Counter<numSamples=117533328, sum=1030780894190743.000000, min=0.000000, max=1030780894190743.000000, mean=8770115.776784>
waitingTime = Counter<numSamples=100000000, sum=7462226571439804.000000, min=0.000000, max=7462226571439804.000000, mean=74622265.714398>
processingTime = Counter<numSamples=99999999, sum=999999990.000000, min=0.000000, max=999999990.000000, mean=10.000000>
retentionTime = Counter<numSamples=99999999, sum=7462227422210204.000000, min=0.000000, max=7462227422210204.000000, mean=74622274.968325>
customerBlocked = Counter<numSamples=217533328, sum=117533328.000000, min=0.000000, max=117533328.000000, mean=0.540300>
utilization = TDCounter<numSamples=99999999, sum=999999990.000000, min=0.000000, max=0.000000, firstSampleTime=0, lastSampleTime=999999996, totalDuration=999999996, mean=1.000000>

SYSTEM F:
terminationTime=1e7
resolution=100
waitingQueue.maxSize=Inf
log.level=DEBUG
interArrivalTimes.dist=Uni
interArrivalTimes.seed=100
interArrivalTimes.min=0.111111111
interArrivalTimes.max=0.111111111
interArrivalTimes.scale=@resolution
serviceTimes.dist=Exp
serviceTimes.seed=200
serviceTimes.mean=0.1
serviceTimes.scale=@resolution
numCustomers = 90909091
waitingQueueLength = Counter<numSamples=69216527, sum=218523156.000000, min=0.000000, max=218523156.000000, mean=3.157095>
waitingTime = Counter<numSamples=90909089, sum=2757584100.000000, min=0.000000, max=2757584100.000000, mean=30.333426>
processingTime = Counter<numSamples=90909088, sum=864466316.000000, min=0.000000, max=864466316.000000, mean=9.509130>
retentionTime = Counter<numSamples=90909088, sum=3622050398.000000, min=0.000000, max=3622050398.000000, mean=39.842556>
customerBlocked = Counter<numSamples=160125616, sum=69216527.000000, min=0.000000, max=69216527.000000, mean=0.432264>
utilization = TDCounter<numSamples=90909088, sum=864466316.000000, min=0.000000, max=0.000000, firstSampleTime=0, lastSampleTime=999999997, totalDuration=999999997, mean=0.864466>