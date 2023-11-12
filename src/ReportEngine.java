import java.util.ArrayList;

public class ReportEngine { //механизм отчетов
    public FileReader fileReader = new FileReader();

    public ArrayList<TransactionMonth> month(String path) { //заполение элементов
        ArrayList<String> content = fileReader.readFileContents(path);
        ArrayList<TransactionMonth> monthReport = new ArrayList<>();

        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] lineContents = line.split(",");
            String itemName = lineContents[0];
            boolean isExpense = Boolean.parseBoolean(lineContents[1]);
            int quantity = Integer.parseInt(lineContents[2]);
            int unitPrice = Integer.parseInt(lineContents[3]);

            TransactionMonth transaction = new TransactionMonth(itemName, isExpense, quantity, unitPrice);
            monthReport.add(transaction);
        }
        return monthReport;
    }

    public ArrayList<TransactionYear> year(String path) { //заполение элементов
        ArrayList<String> content = fileReader.readFileContents(path);
        ArrayList<TransactionYear> yearReport = new ArrayList<>();

        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] lineContents = line.split(",");
            String month = lineContents[0];
            int amount = Integer.parseInt(lineContents[1]);
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);

            TransactionYear transaction = new TransactionYear(month, amount, isExpense);
            yearReport.add(transaction);
        }
        return yearReport;
    }

}
