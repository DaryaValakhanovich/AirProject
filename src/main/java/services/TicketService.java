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

    TicketDao ticketDao = new TicketDao();
    SeatDao seatDao = new SeatDao();
    FlightDao flightDao = new FlightDao();

    public void create(Ticket object) {
        ticketDao.save(object);
        List<Ticket> tickets = ticketDao.findByAccountId(object.getAccount().getId());
        Ticket ticket = tickets.get(tickets.size()-1);
        SeatService.getInstance().save(ticket.getId(), object.getFlight().getNumberOfFreeSeats() ,object.getNumberOfSeats());
        flightDao.buyTicket(ticket.getFlight().getId(), object.getNumberOfSeats());
    }

   public void deactivate(Integer id){
        ticketDao.deactivate(ticketDao.findById(id));
   }

    public Ticket findByd(Integer id){
        return ticketDao.findById(id);}

   public List<Ticket> findByAccountEmail(String email){
       return ticketDao.findByAccountId(new AccountDao().findByEmail(email).getId());
      //  return null;
      //  return TicketDao.getInstance().findByAccountId(AccountDao.getInstance().findByEmail(email).getId());
   }
}
