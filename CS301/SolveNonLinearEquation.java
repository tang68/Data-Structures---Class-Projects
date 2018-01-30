package project2;

import java.util.*;

public class SolveNonLinearEquation {

	public static void main(String[] args) {
		
		System.out.println("*****Bisection*****");		
		System.out.println("\nfunction1 root #1");
		bisection(1, 0, 1, 100, 0.01, 0.365098);
		System.out.println("\nfunction1 root #2");
		bisection(1, 1, 2, 100, 0.01, 1.921741);
		System.out.println("\nfunction1 root #3");
		bisection(1, 3, 4, 100, 0.01, 3.563161);
		System.out.println("\nfunction2");
		bisection(2, 125, 130, 100, 0.01, 126.63243604);
		System.out.println("\n");

		System.out.println("*****Newton*****"); 
		System.out.println("\nfunction1 root #1");
		newton(1, 1, 100, 0.01, 0.365098); 
		System.out.println("\nfunction1 root #2");
		newton(1, 1.5, 100, 0.01, 1.921741); 
		System.out.println("\nfunction1 root #3");
		newton(1, 3, 100, 0.01, 3.563161); 
		System.out.println("\nfunction2");
		newton(2, 120, 100, 0.01, 126.63243604);
		System.out.println("\n");

		System.out.println("*****Secant*****"); 
		System.out.println("\nfunction1 root #1");
		secant(1, 0, 1, 100, 0.01, 0.365098); 
		System.out.println("\nfunction1 root #2");
		secant(1, 1, 2, 100, 0.01, 1.921741); 
		System.out.println("\nfunction1 root #3");
		secant(1, 3, 4, 100, 0.01, 3.563161); 
		System.out.println("\nfunction2");
		secant(2, 125, 130, 100, 0.01, 126.63243604);
		System.out.println("\n");

		System.out.println("*****Modified Secant*****"); 
		System.out.println("\nfunction1 root #1");
		modifiedSecant(1,0.5, 0.01, 100, 0.01, 0.365098); 
		System.out.println("\nfunction1 root #2");
		modifiedSecant(1, 1.5, 0.01, 100,0.01, 1.921741); 
		System.out.println("\nfunction1 root #3");
		modifiedSecant(1, 4, 0.01, 100, 0.01, 3.563161);
		System.out.println("\nfunction2");
		modifiedSecant(2, 120, 0.01, 100, 0.01, 126.63243604);
		System.out.println("\n");
		
		System.out.println("*****False Position*****");
		System.out.println("\nfunction1 root #1");
		falsePosition(1, 0, 1, 100, 0.01, 0.365098);
		System.out.println("\nfunction1 root #2");
		falsePosition(1, 1, 2, 100, 0.01, 1.921741);
		System.out.println("\nfunction1 root #3");
		falsePosition(1, 3, 4, 100, 0.01, 3.563161);
		System.out.println("\nfunction2");
		falsePosition(2, 125, 130, 100, 0.01, 126.63243604);
		System.out.println("\n");

	}

	private static double function1(double x) {
		return 2 * Math.pow(x, 3) - 11.7 * x * x + 17.7 * x - 5;
	}

	private static double func1Derivative(double x) {
		return 6 * x * x - 23.4 * x + 17.7;
	}

	private static double function2(double x) {
		return x + 10 - x * 0.5 * (Math.exp(50 / x) + Math.exp(-50 / x));
	}

	private static double func2Derivative(double x) {
		double coshx = 0.5 * (Math.exp(50 / x) + Math.exp(-50 / x));
		double sinhx = 0.5 * (Math.exp(50 / x) - Math.exp(-50 / x));

		return 1 - coshx + 50 / x * sinhx;
	}

	private static void bisection(int func, double a, double b, 
			int nMax, double percentErr, double trueRoot) {

		double c, fa = 0, fb = 0, fc = 0, currentErr, Errt;

		if (func == 1) {
			fa = function1(a);
			fb = function1(b);
		} else {
			fa = function2(a);
			fb = function2(b);
		}
		if ((fa > 0 && fb > 0) || (fa < 0 && fb < 0)) {
			System.out.printf("a: %f\tb: %f\tfa: %f\tfa: %f", a, b, fa, fb);
			System.out.println("Function has same sign at a and b");
			return;
		}
		currentErr = b - a;
		for (int i = 0; i < nMax; i++) {
			currentErr = currentErr / 2;
			c = a + currentErr;
			if (func == 1)
				fc = function1(c);
			else
				fc = function2(c);
			Errt = (trueRoot - c) / trueRoot;
			System.out.printf("n: %d\ta: %f\tb: %f\tc: %f\tfa: %f\tfb: %f\tfc: %f\tEa: %f\tEt: %f",
					i, a, b, c, fa, fb, fc, Math.abs(currentErr)*100, Math.abs(Errt)*100);

			if (Math.abs(currentErr) < percentErr) {
				System.out.println("\nConvergence");
				return;
			}
			if ((fa < 0 && fc > 0) || (fa > 0 && fc < 0)) {
				b = c;
				fb = fc;
			} else {
				a = c;
				fa = fc;
			}
			System.out.println();
		}

	}

	private static void falsePosition(int func, double a, double b, 
			int nMax, double percentErr, double trueRoot) {
		double c = 0, fa = 0, fb = 0, fc = 0, currentErr, Errt, previousC = 0;

		if (func == 1) {
			fa = function1(a);
			fb = function1(b);
		} else {
			fa = function2(a);
			fb = function2(b);
		}
		if ((fa > 0 && fb > 0) || (fa < 0 && fb < 0)) {
			System.out.printf("a: %f\tb: %f\tfa: %f\tfa: %f", a, b, fa, fb);
			System.out.println("Function has same sign at a and b");
			return;
		}

		for (int i = 0; i < nMax; i++) {
			if (i > 0)
				previousC = c;
			c = b - fb * (b - a)/(fb - fa);
			if (func == 1)
				fc = function1(c);
			else
				fc = function2(c);
			Errt = (trueRoot - c) / trueRoot;
			currentErr = (c - previousC)/c;
			System.out.printf("n: %d\ta: %f\tb: %f\tc: %f\tfa: %f\tfb: %f\tfc: %f\tEa: %f\tEt: %f",
					i, a, b, c, fa, fb, fc, Math.abs(currentErr)*100, Math.abs(Errt)*100);

			if (Math.abs(currentErr) < percentErr) {
				System.out.println("\nConvergence");
				return;
			}
			if ((fa < 0 && fc > 0) || (fa > 0 && fc < 0)) {
				b = c;
				fb = fc;
			} else {
				a = c;
				fa = fc;
			}
			System.out.println();
		}
	}

	private static void newton(int func, double x, int nMax, double percentErr, double trueRoot) {
		double fx = 0, fp = 0, currentErr, Errt;
		double checkDeriv = 0.000001;

		if (func == 1)
			fx = function1(x);
		else
			fx = function2(x);
		System.out.printf("n: 0\tx: %f\tfx: %f", x, fx);
		System.out.println();
		for (int i = 1; i < nMax; i++) {
			if (func == 1)
				fp = func1Derivative(x);
			else
				fp = func2Derivative(x);

			if (Math.abs(fp) < checkDeriv) {
				System.out.println(" Derivetive is getting very close to zero");
				return;
			}
			double d = fx / fp;
			double previousX = x;
			x = x - d;
			if (func == 1)
				fx = function1(x);
			else
				fx = function2(x);
			Errt = (trueRoot - x) / trueRoot;
			currentErr = (Math.abs(x) - Math.abs(previousX)) / Math.abs(x);
			System.out.printf("n: %d\tx: %f\tfx: %f\tEa: %f\tEt: %f", i, x, fx, 
					Math.abs(currentErr)*100, Math.abs(Errt)*100);

			if (Math.abs(currentErr) < percentErr) {
				System.out.println("\nConvergence");
				return;
			}
			System.out.println();
		}

	}

	private static void secant(int func, double a, double b, int nMax, double percentErr, double trueRoot) {
		double fa = 0, fb = 0, currentErr, d;

		if (func == 1) {
			fa = function1(a);
			fb = function1(b);
		} else {
			fa = function2(a);
			fb = function2(b);
		}

		if (Math.abs(fa) > Math.abs(fb)) {
			double temp = a;
			a = b;
			b = temp;
			temp = fa;
			fa = fb;
			fb = temp;
		}
		System.out.printf("n: 0\ta: %f\tb: %f\tfa: %f\tfb: %f", a, b, fa, fb);
		System.out.println();
		for (int i = 1; i < nMax; i++) {
			if (Math.abs(fa) > Math.abs(fb)) {
				double temp = a;
				a = b;
				b = temp;
				temp = fa;
				fa = fb;
				fb = temp;
			}

			d = (b - a) / (fb - fa);
			b = a;
			fb = fa;
			d = d * fa;
			double previousX = a;
			a = a - d;
			if (func == 1)
				fa = function1(a);
			else
				fa = function2(a);

			currentErr = (a - previousX) / a;
			double Errt = (trueRoot - a) / trueRoot;
			System.out.printf("n: 0\ta: %f\tb: %f\tfa: %f\tfb: %f\tEa: %f\tEt: %f",
					a, b, fa, fb, Math.abs(currentErr)*100, Math.abs(Errt)*100);

			if (Math.abs(d) < percentErr) {
				System.out.println("\nConvergence");
				return;
			}
			System.out.println();
		}
	}

	private static void modifiedSecant(int func, double x, double delta, int nMax, 
			double percentErr, double trueRoot) {

		double fx = 0, fd = 0, previousX, currentErr, Errt;

		if (func == 1) {
			fx = function1(x);
			fd = function1(x + x * delta);
		} else {
			fx = function2(x);
			fd = function2(x + x * delta);
		}

		System.out.printf("n: 0\tx: %f\tfx: %f", x, fx);
		System.out.println();
		for (int i = 1; i < nMax; i++) {
			if (Math.abs(fd - fx) < 0.000001) {
				System.out.println("f(x + xdelta) - f(x) is 0 => change initial guess");
				return;
			}
			previousX = x;
			x = x - fx * (delta * x) / (fd - fx);
			if (func == 1) {
				fx = function1(x);
				fd = function1(x + x * delta);
			} else {
				fx = function2(x);
				fd = function2(x + x * delta);
			}
			currentErr = (x - previousX) / x;
			Errt = (trueRoot - x) / trueRoot;

			System.out.printf("n: %d\tx: %f\tfx: %f\tEa: %f\tEt: %f",
					i, x, fx, Math.abs(currentErr)*100, Math.abs(Errt)*100);
			if (Math.abs(currentErr) < percentErr) {
				System.out.println("\nConvergence");
				return;
			}
			System.out.println();
		}

	}

}













