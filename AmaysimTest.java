import java.util.*;
import java.lang.*;
import java.io.*;

public class AmaysimTest {

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