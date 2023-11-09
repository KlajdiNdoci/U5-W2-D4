package KlajdiNdoci.U5W2D3.exceptions;

import KlajdiNdoci.U5W2D3.payloads.ErrorsResponseWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseWithListDTO handleBadRequest(BadRequestException e){
        if (e.getErrorList() != null){
            List<String> errorsList = e.getErrorList().stream().map(ObjectError::getDefaultMessage).toList();
        return new ErrorsResponseWithListDTO (e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsResponseWithListDTO (e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException e){
        return new ErrorsPayload(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGenericError(Exception e){
        return new ErrorsPayload("server error", new Date());
    }
}
