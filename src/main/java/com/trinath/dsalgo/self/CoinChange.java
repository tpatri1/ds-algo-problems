package com.trinath.dsalgo.self;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CoinChange {

    public static long makeChangeMemo(int[] coins, int money, int index){
        return makeChange(coins, money, index, new HashMap<String, Integer>());
    }
    private static long makeChange(int[] coins, int money, int index, HashMap<String, Integer> memo){
        if(money==0){
            return 1;
        }
        if(index>=coins.length){
            return 0;
        }
        long ways =0;
        int moneyWithCoins =0;
        String key = money+"-"+index;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        while(moneyWithCoins<= money){
            int remaining = money - moneyWithCoins;
            ways += makeChange(coins, remaining, index+1, memo);
            moneyWithCoins+=coins[index];
            System.out.println("moneyWithCoins"+ moneyWithCoins);
            System.out.println("index"+ index);
        }
        System.out.println("ways"+ ways);

        return ways;
    }
    // public static long makeChange(int[] coins, int money) {
//         long[] DP = new long[money + 1]; // O(N) space.
//         DP[0] = (long) 1; 	// n == 0 case.
//         for(int coin : coins) {
//             for(int j = coin; j < DP.length; j++) {
//             // The only tricky step.
//                 DP[j] += DP[j - coin];
//             }
//         }       
//         return DP[money];
//     }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for(int coins_i=0; coins_i < m; coins_i++){
            coins[coins_i] = in.nextInt();
        }
        System.out.println(makeChangeMemo(coins, n,0));
    }
}
