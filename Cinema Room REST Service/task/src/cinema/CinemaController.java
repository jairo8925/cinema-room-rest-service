package cinema;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Map<String, ?> getCinema() {
        return Map.of(
                "total_rows", cinema.getTotalRows(),
                "total_columns", cinema.getTotalColumns(),
                "available_seats", cinema.getAvailableSeats()
        );
    }

    @PostMapping("/purchase")
    public Map<String, ?> postSeats(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();

        List<Seat> availableSeats = cinema.getAvailableSeats();

        if (row > 9 || row < 1 || column > 9 || column < 1) {
            // respond with 400 status code if user passed a wrong row/column number
            throw new SeatNotAvailableException("The number of a row or a column is out of bounds!");
        }

        Seat selectedSeat = new Seat(row, column);

        // check if seat is already taken
        if (!availableSeats.contains(selectedSeat)) {
            // respond with 400 status code if seat is taken
            throw new SeatNotAvailableException("The ticket has been already purchased!");
        } else {
            // remove seat from list once it is purchased
            availableSeats.remove(selectedSeat);
            return Map.of(
                    "row", selectedSeat.getRow(),
                    "column", selectedSeat.getColumn(),
                    "price", selectedSeat.getPrice()
            );
        }
    }
}
