import java.util.*;
import java.lang.*;
import java.io.*;

public class ShoppingCart {
		
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