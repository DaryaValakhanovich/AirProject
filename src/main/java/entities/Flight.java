package entities;

import java.time.LocalDateTime;

public class Flight extends BaseEntity {
    private long id;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int numberOfFreeSeats;
    private String startAirport;
    private String finalAirport;
    private Plane plane;
    private String price;

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
