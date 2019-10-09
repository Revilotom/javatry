package org.docksidestage.bizfw.basic.objanimal;

public class BarkingProcess extends Animal {

    private static String barkWord;

    public BarkingProcess(String barkWord) {
        BarkingProcess.barkWord = barkWord;
    }

    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        downHitPoint();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }
    @Override
    protected String getBarkWord() {
        return barkWord;
    }

    protected BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

    @Override
    public void poop() {
    }
}
