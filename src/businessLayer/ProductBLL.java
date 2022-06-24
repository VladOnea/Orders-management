package businessLayer;

import businessLayer.validators.*;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new StockValidator());
        validators.add(new PriceValidator());

        productDAO = new ProductDAO();
    }
    public Product findProductById(int id){
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    public List<Product> findAll(){
        List<Product> products = productDAO.findAll();
        if(products == null && products.size()==0) {
            throw new NoSuchElementException("Cannot display the products!");
        }
        return products;
    }

    public Product insert(Product product){
        for(Validator validator:validators)
            validator.validate(product);
        return productDAO.insert(product);
    }

    public Product update(Product product,int id){
        for(Validator validator:validators)
            validator.validate(product);
        return productDAO.update(product,id);
    }

    public void delete(int id){
        productDAO.delete(id);
    }
}
