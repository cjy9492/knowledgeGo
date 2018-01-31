package thread.impl;

import answerget.SohuAnswer;
import millionhero.DoAnswers;
import thread.DoThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class MillionHeroThread implements DoThread {

    public void run() {
        DoAnswers myThread = new DoAnswers();
        Thread thread = new Thread(myThread);
        SohuAnswer sohuAnswer = new SohuAnswer();
        sohuAnswer.setId(1);
        Thread thread3 = new Thread(sohuAnswer);
        thread.start();
        thread3.start();
    }

    public void run(String session) {

    }
}
