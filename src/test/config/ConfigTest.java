package test.config; 

import config.Config;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Config Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®Ò»ÔÂ 4, 2017</pre> 
* @version 1.0 
*/ 
public class ConfigTest { 

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: InitConfig() 
* 
*/ 
@Test
public void testInitConfig() throws Exception {
    Config config = new Config();
    config.InitConfig("D:\\MYPROJ\\MYJAVA\\src\\");
    System.out.println(Config.ServerPort);
    System.out.println(Config.DB_NAME);
    System.out.println(Config.DB_HOST);
    System.out.println(Config.DB_PORT);
    System.out.println(Config.DB_USER);
    System.out.println(Config.DB_PWD);
}


} 
