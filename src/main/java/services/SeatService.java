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

    public static SeatService  getInstance() {
        return SeatService.SeatServiceHolder.INSTANCE;
    }

    public List<Seat> findByTicketId(long ticketId){
        return SeatDao.getInstance().findByTicketId(ticketId);
    }
}
