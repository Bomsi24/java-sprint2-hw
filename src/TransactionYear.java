

public class TransactionYear {


    String month;
    int amount;
    boolean isExpense;


    public TransactionYear(String month, int amount, boolean isExpense) {//Год
        this.isExpense = isExpense;
        this.month = month;
        this.amount = amount;
    }

}