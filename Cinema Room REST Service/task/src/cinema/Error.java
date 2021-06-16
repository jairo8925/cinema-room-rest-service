package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

/** This object is used for the response body of
 * a 400 (Bad Request) status code
 */
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
