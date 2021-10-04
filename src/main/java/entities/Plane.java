package entities;

public class Plane extends BaseEntity {
    private int numberOfSeats;
    private double weight;
    private double cruisingSpeed;
    private String model;
    private String company;
    private double maxFlightAltitude;
    private double maxRangeOfFlight;

    public Plane() {
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
