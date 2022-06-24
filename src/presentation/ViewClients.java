package presentation;

import dataAccessLayer.ClientDAO;
import model.Client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewClients extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldLastName;
    private JTextField textFieldAge;
    private JTextField textFieldAddress;
    private JTextField textFieldEmail;
    private JTextField textFieldTel;
    private JTextField textFieldCity;
    private JTextField textFieldFirstName;
    private JTextField textFieldid;
    private JLabel errorMessage;

    JButton btnAddClient;
    JButton btnUpdateClient;
    JButton btnDeleteClient;
    JButton btnFindClient;
    JScrollPane scrollPane;
    ClientDAO clientDAO=new ClientDAO();
    AbstractTable<Client> abstractTable=new AbstractTable<Client>();

    /**
     * Create the frame.
     */
    public ViewClients() {
        setVisible(false);
        setTitle("Clients management");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblYouCan = new JLabel("You can:");
        lblYouCan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblYouCan.setBounds(29, 25, 55, 25);
        contentPane.add(lblYouCan);

        btnAddClient = new JButton("Add client");
        btnAddClient.setBounds(10, 76, 150, 21);
        contentPane.add(btnAddClient);

        btnUpdateClient = new JButton("Update client");
        btnUpdateClient.setBounds(10, 128, 150, 21);
        contentPane.add(btnUpdateClient);

        btnDeleteClient = new JButton("Delete client");
        btnDeleteClient.setBounds(10, 176, 150, 21);
        contentPane.add(btnDeleteClient);

        scrollPane=new JScrollPane();
        scrollPane.setBounds(393, 60, 561, 350);
        contentPane.add(scrollPane);
        scrollPane.setVisible(true);

        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(194, 111, 100, 13);
        contentPane.add(lblLastName);

        textFieldLastName = new JTextField();
        textFieldLastName.setBounds(194, 134, 134, 19);
        contentPane.add(textFieldLastName);
        textFieldLastName.setColumns(10);

        btnFindClient = new JButton("Find client");
        btnFindClient.setBounds(10, 223, 150, 21);
        contentPane.add(btnFindClient);

        textFieldAge = new JTextField();
        textFieldAge.setColumns(10);
        textFieldAge.setBounds(194, 186, 134, 19);
        contentPane.add(textFieldAge);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(194, 163, 45, 13);
        contentPane.add(lblAge);

        textFieldAddress = new JTextField();
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(194, 232, 134, 19);
        contentPane.add(textFieldAddress);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(197, 209, 100, 13);
        contentPane.add(lblAddress);

        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(194, 284, 134, 19);
        contentPane.add(textFieldEmail);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(197, 261, 45, 13);
        contentPane.add(lblEmail);

        textFieldTel = new JTextField();
        textFieldTel.setColumns(10);
        textFieldTel.setBounds(194, 336, 134, 19);
        contentPane.add(textFieldTel);

        JLabel lblTel = new JLabel("Telephone Number");
        lblTel.setBounds(197, 313, 150, 13);
        contentPane.add(lblTel);

        textFieldCity = new JTextField();
        textFieldCity.setColumns(10);
        textFieldCity.setBounds(194, 388, 134, 19);
        contentPane.add(textFieldCity);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(197, 365, 45, 13);
        contentPane.add(lblCity);

        textFieldFirstName = new JTextField();
        textFieldFirstName.setColumns(10);
        textFieldFirstName.setBounds(194, 83, 134, 19);
        contentPane.add(textFieldFirstName);

        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(197, 60, 63, 13);
        contentPane.add(lblFirstName);

        JLabel lblid = new JLabel("id");
        lblid.setBounds(197, 10, 63, 13);
        contentPane.add(lblid);

        textFieldid = new JTextField();
        textFieldid.setColumns(10);
        textFieldid.setBounds(194, 33, 134, 19);
        contentPane.add(textFieldid);

        errorMessage= new JLabel("");
        errorMessage.setForeground(Color.RED);
        errorMessage.setBounds(197, 420, 300, 13);
        errorMessage.setVisible(false);
        contentPane.add(errorMessage);
        refresh();
    }

    public JTextField getTextFieldLastName() {
        return textFieldLastName;
    }

    public void setTextFieldLastName(JTextField textFieldLastName) {
        this.textFieldLastName = textFieldLastName;
    }

    public JTextField getTextFieldAge() {
        return textFieldAge;
    }

    public void setTextFieldAge(JTextField textFieldAge) {
        this.textFieldAge = textFieldAge;
    }

    public JTextField getTextFieldAddress() {
        return textFieldAddress;
    }

    public void setTextFieldAddress(JTextField textFieldAddress) {
        this.textFieldAddress = textFieldAddress;
    }

    public JTextField getTextFieldEmail() {
        return textFieldEmail;
    }

    public void setTextFieldEmail(JTextField textFieldEmail) {
        this.textFieldEmail = textFieldEmail;
    }

    public JTextField getTextFieldTel() {
        return textFieldTel;
    }

    public void setTextFieldTel(JTextField textFieldTel) {
        this.textFieldTel = textFieldTel;
    }

    public JTextField getTextFieldCity() {
        return textFieldCity;
    }

    public void setTextFieldCity(JTextField textFieldCity) {
        this.textFieldCity = textFieldCity;
    }

    public JTextField getTextFieldFirstName() {
        return textFieldFirstName;
    }

    public void setTextFieldFirstName(JTextField textFieldFirstName) {
        this.textFieldFirstName = textFieldFirstName;
    }

    public JTextField getTextFieldid() {
        return textFieldid;
    }

    public void setTextFieldid(JTextField textFieldid) {
        this.textFieldid = textFieldid;
    }

    public JButton getBtnAddClient() {
        return btnAddClient;
    }

    public void setBtnAddClient(JButton btnAddClient) {
        this.btnAddClient = btnAddClient;
    }

    public JButton getBtnUpdateClient() {
        return btnUpdateClient;
    }

    public void setBtnUpdateClient(JButton btnUpdateClient) {
        this.btnUpdateClient = btnUpdateClient;
    }

    public JButton getBtnDeleteClient() {
        return btnDeleteClient;
    }

    public void setBtnDeleteClient(JButton btnDeleteClient) {
        this.btnDeleteClient = btnDeleteClient;
    }

    public JButton getBtnFindClient() {
        return btnFindClient;
    }

    public void setBtnFindClient(JButton btnFindClient) {
        this.btnFindClient = btnFindClient;
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
        JTable table=abstractTable.createTable(clientDAO.findAll());
        table.setBounds(0,0,561, 350);
        scrollPane.setViewportView(table);
        revalidate();
        repaint();
    }
}
