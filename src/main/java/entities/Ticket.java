package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tickets", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="accountid", nullable=false)
    private Account account;
    @ManyToOne
    @JoinColumn(name="flightid")
    private Flight flight;
    @Column(name = "numberOfSeats")
    private int numberOfSeats;
    @Column(name = "active")
    private boolean active;

    public Ticket(Account account, Flight flight, int numberOfSeats) {
        this.account = account;
        this.flight = flight;
        this.numberOfSeats = numberOfSeats;
        active = true;
    }


    public Ticket() {
        active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
                "accountId=" + account+
                ", flight=" + flight +
                ", numberOfSeats=" + numberOfSeats +
                ", active=" + active +
                '}';
    }
}
