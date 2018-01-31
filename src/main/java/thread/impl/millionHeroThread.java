package thread.impl;

import thread.doThread;

/**
 * Created by chenjy on 2018-01-25.
 */
public class millionHeroThread implements doThread {

    public void run() {
        millionHero.doAnswers myThread = new millionHero.doAnswers();
        Thread thread = new Thread(myThread);
        answerGet.sohuAnswer sohuAnswer = new answerGet.sohuAnswer();
        sohuAnswer.setId(1);
        Thread thread3 = new Thread(sohuAnswer);
        thread.start();
        thread3.start();
    }

    public void run(String session) {

    }
}
