package org.izba.first_rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.izba.first_rest.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage erMsg = new ErrorMessage(ex.getMessage(), 500, "something wrong");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(erMsg)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
