package businessLayer.validators;

import dataAccessLayer.ProductDAO;
import model.Order;
import model.Product;

public class QuantityValidator implements Validator<Order>{

    public void validate(Order order){
        ProductDAO productDAO=new ProductDAO();
        Product product=productDAO.findById(order.getIdProduct());
        if(product.getAvailableStock()<order.getQuantity())
            throw new IllegalArgumentException("Available stock should be less than the quantity!");
    }
}
