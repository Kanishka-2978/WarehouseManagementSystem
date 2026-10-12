import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockTransactionDAO {

    // Stock In / Stock Out
    public void processStock(int productId, String type, int quantity) {

        String checkSql = "SELECT quantity FROM product WHERE product_id = ?";
        String updateSql;

        if (type.equalsIgnoreCase("IN")) {
            updateSql = "UPDATE product SET quantity = quantity + ? " +
                        "WHERE product_id = ?";
        } else {
            updateSql = "UPDATE product SET quantity = quantity - ? " +
                        "WHERE product_id = ? AND quantity >= ?";
        }

        String transactionSql =
                "INSERT INTO stock_transactions " +
                "(product_id, transaction_type, quantity) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            // Check product
            try (PreparedStatement check =
                         con.prepareStatement(checkSql)) {

                check.setInt(1, productId);

                ResultSet rs = check.executeQuery();

                if (!rs.next()) {
                    System.out.println("Product not found!");
                    return;
                }
            }

            // Update product quantity
            try (PreparedStatement update =
                         con.prepareStatement(updateSql)) {

                update.setInt(1, quantity);
                update.setInt(2, productId);

                if (type.equalsIgnoreCase("OUT")) {
                    update.setInt(3, quantity);
                }

                int rows = update.executeUpdate();

                if (rows == 0) {
                    System.out.println("Insufficient stock!");
                    return;
                }
            }

            // Record transaction
            try (PreparedStatement transaction =
                         con.prepareStatement(transactionSql)) {

                transaction.setInt(1, productId);
                transaction.setString(2, type.toUpperCase());
                transaction.setInt(3, quantity);

                transaction.executeUpdate();
            }

            System.out.println("Stock " + type.toUpperCase() +
                               " completed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Transactions
    public void viewTransactions() {

        String sql = "SELECT * FROM stock_transactions";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println("Transaction ID: " +
                        rs.getInt("transaction_id"));

                System.out.println("Product ID: " +
                        rs.getInt("product_id"));

                System.out.println("Type: " +
                        rs.getString("transaction_type"));

                System.out.println("Quantity: " +
                        rs.getInt("quantity"));

                System.out.println("Date: " +
                        rs.getTimestamp("transaction_date"));

                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}