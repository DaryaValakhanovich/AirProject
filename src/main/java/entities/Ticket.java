package entities;

public class Ticket extends BaseEntity {
    private long accountId;
    private Flight flight;
    private int numberOfSeats;
    private boolean active;

    public Ticket() {
        active = true;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "accountId=" + accountId +
                ", flight=" + flight +
                ", numberOfSeats=" + numberOfSeats +
                ", active=" + active +
                '}';
    }
}
