package in.obvious.assignments.cosmos.domain;

/*
 * Exception to represent what's wrong with the Domain Request.*/

public class RequestErrorException extends Exception {
    private RequestError requestError;

    public RequestErrorException(RequestError requestError) {
        this.requestError = requestError;
    }

    public RequestError getRequestError() {
        return this.requestError;
    }

    public void setRequestError(RequestError RequestError) {
        this.requestError = RequestError;
    }
}