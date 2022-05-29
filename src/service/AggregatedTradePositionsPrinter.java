package service;

import java.util.Map;
import java.util.stream.Collectors;

public class AggregatedTradePositionsPrinter implements Printer{
    @Override
    public void display(Map<String, Integer> accountSecurityCount) {
        for(String key : accountSecurityCount.keySet().stream().sorted().collect(Collectors.toList())){
            System.out.println(key.split("_")[0] + " " +
                    key.split("_")[1] + " "+
                    accountSecurityCount.get(key));
        }
    }
}
