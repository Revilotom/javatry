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
    private int twoDayQuantity = MAX_TWO_DAY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        salesProceeds = new Integer(0);
        priceMap = new HashMap();
        priceMap.put(1, 7400);
        priceMap.put(2, 13200);

        quantityMap = new HashMap();
        quantityMap.put(1, 10);
        quantityMap.put(2, 10);
    }

    // TODO Done 今のcodeだとOneDayとTwoDayのチケットしか買うことができない...。FourDayのチケットも買えるようにしてみよう by もってぃ
    private void buyPassportLogic(int money, int days) throws Exception {
        if (!quantityMap.containsKey(days) || !priceMap.containsKey(days)){
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
        int change=  money - TWO_DAY_PRICE;
        return new TicketBuyResult(new Ticket(TWO_DAY_PRICE, false), change);
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

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
