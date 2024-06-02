import java.lang.Math;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws IOException {
		double [] c1 = {6,0,0,5};
		int [] e1 = {0,1,2,3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {0,-2,0,0,-9};
		int [] e2 = {0,1,2,3,4};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		System.out.println("-------------------");
		System.out.println("Add Test:");

		for(int i = 0; i < s.coefficient.length; i++){
			System.out.print(s.coefficient[i] + " ");
		}
		System.out.println();

		for(int i = 0; i < s.exponents.length; i++){
			System.out.print(s.exponents[i]+ " ");
		}
		System.out.println();
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		System.out.println("-------------------");

		if(s.hasRoot(1))
		System.out.println("1 is a root of s");
		else
		System.out.println("1 is not a root of s");

		// check multiply.
		double [] a1 = {1,2,3};
		int [] a2 = {0, 2, 1};

		Polynomial a = new Polynomial(a1, a2);

		double [] b1 = {2,3};
		int [] b2 = {3, 4};

		Polynomial b = new  Polynomial(b1, b2);
		
		Polynomial r = a.multiply(b);

		System.out.println("");

		System.out.println("-------------------");

		System.out.println("Multiply Test:");
		System.out.println("r(0.1) = " + r.evaluate(1));
		System.out.println("-------------------");

		File file_poly1 = new File("poly1.txt");
		File file_poly2 = new File("poly2.txt");

		Polynomial poly_file1 = new Polynomial(file_poly1);
		Polynomial poly_file2 = new Polynomial(file_poly2);

		System.out.println("-------------------");
		System.out.println("Poly1 from file");
		for(int i = 0; i < poly_file1.coefficient.length; i++){
			System.out.print(poly_file1.coefficient[i] + " ");
		}
		System.out.println();

		for(int i = 0; i < poly_file1.exponents.length; i++){
			System.out.print(poly_file1.exponents[i]+ " ");
		}
		System.out.println();
		System.out.println("-------------------");

		System.out.println("Poly2 from file");
		for(int i = 0; i < poly_file2.coefficient.length; i++){
			System.out.print(poly_file2.coefficient[i] + " ");
		}
		System.out.println();

		for(int i = 0; i < poly_file2.exponents.length; i++){
			System.out.print(poly_file2.exponents[i]+ " ");
		}
		System.out.println();
		System.out.println("-------------------");
		
		Polynomial l = poly_file1.add(poly_file2);
		l.saveToFile("sum.txt");

		Polynomial m = poly_file1.multiply(poly_file2);
		m.saveToFile("product.txt");







	}
}