package org.izba.first_rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.izba.first_rest.model.ErrorMessage;

public class SQLExceptionMapper implements ExceptionMapper<DBSqlException> {

	@Override
	public Response toResponse(DBSqlException ex) {
		ErrorMessage erMsg = new ErrorMessage(ex.getMessage(), ex.hashCode(), "");
		return Response.status(ex.hashCode())
				.entity(erMsg)
				.build();
	}

}
