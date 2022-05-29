package service;

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
        //ToDo: Add validation around the Security field
        return true;
    }

    public boolean isValidAccount(String columnValue) {
        //ToDo: Add validation around the Account field
        return true;
    }

    public boolean isValidTradeEvent(String columnValue) {
        //ToDo: Add validation around the Event field
        return true;
    }

    public boolean isValidTradeId(String columnValue) {
        //ToDo: Add validation around the id field
        return true;
    }
}
