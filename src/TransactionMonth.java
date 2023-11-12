public class TransactionMonth {
    String itemName;
    boolean isExpense;
    int quantity;
    int unitPrice;

    public TransactionMonth(String itemName, boolean isExpense, int quantity, int unitPrice) {// Месяц
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
