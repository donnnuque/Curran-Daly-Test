import java.util.*;
import java.lang.*;
import java.io.*;

public class Product {
	
	public String code;
	public String name;
	public double price;
		
	public Product(String code) {
		
		this.code = code;
		
		setDetails(code);
	}
		
	public void setDetails(String code) {
		
		ProductCode pCode = ProductCode.valueOf(code);
	
		switch(pCode) {
			case ult_small:
				this.name = "Unlimited 1GB";
				this.price = 24.90;
				break;
			case ult_medium:
				this.name = "Unlimited 2GB";
				this.price = 29.90;
				break;
			case ult_large:
				this.name = "Unlimited 5GB";
				this.price = 44.90;
				break;
			case onegb:
				this.name = "1 GB Data-pack";
				this.price = 9.90;
				break;
			default:
		}
	}
}