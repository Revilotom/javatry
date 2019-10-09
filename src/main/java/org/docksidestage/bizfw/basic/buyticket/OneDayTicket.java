package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements TicketInterface {

    boolean inPark = false;
    int price = 7400;

    @Override
    public void doinPark() {
        if (inPark) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + price);
        }
        inPark = true;

    }
    @Override
    public int getDisplayPrice() {
        return price;
    }
}
