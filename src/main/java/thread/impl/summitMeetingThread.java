package thread.impl;

import answerGet.sohuAnswer;
import thread.doThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class summitMeetingThread implements doThread {
    public void run() {

    }

    public void run(String session) {
        summitMeeting.doAnswers myThread = new summitMeeting.doAnswers();
        myThread.setSessison(session);
        Thread thread = new Thread(myThread);
        answerGet.sohuAnswer sohuAnswer = new answerGet.sohuAnswer();
        sohuAnswer.setId(2);
        Thread thread3 = new Thread(sohuAnswer);
        thread.start();
        thread3.start();
    }
}
