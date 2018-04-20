package JJIGSAWED;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author jmehl
 */
public class DBInteraction 
{
    
    public static String fileName = "/etc/jjigsawed/database.properties";
    
    /**
    *
    * Read database.properties to obtain username
    * @return - value of username in database.properties
    * @author jmehl
    */
    public static String getDBUsername()
    {
        String dbUserName = "";
                
        Properties properties = new Properties();
        try
        {
            FileInputStream input = new FileInputStream(fileName);
            properties.load(input);
            
            dbUserName = properties.getProperty("DB_USERNAME");
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
        
        return dbUserName;
    }
    
    /**
    *
    * Read database.properties to obtain password
    * @return - value of password in database.properties
    * @author jmehl
    */
    public static String getDBPassword()
    {
        String dbPassword = "";
                
        Properties properties = new Properties();
        try
        {
            FileInputStream input = new FileInputStream(fileName);
            properties.load(input);
            
            dbPassword = properties.getProperty("DB_PASSWORD");
        }
        catch (IOException io)
        {
            System.getProperty("user.dir");
        }
        
        return dbPassword;
    }    
}
