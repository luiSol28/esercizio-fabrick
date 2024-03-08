package tnr;

import com.esercizio.backend.fabrick.Application;
import com.esercizio.backend.fabrick.model.dto.api.BankTransferDto;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-integrationtest.properties")
public class BankAccountControllerMockTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ResourceLoader resourceLoader;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    BankAccountControllerMockTest() {
        headers.add("Content-Type", "application/json");
        headers.add("Auth-Schema", "S2S");
        headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
    }

    @Test
    public void getCashAccountBalanceTest() throws JSONException {

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/banckaccount/14537780/balance"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getCashAccountBalanceTest2() {

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/banckaccount/14537780/transactions?fromAccountingDate=2024-03-01&toAccountingDate=2024-03-05"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getCashAccountBalanceTest3() throws IOException {

        BankTransferDto dto = covertObjectInJSONMock();
        HttpEntity<BankTransferDto> entity = new HttpEntity<>(dto,headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/banckaccount/14537780/banktransfer"),
                HttpMethod.POST, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private BankTransferDto covertObjectInJSONMock() throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:mock/BankTransferDtoMock.json");
        String jsonString = UtilityClassRestClient.convertStreamToString(fileResource.getInputStream());
        return covertObjectInJSON(jsonString);
    }
    private BankTransferDto covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, BankTransferDto.class);
    }

}