package br.com.ecommerce.ecommerce.exceptions;

public class ItemPedidoNaoEncontradoException extends RuntimeException {
    public ItemPedidoNaoEncontradoException(String message) {
        super(message);
    }
}
