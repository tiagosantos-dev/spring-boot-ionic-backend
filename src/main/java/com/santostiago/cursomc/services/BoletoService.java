package com.santostiago.cursomc.services;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.santostiago.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagt , java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagt.setDataVencimento(calendar.getTime());
	}

}
