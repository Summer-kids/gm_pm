package cc.guomai.gm_pm.utils;

import com.alibaba.fastjson.JSONObject;

public class ValidateUtil {
    public static void validJSONParam(JSONObject json, String... keys){
        for(String key :keys){
            if(!json.containsKey(key)){
                throw new RuntimeException("参数错误");
            }
        }
    }
}
