package br.com.ecommerce.ecommerce.exceptions;

public class PagamentoNaoEncontradoException extends RuntimeException {
    public PagamentoNaoEncontradoException(String message) {
        super(message);
    }
}
