import java.lang.Math;

public class Polynomial{
	double [] coefficient;

	public Polynomial(){
		coefficient = new double[1];
		coefficient[0] = 0;
	}

	public Polynomial(double [] coeffs){
		this.coefficient = new double[coeffs.length];
		for(int i = 0; i < coeffs.length; i++){
			this.coefficient[i] = coeffs[i]; 
		}
	}

	public Polynomial add(Polynomial poly){
		
		int length = Math.max(this.coefficient.length, poly.coefficient.length);
		double[] new_polynomial = new double[length];
		for (int i = 0; i < this.coefficient.length; i++){
			new_polynomial[i] = this.coefficient[i];
		}
		for (int i = 0; i < poly.coefficient.length; i++){
			 new_polynomial[i] += poly.coefficient[i];
		}
		this.coefficient = new_polynomial;
		return this;
	}

	public double evaluate(double number){
		double result = 0;
		for (int i = 0; i < this.coefficient.length; i++){
			result += this.coefficient[i] * Math.pow(number, i);
		}
		return result;
	}

	public boolean hasRoot(double number){
		if (evaluate(number) == 0) return true;
		else return false;
	}
}