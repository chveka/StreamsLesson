package org.example.forkJoinPool;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        } else {
            FactorialTask firstPartTask = new FactorialTask(n - 1);
            firstPartTask.fork();
            int result = firstPartTask.join();
            return n * result;
        }
    }
}
