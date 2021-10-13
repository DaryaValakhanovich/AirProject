package entities;

import javax.persistence.*;

@Entity
@Table(name = "planes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Plane{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
       @Column(name = "numberofseats")
    private int numberOfSeats;
       @Column(name = "weight")
    private double weight;
       @Column(name = "cruisingspeed")
    private double cruisingSpeed;
      @Column(name = "model", length = 250)
    private String model;
      @Column(name = "company", length = 250)
    private String company;
       @Column(name = "maxflightaltitude")
    private double maxFlightAltitude;
       @Column(name = "maxrangeofflight")
    private double maxRangeOfFlight;

    public Plane() {
    }

    public Plane(int numberOfSeats, double weight, double cruisingSpeed, String model, String company, double maxFlightAltitude, double maxRangeOfFlight) {
        this.numberOfSeats = numberOfSeats;
        this.weight = weight;
        this.cruisingSpeed = cruisingSpeed;
        this.model = model;
        this.company = company;
        this.maxFlightAltitude = maxFlightAltitude;
        this.maxRangeOfFlight = maxRangeOfFlight;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(double cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    public void setMaxFlightAltitude(double maxFlightAltitude) {
        this.maxFlightAltitude = maxFlightAltitude;
    }

    public double getMaxRangeOfFlight() {
        return maxRangeOfFlight;
    }

    public void setMaxRangeOfFlight(double maxRangeOfFlight) {
        this.maxRangeOfFlight = maxRangeOfFlight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "numberOfSeats=" + numberOfSeats +
                ", weight=" + weight +
                ", cruisingSpeed=" + cruisingSpeed +
                ", model='" + model + '\'' +
                ", company='" + company + '\'' +
                ", maxFlightAltitude=" + maxFlightAltitude +
                ", maxRangeOfFlight=" + maxRangeOfFlight +
                '}';
    }
}
