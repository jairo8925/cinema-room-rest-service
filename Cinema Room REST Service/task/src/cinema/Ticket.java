package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

    @JsonProperty("seat")
    private Seat seat;

    @JsonProperty("token")
    private String token;

    public Ticket(Seat seat, String token) {
        this.seat = seat;
        this.token = token;
    }

    public Ticket() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
