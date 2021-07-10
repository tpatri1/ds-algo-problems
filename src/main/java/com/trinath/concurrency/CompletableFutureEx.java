package com.trinath.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureEx {
    private static void fun() {
        final List<CompletableFuture<String>> completableFutures = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            completableFutures.add(CompletableFuture.supplyAsync(() -> {


                try {
                    System.out.println("Thread "+Thread.currentThread().getName());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Success";
            }));

        }

        for (CompletableFuture<String> completableFuture : completableFutures) {
            try {
                String s = completableFuture.get(3, TimeUnit.SECONDS);
                System.out.println(s);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String args[]){
        fun();
        Map<String , Integer> map = new HashMap<>();

    }
}
