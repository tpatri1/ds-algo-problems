package practice.problems;

public class PrintBinary {
    private static int printBinary(int n) {
        if (n <= 1) return n;
        else {
            int binary = n % 2;
            System.out.println(binary);
          return   printBinary(n / 2);
        }
    }


    public static void main(String[] args){

        printBinary(10);
    }
}
