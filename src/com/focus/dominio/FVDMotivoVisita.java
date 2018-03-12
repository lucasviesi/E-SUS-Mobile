package com.focus.dominio;

import com.focus.banco.Handle_SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FVDMotivoVisita {
	//Variaveis Globais - Banco
	private Handle_SQLite auxBD;
	private SQLiteDatabase bd;
	
	private long _id;
	private long _id_ficha_visita_dom;
	private long cod_motivo_visita;
	
	public FVDMotivoVisita(){
		
	}
	
	public FVDMotivoVisita(Context ctx){
		auxBD = new Handle_SQLite(ctx);
	}

	// INICIO GETTERS E SETTERS
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long get_id_ficha_visita_dom() {
		return _id_ficha_visita_dom;
	}

	public void set_id_ficha_visita_dom(long _id_ficha_visita_dom) {
		this._id_ficha_visita_dom = _id_ficha_visita_dom;
	}

	public long getCod_motivo_visita() {
		return cod_motivo_visita;
	}

	public void setCod_motivo_visita(long cod_motivo_visita) {
		this.cod_motivo_visita = cod_motivo_visita;
	}
	// FIM GETTERS E SETTERS
	
	public int conta() {
		//Conta Todos
		bd = auxBD.getWritableDatabase();
		try {
			String colunas[] = { "_ID" };
			Cursor c = bd.query("FVD_MOTIVO_VISITA", colunas, null, null, null, null, null);	
			return c.getCount();	

		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro em ao conta (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			return -1;
		} finally {
			bd.close();
		}
	}
}
