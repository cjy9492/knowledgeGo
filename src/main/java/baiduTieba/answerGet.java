package baiduTieba;

import baiduTieba.utils.UnicodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2018/1/14 0014.
 */
public class answerGet {
    private static String lastQues="0";
    public static List getanswer(String guessid,String cookies) throws Exception {
        String URL ="https://tieba.baidu.com/alaguess/guess/getQuestion";
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();
        List quest=new ArrayList();
        HttpPost httpRequest = new HttpPost(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        httpRequest.setHeader("Content-Type","application/x-javascript;charset=utf-8");
        httpRequest.setHeader("Connection","keep-alive");
        httpRequest.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpRequest.setHeader("Accept-Encoding","gzip, deflate, br");
        httpRequest.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpRequest.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpRequest.setHeader("Host","tieba.baidu.com");
        httpRequest.setHeader("Origin","https://tieba.baidu.com");
        httpRequest.setHeader("Referer","https://tieba.baidu.com/");
        httpRequest.setHeader("Cookie",cookies);
        //设置参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("_client_version", "9.2.8.4"));
            nvps.add(new BasicNameValuePair("guess_id", guessid));
            nvps.add(new BasicNameValuePair("guess_status", "2"));
            nvps.add(new BasicNameValuePair("msg_id", "0"));
            nvps.add(new BasicNameValuePair("sign", "123456tbclient654321"));
        httpRequest.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");
             //System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject question = data.getJSONObject("question");
            if(question!=null){
            String msg= UnicodeUtil.ascii2native(question.getString("title"));
            if(!lastQues.equals(msg)){
                lastQues=msg;
                System.out.println(msg);

            quest.add(msg);
            List answer=new ArrayList();
            JSONArray options=  question.getJSONArray("options");
            for(int i=0;i<options.size();i++){
                JSONObject job = options.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                String option=job.get("title").toString();
                answer.add(UnicodeUtil.ascii2native(option));
            }
            quest.add(answer);
            return quest;
            }
            }
        }

        return quest;

    }

    public static String getguessid(String cookies) throws Exception {
        String URL ="https://tieba.baidu.com/alaguess/guess/home";
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();
        List quest=new ArrayList();
        HttpPost httpRequest = new HttpPost(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        httpRequest.setHeader("Content-Type","application/x-javascript;charset=utf-8");
        httpRequest.setHeader("Connection","keep-alive");
        httpRequest.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpRequest.setHeader("Accept-Encoding","gzip, deflate, br");
        httpRequest.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpRequest.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpRequest.setHeader("Host","tieba.baidu.com");
        httpRequest.setHeader("Origin","https://tieba.baidu.com");
        httpRequest.setHeader("Referer","https://tieba.baidu.com/");
        httpRequest.setHeader("Cookie",cookies);
        //设置参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("_client_version", "9.2.8.4"));
        nvps.add(new BasicNameValuePair("sign", "123456tbclient654321"));
        httpRequest.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");
            //System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            JSONObject data = jsonObject.getJSONObject("data");
            String guess_id=data.get("guess_id").toString();
            return guess_id;
        }

        return "0000";

    }


}
