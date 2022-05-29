package service;

import java.util.regex.Pattern;

public class ValidationService {
    public boolean isValidRow(String[] row) {
        if(row.length != 5) return false;

        //Other validations around field values
        return isValidTradeId(row[0]) &&
                isValidTradeEvent(row[1]) &&
                isValidAccount(row[2]) &&
                isValidSecurity(row[3]) &&
                isValidQuantity(row[4]);
    }

    public boolean isValidQuantity(String columnValue) {
        try{
            int quantity = Integer.parseInt(columnValue);
            if(quantity < 0) return false;
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public boolean isValidSecurity(String columnValue) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasSpecialChar = p.matcher(columnValue).find();
        return !hasSpecialChar && columnValue.length() < 10;
    }

    public boolean isValidAccount(String columnValue) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasSpecialChar = p.matcher(columnValue).find();
        return !hasSpecialChar && columnValue.length() == 4;
    }

    public boolean isValidTradeEvent(String columnValue) {
        return columnValue.equals("BUY") || columnValue.equals("SELL") || columnValue.equals("CANCEL");
    }

    public boolean isValidTradeId(String columnValue) {
        try{
            int id = Integer.parseInt(columnValue);
            if(id <= 0) return false;
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}
