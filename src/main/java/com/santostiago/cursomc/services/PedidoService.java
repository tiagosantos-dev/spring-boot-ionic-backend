package com.santostiago.cursomc.services;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santostiago.cursomc.domain.PagamentoComBoleto;
import com.santostiago.cursomc.domain.Pedido;
import com.santostiago.cursomc.domain.enuns.EstadoPagamento;
import com.santostiago.cursomc.repositories.ItemPedidoRepository;
import com.santostiago.cursomc.repositories.PagamentoRepository;
import com.santostiago.cursomc.repositories.PedidoRepository;
import com.santostiago.cursomc.repositories.ProdutoRepository;
import com.santostiago.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemRepository;
	
	
	public Pedido buscar(Integer id) {
		
		
	Optional<Pedido> obj = repo.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException(
			"Objeto não encontrado!! ID"+ id +"Tipo:"+Pedido.class.getName()));
			
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date(0));
		obj.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagt =(PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagt,obj.getInstante());
			
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
//		
//		for(ItemPedido x: obj.getItens()) {
//			x.setDesconto(0.0);
//			x.setPreco(82.33);
//			x.setProduto(x.getProduto());
//			x.setQuantidade(x.getQuantidade());
//			x.setPedido(obj);
//			
//		}
		
		itemRepository.saveAll(obj.getItens());
		return obj;
	}
}
