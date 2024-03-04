package controller;

import com.esercizio.backend.fabrick.controller.BankAccountController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

//@ExtendWith(MockitoExtension.class)
public class BankAccountControllerTest {

    Logger logger = LoggerFactory.getLogger(BankAccountControllerTest.class);

    @InjectMocks
    BankAccountController bankAccountController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);    }
/*
    @Test
    public void test_getBalance_status_200_OK() {
        logger.info("test_getBalance_status_200_OK");
        ResponseEntity response = bankAccountController.getBalance("14537780");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void test_getTransactions_status_200_OK() {
        logger.info("test_getTransactions_status_200_OK");
        ResponseEntity response = bankAccountController.getTransactions("14537780", LocalDate.MAX, LocalDate.now());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
*/
}