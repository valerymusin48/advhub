package letscode.com.controllers;


import com.esotericsoftware.kryo.DefaultSerializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    private static final String APPLICATION_JSON_PRODUCER = "application/json;charset=utf-8";
    private static final String URL = "/api/compare";
    private MockHttpSession mockHttpSession;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Value("${JsonEq}")
    private String JsonEq;

    @Value("${JsonLarger}")
    private String JsonLarger;

    @Value("${JsonSmoller}")
    private String JsonSmoller;

    @Value("${JsonInc}")
    private String JsonInc;

    @Value("${Equal}")
    private String Equal;

    @Value("${Smoller}")
    private String Smoller;

    @Value("${Larger}")
    private String Larger;

    @Value("${Incomparable}")
    private String Incomparable;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    @Test
    public void validateTransEq() throws Exception {
        String trans_token = JsonEq;
        mockMvc.perform(post(URL)
                .param("jsonStr", trans_token))
                .andExpect(jsonPath("$.result").value(Equal));
    }

    @Test
    public void validateTransLa() throws Exception {
        String trans_token = JsonLarger;
        mockMvc.perform(post(URL)
                .param("jsonStr", trans_token))
                .andExpect(jsonPath("$.result").value(Larger));
    }

    @Test
    public void validateTransSm() throws Exception {
        String trans_token = JsonSmoller;
        mockMvc.perform(post(URL)
                .param("jsonStr", trans_token))
                .andExpect(jsonPath("$.result").value(Smoller));
    }

    @Test
    public void validateTransInc() throws Exception {
        String trans_token = JsonInc;
        mockMvc.perform(post(URL)
                .param("jsonStr", trans_token))
                .andExpect(jsonPath("$.result").value(Incomparable));
    }



}
