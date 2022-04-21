import java.util.Scanner;
public class GCD{
	// Find GCD/HCF of two integers
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int a = s.nextInt(),
			b = s.nextInt();
		s.close();
		System.out.println(getGCD(a,b));
	}
	public static int getGCD(int a, int b){
		while(b != 0){
			int c = b;
			b = a % b;
			a = c;
		}
		return a;
	}
}
