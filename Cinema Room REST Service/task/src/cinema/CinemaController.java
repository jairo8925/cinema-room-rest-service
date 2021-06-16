package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema(9, 9);
    private final List<Ticket> ticketList = new ArrayList<>();

    @GetMapping("/seats")
    public Map<String, ?> getSeats() {
        return Map.of(
                "total_rows", cinema.getTotalRows(),
                "total_columns", cinema.getTotalColumns(),
                "available_seats", cinema.getAvailableSeats()
        );
    }

    @PostMapping("/purchase")
    public Map<String, ?> purchaseTicket(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();

        List<Seat> availableSeats = cinema.getAvailableSeats();

        if (row > 9 || row < 1 || column > 9 || column < 1) {
            // respond with 400 status code if user passed a wrong row/column number
            throw new InvalidSeatException("The number of a row or a column is out of bounds!");
        }

        Seat selectedSeat = new Seat(row, column);

        // check if seat is already taken
        if (!availableSeats.contains(selectedSeat)) {
            // respond with 400 status code if seat is taken
            throw new InvalidSeatException("The ticket has been already purchased!");
        } else {
            // remove seat from list once it is purchased
            availableSeats.remove(selectedSeat);
            // generate a random token
            String token = UUID.randomUUID().toString();
            // add bought ticket to the list of tickets
            ticketList.add(new Ticket(selectedSeat, token));
            return Map.of(
                    "token", token,
                    "ticket", selectedSeat
            );
        }
    }

    @PostMapping("/return")
    public Map<String, ?> refundTicket(@RequestBody Ticket t) {
        String token = t.getToken();
        Ticket returned_ticket = null;

        // find the ticket that the token belongs to
        for (Ticket ticket : ticketList) {
            if (ticket.getToken().equals(token)) {
                returned_ticket = ticket;
            }
        }

        if (returned_ticket == null) {
            // respond with 400 status code if token isn't valid
            throw new InvalidTokenException("Wrong token!");
        } else {
            // remove returned ticket from the ticket list
            ticketList.remove(returned_ticket);
            Seat seat = returned_ticket.getSeat();
            // add the seat back to the available seats
            cinema.getAvailableSeats().add(seat);
            return Map.of(
                    "returned_ticket", seat
            );
        }
    }

    @PostMapping("/stats")
    public Map<String, ?> showStats(@RequestParam(required = false) String password) {
        if (!"super_secret".equals(password)) {
            // return error
            throw new WrongPasswordException("The password is wrong!");
        }

        int income = 0;
        for (Ticket t : ticketList) {
            income += t.getSeat().getPrice();
        }

        int available_seats = cinema.getAvailableSeats().size();
        int purchased_tickets = ticketList.size();

        return Map.of(
                "current_income", income,
                "number_of_available_seats", available_seats,
                "number_of_purchased_tickets", purchased_tickets
        );
    }



}
