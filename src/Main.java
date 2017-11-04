import com.sun.net.httpserver.HttpServer;
import config.Config;
import controller.RootHandler;
import controller.UserController;
import customException.EnvInitException;
import db.DataBaseConn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static void setupEnv() throws EnvInitException{
        System.out.println("SetUpEnv...");
        Config conf = new Config();
        conf.InitConfig("./src/");

        boolean flag = DataBaseConn.InitDb();
        if (!flag){
            throw new EnvInitException("InitDb failed");
        }
    }

    public static void main(String[] args) {
        Logger log = Logger.getLogger("log");
        log.setLevel(Level.INFO);

        try {
            setupEnv();
        }catch (EnvInitException eie){
            log.severe("setupEnv failed "+eie.getMessage());
            eie.printStackTrace();
            return;
        }

        int portInt;
        try {
            portInt = Integer.parseInt(Config.ServerPort);
        }catch (NumberFormatException nfe){
            log.severe("ServerPort parse int failed "+nfe.getMessage());
            nfe.printStackTrace();
            return;
        }

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(portInt),0);
            log.info("Server started at "+portInt);
            server.createContext("/",new RootHandler());
            server.createContext("/test", new UserController.InsertUserHandler());
            server.setExecutor(null);
            server.start();
        }catch (IOException ioe){
            ioe.printStackTrace();
            log.info("Server started failed at "+portInt);
        }

    }
}
