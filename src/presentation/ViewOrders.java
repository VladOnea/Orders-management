package presentation;

import dataAccessLayer.OrderDAO;
import model.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewOrders extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldidClient;
    private JTextField textFieldidProduct;
    private JTextField textFieldQuantity;
    JButton btnAddOrder;
    JLabel errorMessage;
    JScrollPane scrollPane;
    OrderDAO orderDAO=new OrderDAO();
    AbstractTable<Order> abstractTable=new AbstractTable();


    /**
     * Create the frame.
     */
    public ViewOrders() {

        setVisible(false);
        setTitle("Orders management");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblYouCan = new JLabel("You can:");
        lblYouCan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblYouCan.setBounds(29, 25, 55, 25);
        contentPane.add(lblYouCan);

        btnAddOrder = new JButton("Add order");
        btnAddOrder.setBounds(10, 76, 97, 21);
        contentPane.add(btnAddOrder);

        scrollPane=new JScrollPane();
        scrollPane.setBounds(393, 60, 284, 173);
        contentPane.add(scrollPane);

        JLabel lblidClient = new JLabel("id Client");
        lblidClient.setBounds(195, 105, 55, 13);
        contentPane.add(lblidClient);

        textFieldidClient = new JTextField();
        textFieldidClient.setBounds(195, 125, 122, 19);
        contentPane.add(textFieldidClient);
        textFieldidClient.setColumns(10);


        JLabel lblidProduct = new JLabel("id Product");
        lblidProduct.setBounds(195, 60, 100, 13);
        contentPane.add(lblidProduct);


        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(198, 158, 100, 13);
        contentPane.add(lblQuantity);

        textFieldidProduct = new JTextField();
        textFieldidProduct.setColumns(10);
        textFieldidProduct.setBounds(195, 76, 122, 19);
        contentPane.add(textFieldidProduct);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setColumns(10);
        textFieldQuantity.setBounds(195, 181, 122, 19);
        contentPane.add(textFieldQuantity);

        errorMessage = new JLabel("");
        errorMessage.setBounds(198, 250, 300, 13);
        contentPane.add(errorMessage);
        errorMessage.setVisible(false);

        refresh();
    }

    public JTextField getTextFieldidClient() {
        return textFieldidClient;
    }

    public JTextField getTextFieldidProduct() {
        return textFieldidProduct;
    }

    public JTextField getTextFieldQuantity() {
        return textFieldQuantity;
    }

    public JButton getBtnAddOrder() {
        return btnAddOrder;
    }

    public void error(String message){
        errorMessage.setText(message);
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(true);
    }

    public void success(){
        errorMessage.setText("Successful operation!");
        errorMessage.setForeground(Color.BLACK);
        errorMessage.setVisible(true);
    }

    public void refresh(){
        errorMessage.setVisible(false);
        JTable table=abstractTable.createTable(orderDAO.findAll());
        table.setBounds(0,0,284, 173);
        scrollPane.setViewportView(table);
        revalidate();
        repaint();
    }
}

