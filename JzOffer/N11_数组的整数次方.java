package JzOffer;

// over
public class N11_数组的整数次方 {
	
	//mine, careful when exponent is negative
	//not consider when base is 0.0 while exponent is negative
	//not consider when base is 0.0 while exponent is 0; the answer is 0 or 1;
	public double Power(double base, int exponent) {
        boolean isNegative = false;
        if(exponent < 0){
            isNegative = true;
            exponent = -exponent;
        }
		double res = 1.0;
		while(exponent > 0)
		{
			res *= base;
			exponent--;
		}
        if(isNegative) res = 1.0 / res;
		return res;
	}
	
	//better and complete
	//careful when base is 0.0 while exponent is negative
	//careful when base is 0.0 while exponent is 0, the answer is 0 or 1;
	//careful the compare between two float or double, cannot use ==;
	//不太高效
	public double Power2(double base, int exponent) {
		boolean isvalidInput = false;
		
		//donnot forget
		if(equal(base, 0.0) && exponent < 0)
		{
			isvalidInput = true;
			return 0.0;
		}
		
        boolean isNegative = false;
        if(exponent < 0){
            isNegative = true;
            exponent = -exponent;
        }
		double res = 1.0;
		while(exponent > 0)
		{
			res *= base;
			exponent--;
		}
        if(isNegative) res = 1.0 / res;
		return res;
	}
	private boolean equal(double num1, double num2) {
		// TODO Auto-generated method stub
		if(((num1 - num2) > -0.00000001) && ((num1 - num2) < 0.0000001)) return true;
		else return false;
	}
	
	//best
	//高效
	public double Power3(double base, int exponent) {
		boolean isvalidInput = false;
		
		//donnot forget
		if(equal(base, 0.0) && exponent < 0)
		{
			isvalidInput = true;
			return 0.0;
		}
		
        boolean isNegative = false;
        if(exponent < 0){
            isNegative = true;
            exponent = -exponent;
        }
		double res = powerWithUnsignedExponent(base, exponent);
        if(isNegative) res = 1.0 / res;
		return res;
	}
	private double powerWithUnsignedExponent(double base, int exponent) {
		if(exponent == 0) return 1;
		if(exponent == 1) return base;
		double res = powerWithUnsignedExponent(base, exponent >> 1);
		res *= res;
		if((exponent & 1) == 1) res *= base;
		return res;
	}

	public static void main(String[] args) {
		System.out.println((int)(1.999999999999999d));
		int res = (int)(1.999999999999999d); // 2, else 1
		double result = 1.0 - 0.9;  //  result : 0.09999999999999998
	}
}
