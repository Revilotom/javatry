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
package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_TWO_DAY = 10;
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200; // when 2019/06/15

    private static Map<Integer, Integer> priceMap;
    private static Map<Integer, Integer> quantityMap;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    // Done Tom never used なのは消してしまおう by katashin 2019/10/17
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        // Done Tom In this case, null of salesProceeds had meaning but erased already. write reason why you erased. by katashin (2019/10/09)
        // 消されてしまったけど、salesProceeds の nullに意味があったね。 理由を書いてほしい。
        // Because I dont want to have to check if its initialised or not, it doesn't make sense to have "null" salesProceeds.
        // Done Tom IntelliJ mentions warning below code. keep in mind to solve warnings.
        // IntelliJが警告を出しているね。消すように心がけてみよう。
        // Ok
        salesProceeds = 0;
        priceMap = new HashMap<>();
        priceMap.put(1, 7400);
        priceMap.put(2, 13200);
        priceMap.put(4, 22400);

        quantityMap = new HashMap<>();
        quantityMap.put(1, 10);
        quantityMap.put(2, 10);
        quantityMap.put(4, 10);
    }

    // Done 今のcodeだとOneDayとTwoDayのチケットしか買うことができない...。FourDayのチケットも買えるようにしてみよう by もってぃ
    private void buyPassportLogic(int money, int days) throws Exception {
        if (!quantityMap.containsKey(days) || !priceMap.containsKey(days)) {
            throw new Exception("Unknown ticket day count");
        }
        int quant = quantityMap.get(days);
        int price = priceMap.get(days);

        if (quant <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        if (money < price) {
            throw new TicketShortMoneyException("Short money: " + money);
        }

        salesProceeds += price;
        quantityMap.put(days, quantity - 1);

    }

    public Ticket buyOneDayPassport(int money) throws Exception {
        buyPassportLogic(money, 1);
        return new Ticket(ONE_DAY_PRICE, true);
    }
    public TicketBuyResult buyTwoDayPassport(int money) throws Exception {
        buyPassportLogic(money, 2);
        int change = money - TWO_DAY_PRICE;
        return new TicketBuyResult(new Ticket(TWO_DAY_PRICE, false), change);
    }

    public TicketBuyResult buyFourDayPassport(int money) throws Exception {
        buyPassportLogic(money, 4);
        int price = priceMap.get(4);
        int change = money - price;
        return new TicketBuyResult(new Ticket(price, false), change);
    }
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }
    public Integer getSalesProceeds() {
        return salesProceeds;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }
}
