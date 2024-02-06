package br.com.app.space.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpServerErrorException;


public class SpaceException  extends HttpServerErrorException {
    public SpaceException(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
