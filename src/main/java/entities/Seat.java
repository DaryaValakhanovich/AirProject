package entities;

public class Seat {
    private long ticketId;
    private int seat;


    public Seat() {
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "ticketId=" + ticketId +
                ", seat=" + seat +
                '}';
    }
}
