package controller;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.controller.BankAccountController;
import com.esercizio.backend.fabrick.model.api.response.AccountTransactionsResponse;
import com.esercizio.backend.fabrick.model.api.response.CashAccountBalanceResponse;
import com.esercizio.backend.fabrick.model.api.response.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.dto.api.BankTransferDto;
import com.esercizio.backend.fabrick.service.platformApi.api.AccountBalanceApiServicePlatformApi;
import com.esercizio.backend.fabrick.service.platformApi.api.AccountTransactionsApiServicePlatformApi;
import com.esercizio.backend.fabrick.service.platformApi.api.BankTransferApiServicePlatformApi;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankAccountControllerTest {

    @Mock
    private AccountBalanceApiServicePlatformApi accountBalanceApiService;

    @Mock
    private AccountTransactionsApiServicePlatformApi accountTransactionsApiService;

    @Mock
    private BankTransferApiServicePlatformApi bankTransferApiService;

    @InjectMocks
    private BankAccountController bankAccountController;

    private BankAccontParamInputBin bankAccontParamInputBin;

    private BankAccontParamInputBin bankAccontParamInputBin2;

    private BankAccontParamInputBin bankAccontParamInputBin3;

    private BankTransferDto bankTransferDto;

    @Before
    public void init() {
        bankTransferDto = new BankTransferDto();
        bankAccontParamInputBin = BankAccontParamInputBin
                .builder()
                .idAccount("14537780")
                .apiKey("FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
                .authSchema("S2S")
                .contentType("application/json")
                .build();
        bankAccontParamInputBin2 = BankAccontParamInputBin
                .builder()
                .idAccount("14537780")
                .fromAccountingDate(LocalDate.MAX.toString())
                .toAccountingDate(LocalDate.MAX.toString())
                .apiKey("FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
                .authSchema("S2S")
                .contentType("application/json")
                .build();
        bankAccontParamInputBin3 = BankAccontParamInputBin
                .builder()
                .idAccount("14537780")
                .apiKey("FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
                .bankTransferDto(bankTransferDto)
                .authSchema("S2S")
                .contentType("application/json")
                .build();
    }

    @Test
    public void getCashAccountBalanceTest() throws IOException, JSONException {
        ResponseEntity<CashAccountBalanceResponse> response = new ResponseEntity<>(HttpStatus.ACCEPTED);
        Mockito.when(accountBalanceApiService.executeApi(bankAccontParamInputBin)).thenReturn(response);

        ResponseEntity<CashAccountBalanceResponse> res = bankAccountController.getCashAccountBalance("14537780","application/json", "S2S", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        Assert.assertNotNull(res);
    }

    @Test
    public void getTransactionsTest() throws IOException, JSONException {
        ResponseEntity<AccountTransactionsResponse> response = new ResponseEntity<AccountTransactionsResponse>(HttpStatus.ACCEPTED);
        Mockito.when(accountTransactionsApiService.executeApi(bankAccontParamInputBin2)).thenReturn(response);

        ResponseEntity<AccountTransactionsResponse> res = bankAccountController.getTransactions("14537780", LocalDate.MAX, LocalDate.MAX, "application/json", "S2S", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        Assert.assertNotNull(res);
    }

    @Test
    public void postBankTransferTest() throws IOException, JSONException {
        ResponseEntity<ExecuteBankTransferResponse> response = new ResponseEntity<ExecuteBankTransferResponse>(HttpStatus.ACCEPTED);
        Mockito.when(bankTransferApiService.executeApi(bankAccontParamInputBin3)).thenReturn(response);

        ResponseEntity<ExecuteBankTransferResponse> res = bankAccountController.postBankTransfer("14537780", new BankTransferDto(),"application/json", "S2S", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        Assert.assertNotNull(res);
    }

}
