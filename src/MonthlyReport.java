import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport { //Ежемесячный отчет

    ReportEngine reportEngine = new ReportEngine();

    public HashMap<String, ArrayList<TransactionMonth>> report = new HashMap<>();
    private boolean isMonth = false;

    public void loadMonth(String monthName, String monthReport) {
        this.report.put(monthName, reportEngine.month(monthReport));
        this.isMonth = true;
    }

    public HashMap<String, Integer> getTopIncome(String monthName) { // самый большой доход
        HashMap<String, Integer> income = new HashMap<>();
        ArrayList<TransactionMonth> month = report.get(monthName);
        for (TransactionMonth transaction : month) {// проходимся по спику  transactions
            if (!transaction.isExpense) { // Проверяем является ли доходом
                income.put(transaction.itemName, (income.getOrDefault(transaction.itemName,
                        0) + transaction.quantity) * transaction.unitPrice); // обновялем Map
            }
        }
        String maxName = null;//город
        Integer maxQuantity = null;

        for (String itemName : income.keySet()) {// поиск максимального значения
            if (maxName == null) {
                maxName = itemName;
                continue;
            }
            if (income.get(maxName) < income.get(itemName)) {
                maxName = itemName;
                maxQuantity = income.get(maxName);

            }
            if (maxQuantity == null) {
                maxQuantity = income.get(maxName);
            }

        }
        income.clear();
        income.put(maxName, maxQuantity);
        return income;
    }

    public HashMap<String, Integer> getTopExpenses(String monthName) { // самая большая трата
        HashMap<String, Integer> expense = new HashMap<>();
        ArrayList<TransactionMonth> month = report.get(monthName);
        for (TransactionMonth transaction : month) {// проходимся по спику  transactions
            if (transaction.isExpense) { // Проверяем является ли расходом
                expense.put(transaction.itemName, (expense.getOrDefault(transaction.itemName,
                        0) + transaction.quantity) * transaction.unitPrice); //
            }
        }
        String maxName = null;//город
        Integer maxQuantity = null;

        for (String itemName : expense.keySet()) {
            if (maxName == null) {
                maxName = itemName;
                continue;
            }
            if (expense.get(maxName) < expense.get(itemName)) {
                maxName = itemName;
                maxQuantity = expense.get(maxName);

            }
            if (maxQuantity == null) {
                maxQuantity = expense.get(maxName);
            }

        }
        expense.clear();
        expense.put(maxName, maxQuantity);
        return expense;

    }

    public HashMap<String, Integer> getIncome() {// доходы по каждому месяцу
        HashMap<String, Integer> incomes = new HashMap<>();
        for (String mont : report.keySet()) {
            ArrayList<TransactionMonth> month = report.get(mont);
            int sum = 0;
            for (TransactionMonth transaction : month) {// проходимся по спику  transactions
                if (!transaction.isExpense) { // Проверяем является ли доходом
                    sum = sum + (transaction.quantity * transaction.unitPrice);
                }
            }
            incomes.put(mont, sum);

        }
        return incomes;
    }

    public HashMap<String, Integer> getExpenses() {// расходы по каждому месяцу
        HashMap<String, Integer> expenses = new HashMap<>();
        for (String mont : report.keySet()) {
            ArrayList<TransactionMonth> month = report.get(mont);
            int sum = 0;
            for (TransactionMonth transaction : month) {// проходимся по спику  transactions
                if (transaction.isExpense) { // Проверяем является ли расходом
                    sum = sum + (transaction.quantity * transaction.unitPrice);
                }
            }
            expenses.put(mont, sum);

        }
        return expenses;
    }

    public boolean isCheck() {
        return isMonth;
    }

}
