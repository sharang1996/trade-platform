import exception.UnsupportedInputFormatException;
import service.AggregatedTradePositionsPrinter;
import service.Printer;
import service.RecordsParser;
import service.TradeRecordsParserImpl;

import java.util.*;

public class PositionBook {

    Set<String> cancelledIdSet = new HashSet<>();

    public static void main(String[] args) throws UnsupportedInputFormatException {

        //If using a framework based on spring, the service dependencies will be autowired(dependency inversion)
        PositionBook positionBook = new PositionBook();
        Scanner scanner = new Scanner(System.in);
        RecordsParser parser = new TradeRecordsParserImpl();
        Printer printer = new AggregatedTradePositionsPrinter();

        ArrayList<String[]> rows = new ArrayList<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if ("".equals(line)) {
                break;
            }
            String[] row = line.split("\\s+");
            if(!positionBook.isValidRow(row)) throw new UnsupportedInputFormatException();

            //Identifying cancelled trade events when accepting inputs
            if(!positionBook.cancelledIdSet.contains(row[0]) && !row[1].equals("CANCEL")) rows.add(row);
            else positionBook.cancelledIdSet.add(row[0]);
        }

        Map<String, Integer> accountSecurityCount = parser.parse(positionBook.cancelledIdSet, rows);
        printer.display(accountSecurityCount);
    }


    private boolean isValidRow(String[] row) {
        if(row.length != 5) return false;

        //Other validations around field values
        return isValidTradeId(row[0]) &&
                isValidTradeEvent(row[1]) &&
                isValidAccount(row[2]) &&
                isValidSecurity(row[3]) &&
                isValidQuantity(row[4]);
    }

    private boolean isValidQuantity(String columnValue) {
        //ToDo: Add validation around the quantity field
        return true;
    }

    private boolean isValidSecurity(String columnValue) {
        //ToDo: Add validation around the Security field
        return true;
    }

    private boolean isValidAccount(String columnValue) {
        //ToDo: Add validation around the Account field
        return true;
    }

    private boolean isValidTradeEvent(String columnValue) {
        //ToDo: Add validation around the Event field
        return true;
    }

    private boolean isValidTradeId(String columnValue) {
        //ToDo: Add validation around the id field
        return true;
    }
}