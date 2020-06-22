package com.santostiago.cursomc;


//import java.text.SimpleDateFormat; 
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.santostiago.cursomc.domain.Categoria;
import com.santostiago.cursomc.domain.Cidade;
import com.santostiago.cursomc.domain.Cliente;
import com.santostiago.cursomc.domain.Endereco;
import com.santostiago.cursomc.domain.Estado;
import com.santostiago.cursomc.domain.ItemPedido;
import com.santostiago.cursomc.domain.Pagamento;
import com.santostiago.cursomc.domain.PagamentoComBoleto;
import com.santostiago.cursomc.domain.PagamentoComCartao;
import com.santostiago.cursomc.domain.Pedido;
import com.santostiago.cursomc.domain.Produto;
import com.santostiago.cursomc.domain.enuns.EstadoPagamento;
import com.santostiago.cursomc.domain.enuns.TipoCliente;
import com.santostiago.cursomc.repositories.CategoriaRepository;
import com.santostiago.cursomc.repositories.CidadeRepository;
import com.santostiago.cursomc.repositories.ClienteRepository;
import com.santostiago.cursomc.repositories.EnderecoRepository;
import com.santostiago.cursomc.repositories.EstadoRepository;
import com.santostiago.cursomc.repositories.ItemPedidoRepository;
import com.santostiago.cursomc.repositories.PagamentoRepository;
import com.santostiago.cursomc.repositories.PedidoRepository;
import com.santostiago.cursomc.repositories.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//CATEGORIA
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Manutecao");
		Categoria cat5 = new Categoria(null, "Equipamentos");
		Categoria cat6 = new Categoria(null, "Casa");
		Categoria cat7 = new Categoria(null, "Festas");
		
		//PRODUTO
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//ESTADO
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//CIDADE
		Cidade c1 = new Cidade(null, "Uberlândia",est1 );
		Cidade c2 = new Cidade(null, "São Paulo",est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		//CLIENTE
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36578912377",TipoCliente.PESSOAFISICA );
		cli1.getTelefones().addAll(Arrays.asList("98398388","44343444"));
		
		//ENDERECO
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apt 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38998948", cli1, c2);
		
		//PEDIDO
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date dataAtual = new Date();
		
		Pedido ped1 = new Pedido(null, dataAtual, cli1, e1);
		Pedido ped2 = new Pedido(null, dataAtual, cli1, e2);
		
		
		//PAGAMENTO
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2,dataAtual,dataAtual);
		ped2.setPagamento(pagto2);
		
		//ITEM PEDIDO
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		cli1.getPedido().addAll(Arrays.asList(ped1,ped2));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
