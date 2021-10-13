package entities;


import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "flights", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Flight{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //как перевести timestamp в LocalDateTime
       @Column(name = "departure", columnDefinition="TIMESTAMP")
    private LocalDateTime departure;
    //как перевести timestamp в LocalDateTime
       @Column(name = "arrival", columnDefinition="TIMESTAMP")
    private LocalDateTime arrival;
       @Column(name = "numberOfFreeSeats")
    private int numberOfFreeSeats;
      @Column(name = "startAirport", length = 250)
    private String startAirport;
      @Column(name = "finalAirport", length = 250)
    private String finalAirport;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "planeid", referencedColumnName = "id")
    private Plane plane;
    @Transient
    private String price;

    public Flight() {
    }

    public Flight(LocalDateTime departure, LocalDateTime arrival, String startAirport, String finalAirport, Plane plane) {
        this.departure = departure;
        this.arrival = arrival;
        this.startAirport = startAirport;
        this.finalAirport = finalAirport;
        this.plane = plane;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public int getNumberOfFreeSeats() {
        return numberOfFreeSeats;
    }

    public void setNumberOfFreeSeats(int numberOfFreeSeats) {
        this.numberOfFreeSeats = numberOfFreeSeats;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getFinalAirport() {
        return finalAirport;
    }

    public void setFinalAirport(String finalAirport) {
        this.finalAirport = finalAirport;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", numberOfFreeSeats=" + numberOfFreeSeats +
                ", startAirport='" + startAirport + '\'' +
                ", finalAirport='" + finalAirport + '\'' +
                ", plane=" + plane +
                '}';
    }
}
