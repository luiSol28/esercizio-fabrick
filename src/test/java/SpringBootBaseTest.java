import com.esercizio.backend.fabrick.controller.BankAccountController;
import org.springframework.boot.test.context.SpringBootTest;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unittests")
@TestPropertySource({"classpath:application-unittests.yml", "classpath:env/settings-unittests.yml"})
@ContextConfiguration(classes = {TestConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class SpringBootBaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    protected BankAccountController bankAccountController;
}
