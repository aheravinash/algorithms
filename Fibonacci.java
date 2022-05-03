import java.util.*;
import java.lang.*;
public class Fibonacci{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        //printFibonacciSeries(n);
        System.out.println(checkFibonacciMember(n));
    }
    /* Functions */
    /* function printFibonacciSeries : prints first N terms of Fibonacci series*/
    public static void printFibonacciSeries(int n){
        int a = -1, b = 1, c;
        for(int i = 0; i <= n; i++){
            c = b;
            b = a + b;
            a = c;
            System.out.print(b + " ");
        }
        System.out.println();
    }
    /* function checkFibonacciMember : checks is a number is a member of the Fibinacci series*/
    public static boolean checkFibonacciMember(int n){
        // A number is a Fibonacci number if and only if one or both of
        // (5n^2 + 4) and (5n^2 - 4) is a perfect square
        int n1 = 5*n*n + 4,
            n2 = n1 - 8,
            sq1 = (int)(Math.sqrt(n1)),
            sq2 = (int)(Math.sqrt(n2));
        if(sq1*sq1 == n1 || sq2*sq2 == n2) return true;
        else return false;
    }
}
