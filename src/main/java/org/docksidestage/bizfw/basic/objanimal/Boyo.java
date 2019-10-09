package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.Animal;

public class Boyo extends Animal {
    @Override
    public String getBarkWord() {
        return "yeahhhh boooooooiiiiiiiiiii~";
    }
    @Override
    public void poop() {
        System.out.println("You might want to give it a couple minutes before you go in there...");
    }
}