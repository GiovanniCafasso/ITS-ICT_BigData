
## Spark RDDs (Resilient Distributed Datasets) basics

This lab contains multiple examples about the basics of RDDs (Resilient Distributed Datasets).

Here's a list about the example included:

- Map + Collect (example name: MapExample, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.transformation.MapExample)
- FlatMap + Collect  (example name: FlatMapExample, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.transformation.FlatMapExample)
- Filter + Collect  (example name: FilterExample, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.transformation.FilterExample)
- FlatMap + CountByValue (example name: WordCount, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.WordCount)
- Distinct + Collect  (example name: DistinctExample, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.transformation.DistinctExample)
- Join (example name: JoinExample, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.transformation.JoinExample)
- Reduce (example name: ReduceExample, fully qualified main class: it.sunnyvale.academy.sparkrddsbasics.transformation.ReduceExample)


## Prerequisites

To run the examples on your local machine, the following prerequisites must be met:

- JDK 1.8 or above must be installed
- Maven 3.6 or above must be installed

To run the examples on a YARN cluster, the following prerequisites must be met:

- Having completed labs 02 (Provision the environment) and 10 (Spark installation)

## Run examples locally

Compile the source code

```console
$ mvn clean &&  mvn package
```

Run the example of your choice

```console
$ mvn exec:exec \
  -Dspark.master=local \
  -P <EXAMPLE NAME>
```

Make sure to change **\<EXAMPLE NAME\>** with the real example's name before running the previous command (ie: WordCount).

## Run examples on YARN cluster

Go to path **ITS-ICT_BigData/labs/02-Provision_the_environment/Vagrant** and connect to node 1.

```console
$ vagrant ssh node1 
[vagrant@node1 ~]$ 
```

Move to this lab's home directory

```console
[vagrant@node1 ~]$ cd ITS-ICT_BigData/labs/11-Spark_RDDs_basics
```

Create the lab input directory on HDFS

```console
[vagrant@node1]$ hadoop fs -mkdir lab11_input
```

To simplify the operation of loading the input datasets on HDFS, a script has been provided. Run it with the command:

```console
[vagrant@node1]$ ./copy_input_datasets.sh 
```

Copile and package the application

```console
[vagrant@node1]$ mvn clean && mvn package
```

Submit Spark job on the cluster

```console
[vagrant@node1]$ spark-submit \
  --master yarn \
  --driver-memory 512m  \
  --executor-memory 512m \
  --class <EXAMPLE FULLY QUALIFIED CLASS NAME> \
  --deploy-mode cluster \
  target/spark-rdds-basics-1.0-SNAPSHOT.jar 
```

Make sure to change **\<EXAMPLE FULLY QUALIFIED MAIN CLASS NAME\>** with the real example's fully qualified main class name in previous command (ie: it.sunnyvale.academy.sparkrddsbasics.WordCount).

To see the job's output run

```console
[vagrant@node1]$ yarn logs -applicationId <APPLICATION ID>
```

Application ID is showed as part of the `spark-submit` command (ie: application_1601154210621_0006)

To kill an application:

```console
[vagrant@node1]$ yarn application -kill <APPLICATION ID>
```

More at https://spark.apache.org/docs/2.2.1/rdd-programming-guide.html

