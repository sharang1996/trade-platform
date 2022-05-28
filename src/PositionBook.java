import exception.UnsupportedInputFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class PositionBook {


    public static void main(String[] args) throws UnsupportedInputFormatException {

        PositionBook positionBook = new PositionBook();
        Scanner s = new Scanner(System.in);

        ArrayList<String[]> rows = new ArrayList<>();

        while (s.hasNextLine()){
            String line = s.nextLine();
            if ("".equals(line)) {
                break;
            }
            String[] row = line.split("\\s+");
            if(!positionBook.isValidRow(row)) throw new UnsupportedInputFormatException();
            rows.add(row);
        }


    }

    private boolean isValidRow(String[] row) throws UnsupportedInputFormatException {
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