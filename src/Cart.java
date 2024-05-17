import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int customerId;
    private int nProducts;
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = Math.abs(customerId);
    }

    public int getNProducts() {
        return nProducts;
    }

    public void setNProducts(int nProducts) {
        this.nProducts = Math.abs(nProducts);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        nProducts = products.size();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        nProducts = products.size();
    }

    public float calculatePrice() {
        float total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void placeOrder() {
        // Implement order placement logic
    }
}
