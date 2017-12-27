package org.izba.first_rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.izba.first_rest.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		
		ErrorMessage erMsg = new ErrorMessage(ex.getMessage(), 404, "something wrong");
		return Response.status(Status.NOT_FOUND)
			   .entity(erMsg)
			   .build();
	}

	
}
