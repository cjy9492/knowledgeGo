package thread.impl;

import thread.doThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class chessSupermanThread implements doThread {
    public void run() {
        cheeseSuperman.doAnswers myThread = new cheeseSuperman.doAnswers();
        Thread thread = new Thread(myThread);
        answerGet.dangeAnswer dangeAnswer = new answerGet.dangeAnswer();
        dangeAnswer.setUrl("yingke");
        Thread thread1 = new Thread(dangeAnswer);
        answerGet.sogouAnswer sogouAnswer = new answerGet.sogouAnswer();
        sogouAnswer.setApp("zscr");
        Thread thread2 = new Thread(sogouAnswer);
        thread.start();
        thread1.start();
        thread2.start();
    }

    public void run(String session) {

    }
}
