# jmh-benchmarks
This repo is for comparing old style array loop and java 8 streams by using JMH as a microbenchmarking framework.

To run:
1) mvn clean package
2) java -jar target/benchmarks.jar

You can use parameters "-wi, -i ..." for arranging iteration numbers.

More information about jmh:

http://psy-lob-saw.blogspot.com/2013/05/using-jmh-to-benchmark-multi-threaded.html
http://psy-lob-saw.blogspot.com/2013/04/writing-java-micro-benchmarks-with-jmh.html
http://openjdk.java.net/projects/code-tools/jmh/