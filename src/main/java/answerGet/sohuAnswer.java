package answerget;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import utils.HttpClientUtil;

/**
 * Created by hero on 2018/1/24 0024.
 */
public class SohuAnswer implements Runnable{
    private  String sougou="";
    private  String dange="";
    private  String uc="";
    private  String car="";
    private  int id=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  void getanswaer() throws Exception {
        String URL ="https://h-ss.sohu.com/hotspot/millionHero/getQuestion";
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();
        HttpPost httpRequest = new HttpPost(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13G36 iphone sohuinfonews2_2_0");
        httpRequest.setHeader("Accept","application/json,text/javascript,*/*;q=0.01");
        httpRequest.setHeader("Accept-Encoding","gzip, deflate");
        httpRequest.setHeader("Accept-Language","zh-cn");
        httpRequest.setHeader("X-Requested-With","XMLHttpRequest");
        httpRequest.setHeader("Content-Type","application/json;charset=UTF-8");
        httpRequest.setHeader("Referer","https://h-ss.sohu.com/activity/million");
        httpRequest.setHeader("Origin","https://h-ss.sohu.com");
        httpRequest.setHeader("Connection","keep-alive");
        httpRequest.setHeader("Host","h-ss.sohu.com");
        httpRequest.setHeader("Proxy-Connection","keep-alive");


        //        json方式
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("appType", id);
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpRequest.setEntity(entity);
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");

            //System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            JSONObject date= jsonObject.getJSONObject("data");
            JSONArray jsonresults= date.getJSONArray("results");
            for(int i=0;i<jsonresults.size();i++){
                JSONObject job = jsonresults.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                String title=job.get("option").toString();
                String result=job.get("optionContent").toString();
                String results=job.get("title").toString();
                if(i==0){
                    if((!sougou.equals(results))&&!"".equals(results)){
                        if(!"".equals(title)||!"".equals(result)) {
                            System.out.println("搜狗的答案是：" + title + " " + result);
                            sougou = results;
                        }
                    }
                }
                if(i==1){
                    if((!dange.equals(results))&&!"".equals(results)){
                        if(!"".equals(title)||!"".equals(result)) {
                            System.out.println("蛋哥的答案是：" + title + " " + result);
                            dange = results;
                        }
                    }
                }
                if(i==2){
                    if((!uc.equals(results))&&!"".equals(results)){
                        if(!"".equals(title)||!"".equals(result)) {
                            System.out.println("UC的答案是：" + title + " " + result);
                            uc = results;
                        }
                    }
                }
                if(i==3){
                    if(!car.equals(results)&&!"".equals(results)){
                        if(!"".equals(title)||!"".equals(result)) {
                            System.out.println("驾车宝典的答案是：" + title + " " + result);
                            car = results;
                        }
                    }
                }
            }

        }

    }


    public void run() {
        while (true) {
            try {
                getanswaer();
                Thread.sleep(500);
            } catch (Exception e) {
                 //e.printStackTrace();
            }
        }
    }

}
