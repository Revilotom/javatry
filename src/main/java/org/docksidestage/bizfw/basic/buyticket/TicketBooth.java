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

    }

    private void buyPassportLogic(int money, boolean isOneDay){
        int price = isOneDay ? ONE_DAY_PRICE : TWO_DAY_PRICE;
        int quant = isOneDay ? quantity : twoDayQuantity;


        if (quant <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

//        int price = isOneDay ? ONE_DAY_PRICE : TWO_DAY_PRICE;

        if (money < price) {
            throw new TicketShortMoneyException("Short money: " + money);
        }

        salesProceeds += price;

        if (isOneDay){
            quantity--;
        }else{
            twoDayQuantity--;
        }
    }

    public Ticket buyOneDayPassport(int money) {
        buyPassportLogic(money, true);
        return new Ticket(ONE_DAY_PRICE, true);
    }
    public TicketBuyResult buyTwoDayPassport(int money) {
        buyPassportLogic(money, false);
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
