import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierDAO {

    // Add Supplier
    public void addSupplier(String name, String phone, String email) {

        String sql = "INSERT INTO supplier " +
                     "(supplier_name, phone, email) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);

            ps.executeUpdate();

            System.out.println("Supplier added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Suppliers
    public void viewSuppliers() {

        String sql = "SELECT * FROM supplier";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println("Supplier ID: " +
                        rs.getInt("supplier_id"));

                System.out.println("Name: " +
                        rs.getString("supplier_name"));

                System.out.println("Phone: " +
                        rs.getString("phone"));

                System.out.println("Email: " +
                        rs.getString("email"));

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}