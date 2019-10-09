package org.docksidestage.bizfw.basic.buyticket;

public class PluralDayTicket implements TicketInterface {

    int price;
    int days;
    public PluralDayTicket(int days, int price) {
        this.days = days;
        this.price = price;
    }
    @Override
    public void doinPark() {
        if (days <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + price);
        }
        days--;

    }
    @Override
    public int getDisplayPrice() {
        return price;
    }
}
