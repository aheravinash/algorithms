import java.util.*;
import java.lang.*;
public class PermutationsAndCombinations{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(),
            r = s.nextInt();
        System.out.println(nCr(n,r));
    }
    public static int nCr(int n, int r){
        if(n < 2 || n == r || r == 0)
            return 1;
        if(r > n/2)
            r = n - r; // because nCr is equal to nC(n-r)
        int numerator = n,
            denominator = r;
        for(int i = 1; i < r; i++){
            numerator *= n - i;
            denominator *= i;
        }
        return numerator / denominator;
    }
}
