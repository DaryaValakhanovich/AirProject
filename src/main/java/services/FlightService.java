package services;

import dao.FlightDao;
import entities.Flight;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private FlightService() {
    }

    private static final class FlightServiceHolder {
        private static final FlightService INSTANCE = new FlightService();
    }

    public static FlightService getInstance() {
        return FlightService.FlightServiceHolder.INSTANCE;
    }

    public Flight findById(long id){
        return FlightDao.getInstance().findById(id).orElse(null);
    }

    public Flight create(Flight flight){
        return FlightDao.getInstance().create(flight);
    }

    public List<Flight> findRightFlights(LocalDate departure, int numberOfSeats,
                                         String startAirport, String finalAirport){
        FlightDao.getInstance().findRightFlights(departure,numberOfSeats,startAirport,finalAirport).forEach(System.out::println);
        return FlightDao.getInstance().findRightFlights(departure,numberOfSeats,startAirport,finalAirport);
    }

    public List<Flight> findRightDifficultWay(LocalDate departure, int numberOfSeats,
                                         String startAirport, String finalAirport){
        List<Flight> flights = FlightDao.getInstance().findFlightsForDifficultWay(departure,numberOfSeats);
        flights = findDifficultWay2(startAirport, finalAirport, flights, new ArrayList<>());
        return flights;
    }


    private List<Flight> findDifficultWay2(String startAirport, String finalAirport,
                                           List<Flight> allFlights, List<Flight> previousFlights) {
        List<Flight> resultFlights = new ArrayList<>();
        List<Flight> probablyFlights = new ArrayList<>();
        for (Flight currentFlight : allFlights) {
            if (startAirport.equals(currentFlight.getStartAirport())){
                if (previousFlights.isEmpty() ||
                        previousFlights.get(previousFlights.size() - 1).getArrival()
                                .isBefore(currentFlight.getDeparture())) {
                    if (currentFlight.getFinalAirport().equals(finalAirport)) {
                        resultFlights.addAll(previousFlights);
                        resultFlights.add(currentFlight);
                        resultFlights.add(null);
                    } else {
                        probablyFlights.add(currentFlight);
                    }
                }
            }
        }
        for (Flight probablyFlight : probablyFlights) {
            List<Flight> newPreviousFlights = new ArrayList<>(previousFlights);
            newPreviousFlights.add(probablyFlight);
            List<Flight> newFlights =
                    findDifficultWay2(probablyFlight.getFinalAirport(), finalAirport, allFlights, newPreviousFlights);
            if (!newFlights.isEmpty()) {
                resultFlights.addAll(newFlights);
            }
        }
        return resultFlights;
    }

    public String getPrice(Flight flight, int numberOfSeats){
        return String.format("%.2f",(flight.getArrival().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - flight.getDeparture().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                * numberOfSeats * 0.00001);
    }
}