import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
	double [] coefficient;
	int [] exponents;

	public Polynomial(){
		this.coefficient = new double [0];
		this.exponents = new int [0];	
	}

	public Polynomial(double [] coefficient){
		this.coefficient = coefficient;
		int [] exponents = new int[coefficient.length];
		for(int i = 0; i < coefficient.length; i++){
			exponents[i] = i;
		}
		this.exponents = exponents;
	}

	public Polynomial(double [] coefficient, int [] exponent){
		this.coefficient = coefficient;
		this.exponents = exponent;
	}

	public Polynomial(File file) throws FileNotFoundException{

		
		Scanner line = new Scanner(file);

		if(!line.hasNextLine()){
			this.coefficient = new double[0];
			this.exponents = new int[0];
		}
		else {
			String data = line.nextLine();
			if(data.toCharArray()[0] != '-'){
				data = '+' + data;
			}
				
			String[] split = data.split("[-+]");
			double [] coefficient = new double [split.length-1];
			int [] exponents = new int [split.length-1];

			String[] signs = new String[split.length-1];
			
			int index = 0;
			for (char c : data.toCharArray()){
				if(c == '-'){
					signs[index] = "-";
					index++;
				}
				else if(c == '+'){
					signs[index] = "+";
					index++;
				}
			}

			for(int i = 1; i < split.length; i++){
				String[] split_item = split[i].split("[x]");
				
				if(split_item.length == 1){
					coefficient[i-1] = Double.parseDouble(signs[i-1] + split_item[0]);
					exponents[i-1] = 0;
				} 
				else{
					coefficient[i-1] = Double.parseDouble(signs[i-1] + split_item[0]);
					exponents[i-1] = Integer.parseInt(split_item[1]);
				}
			}			
			this.coefficient = coefficient;
			this.exponents = exponents;
		}
		line.close();
	}
	
	public void saveToFile(String file_name) throws IOException{
		String output = "";
		for(int i = 0; i < this.coefficient.length; i++){
			if(this.coefficient[i] > 0){
				output = output + "+" + this.coefficient[i];
			}
			else{
				output = output + this.coefficient[i];
			}

			if(this.exponents[i] != 0){
				output = output + "x" + this.exponents[i];
			}		
		}
		int index = 0;
		String new_output = "";
		for (char c : output.toCharArray()){
			if(index == 0 && c == '+'){
				index++;
				continue;
			}
			new_output = new_output + c;
			
		}
		FileWriter fw = new FileWriter(file_name);
		fw.write(new_output);
		fw.close();
	}


	public Polynomial add(Polynomial poly){
		// make a new polynomial;
		double [] copy_coefficient = new double [this.coefficient.length];
		int [] copy_exponents = new int [this.exponents.length];

		if(this.coefficient.length  == 0){
			double [] copy_coefficient2 = new double [poly.coefficient.length];
			int [] copy_exponents2 = new int [poly.exponents.length];

			for(int i = 0; i < poly.coefficient.length; i++){
				copy_coefficient2[i] = poly.coefficient[i];
				copy_exponents2[i] = poly.exponents[i];
			}
			Polynomial output = new Polynomial(copy_coefficient2, copy_exponents2);
			return output;
		}

		for(int i = 0; i < this.coefficient.length; i++){
			copy_coefficient[i] = this.coefficient[i];
			copy_exponents[i] = this.exponents[i];
		}
		Polynomial output = new Polynomial(copy_coefficient, copy_exponents);
		
		for(int i = 0; i < poly.exponents.length; i++){
			int counter = 0;
			for(int j = 0; j < output.exponents.length; j++){
				if (poly.exponents[i] == output.exponents[j]){
					output.coefficient[j] += poly.coefficient[i];
					counter = 1;
					break;
				}
			}
			if (counter == 0){
					int [] new_exponent = new int[output.exponents.length + 1];
					double [] new_coefficients = new double[output.coefficient.length + 1];
					for(int k = 0; k < output.exponents.length; k++){
						new_exponent[k] = output.exponents[k];
						new_coefficients[k] = output.coefficient[k];
					}
					new_exponent[output.exponents.length] = poly.exponents[i];
					new_coefficients[output.exponents.length] = poly.coefficient[i];
					output.coefficient = new_coefficients;
					output.exponents = new_exponent;
				}
		}
		
		int zeros = 0;
		for(int i = 0; i < output.coefficient.length; i++){
			if(output.coefficient[i] == 0){
				zeros++;
			}
		}
		int [] new_exponents2 = new int[output.coefficient.length - zeros];
		double [] new_coefficients2 = new double[output.exponents.length - zeros];
		int index = 0;
		
		for(int i = 0; i < output.coefficient.length; i++){
			if(output.coefficient[i] == 0){
				continue;
			}
			new_exponents2[index] = output.exponents[i];
			new_coefficients2[index] = output.coefficient[i];
			index++;
		}
		output.exponents = new_exponents2;
		output.coefficient = new_coefficients2;
		return output;
	}

	public double evaluate(double number){
		double value = 0;
		for (int i = 0; i < this.coefficient.length; i++) {
			value += this.coefficient[i] * Math.pow(number, this.exponents[i]);
		}
		return value;
	}

	public boolean hasRoot(double number){
		double root = this.evaluate(number);
		return (root == 0);
	}

	public Polynomial multiply(Polynomial poly){
		Polynomial output = new Polynomial();
		Polynomial adder = new Polynomial();

		for(int i = 0; i < poly.coefficient.length; i++){
			for(int j = 0; j < this.coefficient.length; j++){
				double [] add_coeff_array = new double [1];
				int [] add_exp_array = new int [1];
				add_coeff_array[0] = this.coefficient[j] * poly.coefficient[i];
				add_exp_array[0] = this.exponents[j] + poly.exponents[i];
				adder.coefficient = add_coeff_array;
				adder.exponents = add_exp_array;
				output = output.add(adder);
			}
		}
		return output;
	}
}

