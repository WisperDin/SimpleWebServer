package config;

import customException.ConfInitException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Logger;


public class Config {

    public static String ServerPort;
    public static String DB_NAME;
    public static String DB_HOST;
    public static String DB_PORT;
    public static String DB_USER;
    public static String DB_PWD;

    /**
     *
     * @param propDir properties所在目录
     * @throws ConfInitException
     */
    public void InitConfig(String propDir) throws ConfInitException {
        Logger log = Logger.getLogger("log");
        if (propDir==null||propDir.equals("")){
            log.severe("propDir null or empty");
            return;
        }
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(propDir+"app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConfInitException("prop.load failed "+e.getMessage());
        }

        //通过反射将对应变量名字的 Properties 的对应值写回变量中
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String val = prop.getProperty(field.getName());
            if (val!=null){
                try {
                    field.set(null, ((Object) val));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new ConfInitException("can't not access field"+e.getMessage());
                }
            }
        }
    }
}
