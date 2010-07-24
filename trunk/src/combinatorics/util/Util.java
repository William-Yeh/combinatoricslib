package combinatorics.util;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Utility class for combinatorial package
 */
public class Util {

	/**
	 * Calculates factorial of the given integer value <code>x</code>
	 * 
	 * @param x
	 *            Integer value
	 */
	public static long factorial(long x) {
		long result = 1;
		for (long i = 2; i <= x; i++) {
			result *= i;
		}
		return result;
	}

	/**
	 * Calculates 2 in power of integer value <code>x</code>
	 * 
	 * @param x
	 * @return
	 */
	public static long pow2(long x) {
		long result = 1;
		for (long i = 1; i <= x; i++) {
			result *= 2;
		}
		return result;
	}

	/**
	 * Calculates the number of k-combinations (each of size k) from a set with
	 * n elements (size n) (also known as the "choose function")
	 * 
	 * @param n
	 *            Value n
	 * @param k
	 *            Value k
	 */
	public static long combination(long n, long k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	/**
	 * Calculates greatest common divisor (GCD) of two integer values
	 * <code>a</code> and <code>b</code>
	 * 
	 * @param a
	 *            Value a
	 * @param b
	 *            Value b
	 */
	public static long gcd(long a, long b) {
		if (a == 0)
			return b;
		if (b == 0)
			return a;
		if (a == b)
			return a;
		if (a == 1 | b == 1)
			return 1;
		if ((a % 2 == 0) & (b % 2 == 0))
			return 2 * gcd(a / 2, b / 2);
		if ((a % 2 == 0) & (b % 2 != 0))
			return gcd(a / 2, b);
		if ((a % 2 != 0) & (b % 2 == 0))
			return gcd(a, b / 2);
		return gcd(b, Math.abs(a - b));
	}

	/**
	 * Calculates lowest common multiple (LCM) of two integer values
	 * <code>a</code> and <code>b</code>
	 * 
	 * @param a
	 *            Value a
	 * @param b
	 *            Value b
	 */
	public static long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}

	/**
	 * The linear interpolant is the straight line between these points
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @param x
	 * @return y
	 */
	public static double linearInterpolation(double x0, double y0, double x1,
			double y1, double x) {
		return y0 + ((y1 - y0) / (x1 - x0)) * (x - x0);
	}

	public static Point2D calculateIntersection(Line2D line1, Line2D line2) {
		double a, b, c, d, e, f, g, h;
		if (line1.intersectsLine(line2)) {
			a = line1.getX1();
			b = line1.getY1();
			c = line2.getX1();
			d = line2.getY1();
			e = line1.getX2();
			f = line1.getY2();
			g = line2.getX2();
			h = line2.getY2();

			double x, y;

			if (line1.getX2() == line1.getX1()
					&& line2.getY2() == line2.getY1()) {
				x = line1.getX1();
				y = line2.getY1();
			} else if (line1.getY2() == line1.getY1()
					&& line2.getX2() == line2.getX1()) {
				x = line2.getX1();
				y = line1.getY1();
			} else {

				// if (line1.getX2() < line1.getX1()) {
				// e = line1.getX1();
				// a = line1.getX2();
				// }
				// if (line1.getY2() < line1.getY1()) {
				// f = line1.getY1();
				// b = line1.getY2();
				// }
				// if (line2.getX2() < line2.getX1()) {
				// g = line2.getX1();
				// c = line2.getX2();
				// }
				// if (line2.getY2() < line2.getY1()) {
				// h = line2.getY1();
				// d = line2.getY2();
				// }
				double t1 = -a * d + a * h + b * c - b * g - c * h + d * g;
				double zn = a * d - a * h - b * c + b * g + c * f - d * e + e
						* h - f * g;

				if (zn == 0.0)
					return null;

				t1 = t1 / zn;
				double t2 = a * d - a * f - b * c + b * e + c * f - d * e;
				t2 = t2 / (-zn);

				x = a + t1 * (e - a);
				y = b + t1 * (f - b);
			}
			return new Point2D.Double(x, y);
		}
		return null;
	}
}