package fractions;

import java.math.*;

/**
 * Immutable fractions of whole numbers, also known as rational numbers,
 * with their usual arithmetic operations.
 * 
 * Similar to java.lang.BigInteger, the documentation uses pseudo-code
 * throughout the descriptions of Fraction methods. The pseudo-code
 * expression (i + j) is shorthand for "a Fraction whose value is that
 * of the Fraction plus that of the Fraction j". Other pseudo-code
 * expressions are interpreted similarly. 
 *
 * @see java.math.BigInteger
 *
 * @author Marco Guimaraes
 */
public class Fraction {
	    
	private BigInteger numerator;
	private BigInteger denominator;
   

	/**
     * Constructs a Fraction taking the value of its parameter.
     *
     * @param val  non-null; the value the Fraction is supposed to take
     */
    public Fraction(BigInteger val) {
    	this(val, BigInteger.ONE);
    }

    /**
     * Constructs a new Fraction taking the value of its parameter.
     *
     * @param val  the value the Fraction is supposed to take
     */
    public Fraction(long val) {
    	this(BigInteger.valueOf(val), BigInteger.ONE);
    }

    /**
     * Constructs a Fraction corresponding to numerator / denominator.
     * The value is 0 if denominator is 0.
     *
     * @param numerator  non-null; value of the numerator
     * @param denominator  non-null; value of the denominator
     */
    public Fraction(BigInteger numerator, BigInteger denominator) {     
        if ( (numerator.compareTo(BigInteger.ZERO) == 0) || (denominator.compareTo(BigInteger.ZERO) == 0)) {
        	// here we should throw an ArithmeticException but we are only making the function 0  
            this.numerator   = BigInteger.ZERO;
            this.denominator = BigInteger.ZERO;
            return;
          
        } else {

        	// simplify the fraction in the constructor as suggested in the coursework specification (normalisation)
        	BigInteger gcd = numerator.gcd(denominator);
        	
        	if (gcd.compareTo(BigInteger.ZERO) != 0) {
                this.numerator   = numerator.divide(gcd);
                this.denominator = denominator.divide(gcd);
        	} else {
	        	this.numerator   = numerator;
	          	this.denominator = denominator;      		
        	}

        }		        
    }

    /**
     * Constructs a Fraction corresponding to numerator / denominator.
     * The value is 0 if denominator is 0.
     *
     * @param numerator  the numerator of the Fraction
     * @param denominator  the denominator of the Fraction
     */
    public Fraction(long numerator, long denominator) {
    	this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }
    
    
    /**
     * Reduce the fraction to it lowest terms
     * @param numerator  the numerator of the Fraction
     * @param denominator  the denominator of the Fraction
     * @return new fraction with its original parameters if gcd equal 1, or simplified otherwise 
     */
    /*
     * FOR TESTING PURSPOSE
    public Fraction normalize(BigInteger numerator, BigInteger denominator){
    	
    	// simplify the fraction in the constructor as suggested(normalisation)
    	BigInteger gcd = numerator.gcd(denominator);
    	
    	if (gcd.compareTo(BigInteger.ZERO) == 0) {
    		return new Fraction(numerator, denominator);
    	} else {         	
          	return new Fraction(numerator.divide(gcd),denominator.divide(gcd));
        }      	    	 
    }
    */

    /**
     * Returns a Fraction whose value is (this + val).
     * (Note that a/b + c/d = (a*d + b*c)/(b*d).)
     * 
     * @param val  non-null; to be added to this
     * @return this + val
     */
    public Fraction add(Fraction val) {
    	if (val != null){
    		return new Fraction(this.numerator.multiply(val.denominator).add(this.denominator.multiply(val.numerator)), this.denominator.multiply(val.denominator));
    		
    	} else {
    		return null;	
    	}        
    }

    /**
     * Returns the sum of all elements of vals.
     *
     * @param fractions  array of Fractions to be summed up; may be or contain null
     * @return null if vals is or contains null; the sum of all elements of
     *  vals otherwise
     */
    public static Fraction sumAll(Fraction[] fractions) {
    	//Fraction result = new Fraction(0,0);
    	BigInteger n = BigInteger.ZERO;
    	BigInteger d = BigInteger.ZERO;
    	
        if (fractions.length > 0) {
        	for (int i = 0; i < fractions.length; i++) {
        		if(fractions[i] != null){
        			n = n.add(fractions[i].numerator.abs());
        			d = d.add(fractions[i].denominator.abs());
        		} else {
        			return null; //null if vals is or contains null
        		}  
        	}
        	return new Fraction(n,d);
        } else {        
        	return new Fraction(0,0);
        }    	
        
    }

    /**
     * Returns a Fraction whose value is (this - val).
     *
     * @param val  non-null; to be subtracted from this Fraction
     * @return this - val
     */
    public Fraction subtract(Fraction val) {        
    	if (val != null){
    		return new Fraction(this.numerator.multiply(val.denominator).subtract(this.denominator.multiply(val.numerator)), this.denominator.multiply(val.denominator));   	
    	} else {
    		return null;	
    	}
    }

    /**
     * Returns a Fraction whose value is (this * val).
     * (Note that a/b * c/d = (a*c)/(b*d).)
     *
     * @param val  non-null; to be multiplied to this Fraction
     * @return this * val
     */
    public Fraction multiply(Fraction val) {
    	if (val != null){    		 
    		return new Fraction(this.numerator.multiply(val.numerator), this.denominator.multiply(val.denominator));    		
    	} else {
    		return null;	
    	}
    }

    /**
     * Returns a Fraction whose value is (this / val).  
     * 
     * @param val  non-null; value by which this Fraction is to be divided
     * @return this / val
     */
    public Fraction divide(Fraction val) {
    	if ((val != null) && (val.denominator.compareTo(BigInteger.ZERO) > 0)) {
    		return new Fraction(this.numerator.multiply(val.denominator), this.denominator.multiply(val.numerator));    		
    	} else {
    		return new Fraction(0, 0);		
    	}
    }

    /**
     * Returns a Fraction whose value is (-this).
     *
     * @return -this
     */
    public Fraction negate() {
    	
    	if ((this.numerator.compareTo(BigInteger.ZERO) < 0) && (this.denominator.compareTo(BigInteger.ZERO) < 0)){
    		return new Fraction(this.numerator.negate(), this.denominator.negate());		    	
    	} else if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
    		return new Fraction(this.numerator, this.denominator.negate());
    	} else {
    		return new Fraction(this.numerator.negate(), this.denominator);
    	}
    			
    }

    /**
     * Returns the inverse of this Fraction, i.e., the Fraction 1 / this.
     * 
     * @return 1 / this
     */
    public Fraction invert() {
    	if ((this != null) && (this.denominator.compareTo(BigInteger.ZERO) > 0)) {
    		return new Fraction(BigInteger.ONE.multiply(this.denominator), BigInteger.ONE.multiply(this.numerator));    		
    	} else {
    		return new Fraction(0, 0);		
    	}
    }

    /**
     * Returns the sign of this Fraction: 1 if its value is positive,
     * 0 if it is zero, -1 if it is negative.
     *
     * @return the sign of this Fraction (1 if its value is positive,
     *  0 if it is zero, -1 if it is negative) 
     */
    public int signum() {
    	 return this.numerator.signum();
        // TODO Auto-generated method stub
        //return 0;
    }

    /**
     * Returns the absolute value of this Fraction, i.e.,
     * the value of the Fraction itself if it is non-negative,
     * otherwise the negated value.
     * 
     * @return the absolute value of this Fraction
     */
    public Fraction abs() {
    	return (signum() < 0 ? negate() : this);
    }

    /**
     * Returns the maximum of this Fraction and val.
     *
     * @param val  non-null; the value with which the maximum is to be computed
     * @return the maximum of this Fraction and val
     */
    public Fraction max(Fraction val) {
    	if (val != null){
    		return (this.compareTo(val) >= 0 ? this : val);
    	} else {
    		return null;
    	}
    }

    /**
     * Returns the minimum of this Fraction and val.
     *
     * @param val  non-null; the value with which the minimum is to be computed
     * @return the minimum of this Fraction and val
     */
    public Fraction min(Fraction val) {
    	if (val != null){
    		return (this.compareTo(val) <= 0 ? this : val);
    	} else {
    		return null;
    	}
    }

    /**
     * Returns this Fraction taken to the power of exponent. Here
     * exponent may also be zero or negative. Note that a^0 = 1 and
     * a^b = (1/a)^(-b) if b < 0. 
     * 
     * @param exponent  the exponent to which we want to take this
     * @return this Fraction taken to the power of exponent
     */
    public Fraction pow(int exponent) {
        if(exponent == 0)
            return new Fraction(1, 1);
          else if (exponent == 1)
            return this;
          else if (exponent < 0)
            return new Fraction(denominator.pow(-exponent), numerator.pow(-exponent));
          else
            return new Fraction(numerator.pow(exponent), denominator.pow(exponent));
    }

    /**
     * Compares this Fraction with the specified Fraction.
     * 
     * @param val  non-null; value with which this Fraction is to be compared 
     * @return -1, 0 or 1 as this Fraction is numerically less than,
     *         equal to, or greater than val
     * @see java.math.BigInteger#compareTo(BigInteger)
     */
    public int compareTo(Fraction val) {
    	if(val != null){
    	          
          if(this.signum() != val.signum()) {
        	  return this.signum() - val.signum();
          } else if (this.denominator.equals(val.denominator)) {
        	  return this.numerator.compareTo(val.numerator);
          } else {          
        	  return this.numerator.multiply(val.denominator).compareTo(this.denominator.multiply(val.numerator));
          }      
          
    	} else {
    		return 0;
        }
    }

    /**
     * Checks if this Fraction and val represent equal values.
     *
     * @param val  potentially null (in this case the method returns false);
     *  the value with which this Fraction is to be compared for equality
     * @return true if this Fraction and other represent the same value;
     *  false otherwise
     */
    public boolean isEqualTo(Fraction val) {
    	if(val != null) {
    		return (this.numerator.equals(val.numerator) && this.denominator.equals(val.denominator));
    	} else {
    		return false;
    	}
    }

    /**
     * Returns a normalised String representation of this Fraction.
     * For example, new Fraction(5,3) and new Fraction(-10,-6) will
     * be represented as "(5 / 3)". The String representation of
     * new Fraction(5,-10) and new Fraction(-12,24) is "(-1 / 2)".
     *
     * In case this Fraction has an integer value, just the String
     * representation of the integer value is returned. For example,
     * new Fraction(-2) has the String representation "-2"; and
     * new Fraction(0), new Fraction(0,3), and new Fraction(4,0)
     * all have the String representation "0".
     *
     * @return a normalised String representation of this Fraction
     */
    public String toString() {	
        if (this.denominator.compareTo(BigInteger.ZERO) == 0) {
        	return "0";        	
        } else if (this.denominator.compareTo(BigInteger.ONE) != 0) {
        	return "(" + numerator.toString() + " / " + denominator.toString() + ")";            
        } else {        	
        	return numerator.toString();
        }       
    	
    }
}
