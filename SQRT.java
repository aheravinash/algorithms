import java.util.Scanner;
public class SQRT{
	// find square root of an integer using Newton Raphson method
    // find the solution of equation x^2 - n = 0
    // f(xn) = xn^2 - n; f'(xn) = 2xn
    // Newton Raphson scheme:
    // xn+1 = xn - f(xn)/f'(xn)
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();
		System.out.println(getSQRT(n));
	}
	public static double getSQRT(int N){
		double n = N,
			   x0 = n / 2.0,
			   x1 = 0.5 * ( x0 + n / x0),
			   error = x1 - x0,
			   tol = 1.0e-5;
		if(error < 0) error = x0 - x1;
		while(error > tol){
			x0 = x1;
			x1 = 0.5 * (x0 + n / x0);
			error = x1 - x0;
			if(error < 0) error = x0 - x1;
		}
		return x1;
	}
}
