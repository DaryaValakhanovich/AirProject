package services;

import dao.AccountDao;
import dao.FlightDao;
import dao.SeatDao;
import dao.TicketDao;
import entities.Ticket;

import java.util.List;

public class TicketService {
    private TicketService() {
    }

    private static final class TicketServiceHolder {
        private static final TicketService INSTANCE = new TicketService();
    }

    public static TicketService getInstance() {
        return TicketService.TicketServiceHolder.INSTANCE;
    }

    public Ticket create(Ticket object) {
        Ticket ticket = TicketDao.getInstance().create(object);
        SeatDao.getInstance().create(ticket.getId(), ticket.getFlight().getNumberOfFreeSeats() ,ticket.getNumberOfSeats());
        FlightDao.getInstance().buyTicket(ticket.getId(), ticket.getNumberOfSeats());
        return ticket;
    }

   public void deactivate(long id){
        TicketDao.getInstance().findById(id).ifPresent(value -> TicketDao.getInstance().deactivate(value));
   }

   public List<Ticket> findByAccountEmail(String email){
        return TicketDao.getInstance().findByAccountId(AccountDao.getInstance().findByEmail(email).getId());
   }
}
