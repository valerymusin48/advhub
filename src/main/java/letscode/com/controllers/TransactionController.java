package letscode.com.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;

//import org.springframework.security.access.prepost.PreAuthorize;
import letscode.com.service.ProcReq;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private ProcReq procReq;

    /**
     *
     * @param jsonStr
     * {"first":{"length":3,"width":5,"hight":7},"second":{"length":7,"width":5,"hight":3}}
     *
     * @return
     * * Формта возвращаемого значения
     *   {"result" : result }
     *
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/compare", method = RequestMethod.POST, produces = "application/json")

    public String getResult(@RequestParam("jsonStr")  String jsonStr)  {

        String res = null;
        try {
            res = procReq.procJson(jsonStr);
        } catch (Exception e) {
            res = e.getMessage();
        }

        return "{\"result\" : \"" + res + "\"}";
    }


}


