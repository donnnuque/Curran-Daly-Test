import java.util.*;
import java.lang.*;
import java.io.*;

public class PricingRules {
		
	public double getPrice(Item item) {
			
		double price = item.product.price * item.count;
			
		ProductCode pCode = ProductCode.valueOf(item.product.code);
			
		switch(pCode) {
			case ult_small:
					
				int quotient = item.count / 3;
				int mod = item.count % 3;
					
				if (quotient > 0) {
					price = item.product.price * (quotient * 2 + mod);
				}
					
				break;
			case ult_large:
					
				if (item.count > 3) {
					price = 39.90 * item.count;
				}
					
				break;
				
			default:
		}
			
		return price;
	}
		
	public double getDiscount(String promoCode) {
			
		double discount = 0;
			
		if (promoCode == "'I<3AMAYSIM'") {
			discount = 0.10;
		}
		
		return discount;
	}
}