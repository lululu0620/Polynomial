// Name: Lu Xie
// USC loginid: luxie
// CS 455 PA2
// Spring 2017
import java.util.Scanner;
import java.util.ArrayList;
public class PolynomialCalculator {
	
	/**
	   Create an array with length of 10 to store the polynomials
	   Get the command and arguments that the user inputs.
	 */
	public static void main(String[] args) {
		final int numberOfPoly = 10;
		Polynomial[] polynomial = new Polynomial[numberOfPoly];
		for (int i = 0; i < polynomial.length; i++) {
			polynomial[i] = new Polynomial();
		}
		
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("cmd> ");
			String command = in.nextLine();
			String cmd = "";
			Scanner commandScanner = new Scanner(command);
			cmd = cmd + commandScanner.next();
			cmd = cmd.toLowerCase();
			ArrayList<Integer> arg = new ArrayList<Integer>();
			while(commandScanner.hasNextInt()) {
				arg.add(commandScanner.nextInt());
			}
			if (cmd.equals("quit")) {
				break;
			}else if (cmd.equals("help")) {
				help();
			}else {
				calculator(polynomial, cmd, arg);
			}
		}	
	}
	
	/**
	   Do some calculators on the polynomials
	 */
	public static void calculator(Polynomial[] polynomial, String cmd, ArrayList<Integer> arg) {	
		if (cmd.equals("create")) {
			if(check(arg)) {
				polynomial[arg.get(0)] = new Polynomial();
				polynomial[arg.get(0)] = polynomial[arg.get(0)].add(create());
			}
		} else if (cmd.equals("print")) {
			if(check(arg)) {System.out.println(polynomial[arg.get(0)].toFormattedString());}
		} else if (cmd.equals("add")) {
			if (arg.size()!=3) {System.out.println("Illegal command. Type 'help' for command options.");}
			else if(check(arg)) {polynomial[arg.get(0)] = polynomial[arg.get(1)].add(polynomial[arg.get(2)]);}
		} else if (cmd.equals("eval")) {
			if(check(arg)) {System.out.println(doEval(polynomial[arg.get(0)]));}
		} else {
			if(check(arg)) {System.out.println("Illegal command. Type 'help' for command options.");}
		}	
	}
	
	/**
	   Check the command and argument of index is valid or not
	 */
	private static boolean check(ArrayList<Integer> arg) {
		boolean run = true;
		for (int k = 0; k < arg.size(); k++) {
			if (arg.get(k) < 0 || arg.get(k) > 9) {
				run = false;
				System.out.println("ERROR: illegal index for a poly. must be between 0 and 9, inclusive.");
				break;
			}
		}
		if (arg.size() == 0) {
			run = false;
			System.out.println("ERROR: Illegal command. Type 'help' for command options.");
		} 
		return run;
	}
	
	/**
	   Use the result of doCreate to create a polynomial 
	 */
	public static Polynomial create() {
		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by enter:");
		Polynomial p = new Polynomial();
		ArrayList<Double> num = doCreate();
		for (int i = 0; i < num.size()/2*2; i += 2) {
			p = p.add(new Polynomial(new Term(num.get(i), (int)Math.round(num.get(i+1)))));
		}
		if(num.size()%2 != 0) {
            System.out.println("WARNING: Odd number of values. The last value will be ignored");
        }
		return p;
	}
	
	/**
	   Output the description of the command 
	 */
	public static void help(){
		System.out.println("There is a array with size of 10 for you to store the polynomials.");
		System.out.println("You can do the following calculations with these polynomials:");
		System.out.println("PRE: 0 <= num <= 9;");
		System.out.println("     0 <= num1 <= 9;");
		System.out.println("     0 <= num2 <= 9;");
		System.out.println("     0 <= num3 <= 9.");
		System.out.println("    create num: input some integers to create a new polynomial in the [num] location of the array.");
		System.out.println("    print num: print the polynomials in the [num] location of the array.");
		System.out.println("    add num1 num2 num3: create a new polynomial in the [num] location of the array with adding the polynomial in the [num2] location and the [num3] location.");
		System.out.println("    eval num: calculate the value of the polynomial in the [num] location of the array at a given value of x.");
		System.out.println("    quit: exit this calcultion.");
		
	}
	/**
	   Get the arguments that the user inputs and return the value of the polynomial
	 */
	private static double doEval(Polynomial b) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a floating point value for x: ");
		double x = in.nextDouble();
		return b.eval(x);
	}
	/**
	   Get the arguments that the user inputs and return coefficient and exponent
	 */
	private static ArrayList<Double> doCreate() {
		Scanner in = new Scanner(System.in);
		String nums = in.nextLine();
		Scanner numsScanner = new Scanner(nums);
		ArrayList<Double> num = new ArrayList<Double>(); 
		
		while (numsScanner.hasNextDouble()) {
			Double k = numsScanner.nextDouble();
			num.add(k);
		}
		for (int i = 0; i < num.size(); i++) {
			if (i%2 == 1 && num.get(i) < 0) {
				System.out.println("WARNING: there exists negative exponent.  use the absolute value.");
				num.set(i, -num.get(i));
			}
		}
		return num;	
	}
}	
