package presentation;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class View extends JFrame {
    JButton btnViewClients;
    JButton btnViewOrders;
    JButton btnViewProducts;

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public View() {
        setTitle("Order management application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 466, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnViewClients = new JButton("View Clients");
        btnViewClients.setBounds(134, 73, 185, 21);
        contentPane.add(btnViewClients);

        btnViewOrders = new JButton("View Orders");
        btnViewOrders.setBounds(134, 164, 185, 21);
        contentPane.add(btnViewOrders);

        btnViewProducts = new JButton("View Products");
        btnViewProducts.setBounds(134, 121, 185, 21);
        contentPane.add(btnViewProducts);
        setVisible(true);
    }

    public JButton getBtnViewClients() {
        return btnViewClients;
    }

    public JButton getBtnViewOrders() {
        return btnViewOrders;
    }

    public JButton getBtnViewProducts() {
        return btnViewProducts;
    }
}





