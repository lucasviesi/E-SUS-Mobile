package com.focus.dominio;

import java.util.ArrayList;

import com.focus.banco.Handle_SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DomicilioVisita {
	//Variaveis Globais - Banco
	private Handle_SQLite auxBd;
	private SQLiteDatabase bd;

	private long _id;
	private long cod_domicilio;
	private String tipo_logradouro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private float latitude;
	private float longitude;
	private long ordem_visita;
	private int visitado;

	public DomicilioVisita() {

	}

	public DomicilioVisita(Context ctx) {
		auxBd = new Handle_SQLite(ctx);
	}

	// INICIO GETTERS e SETTERS
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getCod_domicilio() {
		return cod_domicilio;
	}

	public void setCod_domicilio(long cod_domicilio) {
		this.cod_domicilio = cod_domicilio;
	}

	public String getTipo_logradouro() {
		return tipo_logradouro;
	}

	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public long getOrdem_visita() {
		return ordem_visita;
	}

	public void setOrdem_visita(long ordem_visita) {
		this.ordem_visita = ordem_visita;
	}
	
	public int getVisitado() {
		return visitado;
	}

	public void setVisitado(int visitado) {
		this.visitado = visitado;
	}
	// FIM GETTERS e SETTERS

	public int conta() {
		bd = auxBd.getWritableDatabase();
		try {
			String colunas[] = { "_ID" };
			Cursor c = bd.query("DOMICILIO_VISITA", colunas, null, null, null, null, null);
			return c.getCount();

		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro ao contar Domicilios (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			return -1;
		} finally {
			bd.close();
		}
	}

	public ArrayList<DomicilioVisita> listaDomicilios() {
		bd = auxBd.getWritableDatabase();
		try {
			ArrayList<DomicilioVisita> lista = new ArrayList<DomicilioVisita>();

			String colunas[] = { "_ID", "COD_DOMICILIO", "TIPO_LOGRADOURO", "LOGRADOURO", "NUMERO", "COMPLEMENTO", "BAIRRO",
					"LATITUDE", "LONGITUDE", "ORDEM_VISITA", "VISITADO" };
			Cursor c = bd.query("DOMICILIO_VISITA", colunas, null, null, null, null, null);
			c.moveToFirst();

			if (c.getCount() > 0) {
				c.moveToFirst();
				do {
					DomicilioVisita d = new DomicilioVisita();
					d.set_id(c.getLong(0));
					d.setCod_domicilio(c.getLong(1));
					d.setTipo_logradouro(c.getString(2));
					d.setLogradouro(c.getString(3));
					d.setNumero(c.getString(4));
					d.setComplemento(c.getString(5));
					d.setBairro(c.getString(6));
					d.setLatitude(c.getFloat(7));
					d.setLongitude(c.getFloat(8));
					d.setOrdem_visita(c.getLong(9));
					d.setVisitado(c.getInt(10));
					lista.add(d);
				} while (c.moveToNext());
			}

			return lista;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro em listaDomicilios (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			return null;			
		} finally {
			bd.close();
		}
	}
	
	public int setDomiciliaVisitado(long cod_domicilio){
		if (cod_domicilio < 0){
			return -1;
		}
		//Se foi passado um codigo valido
		try{
			bd = auxBd.getWritableDatabase();
			ContentValues v = new ContentValues();
			v.put("VISITADO", 1);
			int i = bd.update("DOMICILIO_VISITA", v, "COD_DOMICILIO = " + cod_domicilio, null);
			//retorna o numero de linhas alteradas
			return i;	
		} catch (Exception ex){
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro em setDomiciliaVisitado (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			return -9; //indica erro 
		} finally {
			bd.close();
		}
	}
}
