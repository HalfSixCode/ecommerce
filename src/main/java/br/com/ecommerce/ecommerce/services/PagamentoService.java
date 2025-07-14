package br.com.ecommerce.ecommerce.services;

import br.com.ecommerce.ecommerce.dtos.request.PagamentoRequestDTO;
import br.com.ecommerce.ecommerce.models.PagamentoEntity;
import br.com.ecommerce.ecommerce.models.PedidoEntity;
import br.com.ecommerce.ecommerce.models.enums.FormaPagamento;
import br.com.ecommerce.ecommerce.models.enums.StatusPagamento;
import br.com.ecommerce.ecommerce.repository.PagamentoRepository;
import br.com.ecommerce.ecommerce.repository.PedidoRepository;
import br.com.ecommerce.ecommerce.util.PagamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    public PagamentoEntity salvar(PagamentoRequestDTO pagamentoRequestDTO) {
        PedidoEntity pedido = pedidoRepository.findById(pagamentoRequestDTO.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        PagamentoEntity pagamento = pagamentoMapper.toEntity(pagamentoRequestDTO, pedido);
       return pagamentoRepository.save(pagamento);

    }

    public PagamentoEntity buscarPorId(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public List<PagamentoEntity> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    public PagamentoEntity buscarPagamentoPeloPedido(UUID pedidoId) {
        return pagamentoRepository.findByPedido_PedidoId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pagamento para esse pedido não encontrado"));
    }

    public List<PagamentoEntity> buscarPorStatusDoPedido(StatusPagamento statusPagamento) {
        return pagamentoRepository.findByStatusPagamento(statusPagamento);
    }

    public List<PagamentoEntity> listarPorFormaDePagamento(FormaPagamento formaPagamento) {
        return pagamentoRepository.findByFormaPagamento(formaPagamento);
    }

    public void deletarPorId(UUID id) {
        pagamentoRepository.deleteById(id);
    }

    public PagamentoEntity atualizar(UUID id, PagamentoEntity novoPagamento) {
        PagamentoEntity existente = buscarPorId(id);

        existente.setPedidoId(novoPagamento.getPedidoId());
        existente.setPagamentoId(novoPagamento.getPagamentoId());
        existente.setDataPagamento(novoPagamento.getDataPagamento());
        existente.setStatusPagamento(novoPagamento.getStatusPagamento());
        existente.setFormaPagamento(novoPagamento.getFormaPagamento());
        existente.setValorPago(novoPagamento.getValorPago());

        return pagamentoRepository.save(existente);
    }
}
