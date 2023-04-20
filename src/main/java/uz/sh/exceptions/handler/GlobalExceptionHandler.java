//package uz.sh.exceptions.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import uz.fems.exceptions.BadRequestException;
//import uz.fems.exceptions.NotFoundException;
//import uz.fems.exceptions.ValidatorException;
//import uz.fems.response.AppErrorDto;
//import uz.fems.response.DataDTO;
//import uz.fems.response.ResponseEntity;
//
//import java.sql.SQLException;
//
//
//@Slf4j
//@RestController
//@ControllerAdvice
//public class GlobalExceptionHandler extends JsonRpcClientErrorHandler {
//
//
//    @Override
//    protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
//                                                                                            HttpHeaders headers,
//                                                                                            HttpStatus status,
//                                                                                            WebRequest request ) {
//        StringBuilder message = new StringBuilder();
//        for ( FieldError error : ex.getBindingResult().getFieldErrors() ) {
//            message.append("Cause : ").append(error.getDefaultMessage()).append("\n");
//        }
//
//        AppErrorDto appErrorDto = new AppErrorDto(HttpStatus.BAD_REQUEST, message.toString());
//        return new org.springframework.http.ResponseEntity<>(new DataDTO<>(appErrorDto), headers, status);
//    }
//
//
//    @ExceptionHandler(value = {SQLException.class, RuntimeException.class})
//    public ResponseEntity<DataDTO<AppErrorDto>> handle500( RuntimeException e, WebRequest webRequest ) {
//        log.error("Internal server Error occurred with Cause : {}, request : {}", e.getMessage(), webRequest.getDescription(true));
//        return new ResponseEntity<>
//                (new DataDTO<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
//    }
//
//    @ExceptionHandler(value = {BadRequestException.class})
//    public ResponseEntity<DataDTO<AppErrorDto>> handle400( BadRequestException e, WebRequest webRequest ) {
//        return new ResponseEntity<>
//                (new DataDTO<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
//    }
//
//    @ExceptionHandler(value = {NotFoundException.class})
//    public ResponseEntity<DataDTO<AppErrorDto>> handle404( NotFoundException e, WebRequest webRequest ) {
//        return new ResponseEntity<>
//                (new DataDTO<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
//    }
//
//    @ExceptionHandler(value = {ValidatorException.class})
//    public ResponseEntity<DataDTO<AppErrorDto>> handle401( ValidatorException e, WebRequest webRequest ) {
//        return new ResponseEntity<>
//                (new DataDTO<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
//    }
//
//
//}
