public class StockTransaction {

    private int transactionId;
    private int productId;
    private String transactionType;
    private int quantity;

    public StockTransaction(int transactionId, int productId,
                            String transactionType, int quantity) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.transactionType = transactionType;
        this.quantity = quantity;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getProductId() {
        return productId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getQuantity() {
        return quantity;
    }
}