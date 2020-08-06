package letscode.com.service;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;



import java.util.*;

@Service
public class JsonService {

    @Autowired
    private Logger logger;


    public <T> T getValue(String jsonString, String key) throws JSONException {
        JSONObject jsonObject = getObj(jsonString);
//        if (jsonObject == null)
//            return null;
        try {
            return (T) getValue(jsonObject, key);
        } catch (JSONException e){
            throw e;
        }
    }
    public <T> T getValue(JSONObject jsonObject, String key) throws JSONException {
        if (jsonObject == null)
            return null;
        Object obj = null;
        try {
             obj = jsonObject.get(key);
        } catch (JSONException e) {
            throw e;
        }
        try {
            T data = (T) obj;
        } catch (ClassCastException cce) {
            throw new JSONException("wrong json");
        }
        return (T) obj;
    }
    public <T> T getValue(Object object, String key)  throws JSONException {
        JSONObject jsonObject = (JSONObject) object;
        try {
            return (T) getValue(jsonObject, key);
        } catch (JSONException e) {
            return null;
        }

    }

    public JSONObject getObj(String jsonStr) throws JSONException {
        try {
            return new JSONObject(jsonStr);
        } catch (JSONException e) {
            throw e;
        }

    }

}
