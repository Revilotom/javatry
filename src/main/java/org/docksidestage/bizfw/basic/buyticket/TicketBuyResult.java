package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.Ticket;

public class TicketBuyResult {
    Ticket ticket;
    public Ticket getTicket() {
        return ticket;
    }
    public int getChange() {
        return change;
    }
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }
    int change;
}
