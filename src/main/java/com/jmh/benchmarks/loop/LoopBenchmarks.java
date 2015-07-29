package com.jmh.benchmarks.loop;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * Created by Cenk
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class LoopBenchmarks {

   @Param({"50000", "250000", "500000"})
   int size;

   int[] randomArray;

   List<Integer> randomArrayList;

   @Setup
   public void prepare() {
      Random generator = new Random();
      randomArray = new int[size];
      for (int i : randomArray) {
         randomArray[i] = generator.nextInt();
      }
      randomArrayList =  IntStream.of(randomArray).boxed().collect(Collectors.toList());
   }

   @Benchmark
   public int oldStyleArrayLoop() {
      int e = randomArray.length;
      int m = Integer.MIN_VALUE;
      for (int i = 0; i < e; i++) {
         if (randomArray[i] > m) {
           m = randomArray[i];
         }
      }
      return m;
   }

   @Benchmark
   public Integer seqStreamArrayLoop() {
      return Arrays.stream(randomArray).reduce(Integer.MIN_VALUE, Math::max);
   }

   @Benchmark
   public Integer parallelStreamArrayLoop() {
      return Arrays.stream(randomArray).parallel().reduce(Integer.MIN_VALUE, Math::max);
   }

   @Benchmark
   public int oldStyleArrayListLoop() {
      int e = randomArrayList.size();
      int m = Integer.MIN_VALUE;
      for (int i = 0; i < e; i++) {
         if (randomArrayList.get(i) > m) {
            m = randomArrayList.get(i);
         }
      }
      return m;
   }

   @Benchmark
   public Integer seqStreamArrayListLoop(){
      return randomArrayList.stream().reduce(Integer.MIN_VALUE, Math::max);
   }

   @Benchmark
   public Integer parallelStreamArrayListLoop() {
      return randomArrayList.stream().parallel().reduce(Integer.MIN_VALUE, Math::max);
   }

}
