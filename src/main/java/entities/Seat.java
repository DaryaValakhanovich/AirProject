package entities;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="ticketid")
    private Ticket ticket;
    @Column(name = "numberofseat")
    private int numberOfSeat;

    public Seat() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Seat(Ticket ticket, int seat) {
        this.ticket = ticket;
        this.numberOfSeat = seat;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int seat) {
        this.numberOfSeat = seat;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "ticket=" + ticket +
                ", seat=" + numberOfSeat +
                '}';
    }
}
