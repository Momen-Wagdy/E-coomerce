import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EcommerceSystem {
    private static List<Product> products = new ArrayList<>();
    private static Customer customer;
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        createGUI();
    }

    private static void createGUI() {
        JFrame frame = new JFrame("E-Commerce System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JTextField customerIdField = new JTextField();
        JTextField customerNameField = new JTextField();
        JTextField customerAddressField = new JTextField();

        JButton createCustomerButton = new JButton("Create Customer");
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerId = Integer.parseInt(customerIdField.getText());
                String customerName = customerNameField.getText();
                String customerAddress = customerAddressField.getText();

                customer = new Customer();
                customer.setCustomerId(customerId);
                customer.setName(customerName);
                customer.setAddress(customerAddress);
                cart.setCustomerId(customerId);

                JOptionPane.showMessageDialog(frame, "Customer Created!");
            }
        });

        panel.add(new JLabel("Customer ID:"));
        panel.add(customerIdField);
        panel.add(new JLabel("Customer Name:"));
        panel.add(customerNameField);
        panel.add(new JLabel("Customer Address:"));
        panel.add(customerAddressField);
        panel.add(createCustomerButton);

        JButton addProductButton = new JButton("Add Product to Cart");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] productOptions = {"Electronic Product", "Clothing Product", "Book Product"};
                String productType = (String) JOptionPane.showInputDialog(frame, "Select Product Type", "Product Type", JOptionPane.QUESTION_MESSAGE, null, productOptions, productOptions[0]);

                if (productType != null) {
                    JPanel productPanel = new JPanel(new GridLayout(0, 2));
                    JTextField productIdField = new JTextField();
                    JTextField productNameField = new JTextField();
                    JTextField productPriceField = new JTextField();

                    productPanel.add(new JLabel("Product ID:"));
                    productPanel.add(productIdField);
                    productPanel.add(new JLabel("Product Name:"));
                    productPanel.add(productNameField);
                    productPanel.add(new JLabel("Product Price:"));
                    productPanel.add(productPriceField);

                    if (productType.equals("Electronic Product")) {
                        JTextField brandField = new JTextField();
                        JTextField warrantyField = new JTextField();
                        productPanel.add(new JLabel("Brand:"));
                        productPanel.add(brandField);
                        productPanel.add(new JLabel("Warranty Period:"));
                        productPanel.add(warrantyField);

                        int result = JOptionPane.showConfirmDialog(frame, productPanel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            ElectronicProduct product = new ElectronicProduct();
                            product.setProductId(Integer.parseInt(productIdField.getText()));
                            product.setName(productNameField.getText());
                            product.setPrice(Float.parseFloat(productPriceField.getText()));
                            product.setBrand(brandField.getText());
                            product.setWarrantyPeriod(Integer.parseInt(warrantyField.getText()));

                            cart.addProduct(product);
                            JOptionPane.showMessageDialog(frame, "Product Added to Cart!");
                        }
                    } else if (productType.equals("Clothing Product")) {
                        JTextField sizeField = new JTextField();
                        JTextField fabricField = new JTextField();
                        productPanel.add(new JLabel("Size:"));
                        productPanel.add(sizeField);
                        productPanel.add(new JLabel("Fabric:"));
                        productPanel.add(fabricField);

                        int result = JOptionPane.showConfirmDialog(frame, productPanel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            ClothingProduct product = new ClothingProduct();
                            product.setProductId(Integer.parseInt(productIdField.getText()));
                            product.setName(productNameField.getText());
                            product.setPrice(Float.parseFloat(productPriceField.getText()));
                            product.setSize(sizeField.getText());
                            product.setFabric(fabricField.getText());

                            cart.addProduct(product);
                            JOptionPane.showMessageDialog(frame, "Product Added to Cart!");
                        }
                    } else if (productType.equals("Book Product")) {
                        JTextField authorField = new JTextField();
                        JTextField publisherField = new JTextField();
                        productPanel.add(new JLabel("Author:"));
                        productPanel.add(authorField);
                        productPanel.add(new JLabel("Publisher:"));
                        productPanel.add(publisherField);

                        int result = JOptionPane.showConfirmDialog(frame, productPanel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            BookProduct product = new BookProduct();
                            product.setProductId(Integer.parseInt(productIdField.getText()));
                            product.setName(productNameField.getText());
                            product.setPrice(Float.parseFloat(productPriceField.getText()));
                            product.setAuthor(authorField.getText());
                            product.setPublisher(publisherField.getText());

                            cart.addProduct(product);
                            JOptionPane.showMessageDialog(frame, "Product Added to Cart!");
                        }
                    }
                }
            }
        });

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                order.setCustomerId(customer.getCustomerId());
                order.setOrderId((int) (Math.random() * 1000));
                order.setProducts(cart.getProducts());
                order.setTotalPrice(cart.calculatePrice());

                order.printOrderInfo();
                JOptionPane.showMessageDialog(frame, "Order Placed!\nTotal Price: $" + order.getTotalPrice());
            }
        });

        panel.add(addProductButton);
        panel.add(placeOrderButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
