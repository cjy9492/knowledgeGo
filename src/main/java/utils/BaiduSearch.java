package utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjy on 2018-01-09.
 */
public class BaiduSearch {
    public static String findbaidu(String a,List answers ) throws Exception {


        String name1="";
        int mumm=0;
        StringBuilder sb =new StringBuilder();
        CloseableHttpClient client = null;
            String URL ="http://www.baidu.com/s?wd="+ URLEncoder.encode(a, "UTF-8");;
            client = HttpClientUtil.getnewHttpClient();
            HttpGet httpRequest = new HttpGet(URL);
            httpRequest.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
            CloseableHttpResponse httpResponse = client.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                HttpEntity entity1 = httpResponse.getEntity();
                String html = EntityUtils.toString(entity1, "utf-8");
                //System.out.println(html);
                for (Object op:answers){
                    String opp=op.toString().replace(" ","");
                    int num = Count.appearNumber(html,opp);
                    sb.append("\t"+opp+"出现次数为"+num+"\n");
                    if(mumm<num){
                        mumm=num;
                        name1=opp;
                    }
                }

            }

        sb.append(""+"根据权重得出的答案是:【"+name1+"】");
        return sb.toString();
    }
    public static String findbaidu2(String a,List answers ) throws Exception {
        StringBuilder sb =new StringBuilder();
        String name1="";
        int mumm=0;
        for (Object b:answers){
            String bb=b.toString().replace(" ","");


            CloseableHttpClient client = null;

                String URL ="http://www.baidu.com/s?wd="+URLEncoder.encode(a+" "+bb, "UTF-8");
                client = HttpClientUtil.getnewHttpClient();
                HttpGet httpRequest = new HttpGet(URL);
                httpRequest.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
                CloseableHttpResponse httpResponse = client.execute(httpRequest);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    HttpEntity entity1 = httpResponse.getEntity();
                    String html = EntityUtils.toString(entity1, "utf-8");
                    html=html.replace(",","");
                    //System.out.println(html);
                    String pattern="结果约([0-9]+)个";
                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(html);
                    if(m.find()) {
                       // System.out.println("url="+m.group(1));
                        sb.append("\t"+bb+"出现次数为"+m.group(1)+"\n");
                        int mun=Integer.parseInt(m.group(1));
                        if(mumm<mun){
                            mumm=mun;
                            name1=bb;
                        }
                    }

                }
        }
        sb.append(""+"根据权重得出的答案是:【"+name1+"】");
        return sb.toString();
    }

}
