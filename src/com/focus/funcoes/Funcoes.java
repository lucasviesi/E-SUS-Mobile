package com.focus.funcoes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class Funcoes {

	//Ativa a vibração por X milisegundos
	public static void vibrar(Context ctx, long milliseconds) {
		Vibrator rr = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
		rr.vibrate(milliseconds);
	}

	public static String converteData(Date data, String formato, boolean aspas) {
		DateFormat dataparabd = new SimpleDateFormat(formato);

		if (String.valueOf(data).equals("null"))
			return null;
		else {
			if (aspas) {
				return "'" + dataparabd.format(data) + "'";
			} else {
				return dataparabd.format(data);
			}
		}
	}// fim converteData

	//Ajusta da no formata yyyy/mm/dd para dd/mm/yyyy (somente visualmente)
	public static String ajustaDataDisplay(String data){
		String retorno = "";
		retorno = data.substring(8, 10) + "/";
		retorno += data.substring(5, 7) + "/";
		retorno += data.substring(0, 4);
		return retorno;
	}
	
	//Semelhante à função do Delphi. Retorna o indice do item dentro do "grupo"
	public static int GetIndexRadioGroup(RadioGroup group){
		//-1 -> Nenhum Item Selecionado
		// 0 -> primeiro
		return group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
	}

	//Semelhante à função do Delphi. Retorna o indice do item dentro do "grupo"
	public static int GetIndexRadioGroupEsus(RadioGroup group){
		//-1 -> Nenhum Item Selecionado
		// 1 -> primeiro
		int x = group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
		
		if (x == -1){
			return x;
		}
		else {
			x++;
			return x; 
		}
	}
	
	//Retorna a Data Atual do dispositivo
	public static String getDataAtual(){
		return Funcoes.converteData(new Date(), "dd/MM/yyyy", false);
	}
	
	//Retorna a Hora Atual do dispositivo
	public static String getHoraAtual(boolean segundos){
		if (segundos){
			return Funcoes.converteData(new Date(), "HH:mm:ss", false);			
		}
		return Funcoes.converteData(new Date(), "HH:mm", false);
	}
	
	//Retorna Data e Hora Atual do dispositivo
	public static String getDataHoraAtual(boolean segundos){
		if (segundos){
			return Funcoes.converteData(new Date(), "dd/MM/yyyy HH:mm:ss", false);
		}	
		return Funcoes.converteData(new Date(), "dd/MM/yyyy HH:mm", false);
	}
	
	//Retorna a string do sexo com base no index
	public static String getSexoByIndex(int index){
		switch (index){
			case 0: return "M";
			case 1: return "F"; 
			default: return "NULL";
		}
	}
	
	//Add zeros a esquerda
	public static String zeroEsquerda(int valor, int tam){
		return String.format("%0" + String.valueOf(tam) + "d", valor);
	}
	
	//Substitui a "/" por "-". (O SQLite da erro ao salvar datas com "/"
	public static String padraoData(String dado){
		return dado.replace("/", "-");
	}
	
	//Limpa String, deixando somente numeros
	public static String limpaCaracterString(String dado){
		return dado.replaceAll("[^0123456789]", "");
	}
	
	public static void CheckedEnabledCheckBox_InLinearLayout(LinearLayout ll, boolean pChecked, boolean pEnabled, int iComeca, int qtdItem){ 
		for (int i = iComeca; i <= iComeca + qtdItem; i++){
			View comp = ll.findViewWithTag("cbMV" + Funcoes.zeroEsquerda(i, 2));
			if (comp instanceof CheckBox){
				CheckBox check = (CheckBox) comp;
//				check.setChecked(pChecked);
				check.setEnabled(pEnabled);
				//
				if (!pEnabled){
					check.setChecked(pChecked);
				}
			}
		}
	}
	
	public static String virgulaPorPonto(String dado){
		return dado.replace(",", ".");
	}
	
	public static double stringToDouble(String valor){
		valor = virgulaPorPonto(valor);
		if (valor.equals("")){
			return 0;
		} else {
			return Double.parseDouble(valor);	
		}				
	}
	
	public static String desagrupa(String dado, String separador){
		dado = dado.trim();
		return dado.substring(0, dado.indexOf(separador)-1);
	}
	
	public static String mascaraCNS(String cns){
		String s;
		if (cns == null){
			return "";
		}
		if (!cns.equals("")){
			cns = cns.trim();
			s = cns.substring(0, 3) + " " + cns.substring(3, 7) + " " + cns.substring(7, 10) + " " + cns.substring(10, 15);
			return s;
		}
		else return "";
	}
	
	public static String getSiglaSexo(String sexo){
		if (sexo == null){
			return null;
		}
		if (sexo.equals("Feminino")){
			return "F";
		} else if (sexo.equals("Masculino")){
			return "M";
		} else {
			return null;
		}
	}

	
	public static String priPalavraMaiuscula(String texto){
		char[] chars = texto.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}
		
}
