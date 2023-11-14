import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static String printCompletionOperation = "Операция завершена";

    public static void main(String[] args) {

        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();


        while (true) {
            System.out.println();//пустая строка для удобства чтения
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();

            if (command == 1) {
                if(monthlyReport.listOfMonths.isEmpty()) { // проверка что бы не дублировались отчеты
                    monthlyReport.listOfMonths.add("m.202101.csv");
                    monthlyReport.listOfMonths.add("m.202102.csv");
                    monthlyReport.listOfMonths.add("m.202103.csv");
                }

                for (int i = 0; i < monthlyReport.listOfMonths.size(); i++) {// заполненение отчета
                    if (!monthlyReport.report.containsKey(monthlyReport.listOfMonths.get(i))) { //проверка данных отчетов нет
                        monthlyReport.loadMonth(monthlyReport.MONTHS_NUMBER[i], monthlyReport.listOfMonths.get(i));
                    }
                }
                System.out.println(printCompletionOperation);
            } else if (command == 2) {
                if(yearlyReport.listOfYears.isEmpty()) {// проверка что бы не дублировались отчеты
                    yearlyReport.yearsNumber.add("2021");
                    yearlyReport.listOfYears.add("y.2021.csv");
                }

                for (int i = 0; i < yearlyReport.listOfYears.size(); i++) {// заполненение отчета
                    if(!yearlyReport.report.containsKey(yearlyReport.yearsNumber.get(i))) //проверка данных отчетов нет
                        yearlyReport.loadYear(yearlyReport.yearsNumber.get(i), yearlyReport.listOfYears.get(i));
                }
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
                        System.out.println("Несоответствие данных по доходам: " + monthlyReport.MONTHS[index - 1]);
                    }
                }
                if (!monthTotalPerYear.dataReconciliationExpense().isEmpty()) {
                    for (String month : monthTotalPerYear.dataReconciliationExpense()) {
                        int index = Integer.parseInt(month);
                        System.out.println("Несоответствие данных расходам: " + monthlyReport.MONTHS[index - 1]);
                    }
                }

                System.out.println(printCompletionOperation);

            } else if (command == 4) {
                if (!monthlyReport.isCheck()) {
                    System.out.println("Считайте месячные отчеты");
                    continue;
                }

                for (int i = 0; i < monthlyReport.listOfMonths.size(); i++) {//Вывод информации по месяцам
                    System.out.println(monthlyReport.MONTHS[i]);
                    for (String month : monthlyReport.getTopExpenses(monthlyReport.MONTHS_NUMBER[i]).keySet()) {
                        System.out.println("Самый большой расход:");
                        System.out.println("Категория: " + month);
                        System.out.println("Расход: " + monthlyReport.getTopExpenses(monthlyReport.MONTHS_NUMBER[i]).get(month));
                    }
                    for (String month : monthlyReport.getTopIncome(monthlyReport.MONTHS_NUMBER[i]).keySet()) {
                        System.out.println("Самый большой доход:");
                        System.out.println("Категория: " + month);
                        System.out.println("Доход: " + monthlyReport.getTopIncome(monthlyReport.MONTHS_NUMBER[i]).get(month));
                    }
                }
                System.out.println(printCompletionOperation);

            } else if (command == 5) {
                if (!yearlyReport.isCheck()) {
                    System.out.println("Считайте годовой отчет");
                    continue;
                }
                for (int i = 0; i < yearlyReport.yearsNumber.size(); i++) {
                    System.out.println("Год " + yearlyReport.yearsNumber.get(i));
                    for (String month : yearlyReport.getProfitableMonth(yearlyReport.yearsNumber.get(i)).keySet()) { // доходы по месяцам
                        int monthNumber = Integer.parseInt(month);
                        System.out.println("Месяц: " + monthlyReport.MONTHS[monthNumber - 1]);
                        System.out.println("Прибыль: " + yearlyReport.getProfitableMonth(yearlyReport.yearsNumber.get(i)).get(month));
                    }
                    System.out.println("Cредний расход за все имеющиеся операции в году: "
                            + yearlyReport.getAverageExpenses(yearlyReport.yearsNumber.get(i)));
                    System.out.println("Cредний доход за все имеющиеся операции в году: "
                            + yearlyReport.getAverageIncome(yearlyReport.yearsNumber.get(i)));
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

