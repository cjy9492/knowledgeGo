package thread.impl;

import thread.doThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class millionHeroThread implements doThread {

    public void run() {
        millionHero.doAnswers myThread = new millionHero.doAnswers();
        Thread thread = new Thread(myThread);
        answerGet.dangeAnswer dangeAnswer = new answerGet.dangeAnswer();
        dangeAnswer.setUrl("toutiao");
        Thread thread1 = new Thread(dangeAnswer);
        thread.start();
        thread1.start();
    }

    public void run(String session) {

    }
}