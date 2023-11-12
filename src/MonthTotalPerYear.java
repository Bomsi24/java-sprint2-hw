import java.util.ArrayList;
import java.util.HashMap;

public class MonthTotalPerYear { //Всего за месяц за год
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public MonthTotalPerYear(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public ArrayList<String> dataReconciliationIncome() {
        ArrayList<String> reportIncome = new ArrayList<>();
        HashMap<String, Integer> yearIncome = yearlyReport.getIncome("2021");
        HashMap<String, Integer> monthIncome = monthlyReport.getIncome();

        for (String year : yearIncome.keySet()) {
            for (String month : monthIncome.keySet()) {
                if (year.equals(month)) {
                    if (!yearIncome.get(year).equals(monthIncome.get(month))) {
                        reportIncome.add(month);
                    }
                }

            }
        }
        return reportIncome;
    }

    public ArrayList<String> dataReconciliationExpense() {
        ArrayList<String> reportExpense = new ArrayList<>();
        HashMap<String, Integer> yearExpense = yearlyReport.getExpenses("2021");
        HashMap<String, Integer> monthExpenses = monthlyReport.getExpenses();
        for (String year : yearExpense.keySet()) {
            for (String month : monthExpenses.keySet()) {
                if (year.equals(month)) {
                    if (!yearExpense.get(year).equals(monthExpenses.get(month))) {
                        reportExpense.add(month);
                    }
                }

            }
        }

        return reportExpense;
    }


}
