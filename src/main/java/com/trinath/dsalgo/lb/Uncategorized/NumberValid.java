package com.trinath.dsalgo.lb.Uncategorized;

class NumberValid{
    static boolean isNumberValid(String s) {
        STATE currentState = STATE.START;
        int i=0;
        if(s.charAt(0)=='+' ||s.charAt(0)=='-'){
            i++;// no op
        }
        while(i<s.length()){
             currentState = getNextSate(currentState,s.charAt(i));
            if(currentState==STATE.UNKNOWN){
                return false;
            }
            i++;
        }
        if (currentState == STATE.DECIMAL){
            return false;
        }
        return true;
    }

    static enum STATE{
        START,
        INTEGER,
        DECIMAL,
        AFTER_DECIMAL,
        UNKNOWN
    }
//0.234.
    static STATE getNextSate(STATE currentState, char ch){
        switch(currentState){
            case START:
            case INTEGER:
                if (ch == '.') {
                    return STATE.DECIMAL;
                } else if (ch >= '0' && ch <= '9') {
                    return STATE.INTEGER;
                } else {
                    return STATE.UNKNOWN;
                }
            case DECIMAL:
                if(ch>='0' && ch<='9'){
                    return STATE.AFTER_DECIMAL;
                }else{
                    return STATE.UNKNOWN;
                }
            case AFTER_DECIMAL:
                if(ch>='0' && ch<='9'){
                    return STATE.AFTER_DECIMAL;
                }else{
                    return STATE.UNKNOWN;
                }
        }
        return STATE.UNKNOWN;
    }

    public static void main(String args[]){
        System.out.print(isNumberValid("-22.22."));
    }
}