import exception.UnsupportedInputFormatException;
import service.RecordsParser;
import service.TradeRecordsParserImpl;

import java.util.*;
import java.util.stream.Collectors;

public class PositionBook {

    Set<String> cancelledIdSet = new HashSet<>();

    public static void main(String[] args) throws UnsupportedInputFormatException {

        PositionBook positionBook = new PositionBook();
        Scanner scanner = new Scanner(System.in);
        RecordsParser parser = new TradeRecordsParserImpl();

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

        for(String key : accountSecurityCount.keySet().stream().sorted().collect(Collectors.toList())){
            System.out.println(key.split("_")[0] + " " +
                    key.split("_")[1] + " "+
                    accountSecurityCount.get(key));
        }

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