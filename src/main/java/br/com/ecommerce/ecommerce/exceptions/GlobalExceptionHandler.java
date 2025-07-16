package br.com.ecommerce.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> buildResponse (HttpStatus status, String mensagem){
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("erro", status.getReasonPhrase());
        body.put("mensagem", mensagem);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handlePedidoNaoEncontrado (PedidoNaoEncontradoException ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ItemPedidoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleItemPedidoNaoEncontrado (ItemPedidoNaoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PagamentoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handlePagamentoNaoEncontrado (PagamentoNaoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
