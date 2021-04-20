package com.trinath.dsalgo.self;

import java.util.function.Consumer;

public class CallBackTest {
    public static void getSalary(Consumer<Double> callback) {
        double salary = 50_000.00;
        System.out.println("Get salary...");
        // call back our callback function
        callback.accept(salary);
    }

    public static void deductTax(double grossSalary, Consumer<Double> callback) {
        System.out.println("Deduct tax...");
        double tax = 5; // 5 percent of our gross salary
        double afterTax = grossSalary * (100 - tax) / 100;
        callback.accept(afterTax);
    }
    public static void main(String... args) {
        // here we are passing a consumer function
        // as an argument
        getSalary((grossSalary) -> {
            System.out.println("Gross salary :" + grossSalary);
            deductTax(grossSalary, (afterTaxSalary) -> {
                System.out.println("After tax :" + afterTaxSalary);
            });
        });
    }
}
