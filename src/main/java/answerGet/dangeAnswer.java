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
 * Created by hero on 2018/1/24 0024.
 */
public class dangeAnswer implements Runnable{
    private  String sougou="";
    private  String dange="";
    private  String uc="";
    private  String car="";
    private  String url="";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  void getanswaer() throws Exception {
        String URL ="https://www.bainianaolai.com/Z/do/platform/"+url+"/id/";
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();
        HttpGet httpRequest = new HttpGet(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_2 like Mac OS X) AppleWebKit/604.4.7 (KHTML, like Gecko) Mobile/15C202 Sogousearch/Ios/5.9.8");
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");

            //System.out.println(html);
            JSONObject jsonObject = JSON.parseObject(html);
            JSONArray date= jsonObject.getJSONArray("data");
            for(int i=0;i<date.size();i++){
                JSONObject job = date.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
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
                // e.printStackTrace();
            }
        }
    }
}
