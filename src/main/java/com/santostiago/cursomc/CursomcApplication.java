package com.santostiago.cursomc;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.santostiago.cursomc.domain.Categoria;
import com.santostiago.cursomc.domain.Cidade;
import com.santostiago.cursomc.domain.Cliente;
import com.santostiago.cursomc.domain.Endereco;
import com.santostiago.cursomc.domain.Estado;
import com.santostiago.cursomc.domain.Produto;
import com.santostiago.cursomc.domain.enuns.TipoCliente;
import com.santostiago.cursomc.repositories.CategoriaRepository;
import com.santostiago.cursomc.repositories.CidadeRepository;
import com.santostiago.cursomc.repositories.ClienteRepository;
import com.santostiago.cursomc.repositories.EnderecoRepository;
import com.santostiago.cursomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//CATEGORIA
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
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
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
