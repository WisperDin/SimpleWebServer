package controller;

import utils.Utils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class Tools {

    /**
     *
     * @param is
     * @param bufferSize 缓冲区的字节长度
     * @return
     */
    public static Map<String,String> ParseBodyToKVMap(InputStream is,int bufferSize) throws Exception {
        if (is==null){
            //todo ... log
            return null;
        }
        if (bufferSize<=0){
            //todo ... log
            return null;
        }
        byte[] tmp = new byte[bufferSize];
        int readCount = is.read(tmp);
        byte[] bodyByte = Arrays.copyOf(tmp,readCount);
        String bodyStr = new String(bodyByte, "UTF-8");
        Map<String,String > pairs = Utils.splitParis(bodyStr);
        if (pairs==null){
            //todo...log
            return null;
        }
        return pairs;
    }
}
