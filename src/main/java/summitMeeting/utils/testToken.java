package summitmeeting.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import utils.HttpClientUtil;

/**
 * Created by hero on 2018/1/14 0014.
 */
public class TestToken {
    public static boolean getanswaer(String token) throws Exception {
        String URL ="http://api.api.chongdingdahui.com/win/me";
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();
        HttpPost httpRequest = new HttpPost(URL);
        httpRequest.setHeader("User-Agent","LiveTrivia/1.0.4 (com.chongdingdahui.app; build:0.1.7; iOS 9.3.5) Alamofire/4.6.0");
        httpRequest.setHeader("X-Live-Session-Token",token);
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");
            System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            String msg=  jsonObject.getString("msg");
            if("请求成功".equals(msg)){
                return true;
            }
            return false;
        }
        return false;

    }

}
