package thread.impl;

import answerget.SohuAnswer;
import cheesesuperman.DoAnswers;
import thread.DoThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class ChessSupermanThread implements DoThread {
    public void run() {
        DoAnswers myThread = new DoAnswers();
        Thread thread = new Thread(myThread);
        SohuAnswer sohuAnswer = new SohuAnswer();
        sohuAnswer.setId(4);
        Thread thread3 = new Thread(sohuAnswer);
        thread.start();
        thread3.start();
    }

    public void run(String session) {

    }
}
