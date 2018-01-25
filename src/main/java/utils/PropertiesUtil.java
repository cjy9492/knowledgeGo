package utils;







import java.io.*;
import java.util.Properties;


/**
 * Created by hspcadmin on 2017/5/25.
 */
public class PropertiesUtil {
    public static String readValue( String key) {
        Properties props = new Properties();
        try {
            InputStream ips = new BufferedInputStream(new FileInputStream("sign.properties"));
           /* InputStream ips = PropertiesUtil.class.getResourceAsStream("/sign.properties");*/
            BufferedReader ipss = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
            props.load(ipss);
            String value = props.getProperty(key);
            return value;
        } catch (FileNotFoundException e) {
            System.out.println("无法找到文件:"+e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("读文件出错:"+"---"+e.getMessage());
            return null;
        }
    }

}
