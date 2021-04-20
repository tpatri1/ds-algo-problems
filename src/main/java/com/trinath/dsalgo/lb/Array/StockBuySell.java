package com.trinath.dsalgo.lb.Array;

public class StockBuySell {
    static class Tuple<X, Y> {
        public X x;
        public Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Tuple find_buy_sell_stock_prices(int[] array) {
        Integer buy = array[0];
        Integer sale = array[0];
        int globalProfit = Integer.MIN_VALUE;
        int prevMin = buy;
        for (int i = 1; i < array.length; i++) {
            int currProfit = array[i] - prevMin;
            if(currProfit>globalProfit){
                globalProfit  = currProfit;
                buy = prevMin;
                prevMin = Math.min(array[i], prevMin);
                sale = array[i];
            }
        }
        System.out.println(globalProfit);
        Tuple<Integer, Integer> result =
                new Tuple<Integer, Integer>(
                        buy, //buy price
                        sale //sell price
                );

        return result;
    }

    public static void main(String args[]){
        Tuple tuple = find_buy_sell_stock_prices(new int[]{17,12,10,2});
        System.out.println(tuple.x+" "+tuple.y);
    }

}
