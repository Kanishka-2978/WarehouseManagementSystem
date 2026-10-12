import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WarehouseDAO {

    // Add Warehouse
    public void addWarehouse(String name, String location) {

        String sql = "INSERT INTO warehouse " +
                     "(warehouse_name, location) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, location);

            ps.executeUpdate();

            System.out.println("Warehouse added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Warehouses
    public void viewWarehouses() {

        String sql = "SELECT * FROM warehouse";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println("Warehouse ID: " +
                        rs.getInt("warehouse_id"));

                System.out.println("Warehouse Name: " +
                        rs.getString("warehouse_name"));

                System.out.println("Location: " +
                        rs.getString("location"));

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}