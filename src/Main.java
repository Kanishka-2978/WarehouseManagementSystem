import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductDAO productDAO = new ProductDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        StockTransactionDAO stockDAO = new StockTransactionDAO();

        while (true) {

            System.out.println("\n===== WAREHOUSE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Add Supplier");
            System.out.println("7. View Suppliers");
            System.out.println("8. Add Warehouse");
            System.out.println("9. View Warehouses");
            System.out.println("10. Stock In");
            System.out.println("11. Stock Out");
            System.out.println("12. View Transactions");
            System.out.println("13. Low Stock Alert");
            System.out.println("14. Inventory Report");
            System.out.println("15. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Product Name: ");
                    String name = sc.next();

                    System.out.print("Enter Category: ");
                    String category = sc.next();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Supplier ID: ");
                    int supplierId = sc.nextInt();

                    productDAO.addProduct(
                            name, category, price, quantity, supplierId);
                    break;

                case 2:
                    productDAO.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID to search: ");
                    int searchId = sc.nextInt();

                    productDAO.searchProduct(searchId);
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();

                    productDAO.updateProduct(
                            updateId, newPrice, newQuantity);
                    break;

                case 5:
                    System.out.print("Enter Product ID: ");
                    int deleteId = sc.nextInt();

                    productDAO.deleteProduct(deleteId);
                    break;

                case 6:
                    System.out.print("Enter Supplier Name: ");
                    String supplierName = sc.next();

                    System.out.print("Enter Phone: ");
                    String phone = sc.next();

                    System.out.print("Enter Email: ");
                    String email = sc.next();

                    supplierDAO.addSupplier(
                            supplierName, phone, email);
                    break;

                case 7:
                    supplierDAO.viewSuppliers();
                    break;

                case 8:
                    System.out.print("Enter Warehouse Name: ");
                    String warehouseName = sc.next();

                    System.out.print("Enter Location: ");
                    String location = sc.next();

                    warehouseDAO.addWarehouse(
                            warehouseName, location);
                    break;

                case 9:
                    warehouseDAO.viewWarehouses();
                    break;

                case 10:
                    System.out.print("Enter Product ID: ");
                    int inProductId = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int inQuantity = sc.nextInt();

                    stockDAO.processStock(
                            inProductId, "IN", inQuantity);
                    break;

                case 11:
                    System.out.print("Enter Product ID: ");
                    int outProductId = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int outQuantity = sc.nextInt();

                    stockDAO.processStock(
                            outProductId, "OUT", outQuantity);
                    break;

                case 12:
                    stockDAO.viewTransactions();
                    break;

                case 13:
                    productDAO.lowStockAlert();
                    break;

                case 14:
                    productDAO.inventoryReport();
                    break;

                case 15:
                    System.out.println(
                            "Thank you for using Warehouse Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}