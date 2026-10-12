public class Product {
    private int productId;
    private String productName;
    private String category;
    private double price;
    private int quantity;
    private int supplierId;

    public Product(int productId, String productName, String category,
                   double price, int quantity, int supplierId) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void displayProduct() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Category: " + category);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Supplier ID: " + supplierId);
        System.out.println("-----------------------------");
    }
}
