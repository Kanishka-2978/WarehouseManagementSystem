public class Supplier {

    private int supplierId;
    private String supplierName;
    private String phone;
    private String email;

    public Supplier(int supplierId, String supplierName,
                    String phone, String email) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.phone = phone;
        this.email = email;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}