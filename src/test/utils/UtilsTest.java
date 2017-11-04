package test.utils; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import utils.Utils;

import javax.rmi.CORBA.Util;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/** 
* Utils Tester. 
* 
* @author <Authors name> 
* @since <pre>十一月 3, 2017</pre> 
* @version 1.0 
*/ 
public class UtilsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: splitQuery(URL url) 
* 
*/ 
@Test
public void testSplitQuery() throws Exception { 
    String url = "http://www.example.com/something.html?one=1&two=2&three=3&three=3a&name=??";

    Map<String,String> kv = Utils.splitQuery(new URL(url));
    assertEquals("1",kv.get("one"));
    assertEquals("2",kv.get("two"));
    assertEquals("3a",kv.get("three"));
    assertEquals("??",kv.get("name"));
}

@Test
public void testSplitPairs() throws Exception {
    String pairs = "one=1&two=2&three=3&three=3a&name=%E4%BD%A0%E5%A5%BD";

    Map<String,String> kv = Utils.splitParis(pairs);
    assertEquals("1",kv.get("one"));
    assertEquals("2",kv.get("two"));
    assertEquals("3a",kv.get("three"));
    assertEquals("你好",kv.get("name"));
    assertEquals(null,kv.get("notexist"));
}


} 
