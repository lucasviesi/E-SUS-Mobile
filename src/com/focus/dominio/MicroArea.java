package com.focus.dominio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MicroArea {
	//Variaveis Globais - Banco
	private SQLiteDatabase bd;	
	
	private long cod_micro;
	private long cod_area;
	private String nome;
	private String numero;
	
	public MicroArea() {

	}

	public MicroArea(SQLiteDatabase bd) {
		this.bd = bd;
	}	

	// INICIO GETTERS e SETTERS	
	public long getCod_micro() {
		return cod_micro;
	}

	public void setCod_micro(long cod_micro) {
		this.cod_micro = cod_micro;
	}

	public long getCod_area() {
		return cod_area;
	}

	public void setCod_area(long cod_area) {
		this.cod_area = cod_area;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	// FIM GETTERS e SETTERS
	
	public MicroArea getDadosMicroArea(long CodMicroArea) {
		try {
			String colunas[] = { "COD_MICRO", "COD_AREA", "NOME", "NUMERO" };
			Cursor c = bd.query("MICRO_AREA", colunas, "COD_MICRO = " + String.valueOf(CodMicroArea), null, null, null, null);
			c.moveToFirst();
				
			if (c.getCount() > 0) {
				MicroArea m = new MicroArea();
				m.setCod_micro(c.getLong(c.getColumnIndex("COD_MICRO")));
				m.setCod_area(c.getLong(c.getColumnIndex("COD_AREA")));
				m.setNome(c.getString(c.getColumnIndex("NOME")));
				m.setNumero(c.getString(c.getColumnIndex("NUMERO")));
				return m;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}	
}
