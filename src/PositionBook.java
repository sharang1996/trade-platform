import exception.UnsupportedInputFormatException;
import service.*;

import java.util.*;

public class PositionBook {

    Set<String> cancelledIdSet = new HashSet<>();

    public static void main(String[] args) throws UnsupportedInputFormatException {

        //If using a framework based on spring, the service dependencies will be autowired(dependency inversion)
        PositionBook positionBook = new PositionBook();
        Scanner scanner = new Scanner(System.in);
        RecordsParser parser = new TradeRecordsParserImpl();
        Printer printer = new AggregatedTradePositionsPrinter();
        ValidationService validationService = new ValidationService();

        ArrayList<String[]> rows = new ArrayList<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if ("".equals(line)) {
                break;
            }
            String[] row = line.split("\\s+");
            if(!validationService.isValidRow(row)) throw new UnsupportedInputFormatException();

            //Identifying cancelled trade events when accepting inputs
            if(!positionBook.cancelledIdSet.contains(row[0]) && !row[1].equals("CANCEL")) rows.add(row);
            else positionBook.cancelledIdSet.add(row[0]);
        }

        Map<String, Integer> accountSecurityCount = parser.parse(positionBook.cancelledIdSet, rows);
        printer.display(accountSecurityCount);
    }
}