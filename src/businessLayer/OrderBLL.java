package businessLayer;

import businessLayer.validators.QuantityValidator;
import businessLayer.validators.Validator;

import dataAccessLayer.OrderDAO;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private List<Validator<Order>> validators;
    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<Validator<Order>>();
        validators.add(new QuantityValidator());

        orderDAO = new OrderDAO();
    }
       /* public Order findOrderById(int id){
            Order order = orderDAO.findById(id);
            if (order == null) {
                throw new NoSuchElementException("The order with id =" + id + " was not found!");
            }
            return order;
        } */

    public List<Order> findAll(){
        List<Order> orders = orderDAO.findAll();
        if(orders == null && orders.size()==0) {
            throw new NoSuchElementException("Cannot display the orders!");
        }
        return orders;
    }

    public Order insert(Order order){
        for(Validator validator:validators)
            validator.validate(order);
        return  orderDAO.insert(order);
    }

    /*public Order update(Order order,int id){
        for(Validator validator:validators)
            validator.validate(order);
        return  orderDAO.update(order,id);
    }

    public void delete(int id){
        orderDAO.delete(id);
    }*/
}
