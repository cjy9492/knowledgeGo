package answerGet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import utils.HttpClientUtil;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjy on 2018-01-23.
 */
public class sogouAnswer implements Runnable {
    private static String lastQues1="0";
    private String app="";
    private List lastQues=new ArrayList();

    public List getLastQues() {
        return lastQues;
    }

    public void setLastQues(List lastQues) {
        this.lastQues = lastQues;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public  void getanswaer() throws Exception {
        String URL ="http://140.143.49.31/api/ans2?key="+app;
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();;
        List quest=new ArrayList();
        List answer=new ArrayList();
        HttpGet httpRequest = new HttpGet(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_2 like Mac OS X) AppleWebKit/604.4.7 (KHTML, like Gecko) Mobile/15C202 Sogousearch/Ios/5.9.8");
        httpRequest.setHeader("Referer","http://nb.sa.sogou.com/");
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");
            //String html = "{\"status\":0,\"data\":{\"title\":\"\",\"time\":1516000958,\"status\":0,\"round\":\"\",\"correct\":\"\",\"options\":[],\"sid\":\"106\"}}";
            //String html = "{\"status\":0,\"data\":{\"title\":\"一个半径为4和半径为3的圆相切，那么两圆的圆心距是多少？\",\"time\":1516000833,\"status\":1,\"round\":\"4\",\"correct\":\"0\",\"options\":[{\"confidence\":\"221.515289307\",\"score\":\"39.4128074509\",\"title\":\"镍\"},{\"confidence\":\"170.261779785\",\"score\":\"30.2935962746\",\"title\":\"铝\"},{\"confidence\":\"170.261779785\",\"score\":\"30.2935962746\",\"title\":\"铜\"}],\"sid\":\"105\"}}";
            //System.out.println(html);
            html=html.replace("undefined(","");
            html=html.replace(")","");
            html=html.replace("\"{","{");
            html=html.replace("}\"","}");
            html=html.replace("\\","");
            String search_infos=html.substring(html.indexOf("fos\":[{")+7,html.indexOf("}],\"ti"));
            html=html.replace(search_infos,"");
            html=html.replace("\"search_infos\":[{}],","");
            String search_infos1=html.substring(html.indexOf("fos\":[{")+7,html.indexOf("}],\"ti"));
            html=html.replace(search_infos1,"");
            //System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            JSONArray date= jsonObject.getJSONArray("result");
            for(int i=0;i<date.size();i++){
                JSONObject job = date.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                String title=job.get("title").toString();
                String result=job.get("result").toString();
                String choices=job.get("choices").toString();
                if(!lastQues.contains(title)){
                    System.out.println(title);
                    lastQues.add(title);
                    System.out.println("搜狗推荐的答案为"+result);
                    String URL1 ="http://www.baidu.com/s?wd="+ URLEncoder.encode(title, "UTF-8");
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+URL1);
                }

            }

    }

    }
    public static void getanswaercddh(String app,List lastQues) throws Exception {
        String URL ="http://140.143.49.31/api/ans2?key="+app;
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();;
        List quest=new ArrayList();
        List answer=new ArrayList();
        HttpGet httpRequest = new HttpGet(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_2 like Mac OS X) AppleWebKit/604.4.7 (KHTML, like Gecko) Mobile/15C202 Sogousearch/Ios/5.9.8");
        httpRequest.setHeader("Referer","http://nb.sa.sogou.com/");
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");
            //String html = "{\"status\":0,\"data\":{\"title\":\"\",\"time\":1516000958,\"status\":0,\"round\":\"\",\"correct\":\"\",\"options\":[],\"sid\":\"106\"}}";
            //String html = "{\"status\":0,\"data\":{\"title\":\"一个半径为4和半径为3的圆相切，那么两圆的圆心距是多少？\",\"time\":1516000833,\"status\":1,\"round\":\"4\",\"correct\":\"0\",\"options\":[{\"confidence\":\"221.515289307\",\"score\":\"39.4128074509\",\"title\":\"镍\"},{\"confidence\":\"170.261779785\",\"score\":\"30.2935962746\",\"title\":\"铝\"},{\"confidence\":\"170.261779785\",\"score\":\"30.2935962746\",\"title\":\"铜\"}],\"sid\":\"105\"}}";
            //System.out.println(html);
            html=html.replace("undefined(","");
            html=html.replace(")","");
            html=html.replace("\"{","{");
            html=html.replace("}\"","}");
            html=html.replace("\\","");
            String search_infos=html.substring(html.indexOf("fos\":[{")+7,html.indexOf("}],\"ti"));
            html=html.replace(search_infos,"");
            html=html.replace("\"search_infos\":[{}],","");
            String search_infos1=html.substring(html.indexOf("fos\":[{")+7,html.indexOf("}],\"ti"));
            html=html.replace(search_infos1,"");
            //System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            JSONArray date= jsonObject.getJSONArray("result");
            for(int i=0;i<date.size();i++){
                JSONObject job = date.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                String title=job.get("title").toString();
                String result=job.get("result").toString();
                String choices=job.get("choices").toString();
                if(!lastQues.contains(title)){
                    //System.out.println(html);
                    lastQues.add(title);
                    System.out.println("搜狗推荐的答案为【"+result+"】");
                }


            }

        }

    }

    public void run() {
      while (true){
          try {
              getanswaer();
          } catch (Exception e) {
              continue;
              //e.printStackTrace();
          }
      }
    }
}
