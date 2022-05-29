package service;

import exception.UnsupportedInputFormatException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputService {
    Scanner scanner = new Scanner(System.in);
    ValidationService validationService = new ValidationService();
    Set<String> cancelledIdSet = new HashSet<>();

    public Set<String> readInput(ArrayList<String[]> rows) throws UnsupportedInputFormatException {

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if ("".equals(line)) {
                break;
            }
            String[] row = line.split("\\s+");
            if(!validationService.isValidRow(row)) throw new UnsupportedInputFormatException();

            //Identifying cancelled trade events when accepting inputs
            if(!cancelledIdSet.contains(row[0]) && !row[1].equals("CANCEL")) rows.add(row);
            else cancelledIdSet.add(row[0]);
        }

        return cancelledIdSet;
    }
}
