package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    @JsonProperty("error")
    private String error;

    // getters and setters

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Error(String error) {
        this.error = error;
    }

    public Error() {}


}
