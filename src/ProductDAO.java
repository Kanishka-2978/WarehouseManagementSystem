import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDAO {

    // Add Product
    public void addProduct(String name, String category,
                           double price, int quantity, int supplierId) {

        String sql = "INSERT INTO product " +
                     "(product_name, category, price, quantity, supplier_id) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            ps.setInt(5, supplierId);

            ps.executeUpdate();
            System.out.println("Product added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Products
    public void viewProducts() {

        String sql = "SELECT * FROM product";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Product ID: " +
                        rs.getInt("product_id"));
                System.out.println("Name: " +
                        rs.getString("product_name"));
                System.out.println("Category: " +
                        rs.getString("category"));
                System.out.println("Price: " +
                        rs.getDouble("price"));
                System.out.println("Quantity: " +
                        rs.getInt("quantity"));
                System.out.println("Supplier ID: " +
                        rs.getInt("supplier_id"));
                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update Product
    public void updateProduct(int id, double price, int quantity) {

        String sql = "UPDATE product SET price = ?, quantity = ? " +
                     "WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, price);
            ps.setInt(2, quantity);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Product updated successfully!");
            else
                System.out.println("Product not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Product
    public void deleteProduct(int id) {

        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Product deleted successfully!");
            else
                System.out.println("Product not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// Low Stock Alert
    public void lowStockAlert() {

        String sql = "SELECT * FROM product WHERE quantity <= 10";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== LOW STOCK PRODUCTS =====");

            while (rs.next()) {
                found = true;

                System.out.println("Product ID: " +
                        rs.getInt("product_id"));

                System.out.println("Product Name: " +
                        rs.getString("product_name"));

                System.out.println("Category: " +
                        rs.getString("category"));

                System.out.println("Quantity: " +
                        rs.getInt("quantity"));

                System.out.println("-----------------------------");
            }

            if (!found) {
                System.out.println("No low-stock products!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// Search Product
public void searchProduct(int productId) {

    String sql = "SELECT * FROM product WHERE product_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            System.out.println("\n===== PRODUCT DETAILS =====");
            System.out.println("Product ID: " +
                    rs.getInt("product_id"));
            System.out.println("Product Name: " +
                    rs.getString("product_name"));
            System.out.println("Category: " +
                    rs.getString("category"));
            System.out.println("Price: " +
                    rs.getDouble("price"));
            System.out.println("Quantity: " +
                    rs.getInt("quantity"));
            System.out.println("Supplier ID: " +
                    rs.getInt("supplier_id"));

        } else {
            System.out.println("Product not found!");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}// Inventory Report
public void inventoryReport() {

    String sql = "SELECT COUNT(*) AS total_products, " +
                 "COALESCE(SUM(quantity), 0) AS total_stock, " +
                 "COALESCE(SUM(price * quantity), 0) AS total_value, " +
                 "SUM(CASE WHEN quantity <= 10 THEN 1 ELSE 0 END) AS low_stock " +
                 "FROM product";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {

            System.out.println("\n===== INVENTORY REPORT =====");

            System.out.println("Total Products: " +
                    rs.getInt("total_products"));

            System.out.println("Total Stock: " +
                    rs.getInt("total_stock"));

            System.out.println("Total Inventory Value: ₹" +
                    rs.getDouble("total_value"));

            System.out.println("Low Stock Products: " +
                    rs.getInt("low_stock"));

            System.out.println("============================");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
