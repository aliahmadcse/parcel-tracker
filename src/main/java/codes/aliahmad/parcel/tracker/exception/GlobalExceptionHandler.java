package codes.aliahmad.parcel.tracker.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler
{
  @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<ExceptionResponse> handleError404(ServletException exception, HttpServletRequest request)
  {
    return new ResponseEntity<>(getServletExceptionResponse(exception, request), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception)
  {
    if (exception instanceof ApplicationException appException)
    {
      return handleApplicationException(appException);
    }
    else if (exception instanceof HttpMessageNotReadableException notReadableException)
    {
      return handleFormattingException(notReadableException);
    }

    log.error("Exception: ", exception);
    return new ResponseEntity<>(ExceptionResponse.of(ErrorCode.UNKNOWN_ERROR_OCCURRED, "Unknown error occurred please contact site admin"), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex)
  {
    String errorMessage = ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));
    return new ResponseEntity<>(ExceptionResponse.of(ErrorCode.BAD_INPUT_PARAMETER,
            errorMessage),
            HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<ExceptionResponse> handleFormattingException(HttpMessageNotReadableException exception)
  {
    String message;

    if (exception.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException)
    {
      message = "Parsing error, Boolean/Date/Number/Enum format might not be correct";
    }
    else
    {
      message = "Parser error, please make sure JSON is properly formatted";
    }

    return new ResponseEntity<>(ExceptionResponse.of(ErrorCode.UNABLE_TO_MAP_OBJECT,
            message),
            HttpStatus.BAD_REQUEST);
  }

  protected ResponseEntity<ExceptionResponse> handleApplicationException(ApplicationException exception)
  {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if (exception instanceof RecordNotFoundException)
    {
      status = HttpStatus.NOT_FOUND;
    }

    return new ResponseEntity<>(ExceptionResponse.of(exception), status);
  }

  private ExceptionResponse getServletExceptionResponse(ServletException exception, HttpServletRequest request)
  {
    ExceptionResponse response;
    if (exception instanceof NoHandlerFoundException)
    {
      response = ExceptionResponse.of(ErrorCode.ENDPOINT_NOT_FOUND,
              "END POINT not found " + request.getRequestURI());
    }
    else
    {
      response = ExceptionResponse.of(ErrorCode.HTTP_METHOD_NOT_SUPPORTED,
              "HTTP Method Not supported: " + request.getMethod());
    }

    return response;
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<ExceptionResponse> handleConstraintViolationExceptions(ConstraintViolationException ex)
  {
    String errorMessage = ex.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessageTemplate)
            .collect(Collectors.joining(", "));

    return new ResponseEntity<>(ExceptionResponse.of(ErrorCode.BAD_INPUT_PARAMETER, errorMessage),
            HttpStatus.BAD_REQUEST);
  }
}
