package com.focus.funcoes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncoesBD {
	//Segue o mesmo padrão das funções do Delphi, para fins de padronização
	
	public static String textToSQL(String dado, boolean aspas){
		if (dado == null || dado.equals("")){
			return "NULL";
		} else {
			if (aspas){
				return "'" + dado + "'";
			}		
			return dado;
		}
	}
	
	public static String dateTimeToSQL(String data, boolean aspas){
		try {
			if (data == null || data.equals("")){
				return "NULL";
			} else {
				String format = "dd-MM-yyyy HH:mm:ss";
				DateFormat df = new SimpleDateFormat(format);
				Date date = df.parse(data);
				
				String retorno = Funcoes.converteData(date, "yyyy-MM-dd HH:mm:ss", false);
				
				if (aspas){
					return "'" + String.valueOf(retorno) + "'";	
				}
				return String.valueOf(retorno);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String dateToSQL(String data, boolean aspas){
		try {
			if (data == null || data.equals("")){
				return "NULL";
			} else {
				String format = "dd-MM-yyyy";
				DateFormat df = new SimpleDateFormat(format);
				Date date = df.parse(data);
				
				String retorno = Funcoes.converteData(date, "yyyy-MM-dd", false);
				
				if (aspas){
					return "'" + String.valueOf(retorno) + "'";	
				}
				return String.valueOf(retorno);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String NumberToSQL(int number, boolean aspas){
		if (number < 0){
			return "NULL";
		} else {
			if (aspas){
				return "'" + String.valueOf(number) + "'";	
			}
			return String.valueOf(number);
		}
	}
	
	public static String NumberToSQL(long number, boolean aspas){
		if (number < 0){
			return "NULL";
		} else {
			if (aspas){
				return "'" + String.valueOf(number) + "'";	
			}
			return String.valueOf(number);
		}
	}
	
	public static String doubleToSQL(double number, double valueNull, boolean aspas){
		if (number == valueNull){
			return "NULL";
		} else {
			if (aspas){
				return "'" + Funcoes.virgulaPorPonto(String.valueOf(number)) + "'";	
			}
			return Funcoes.virgulaPorPonto(String.valueOf(number));			
		}
	}
	
	public static String booleanToSQL(boolean valor){
		if (valor){
			return "1";
		} else {
			return "0";
		}
	}
}
