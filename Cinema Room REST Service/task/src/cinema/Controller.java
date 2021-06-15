package cinema;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class Controller {

    private final Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Map<String, ?> getSeats() {
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
        if (row > 9 || row < 1 || column > 9 || column < 1) {
            // return error
            throw new SeatNotAvailableException("The number of a row or a column is out of bounds!");
        }
        Seat selectedSeat = new Seat(row, column);
        if (!cinema.getAvailableSeats().contains(selectedSeat)) {
            // return error
            throw new SeatNotAvailableException("The ticket has been already purchased!");
        } else {
            cinema.getAvailableSeats().remove(selectedSeat);
            return Map.of(
                    "row", selectedSeat.getRow(),
                    "column", selectedSeat.getColumn(),
                    "price", selectedSeat.getPrice()
            );
        }
    }
}
