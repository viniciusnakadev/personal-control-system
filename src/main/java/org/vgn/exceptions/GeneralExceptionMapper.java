package org.vgn.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {

        ErrorResponse errorResponse;

        if (exception instanceof BusinessException) {
            errorResponse = new ErrorResponse(exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type("application/json")
                    .build();
        } else if (exception instanceof NotFoundException) {
            errorResponse = new ErrorResponse(exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type("application/json")
                    .build();        
        } else {
            errorResponse = new ErrorResponse("Internal server error");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorResponse)
                    .type("application/json")
                    .build();
        }
    }

}
