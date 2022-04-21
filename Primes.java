import java.util.*;
import java.lang.*;
public class Primes{
	/* a dedicated class for Prime numbers */
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		s.close();
		ArrayList<Integer> primes = getAllPrimesUptoN(N);
		System.out.println(primes.size());
		printArrayList(primes);
	}
	/* Functions */
	/* function getAllPrimesUptoN : returns all prime numbers upto N using Sieve Of Eratosthenes */
	public static ArrayList<Integer> getAllPrimesUptoN(int N){
		if(N < 2) return new ArrayList<Integer>();
		N++; 	// incrementing N so that we can include N if it is prime
		boolean[] notPrimes = new boolean[N];	// boolean array initialized with all false values
		int lim = (int)(Math.sqrt(N));
		if((lim & 1) == 0) lim++;	// if lim is even, make it odd
		ArrayList<Integer> primes = new ArrayList<>();
		primes.add(2);
		// removing factors of 3 and onwards and adding primes
		for(int i = 3; i <= lim; i += 2){
			if( ! notPrimes[i]){
				primes.add(i);
				for(int j = i*i; j < N; j += i) notPrimes[j] = true;
			}
		}
		for(int i = lim + 2; i < N; i += 2){
			if( ! notPrimes[i]) primes.add(i);
		}
		return primes;
	}
	/* function printArrayList : prints an ArrayList of Integers onto screen */
	public static void printArrayList(ArrayList<Integer> A){
		int n = A.size();
		System.out.println("*** Printing ArrayList ***");
		for(int i = 0; i < n; i++){
			System.out.format("%d ", A.get(i));
		}
		System.out.println("***");
	}
}
