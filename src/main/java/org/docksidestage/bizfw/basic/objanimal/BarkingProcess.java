package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarkingProcess {


    private static Logger logger;

    public BarkingProcess(Logger logger) {
        this.logger = logger;
    }

//    public BarkedSound bark() {
//        breatheIn();
//        prepareAbdominalMuscle();
//        String barkWord = getBarkWord();
//        BarkedSound barkedSound = doBark(barkWord);
//        return barkedSound;
//    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
//        downHitPoint();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
//        downHitPoint();
    }


    protected BarkedSound doBark(String barkWord) {
//        downHitPoint();
        return new BarkedSound(barkWord);
    }

}
