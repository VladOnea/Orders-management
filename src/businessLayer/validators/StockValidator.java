package businessLayer.validators;

import model.Product;

public class StockValidator implements Validator<Product>{
    private static final int MIN_STOCK= 0;
    public void validate(Product product){
        if(product.getPrice()< MIN_STOCK){
            throw new IllegalArgumentException("The stock for a product should be grater than 0!");
        }
    }
}
