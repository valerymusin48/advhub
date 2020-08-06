package letscode.com.service;

import letscode.com.entities.Parallep;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:custom.properties")
public class ProcReq {

    @Autowired
    private Logger logger;

    @Value("${Equal}")
    private String Equal;

    @Value("${Smoller}")
    private String Smoller;

    @Value("${Larger}")
    private String Larger;

    @Value("${Incomparable}")
    private String Incomparable;

    @Autowired
    private JsonService jsonService;

    public String procJson(String jsonStr) throws Exception {
        Parallep first = getPar(jsonStr, "first");
        Parallep second = getPar(jsonStr, "second");

        if (first.getValue() == second.getValue()) {
            if (first.fitsIn(second)) {
                return Equal;
            }

        } else {
            if (first.getValue() > second.getValue()) {
                if (first.fitsIn(second)) {
                    return Larger;
                }
            }  else {
                if (second.fitsIn(first)) {
                    return Smoller;
                }
            }
        }
        return Incomparable;
    }
    private Parallep getPar(String jsonStr, String name) throws Exception  {
        JSONObject jsonObject = jsonService.getValue(jsonStr, name);
        int length = jsonService.getValue(jsonObject, "length");
        int width = jsonService.getValue(jsonObject, "width");
        int hight = jsonService.getValue(jsonObject, "hight");
        return new Parallep(length, width, hight);
    }


}
