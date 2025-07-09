package br.com.ecommerce.ecommerce.services;

import br.com.ecommerce.ecommerce.models.ItemPedidoEntity;
import br.com.ecommerce.ecommerce.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoEntity salvar(ItemPedidoEntity itemPedidoEntity) {
        return itemPedidoRepository.save(itemPedidoEntity);
    }

    public ItemPedidoEntity buscarPorId(UUID id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do pedido com ID " + id + " não encontrado"));
    }

    public List<ItemPedidoEntity> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    public void deletarPorId(UUID id) {
        if (!itemPedidoRepository.existsById(id)) {
            throw new RuntimeException("Item do pedido com ID " + id + " não encontrado para exclusão");
        }
        itemPedidoRepository.deleteById(id);
    }

    public ItemPedidoEntity atualizar(UUID id, ItemPedidoEntity novoItem) {
        ItemPedidoEntity existente = buscarPorId(id);

        existente.setProduto(novoItem.getProduto());
        existente.setQuantidade(novoItem.getQuantidade());
        existente.setPrecoUnitario(novoItem.getPrecoUnitario());
        existente.setPedido(novoItem.getPedido());

        return itemPedidoRepository.save(existente);
    }
}
