package cheesesuperman;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
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
public class AnswerGet {
    private static String lastQues;
    public static List getanswaer(String question, List answers) throws Exception {
        String URL ="https://www.bainianaolai.com/Z/show/platform/yingke";
        CloseableHttpClient client = HttpClientUtil.getnewHttpClient();;
        List quest=new ArrayList();
        List answer=new ArrayList();
        HttpGet httpRequest = new HttpGet(URL);
        httpRequest.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.2372.400 QQBrowser/9.5.10548.400");
        CloseableHttpResponse httpResponse = client.execute(httpRequest);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            HttpEntity entity1 = httpResponse.getEntity();
            String html = EntityUtils.toString(entity1, "utf-8");
            Document doc = Jsoup.parse(html);
            Elements qustion = doc.getElementsByClass("qustion");
            if(!"".equals(qustion.get(1).text())){
            Elements answera = doc.getElementsByClass("answer-content answer-a");
            Elements answerb = doc.getElementsByClass("answer-content answer-b");
            Elements answerc = doc.getElementsByClass("answer-content answer-c");
            String tqustion=qustion.get(1).text();
            String tanswera=answera.text();
            String tanswerb=answerb.text();
            String tanswerc=answerc.text();
            answer.add(tanswera);
            answer.add(tanswerb);
            answer.add(tanswerc);
          if (!tqustion.equals(lastQues)) {
                lastQues = tqustion;
                System.out.println(tqustion);
                quest.add(tqustion);
                quest.add(answer);
            }
            } else {
                quest.add("no");
            }
            return quest;
        }else {
            quest.add("no");
        }
        return quest;

    }

}
