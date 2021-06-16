package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

    @JsonProperty("row")
    private int row;

    @JsonProperty("column")
    private int column;

    @JsonProperty("price")
    private int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = setPrice();
    }

    public Seat() {}

    // price is pre-determined by row
    // number and thus it is immutable
    private int setPrice() {
        if (row <= 4) {
            return 10;
        } else {
            return 8;
        }
    }

    // getters and setters

    public int getPrice() {
        return price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Seat) {
            Seat seat = (Seat) obj;
            return seat.row == this.row && seat.column == this.column;
        } else {
            return false;
        }
    }
}
