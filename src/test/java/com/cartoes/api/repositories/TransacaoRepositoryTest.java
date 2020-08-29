package com.cartoes.api.repositories;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cartoes.api.entities.Transacao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TransacaoRepositoryTest {

	@Autowired
	private TransacaoRepository transacaoRepository;  
	
	private Transacao transacaoTeste;
	
	private void CriarTransacaoTestes() throws ParseException {
		
		transacaoTeste = new Transacao();
		
		transacaoTeste.setDataTransacao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		transacaoTeste.setCnpj("18808626000194");
		transacaoTeste.setValor(1500.00);
		transacaoTeste.setQdtParcelas(3);
		transacaoTeste.setJuros(0.05);
		
	}
	
	@Before
	public void setUp() throws Exception {
		
		CriarTransacaoTestes();
		transacaoRepository.save(transacaoTeste);
		
	}
	
	@Test
	public void testFindById() {	
		
		Transacao transacao = transacaoRepository.findById(transacaoTeste.getId()).get();
		assertEquals(transacaoTeste.getId(), transacao.getId());
		
	}

	@After
	public void tearDown() throws Exception {
		
		transacaoRepository.deleteAll();
		
	}
}

