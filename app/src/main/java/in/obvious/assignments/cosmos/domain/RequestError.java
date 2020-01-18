package in.obvious.assignments.cosmos.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestError {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
