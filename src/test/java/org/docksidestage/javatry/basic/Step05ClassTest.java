/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.*;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author Tom Oliver
 */
// done tom add your name to @author. fix it on other files. by katashin (2019/10/09)
// @author に自分の名前を書きましょう。他のファイルもよろしくね。
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() throws Exception {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() throws Exception {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? =>  9
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
        assertEquals(10, sea);
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() throws Exception {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
        assertEquals(7400, sea);
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() throws Exception {
        // comment out after making the method
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        // done tom remove comment and fix to work it by katashin (2019/10/09)
        // ここはコメントを外して、動くように修正しましょう
        TicketBuyResult res = booth.buyTwoDayPassport(money);
        Integer sea = booth.getSalesProceeds() + res.getChange();
        log(sea); // should be same as money

        // and show two-day passport quantity here
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() throws Exception {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() throws Exception {
        //         comment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() throws Exception {
        // comment out after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult twoDayPassportResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = twoDayPassportResult.getTicket();
        int change = twoDayPassportResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    /**
     * Now you cannot judge ticket type "one-day or two-day?", so add method to judge it. <br>
     * (チケットをもらってもOneDayなのかTwoDayなのか区別が付きません。区別を付けられるメソッドを追加しましょう)
     */
    public void test_class_moreFix_type() throws Exception {
        // your confirmation code here

        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult twoDayPassportResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = twoDayPassportResult.getTicket();

        assertFalse(twoDayPassport.isOneDayTicket());
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Now only one use with two-day passport, so split ticket in one-day and two-day class and use interface. <br>
     * <pre>
     * o change Ticket class to interface, define doInPark(), getDisplayPrice() in it
     * o make class for one-day and class for plural days (called implementation class)
     * o make implementation classes implement Ticket interface
     * o doInPark() of plural days can execute defined times
     * </pre>
     * (TwoDayのチケットが一回しか利用できません。OneDayとTwoDayのクラスを分けてインターフェースを使うようにしましょう)
     * <pre>
     * o Ticket をインターフェース(interface)にして、doInPark(), getDisplayPrice() を定義
     * o OneDay用のクラスと複数日用のクラスを作成 (実装クラスと呼ぶ)
     * o 実装クラスが Ticket を implements するように
     * o 複数日用のクラスでは、決められた回数だけ doInPark() できるように
     * </pre>
     */
    public void test_class_moreFix_useInterface() {
        // your confirmation code here
        OneDayTicket oneDayTicket = new OneDayTicket();
        oneDayTicket.doinPark();
        try {
            oneDayTicket.doinPark();
            // done [challenge] tom using fail() is better, and think why it is better, and write reason. by katashin (2019/10/09)
            // fail()メソッドを使うともっと良いと思う。理由も考えて書いてみて欲しい by katashin (2019/10/09)

            // This is to make sure that the code above always throws an exception and does not ever execute the line below.
            fail("Should never get here");
        } catch (IllegalStateException e) {
            // done [challenge] tom using log() is better. and think why it is better, and write reason. by katashin (2019/10/09)
            // log()メソッドを使うともっと良いと思う。理由も考えてみて欲しい

            // A logger can be used to, for example, redirect output to a file or transform objects into different formats like JSON/XML.
            // This is more flexible and more easily maintained way of developing rather than using System.out.
            log("caught");
        }

        PluralDayTicket twoDays = new PluralDayTicket(2, 2000);
        twoDays.doinPark();
        twoDays.doinPark();
    }

    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder() throws Exception {
        // your confirmation code here
        // TODO Done please make buyFourDayPassport method in TicketBoot by ちーかま
        TicketBooth booth = new TicketBooth();
        TicketBuyResult fourDayTicket = booth.buyFourDayPassport(30000);
        fourDayTicket.getTicket().doInPark();
        log(fourDayTicket.getChange());
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // write confirmation code here
    }
}
