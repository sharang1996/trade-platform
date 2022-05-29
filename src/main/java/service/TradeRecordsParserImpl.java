package service;

import exception.UnsupportedInputFormatException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TradeRecordsParserImpl implements RecordsParser{

    Map<String, Integer> accountSecurityCount = new HashMap<>();

    @Override
    public Map<String, Integer> parse(Set<String> cancelledSet, List<String[]> rows) throws UnsupportedInputFormatException {
        for(String[] row: rows){

            String id = row[0];
            String tradeEvent = row[1];
            String account = row[2];
            String security = row[3];
            int quantity = Integer.parseInt(row[4]);

            if(cancelledSet.contains(id)) continue;

            //Aggregating by a combination of the account name and security
            String key = account.concat("_").concat(security);
            switch(tradeEvent){
                case "BUY":
                    if(accountSecurityCount.containsKey(key))
                        accountSecurityCount.put(key, accountSecurityCount.get(key)+quantity);
                    else accountSecurityCount.put(key, quantity);
                    break;

                case "SELL":
                    if(accountSecurityCount.containsKey(key) &&
                            accountSecurityCount.get(key) >= quantity )
                        accountSecurityCount.put(key, accountSecurityCount.get(key)-quantity);
                    else throw new UnsupportedInputFormatException();
                    break;
            }

        }
        return accountSecurityCount;
    }
}
