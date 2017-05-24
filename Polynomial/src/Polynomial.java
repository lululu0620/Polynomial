// Name: Lu Xie
// USC loginid: luxie
// CS 455 PA2
// Spring 2017


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
   
*/
public class Polynomial {
    /**
        Representation Invariants:
        1.There are not any terms where the coefficient is zero in the polynomial. 
        2.There are not two term with the same exponent; if they have the same exponent, they will merge into one term.
        3.We maintain the term as an ordered list, ordered from highest exponent term to lowest. 
        4.The All of the terms in the polynomial
    */
        
    private ArrayList<Term> poly;
    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
    	poly = new ArrayList<Term>();
    	assert this.isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
    	poly = new ArrayList<Term>();
    	if (term.getCoeff() != 0) {
    		poly.add(term);
    	}
    	assert this.isValidPolynomial();
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {
    	assert this.isValidPolynomial();       	
    	assert b.isValidPolynomial();  
	    int i = 0, j = 0;
	    Polynomial polysum = new Polynomial();
    	while (i < poly.size() && j < b.poly.size()) {
    		if (poly.get(i).getExpon() == b.poly.get(j).getExpon()) {
    			if (poly.get(i).getCoeff() + b.poly.get(j).getCoeff() != 0) {
    				polysum.poly.add(new Term(poly.get(i).getCoeff() + b.poly.get(j++).getCoeff(), poly.get(i++).getExpon()));
    			}else {
    				i++;
    				j++;
    			}
    		}else if (poly.get(i).getExpon() > b.poly.get(j).getExpon()) {
    			polysum.poly.add(poly.get(i++));
    		}else {
    			polysum.poly.add(b.poly.get(j++));
    		}
    	}
    	if (i < poly.size()) {
    		for (int k = i; k < poly.size(); k++) {
    			polysum.poly.add(poly.get(k));
    		}
    	}else if (j < b.poly.size()) {
    		for (int k = j; k < b.poly.size(); k++) {
    			polysum.poly.add(b.poly.get(k));
    		}
    	}
    	assert polysum.isValidPolynomial();
    	return polysum;  // dummy code.  just to get stub to compile
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
    	assert this.isValidPolynomial();
    	double sum = 0;
    	for(int i = 0; i < poly.size(); i++){
    		sum += poly.get(i).getCoeff() * Math.pow(x,poly.get(i).getExpon());
    	}
    	return sum;         // dummy code.  just to get stub to compile
    }

    public String toString(){
    	assert this.isValidPolynomial();
    	String str = "";
    	for(int i = 0; i < poly.size(); i++){
    		str += poly.get(i).toString();
    	}
    	return str;
    }

    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
    	String str = "";
    	if (poly.size() == 0) {return "0.0";}
    	for(int i = 0; i < poly.size(); i++) {
    		if (poly.get(i).getExpon() == 0) {
    			str = str + poly.get(i).getCoeff();
    		}else if (poly.get(i).getExpon() == 1) {
    			if (poly.get(i).getCoeff() == 1) {
    				str = str + "x";
    			}else if (poly.get(i).getCoeff() == -1) {
    				str = str + "-x";
    			}else{
    				str = str + poly.get(i).getCoeff() + "x";
    			}
    		}else {
    			if (poly.get(i).getCoeff() == 1) {
    				str = str + "x^" + poly.get(i).getExpon();
    			}else if (poly.get(i).getCoeff() == -1) {
    				str = str + "-x^" + poly.get(i).getExpon();
    			}else{
    				str = str + poly.get(i).getCoeff() + "x^" + poly.get(i).getExpon();
    			}
    		}
    		if (i != poly.size() - 1) {
    			str += " + ";
    		}	
   		}
    	return str;
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true iff the poly data is in a valid state.
	 */
    private boolean isValidPolynomial() {
    	for (int i = 0; i < poly.size(); i++) {
    		if (poly.get(i).getCoeff() == 0) {
    			return false;
    		}
    	}
    	for (int i = 0; i < poly.size(); i++) {
    		for (int j = i+1; j <poly.size();j++) {
    			if (poly.get(i).getExpon() <= poly.get(j).getExpon()) {
    				return false;
    			}
    		}
    	}
    	return true;     // dummy code.  just to get stub to compile
    }

}
