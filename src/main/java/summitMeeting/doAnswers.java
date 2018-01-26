package summitMeeting;

import utils.baiduSearch;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hero on 2018/1/11 0011.
 */
public class doAnswers implements Runnable{
    private List questions=new ArrayList();
    private String sessison;

    public String getSessison() {
        return sessison;
    }

    public void setSessison(String sessison) {
        this.sessison = sessison;
    }

    public List getQuestions() {
        return questions;
    }

    public void setQuestions(List questions) {
        this.questions = questions;
    }

    public  void Answers() throws Exception {
        String question="";
        List answers=new ArrayList();
                long startTime = System.currentTimeMillis();    //获取开始时间
                List Answerlist= answerGet.getanswaer(question,answers,sessison);
                if(Answerlist.size()!=1){
                    question = Answerlist.get(0).toString();
                    answers = (List) Answerlist.get(1);
                    if(!questions.contains(question)){
                        questions.add(question);
                        System.out.println(question);
                        for (Object answer:answers){
                            String a=answer.toString();
                            System.out.println(a);
                        }
                        String URL ="http://www.baidu.com/s?wd="+ URLEncoder.encode(question, "UTF-8");
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+URL);
                        String num= baiduSearch.findbaidu(question,answers);
                        String num2= baiduSearch.findbaidu2(question,answers);
                        System.out.println("第一种权重为：\n"+num);
                        System.out.println("第二种权重为：\n"+num2);
                    }

                    long endTime = System.currentTimeMillis();    //获取结束时间
                    // System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
                }
                else {
                    long endTime = System.currentTimeMillis();    //获取结束时间
                    // System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
                }

    }

    public void run() {
        while (true){
            try {
                Answers();
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }

    }
}
