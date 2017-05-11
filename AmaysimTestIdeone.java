// Tested in Ideone.com

import java.util.*;
import java.lang.*;
import java.io.*;

enum ProductCode { ult_small, ult_medium, ult_large, onegb; }
	
class Product {
	
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
	
 class Item {
		
	public Product product;
	public int count;
		
	public Item(Product product, int count) {
			
		this.product = product;
		this.count = count;
	}
}
	
class ShoppingCart {
		
	public List<Item> items;
	public PricingRules rules;
	public double total;
		
	public ShoppingCart(PricingRules rules) {
			
		items = new ArrayList<Item>();
		this.rules = rules;
	}
		
	public void add(Item item) {
			
		items.add(item);
			
		total += rules.getPrice(item);
			
		ProductCode pCode = ProductCode.valueOf(item.product.code);
		if (pCode == ProductCode.ult_medium) {
			items.add(new Item(new Product("onegb"), item.count));
		}
	}
		
	public void add(Item item, String promoCode) {
			
		add(item);
			
		total -= (total * rules.getDiscount(promoCode));
	}
		
	public void reset() {
		
		items.clear();
		total = 0.00;
	}
}
	
class PricingRules {
		
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
	
class AmaysimTestIdeone {

	public static void printResults(ShoppingCart cart) {
		System.out.print("Expected Cart Total: $");
		System.out.printf("%.2f %n", cart.total);
		
		for (int i = 0; i < cart.items.size(); i++) {
			Item item = cart.items.get(i);
			System.out.println(item.count + " x " + item.product.name);
		}
		
		System.out.println();
	}
	
	public static void main (String[] args) throws java.lang.Exception {
	
		PricingRules rules = new PricingRules();
		
		ShoppingCart cart = new ShoppingCart(rules);
		
		cart.add(new Item(new Product("ult_small"), 3));
		cart.add(new Item(new Product("ult_large"), 1));
		printResults(cart);
		cart.reset();
		
		cart.add(new Item(new Product("ult_small"), 2));
		cart.add(new Item(new Product("ult_large"), 4));
		printResults(cart);
		cart.reset();
		
		cart.add(new Item(new Product("ult_small"), 1));
		cart.add(new Item(new Product("ult_medium"), 2));
		printResults(cart);
		cart.reset();
		
		cart.add(new Item(new Product("ult_small"), 1));
		cart.add(new Item(new Product("onegb"), 1), "'I<3AMAYSIM'");
		printResults(cart);
		cart.reset();
	}
}