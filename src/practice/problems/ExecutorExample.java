package practice.problems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {
    public static void main(String[] args) {
        Runnable runnableTask = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Created : " + i + Thread.currentThread().getName());
                }
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(runnableTask);
        executor.shutdown();
    }


}
