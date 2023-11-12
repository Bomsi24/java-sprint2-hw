import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport { //годовой отчет

    ReportEngine reportEngine = new ReportEngine();

    public HashMap<String, ArrayList<TransactionYear>> report = new HashMap<>();
    private boolean isYear = false;

    public void loadYear(String yearName, String yearReport) {
        this.report.put(yearName, reportEngine.year(yearReport));
        this.isYear = true;

    }

    public HashMap<String, Integer> getProfitableMonth(String yearName) {// самый прибыльный товар
        ArrayList<TransactionYear> year = report.get(yearName);
        HashMap<String, Integer> profit = new HashMap<>();
        HashMap<String, Integer> income = new HashMap<>();
        HashMap<String, Integer> expenses = new HashMap<>();
        for (TransactionYear transactionYear : year) {
            if (!transactionYear.isExpense) {
                income.put(transactionYear.month, transactionYear.amount);
            } else {
                expenses.put(transactionYear.month, transactionYear.amount);
            }
        }
        for (String key : income.keySet()) {
            profit.put(key, income.get(key) - expenses.get(key));
        }
        return profit;

    }

    public int getAverageExpenses(String yearName) { // средний расход за все имеющиеся операции в году
        ArrayList<TransactionYear> year = report.get(yearName);
        ArrayList<Integer> average = new ArrayList<>();

        for (TransactionYear transaction : year) {// проходимся по спику  transactions
            if (transaction.isExpense) { // Проверяем является ли расходом
                average.add(transaction.amount);

            }
        }
        int averageExpense = 0;
        int sum = 0;
        for (Integer i : average) {
            sum += i;
        }
        return averageExpense = sum / average.size();
    }

    public int getAverageIncome(String yearName) { // средний доход за все имеющиеся операции в году.
        ArrayList<TransactionYear> year = report.get(yearName);

        ArrayList<Integer> average = new ArrayList<>();
        int averageIncome = 0;
        int sum = 0;
        for (TransactionYear transaction : year) {// проходимся по спику  transactions
            if (!transaction.isExpense) { // Проверяем является ли доходом
                average.add(transaction.amount);
            }
            for (Integer i : average) {
                sum += i;
            }
        }
        return averageIncome = sum / average.size();
    }

    public HashMap<String, Integer> getIncome(String yearName) {
        ArrayList<TransactionYear> year = report.get(yearName);
        HashMap<String, Integer> income = new HashMap<>();

        for (TransactionYear transactionYear : year) {
            if (!transactionYear.isExpense) {
                income.put(transactionYear.month, transactionYear.amount);
            }

        }
        return income;
    }

    public HashMap<String, Integer> getExpenses(String yearName) {
        ArrayList<TransactionYear> year = report.get(yearName);
        HashMap<String, Integer> expenses = new HashMap<>();

        for (TransactionYear transactionYear : year) {
            if (transactionYear.isExpense) {
                expenses.put(transactionYear.month, transactionYear.amount);
            }
        }
        return expenses;
    }

    public boolean isCheck() {
        return isYear;
    }

}
