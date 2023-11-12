import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static String printCompletionOperation = "Операция завершена";

    public static void main(String[] args) {

        String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май",
                "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Декабрь"};

        String[] MONTHS_NUMBER = {"01", "02", "03", "04", "05", "06", "07", "08", "09", " 10", "11", "12"};

        ArrayList<String> yearsNumber = new ArrayList<>();

        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();

        ArrayList<String> monthReport = new ArrayList<>();
        ArrayList<String> yearReport = new ArrayList<>();

        monthReport.add("m.202101.csv");
        monthReport.add("m.202102.csv");
        monthReport.add("m.202103.csv");

        yearsNumber.add("2021");
        yearReport.add("y.2021.csv");


        while (true) {
            System.out.println();//пустая строка для удобства чтения
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();

            if (command == 1) {
                for (int i = 0; i < monthReport.size(); i++) {// заполненение отчета
                    monthlyReport.loadMonth(MONTHS_NUMBER[i], monthReport.get(i));
                }
                System.out.println(printCompletionOperation);
            } else if (command == 2) {
                yearlyReport.loadYear(yearsNumber.get(0), yearReport.get(0));

                System.out.println(printCompletionOperation);
            } else if (command == 3) {
                if ((!monthlyReport.isCheck()) || (!yearlyReport.isCheck())) {
                    System.out.println("Считайте годовой и месячные отчеты");
                    continue;
                }

                MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear(monthlyReport, yearlyReport);
                if (!monthTotalPerYear.dataReconciliationIncome().isEmpty()) {
                    for (String month : monthTotalPerYear.dataReconciliationIncome()) {
                        int index = Integer.parseInt(month);
                        System.out.println("Несоответствие данных по доходам: " + MONTHS[index - 1]);
                    }
                }
                if (!monthTotalPerYear.dataReconciliationExpense().isEmpty()) {
                    for (String month : monthTotalPerYear.dataReconciliationExpense()) {
                        int index = Integer.parseInt(month);
                        System.out.println("Несоответствие данных расходам: " + MONTHS[index - 1]);
                    }
                }

                System.out.println(printCompletionOperation);

            } else if (command == 4) {
                if (!monthlyReport.isCheck()) {
                    System.out.println("Считайте месячные отчеты");
                    continue;
                }

                for (int i = 0; i < monthReport.size(); i++) {//Вывод информации по месяцам
                    System.out.println(MONTHS[i]);
                    for (String month : monthlyReport.getTopExpenses(MONTHS_NUMBER[i]).keySet()) {
                        System.out.println("Самый большой расход:");
                        System.out.println("Категория: " + month);
                        System.out.println("Расход: " + monthlyReport.getTopExpenses(MONTHS_NUMBER[i]).get(month));
                    }
                    for (String month : monthlyReport.getTopIncome(MONTHS_NUMBER[i]).keySet()) {
                        System.out.println("Самый большой доход:");
                        System.out.println("Категория: " + month);
                        System.out.println("Доход: " + monthlyReport.getTopIncome(MONTHS_NUMBER[i]).get(month));
                    }
                }
                System.out.println(printCompletionOperation);

            } else if (command == 5) {
                if (!yearlyReport.isCheck()) {
                    System.out.println("Считайте годовой отчет");
                    continue;
                }
                for (int i = 0; i < yearsNumber.size(); i++) {
                    System.out.println("Год " + yearsNumber.get(i));
                    for (String month : yearlyReport.getProfitableMonth(yearsNumber.get(i)).keySet()) { // доходы по месяцам
                        int monthNumber = Integer.parseInt(month);
                        System.out.println("Месяц: " + MONTHS[monthNumber - 1]);
                        System.out.println("Прибыль: " + yearlyReport.getProfitableMonth(yearsNumber.get(i)).get(month));
                    }
                    System.out.println("Cредний расход за все имеющиеся операции в году: " + yearlyReport.getAverageExpenses(yearsNumber.get(i)));
                    System.out.println("Cредний доход за все имеющиеся операции в году: " + yearlyReport.getAverageIncome(yearsNumber.get(i)));
                }

                System.out.println(printCompletionOperation);

            } else if (command == 0) {
                System.out.println("Программа завершена.");
                scanner.close();
                break;

            } else {
                System.out.println("Неизвестная команда");
            }

        }


    }

    static void printMenu() {
        System.out.println("1-Считать все месячные отчеты");
        System.out.println("2-Считать годовой отчёт");
        System.out.println("3-Сверить отчёты ");
        System.out.println("4-Вывести информацию обо всех месячных отчётах ");
        System.out.println("5-Вывести информацию о годовом отчёте");
        System.out.println("0-Завершить программу");
    }
}

