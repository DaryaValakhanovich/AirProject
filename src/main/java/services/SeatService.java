package services;

import dao.SeatDao;
import entities.Seat;

import java.util.List;

public class SeatService {
    private SeatService () {
    }

    private static final class SeatServiceHolder {
        private static final SeatService  INSTANCE = new SeatService ();
    }

    SeatDao seatDao = new SeatDao();

    public static SeatService  getInstance() {
        return SeatService.SeatServiceHolder.INSTANCE;
    }

    public void save(Integer ticketId, int numberOfFirstSeat, int amountOfSeats) {
        Seat seat = new Seat(TicketService.getInstance().findByd(ticketId),numberOfFirstSeat);
        for (int i = 0; i < amountOfSeats; i++) {
            seatDao.save(seat);
            numberOfFirstSeat--;
            seat.setNumberOfSeat(numberOfFirstSeat);
        }
    }

    public List<Seat> findByTicketId(Integer ticketId){
        return seatDao.findByTicketId(ticketId);
    }
}
