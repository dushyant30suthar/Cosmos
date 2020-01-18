package in.obvious.assignments.cosmos.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DomainRequestError {
    @SerializedName("message")
    @Expose
    private String errorMessage;

    @SerializedName("statusCode")
    @Expose
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
