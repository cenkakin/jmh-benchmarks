package com.array.test.jmh;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import static java.lang.Math.random;

/**
 * Created by Cenk
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class LoopBenchmarks {
   @Param({ "32768" })
   int size;

   int[] a;

   @Setup
   public void prepare() {
      a = new int[size];
      for (int i : a) {
         a[i] = (int) random();
      }
   }

   @Benchmark
   public void goodOldLoop(Blackhole bh) {
      int e = a.length;
      int m = Integer.MIN_VALUE;
      for(int i=0; i < e; i++)
      if(a[i] > m) {
         bh.consume(m = a[i]);
      }

   }

   @Benchmark
   public void sweetLoop(Blackhole bh) {
      bh.consume(Arrays.stream(a).reduce(Integer.MIN_VALUE, Math::max));
   }
}
