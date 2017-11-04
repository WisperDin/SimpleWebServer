package controller;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.SQLError;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;

public class FeedBack {
    private int errCode;
    private String errMsg;
    private Object data;

    public static final int FB_SUCCESS = 1000;
    public static final int FB_SYS_ERR = 1001;
    public static final int FB_PARA_ERR = 1002;
    public static final int FB_DB_ERR = 1003;

    public static final int USER_ACCOUNT_EXIST = 1004;


    public static boolean MakeFeedBack(int errCode, String errMsg, Object data, HttpExchange he) throws IOException {
        if (errCode<=0||he==null){
            return false;
        }
        FeedBack fb = new FeedBack(errCode,errMsg,data);
        String fbJson = JSON.toJSONString(fb);
        if (fbJson.equals("")){
            return false;
        }
        he.sendResponseHeaders(200,fbJson.getBytes().length);
        OutputStream os = he.getResponseBody();
        os.write(fbJson.getBytes());
        os.close();
        return true;
    }


    private FeedBack(int errCode, String errMsg, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
