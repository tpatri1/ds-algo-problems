package com.trinath.dsalgo.lb;

public class StringToIntegerConversion {

    public static int stringTOinteger(String input){
        int result =0;
        input = input.trim();
        if(input.contains(".")){
            input = input.replaceAll("\\.[0-9]+","").trim(); // remove decimal
        }
        for(int i = input.length()-1; i>=0; i--){
            char c = input.charAt(i);
            //Take care of + and -ve sign
            if(c=='+' && i==0){
                return result ;
            }
            else if(c=='-' && i==0){
                return result*(-1) ;
            }
            //Non number value not supported
            else if(c < '0' || c >'9'){
                throw new UnsupportedOperationException("Can't convert to integer : "+ c);
            }
            result = (int) ((c-'0')*(Math.pow(10,input.length()-i-1))) + result;
        }

        return result;
    }

    public static void main(String args[]){

        System.out.println(stringTOinteger("+123"));
        System.out.println(stringTOinteger("-678"));
        System.out.println(stringTOinteger("-678.76"));
    }
}
