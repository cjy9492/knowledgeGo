package millionhero;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2018/1/11 0011.
 */
public class DoAnswers implements Runnable{
    private List questions=new ArrayList();
    public  void Answers() throws Exception {
        String question="";

        List answers=new ArrayList();
                long startTime = System.currentTimeMillis();    //获取开始时间
                List Answerlist= AnswerGet.getanswaer(question,answers);
                if(Answerlist.size()!=1){
                    question = Answerlist.get(0).toString();
                    answers = (List) Answerlist.get(1);
                    if(!questions.contains(question)){
                        questions.add(question);
                        String URL ="http://www.baidu.com/s?wd="+ URLEncoder.encode(question, "UTF-8");
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+URL);
                       /* String num= baiduSearch.findbaidu(question,answers);
                        String num2= baiduSearch.findbaidu2(question,answers);
                        System.out.println("第一种权重为：\n"+num);
                        System.out.println("第二种权重为：\n"+num2);*/
                    }

                    long endTime = System.currentTimeMillis();    //获取结束时间
                    // System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
                    Thread.sleep(500);
                }
                else {
                    long endTime = System.currentTimeMillis();    //获取结束时间
                    // System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
                    Thread.sleep(500);
                }



    }

    public void run() {
while (true){
    try {
        Answers();
    } catch (Exception e) {

    }
}
    }
}
