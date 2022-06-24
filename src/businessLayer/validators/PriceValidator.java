package businessLayer.validators;

import model.Client;
import model.Product;

public class PriceValidator implements Validator<Product>{
    private static final float MIN_PRICE= 0F;
    public void validate(Product product){
         if(product.getPrice()<= MIN_PRICE){
             throw new IllegalArgumentException("The price for a product should be grater than 0!");
         }
    }
}
