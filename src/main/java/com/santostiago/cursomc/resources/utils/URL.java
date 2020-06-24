package com.santostiago.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String valor) {
		try {
			return URLDecoder.decode(valor, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	
	
	public static List<Integer> decodeInt(String valor){
		String[] vet = valor.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i =0 ; i < vet.length ; i++) {
			list.add(Integer.parseInt(vet[i]));
			
		}
		return list;
		
	}

}
