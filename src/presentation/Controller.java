package presentation;

import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.List;

public class Controller {
    View view;
    ViewClients viewClients;
    ViewProducts viewProducts;
    ViewOrders viewOrders;
    ClientBLL clientBLL=new ClientBLL();
    ProductBLL productBLL=new ProductBLL();
    OrderBLL orderBLL=new OrderBLL();

    public Controller(){
        view=new View();
        viewClients=new ViewClients();
        viewProducts=new ViewProducts();
        viewOrders=new ViewOrders();

        //////////////////////////Main view
        view.getBtnViewClients().addActionListener(e -> viewClients.setVisible(true));
        view.getBtnViewProducts().addActionListener(e->{
            viewProducts.setVisible(true);
        });
        view.getBtnViewOrders().addActionListener(e->{
            viewOrders.setVisible(true);
        });

        ///////////////////////Client view
        viewClients.getBtnAddClient().addActionListener(e->{
            viewClients.refresh();
            Client client;
            try{
                client=new Client(viewClients.getTextFieldFirstName().getText(),viewClients.getTextFieldLastName().getText(),Integer.parseInt(viewClients.getTextFieldAge().getText()),viewClients.getTextFieldAddress().getText(),viewClients.getTextFieldEmail().getText(),viewClients.getTextFieldTel().getText(),viewClients.getTextFieldCity().getText());
            }catch(Exception exception){
                viewClients.error("Invalid age format!");
                return;
            }

            try{
                clientBLL.insert(client);
            }catch(Exception exception){
                viewClients.error(exception.getMessage());
                return;
            }
            viewClients.refresh();
            viewClients.success();
        });
        viewClients.getBtnFindClient().addActionListener(e->{
            viewClients.refresh();
            int id;
            try{
                id=Integer.parseInt(viewClients.getTextFieldid().getText());
            }catch(Exception exception){
                viewClients.error("Invalid id");
                return;
            }
            Client client;
            try{
                client=clientBLL.findClientById(id);
            }catch (Exception exception){
                viewClients.error(exception.getMessage());
                return;
            }

            viewClients.getTextFieldAddress().setText(client.getAddress());
            viewClients.getTextFieldAge().setText(""+client.getAge());
            viewClients.getTextFieldCity().setText(client.getCity());
            viewClients.getTextFieldEmail().setText(client.getEmail());
            viewClients.getTextFieldFirstName().setText(client.getFirstName());
            viewClients.getTextFieldLastName().setText(client.getLastName());
            viewClients.getTextFieldTel().setText(client.getTelephoneNumber());
        });
        viewClients.getBtnUpdateClient().addActionListener(e->{
            viewClients.refresh();
            Client client;
            int id;
            try{
                client=new Client(viewClients.getTextFieldFirstName().getText(),viewClients.getTextFieldLastName().getText(),Integer.parseInt(viewClients.getTextFieldAge().getText()),viewClients.getTextFieldAddress().getText(),viewClients.getTextFieldEmail().getText(),viewClients.getTextFieldTel().getText(),viewClients.getTextFieldCity().getText());
                id=Integer.parseInt(viewClients.getTextFieldid().getText()) ;
            }catch(Exception exception){
                viewClients.error("Invalid number format!");
                return;
            }

            try{
                clientBLL.update(client,id);
            }catch(Exception exception){
                viewClients.error(exception.getMessage());
                return;
            }
            viewClients.refresh();
            viewClients.success();
        });
        viewClients.getBtnDeleteClient().addActionListener(e->{
            viewClients.refresh();
            int id;
            try{
                id=Integer.parseInt(viewClients.getTextFieldid().getText());
            }catch(Exception exception){
                viewClients.error("Invalid id");
                return;
            }
            try{
                clientBLL.delete(id);
            }catch (Exception exception){
                viewClients.error(exception.getMessage());
                return;
            }
            viewClients.refresh();
            viewClients.success();
        });

        ///////////////////////Product view
        viewProducts.getBtnAddProduct().addActionListener(e->{
            viewProducts.refresh();
            Product product;
            try{
                product=new Product(viewProducts.getTextFieldName().getText(),Float.parseFloat(viewProducts.getTextFieldPrice().getText()) ,Integer.parseInt(viewProducts.getTextFieldStock().getText()));
            }catch(Exception exception){
                viewProducts.error("Invalid number format!");
                return;
            }

            try{
                productBLL.insert(product);
            }catch(Exception exception){
                viewProducts.error(exception.getMessage());
                return;
            }
            viewProducts.refresh();
            viewProducts.success();
        });
        viewProducts.getBtnFindProduct().addActionListener(e->{
            viewProducts.refresh();
            int id;
            try{
                id=Integer.parseInt(viewProducts.getTextField().getText());
            }catch(Exception exception){
                viewProducts.error("Invalid id");
                return;
            }
            Product product;
            try{
                product=productBLL.findProductById(id);
            }catch (Exception exception){
                viewProducts.error(exception.getMessage());
                return;
            }

            viewProducts.getTextFieldName().setText(product.getName());
            viewProducts.getTextFieldPrice().setText(""+product.getPrice());
            viewProducts.getTextFieldStock().setText(""+product.getAvailableStock());
        });
        viewProducts.getBtnUpdateProduct().addActionListener(e->{
            viewProducts.refresh();
            Product product;
            int id;
            try{
                product=new Product(viewProducts.getTextFieldName().getText(),Float.parseFloat(viewProducts.getTextFieldPrice().getText()) ,Integer.parseInt(viewProducts.getTextFieldStock().getText()));
                id=Integer.parseInt(viewProducts.getTextField().getText());
            }catch(Exception exception){
                viewProducts.error("Invalid number format!");
                return;
            }

            try{
                productBLL.update(product,id);
            }catch(Exception exception){
                viewProducts.error(exception.getMessage());
                return;
            }
            viewProducts.refresh();
            viewProducts.success();
        });
        viewProducts.getBtnDeleteProduct().addActionListener(e->{
            viewProducts.refresh();
            int id;
            try{
                id=Integer.parseInt(viewProducts.getTextField().getText());
            }catch(Exception exception){
                viewProducts.error("Invalid id");
                return;
            }
            try{
                productBLL.delete(id);
            }catch (Exception exception){
                viewProducts.error(exception.getMessage());
                return;
            }
            viewProducts.refresh();
            viewProducts.success();
        });
        ///////////////////////Order view
        viewOrders.getBtnAddOrder().addActionListener(e->{
            viewOrders.refresh();
            Order order;
            try{
                order=new Order(Integer.parseInt(viewOrders.getTextFieldidClient().getText()) ,Integer.parseInt(viewOrders.getTextFieldidProduct().getText()),Integer.parseInt(viewOrders.getTextFieldQuantity().getText()));
            }catch(Exception exception){
                viewOrders.error("Invalid number format!");
                return;
            }

            Client client;
            Product product;
            try{
                client=clientBLL.findClientById(order.getIdClient());
                product=productBLL.findProductById(order.getIdProduct());
            }catch(Exception exception){
                viewOrders.error(exception.getMessage());
                return;
            }
            try{
                orderBLL.insert(order);
            }catch(Exception exception){
                viewOrders.error(exception.getMessage());
                return;
            }

            product.setAvailableStock(product.getAvailableStock()-order.getQuantity());
            productBLL.update(product,product.getId());
            viewOrders.refresh();
            viewOrders.success();

            try{
                List<Order> list=orderBLL.findAll();
                FileWriter file=new FileWriter("order"+list.get(list.size()-1).getId());//to find the id of the last order made
                file.write("Client: "+client.getFirstName()+" "+client.getLastName()+"\n");
                file.write("Product: "+product.getName()+"\n");
                file.write("Quantity: "+order.getQuantity()+"\n");
                file.write("Price: "+order.getQuantity()*product.getPrice());
                file.close();
            }catch(Exception exception){

            }
        });
    }
}
