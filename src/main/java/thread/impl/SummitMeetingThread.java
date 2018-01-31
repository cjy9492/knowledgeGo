package thread.impl;

import answerget.SohuAnswer;
import summitmeeting.DoAnswers;
import thread.DoThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class SummitMeetingThread implements DoThread {
    public void run() {

    }

    public void run(String session) {
        DoAnswers myThread = new DoAnswers();
        myThread.setSessison(session);
        Thread thread = new Thread(myThread);
        SohuAnswer sohuAnswer = new SohuAnswer();
        sohuAnswer.setId(2);
        Thread thread3 = new Thread(sohuAnswer);
        thread.start();
        thread3.start();
    }
}
