import exception.UnsupportedInputFormatException;
import service.*;
import java.util.*;

public class PositionBookRunner {


    public static void main(String[] args) throws UnsupportedInputFormatException {

        //If using a framework based on spring, the service dependencies will be autowired(dependency inversion)
        RecordsParser parser = new TradeRecordsParserImpl();
        Printer printer = new AggregatedTradePositionsPrinter();
        InputService inputService = new InputService();
        ArrayList<String[]> rows = new ArrayList<>();

        try{
            Set<String> cancelledIdSet = inputService.readInput(rows);
            Map<String, Integer> accountSecurityCount = parser.parse(cancelledIdSet, rows);
            printer.display(accountSecurityCount);
        }catch (UnsupportedInputFormatException e){
            e.printStackTrace();
        }
    }
}