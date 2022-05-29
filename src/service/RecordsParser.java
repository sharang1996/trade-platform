package service;

import exception.UnsupportedInputFormatException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecordsParser {
    Map<String, Integer> parse(Set<String> cancelledSet, List<String[]> rows) throws UnsupportedInputFormatException;
}
