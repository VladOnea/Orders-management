package presentation;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewProducts extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private JTextField textFieldStock;
    private JTextField textField;
    JButton btnAddProduct;
    JButton btnUpdateProduct;
    JButton btnDeleteProduct;
    JButton btnFindProduct;
    JLabel errorMessage;
    JScrollPane scrollPane;
    AbstractTable<Product> abstractTable=new AbstractTable<>();
    ProductDAO productDAO=new ProductDAO();

    /**
     * Create the frame.
     */
    public ViewProducts() {
        setVisible(false);
        setTitle("Products Management");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 1000, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblYouCan = new JLabel("You can:");
        lblYouCan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblYouCan.setBounds(29, 25, 55, 25);
        contentPane.add(lblYouCan);

        btnAddProduct = new JButton("Add product");
        btnAddProduct.setBounds(10, 76, 150, 21);
        contentPane.add(btnAddProduct);

        btnUpdateProduct = new JButton("Update product");
        btnUpdateProduct.setBounds(10, 128, 150, 21);
        contentPane.add(btnUpdateProduct);

        btnDeleteProduct = new JButton("Delete product");
        btnDeleteProduct.setBounds(10, 176, 150, 21);
        contentPane.add(btnDeleteProduct);

        scrollPane=new JScrollPane();
        scrollPane.setBounds(393, 60, 561, 260);
        contentPane.add(scrollPane);
        scrollPane.setVisible(true);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(200, 111, 55, 13);
        contentPane.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(200, 134, 125, 19);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);

        btnFindProduct = new JButton("Find product");
        btnFindProduct.setBounds(10, 223, 150, 21);
        contentPane.add(btnFindProduct);


        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(200, 163, 55, 13);
        contentPane.add(lblPrice);


        JLabel lblStock = new JLabel("Available Stock");
        lblStock.setBounds(203, 209, 150, 13);
        contentPane.add(lblStock);

        textFieldPrice = new JTextField();
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(200, 180, 125, 19);
        contentPane.add(textFieldPrice);

        textFieldStock = new JTextField();
        textFieldStock.setColumns(10);
        textFieldStock.setBounds(200, 232, 125, 19);
        contentPane.add(textFieldStock);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(200, 83, 125, 19);
        contentPane.add(textField);

        JLabel lblid = new JLabel("id");
        lblid.setBounds(200, 60, 55, 13);
        contentPane.add(lblid);

        errorMessage= new JLabel("");
        errorMessage.setBounds(200, 270, 300, 13);
        errorMessage.setVisible(false);
        contentPane.add(errorMessage);
        refresh();
    }

    public JTextField getTextFieldName() {
        return textFieldName;
    }

    public JTextField getTextFieldPrice() {
        return textFieldPrice;
    }

    public JTextField getTextFieldStock() {
        return textFieldStock;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getBtnAddProduct() {
        return btnAddProduct;
    }

    public JButton getBtnUpdateProduct() {
        return btnUpdateProduct;
    }

    public JButton getBtnDeleteProduct() {
        return btnDeleteProduct;
    }

    public JButton getBtnFindProduct() {
        return btnFindProduct;
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
        JTable table=abstractTable.createTable(productDAO.findAll());
        table.setBounds(0,0,561, 260);
        scrollPane.setViewportView(table);
        revalidate();
        repaint();
    }
}
