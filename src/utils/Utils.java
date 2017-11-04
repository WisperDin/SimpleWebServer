package utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {

    public static Map<String,String> splitQuery(URL url) throws UnsupportedEncodingException{
        if (url == null||url.toString().equals("")){
            //todo..log
            return null;
        }
        String query = url.getQuery();
        if (query == null||query.equals("")){
            //todo..log
            return null;
        }

        Map<String,String> query_paris = splitParis(query);
        if (query_paris==null){
            //todo..log
            return null;
        }

        return query_paris;
    }

    public static Map<String,String> splitParis(String query) throws UnsupportedEncodingException{
        if (query == null||query.equals("")){
            //todo..log
            return null;
        }
        Map<String,String> query_paris = new LinkedHashMap<>();
        String[] paris = query.split("&");
        for (String pair:paris) {
            int idx = pair.indexOf("=");
            query_paris.put(URLDecoder.decode(pair.substring(0,idx),"UTF-8"),URLDecoder.decode(pair.substring(idx+1),"UTF-8"));
        }
        return query_paris;
    }
}
