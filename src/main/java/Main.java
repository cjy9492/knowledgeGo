

import baidutieba.DoAnswers;
import summitmeeting.utils.TestToken;
import thread.DoThread;
import thread.impl.ChessSupermanThread;
import thread.impl.MillionHeroThread;
import thread.impl.SummitMeetingThread;
import utils.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chenjy on 2018-01-09.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        List questions=new ArrayList();
        Scanner sc= new Scanner(System.in);
        System.out.println("1:冲顶大会模式 2:百万英雄模式 3:芝士超人模式  4.百度贴吧答题");
        String m=sc.nextLine();//输入字符串
        String session= PropertiesUtil.readValue("session");
        String cookies= PropertiesUtil.readValue("cookies");
        if("1".equals(m)){
            System.out.println("进入冲顶大会模式");
            if(TestToken.getanswaer(session)){
                System.out.println("token校验成功");
            }
            else {
                System.out.println("token已经过期了");
            }
            DoThread myThread = new SummitMeetingThread();
            myThread.run(session);
        }
        if("2".equals(m)){
            System.out.println("进入百万英雄模式");
            DoThread myThread = new MillionHeroThread();
            myThread.run();
        }
        if("3".equals(m)){
            System.out.println("进入芝士超人模式");
            DoThread myThread = new ChessSupermanThread();
            myThread.run();
        }
        if("4".equals(m)){
            System.out.println("进入百度贴吧模式");
            if(cookies==null){
                System.out.println("请配置cookies");
            }
            while (true) {
                try{
                DoAnswers.Answers(questions, cookies);
                Thread.sleep(500);}
                catch (Exception e){
                    continue;
                }
            }
        }
    }


}
