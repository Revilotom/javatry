package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    Ticket ticket;
    int change;
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public int getChange() {
        return change;
    }
}
