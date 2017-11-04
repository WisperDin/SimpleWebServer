package test.model; 

import config.Config;
import customException.ModelException;
import db.DataBaseConn;
import model.UserModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/** 
* UserModel Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®Ò»ÔÂ 2, 2017</pre> 
* @version 1.0 
*/ 
public class UserModelTest {

@BeforeClass
public static void setUpBeforeClass() throws Exception {
    Config conf = new Config();
    conf.InitConfig("./src/");
    DataBaseConn.InitDb();
}

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception {
    UserModel.DeleteUser(String.format("where account='%s'","__T_ABC"));
} 

/** 
* 
* Method: Insert() 
* 
*/ 
@Test
public void testInsert()  {
    UserModel user = new UserModel(0,"__T_ABC","123456");

    boolean flag = false;
    try {
        flag = user.Insert();
    } catch (ModelException e) {
        fail(e.getMessage());
        e.printStackTrace();
    }

    assertTrue(flag);

} 

/** 
* 
* Method: FindUser() 
* 
*/ 
@Test
public void testFindUser() throws Exception {
    String account = "__T_ABC";
    String pwd = "123456";
    UserModel user = new UserModel(0,"__T_ABC","123456");

    boolean flag = false;
    try {
        flag = user.Insert();
    } catch (ModelException e) {
        fail(e.getMessage());
        e.printStackTrace();
    }

    assertTrue(flag);

    UserModel[] users = UserModel.FindUser(String.format("where account='%s'",account),"","");
    assertEquals(1,users.length);
    assertEquals(pwd,users[0].getPassword());

} 

/** 
* 
* Method: Upd() 
* 
*/ 
@Test
public void testUpd() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: DeleteUser() 
* 
*/ 
@Test
public void testDeleteUser() throws Exception {
    System.out.println(UserModel.DeleteUser(String.format("where account='%s'","__T_ABC")));
} 


} 
