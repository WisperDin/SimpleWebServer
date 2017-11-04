package controller;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import customException.ModelException;
import model.UserModel;
import sun.misc.IOUtils;
import utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

public class UserController {
    public static class InsertUserHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange he) throws IOException {
            Logger log = Logger.getLogger("log");

            String method = he.getRequestMethod();
            if (!method.equalsIgnoreCase("POST"))
                return;

            InputStream is = he.getRequestBody();
            if (is==null){
                log.warning("is null");
                FeedBack.MakeFeedBack(FeedBack.FB_SYS_ERR,"系统内部错误",null,he);
                return;
            }
            Map<String,String > pairs = null;
            try {
                pairs = Tools.ParseBodyToKVMap(is,1024);
            } catch (Exception e) {
                log.warning("ParseBodyToKVMap failed");
                try {
                    FeedBack.MakeFeedBack(FeedBack.FB_SYS_ERR, "系统内部错误", null, he);
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
                e.printStackTrace();
                return;
            }

            if (pairs==null){
                log.warning("pairs null");
                FeedBack.MakeFeedBack(FeedBack.FB_PARA_ERR,"参数解析错误",null,he);
                return;
            }

            String accountRaw = pairs.get("account");
            String pwdRaw = pairs.get("pwd");
            if (accountRaw==null||pwdRaw==null){
                log.warning("account or pwd null");
                FeedBack.MakeFeedBack(FeedBack.FB_PARA_ERR,"无参数account或者pwd",null,he);
                return;
            }
            if (accountRaw.equals("")||pwdRaw.equals("")){
                log.warning("account or pwd empty");
                FeedBack.MakeFeedBack(FeedBack.FB_PARA_ERR,"参数account或者pwd为空",null,he);
                return;
            }
            UserModel userModel = new UserModel(0,accountRaw,pwdRaw);
            try {
                if (!userModel.Insert()){
                    log.warning("account has exist");
                    FeedBack.MakeFeedBack(FeedBack.USER_ACCOUNT_EXIST,"账号已经存在",null,he);
                    return;
                }
            } catch (ModelException e) {
                log.warning("insert failed "+e.getMessage());
                FeedBack.MakeFeedBack(FeedBack.FB_DB_ERR,"数据库插入失败",null,he);
                e.printStackTrace();
                return;
            }

            //sucess...
            FeedBack.MakeFeedBack(FeedBack.FB_SUCCESS,"",null,he);
            log.info(String.format("account:%s pwd:%s insert success",accountRaw,pwdRaw));


/*            he.sendResponseHeaders(200,bodyStr.length());
            OutputStream os = he.getResponseBody();
            os.write(bodyByte);
            os.close();*/
            /*String queryRaw = he.getRequestURI().getRawQuery();
            if (queryRaw==null){
                //todo...
                return;
            }
            String query = URLDecoder.decode(queryRaw,"UTF-8");
            Map<String,String> kv = Utils.splitQuery(new URL(query));
            String accountRaw = kv.get("account");
            String pwdRaw = kv.get("pwd");
            if (accountRaw == null){
                //todo...
                return;
            }
            if (accountRaw=="")*/
        }
    }
}
