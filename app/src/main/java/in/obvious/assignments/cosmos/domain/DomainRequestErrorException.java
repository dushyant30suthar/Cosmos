package in.obvious.assignments.cosmos.domain;

/*
 * Exception to represent what's wrong with the Domain Request.*/

public class DomainRequestErrorException extends Exception {
    private DomainRequestError domainRequestError;

    public DomainRequestErrorException(DomainRequestError domainRequestError) {
        this.domainRequestError = domainRequestError;
    }

    public DomainRequestError getDomainRequestError() {
        return this.domainRequestError;
    }

    public void setDomainRequestError(DomainRequestError DomainRequestError) {
        this.domainRequestError = DomainRequestError;
    }
}