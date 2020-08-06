package letscode.com.service;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@PropertySource("classpath:custom.properties")
public class TestJson {

    @Autowired
    private Logger logger;

    @Autowired
    private JsonService jsonService;

    @Autowired
    private ProcReq procReq;

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


    @Test
    public void testJsonEq() {
        String res = null;
        try {
            res = procReq.procJson(JsonEq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(res);
        Assert.assertEquals(Equal, res);
    }
    @Test
    public void testJsonLa() {
        String res = null;
        try {
            res = procReq.procJson(JsonLarger);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(res);
        Assert.assertEquals(Larger, res);
    }

    @Test
    public void testJsonSm() {
        String res = null;
        try {
            res = procReq.procJson(JsonSmoller);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(res);
        Assert.assertEquals(Smoller, res);
    }
    @Test
    public void testJsonInc() {
        String res = null;
        try {
            res = procReq.procJson(JsonInc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(res);
        Assert.assertEquals(Incomparable, res);
    }
}
