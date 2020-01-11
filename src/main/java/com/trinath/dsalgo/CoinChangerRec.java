package com.trinath.dsalgo;

public class CoinChangerRec {



        public static int combi(int amount, int[] coins){
            if (amount == 0) {

                return 1;
            }
            if(amount< 0){
                return 0;
            }
            int nCombi =0;
            for(int i=0;  i<amount; i++){
                nCombi += combi(amount - coins[i], coins);
            }
            return nCombi;
        }

        public static void main(String args){
            int[]  coins = {1,2};
            int  amount=4;
            System.out.println(combi(amount, coins));
        }
    }


