package com.example.kotlindemo.exception

import UserNotFoundException
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDate

@RestControllerAdvice
class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handlerValidException(exception: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
    val errors: MutableMap<String, String?> = HashMap()
    exception.bindingResult.allErrors.stream().forEach{
      erro: ObjectError ->
      val fieldName: String = (erro as FieldError).field
      val messageError: String? = erro.defaultMessage
      errors[fieldName] = messageError
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
      ExceptionDetails(
        title = "Bad Request, Consult the documentation",
        timestamp = LocalDate.now(),
        status = HttpStatus.BAD_REQUEST.value(),
        exception = exception.javaClass.toString(),
        details = errors
      )
    )
  }
  @ExceptionHandler(DataAccessException::class)
  fun handlerValidException(exception: DataAccessException): ResponseEntity<ExceptionDetails> {

    return ResponseEntity.status(HttpStatus.CONFLICT).body(
      ExceptionDetails(
        title = "Conflict, Some argument already exists in DataBase",
        timestamp = LocalDate.now(),
        status = HttpStatus.CONFLICT.value(),
        exception = exception.javaClass.toString(),
        details = mutableMapOf(exception.cause.toString() to exception.message)
      )
    )
  }

  @ExceptionHandler(IllegalAccessException::class)
  fun handlerValidException(exception: IllegalAccessException): ResponseEntity<ExceptionDetails> {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(
        ExceptionDetails(
          title = "Illegal exception, Consult the documentation",
          timestamp = LocalDate.now(),
          status = HttpStatus.BAD_REQUEST.value(),
          exception = exception.javaClass.toString(),
          details = mutableMapOf(exception.cause.toString() to exception.message)
        )
      )
  }

  @ExceptionHandler(UserNotFoundException::class)
  fun handlerValidException(exception: UserNotFoundException): ResponseEntity<ExceptionDetails> {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(
        ExceptionDetails(
          title = "User not found, set a valid user",
          timestamp = LocalDate.now(),
          status = HttpStatus.NOT_FOUND.value(),
          exception = exception.javaClass.toString(),
          details = mutableMapOf(exception.cause.toString() to exception.message)
        )
      )
  }
}