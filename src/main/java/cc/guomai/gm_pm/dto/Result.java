package cc.guomai.gm_pm.dto;


/**
 * 封装返回给浏览器的json数据
 *
 * @author ZhangQI
 * @date 2019.04.17
 */
public class Result {

    public int code;
    public String message;
    public Object body;

    public Result(){
        code = 20000;
    }
    public Result(int code){
        this.code = code;
    }

}
