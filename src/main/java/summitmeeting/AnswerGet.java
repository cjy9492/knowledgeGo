package summitmeeting;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import utils.HttpClientUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenjy on 2018-01-09.
 */
public class AnswerGet {
    private static String lastQues;
    public static List getanswaer(String question, List answers,String session) throws Exception {
        String URL ="http://msg.api.chongdingdahui.com/msg/current";
        CloseableHttpClient client = null;
        List quest=new ArrayList();
            client = HttpClientUtil.getnewHttpClient();
            HttpGet httpRequest = new HttpGet(URL);
            httpRequest.setHeader("User-Agent","LiveTrivia/1.0.4 (com.chongdingdahui.app; build:0.1.7; iOS 9.3.5) Alamofire/4.6.0");
            httpRequest.setHeader("Content-Type","application/json");
            httpRequest.setHeader("X-Live-Session-Token",session);
            CloseableHttpResponse httpResponse = client.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                HttpEntity entity1 = httpResponse.getEntity();
                String html = EntityUtils.toString(entity1, "utf-8");
                //String html = "{\"code\":0,\"msg\":\"成功\",\"data\":{\"event\":{\"answerTime\":10,\"correctOption\":2,\"desc\":\"10.古诗的体制称为风雅颂，其中“雅”指     \",\"displayOrder\":9,\"liveId\":85,\"options\":\"[\\\"民间故事与作品\\\",\\\"宗庙祭祀的舞曲歌辞\\\",\\\"王朝正声\\\"]\",\"questionId\":979,\"showTime\":1515489091540,\"stats\":[1990,7270,4629],\"status\":2,\"type\":\"showAnswer\"},\"type\":\"showAnswer\"}}";
                //String html="{\"code\":0,\"msg\":\"成功\",\"data\":{\"event\":{\"answerTime\":10,\"desc\":\"2.福建省的省会是？   \",\"displayOrder\":1,\"liveId\":84,\"options\":\"[\\\"福州\\\",\\\"厦门\\\",\\\"泉州\\\"]\",\"questionId\":959,\"showTime\":1515474165604,\"status\":0,\"type\":\"showQuestion\"},\"type\":\"showQuestion\"}}";
                //String html="{\"code\":0,\"msg\":\"成功\",\"data\":{\"event\":{\"answerTime\":10,\"desc\":\"12.电影公司派拉蒙现在的标志中共有多少颗星星？     \",\"displayOrder\":11,\"liveId\":85,\"options\":\"[\\\"20\\\",\\\"22\\\",\\\"24\\\"]\",\"questionId\":981,\"showTime\":1515489182682,\"status\":0,\"type\":\"showQuestion\"},\"type\":\"showQuestion\"}}";
                if (!html.equals(lastQues)) {
                    lastQues = html;
                   // System.out.println(html);
                }
                JSONObject jsonObject = JSON.parseObject(html);
                String msg=  jsonObject.getString("msg");
                if(!"no data".equals(msg)){
                    JSONObject date= (JSONObject) jsonObject.get("data");
                    JSONObject event= (JSONObject) date.get("event");
                    String desc=  event.getString("desc");
                    String options=  event.getString("options");
                    String type=  event.getString("type");
                    if(!"showWinner".equals(type)) {
                        options = options.replace("\"", "");
                        options = options.substring(1, options.length() - 1);
                        String[] arr = options.split(",");
                        quest.add(desc.trim().substring(desc.indexOf(".") + 1));
                        quest.add(Arrays.asList(arr));
                    }
                }
                else {
                    quest.add("no");
                }

                return quest;
            }
            else {
                quest.add("no");
            }
            return quest;

    }


}