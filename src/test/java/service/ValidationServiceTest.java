package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
    }


    @ParameterizedTest
    @ValueSource(strings = {"3 BUY ACC1 SEC1 12", "5 BUY ACC2 SECXYZ 33"})
    void isValidRow(String row) {
        String[] arrayParam = row.split("\\s+");
        assertTrue(validationService.isValidRow(arrayParam), "This should pass for a valid row");
    }

    @ParameterizedTest
    @ValueSource(strings = {"3 BUY ACC1 SEC1 12 extrafield", "5 BUY ACC2 33"})
    void isNotValidRow(String row) {
        String[] arrayParam = row.split("\\s+");
        assertFalse(validationService.isValidRow(arrayParam), "This should pass for an invalid row");
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "100", "1000"})
    void isValidQuantity(String columnValue) {
        assertTrue(validationService.isValidQuantity(columnValue), "This should pass for a valid quantity");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100", "some string", "11a"})
    void isNotValidQuantity(String columnValue) {
        assertFalse(validationService.isValidQuantity(columnValue), "This should pass for invalid quantities");
    }

    @ParameterizedTest
    @ValueSource(strings = {"SECXYZ", "SEC1", "SEC2"})
    void isValidSecurity(String columnValue) {
        assertTrue(validationService.isValidSecurity(columnValue), "This should pass for a valid security");
    }

    @ParameterizedTest
    @ValueSource(strings = {"SE@CXYZ", "longsecurityover10chars"})
    void isNotValidSecurity(String columnValue) {
        assertFalse(validationService.isValidSecurity(columnValue), "This should pass for an invalid security");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ACC1", "ACC2", "ACC3"})
    void isValidAccount(String columnValue) {
        assertTrue(validationService.isValidAccount(columnValue), "This should pass for a valid account");
    }

    @ParameterizedTest
    @ValueSource(strings = {"acc@CXYZ", "longaccountover4chars"})
    void isNotValidAccount(String columnValue) {
        assertFalse(validationService.isValidAccount(columnValue), "This should pass for an invalid account");
    }

    @ParameterizedTest
    @ValueSource(strings = {"BUY", "SELL", "CANCEL"})
    void isValidTradeEvent(String columnValue) {
        assertTrue(validationService.isValidTradeEvent(columnValue), "This should pass for a valid event");
    }

    @ParameterizedTest
    @ValueSource(strings = {"BUYabc", "SELL@", "123CANCEL", "Literally anything else"})
    void isNotValidTradeEvent(String columnValue) {
        assertFalse(validationService.isValidTradeEvent(columnValue), "This should pass for a invalid event");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "100", "999", "2147483647"})
    void isValidTradeId(String columnValue) {
        assertTrue(validationService.isValidTradeId(columnValue), "This should pass for a positive int id");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1a", "string", "-999", "2147483648"})
    void isNotValidTradeId(String columnValue) {
        assertFalse(validationService.isValidTradeId(columnValue), "This should pass for an invalid id");
    }
}