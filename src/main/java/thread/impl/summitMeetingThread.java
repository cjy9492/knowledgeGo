package thread.impl;

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
        thread.setPriority(10);
        answerGet.dangeAnswer dangeAnswer = new answerGet.dangeAnswer();
        dangeAnswer.setUrl("chongding");
        Thread thread1 = new Thread(dangeAnswer);
        thread1.setPriority(5);
        thread.start();
        thread1.start();
    }
}
