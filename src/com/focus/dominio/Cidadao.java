package com.focus.dominio;

import java.util.ArrayList;

import com.focus.banco.Handle_SQLite;
import com.focus.funcoes.Mensagens;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Cidadao {
	//Variaveis Globais - Banco
	private Handle_SQLite auxBd;
	private SQLiteDatabase bd;
	private Context ctx;

	private long _id;
	private long cod_cidadao;
	private String cod_pro;
	private String nome;
	private String sobrenome;
	private String sexo;
	private String dtnasc;
	private String rg;
	private String cpf;
	private String cns;
	private long cod_domicilio;
	private int respondeu_fvd;

	public Cidadao() {

	}

	public Cidadao(Context ctx) {
		auxBd = new Handle_SQLite(ctx);
		this.ctx = ctx; 
	}

	// INICIO GETTERS e SETTERS
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getCod_cidadao() {
		return cod_cidadao;
	}

	public void setCod_cidadao(long cod_cidadao) {
		this.cod_cidadao = cod_cidadao;
	}
	
	public String getCod_pro() {
		return cod_pro;
	}

	public void setCod_pro(String cod_pro) {
		this.cod_pro = cod_pro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDtnasc() {
		return dtnasc;
	}

	public void setDtnasc(String dtnasc) {
		this.dtnasc = dtnasc;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public long getCod_domicilio() {
		return cod_domicilio;
	}

	public void setCod_domicilio(long cod_domicilio) {
		this.cod_domicilio = cod_domicilio;
	}
	
	public int getRespondeu_fvd() {
		return respondeu_fvd;
	}
	
	public void setRespondeu_fvd(int respondeu_fvd) {
		this.respondeu_fvd = respondeu_fvd;
	}
	// FIM GETTERS e SETTERS

	public int conta() {
		//Conta Todos
		bd = auxBd.getWritableDatabase();
		try {
			String colunas[] = { "_ID" };
			Cursor c = bd.query("CIDADAO", colunas, null, null, null, null, null);	
			return c.getCount();	

		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro em ao conta (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			return -1;
		} finally {
			bd.close();
		}
	}
	
	public int conta(long cod_domicilio, boolean pOnlyNotResp) {
		bd = auxBd.getWritableDatabase();
		try {
			String colunas[] = { "_ID" };
			Cursor c;			
			if (pOnlyNotResp){
				c = bd.query("CIDADAO", colunas, 
						"COD_DOMICILIO = " + String.valueOf(cod_domicilio) + " AND (RESPONDEU_FVD = 0 OR RESPONDEU_FVD IS NULL) ", null, null, null, null);				
			} else {
				c = bd.query("CIDADAO", colunas, "COD_DOMICILIO = " + String.valueOf(cod_domicilio) , null, null, null, null);
			}
			return c.getCount();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro em ao conta (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			return -1;
		} finally {
			bd.close();
		}
	}

	public Cidadao getDadosCidadao(String Cod_Cid) {
		try {
			if (this.conta() > 0) {
				bd = auxBd.getWritableDatabase();
				String colunas[] = { "_ID", "COD_CIDADAO", "COD_PRO", "NOME", "SOBRENOME", "SEXO", "DTNASC", "RG",
						"CPF", "CNS", "COD_DOMICILIO", "RESPONDEU_FVD" };
				//Não utilizar o comando WHERE
				Cursor c = bd.query("CIDADAO", colunas, "COD_CIDADAO = " + Cod_Cid, null, null, null, null);
				c.moveToFirst();

				Cidadao cid = new Cidadao();
				cid.set_id(c.getLong(c.getColumnIndex("_ID")));
				cid.setCod_cidadao(c.getLong(c.getColumnIndex("COD_CIDADAO")));
				cid.setCod_pro(c.getString(c.getColumnIndex("COD_PRO")));
				cid.setNome(c.getString(c.getColumnIndex("NOME")));
				cid.setSobrenome(c.getString(c.getColumnIndex("SOBRENOME")));
				cid.setSexo(c.getString(c.getColumnIndex("SEXO")));
				cid.setDtnasc(c.getString(c.getColumnIndex("DTNASC")));
				cid.setRg(c.getString(c.getColumnIndex("RG")));
				cid.setCpf(c.getString(c.getColumnIndex("CPF")));
				cid.setCns(c.getString(c.getColumnIndex("CNS")));
				cid.setCod_domicilio(c.getLong(c.getColumnIndex("COD_DOMICILIO")));
				cid.setRespondeu_fvd(c.getInt(c.getColumnIndex("RESPONDEU_FVD")));

				return cid;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de getDadosCidadao (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...", "Erro no SQL de getDadosCidadao (" + this.getClass() + ")");			
			return null;
		} finally {
			bd.close();
		}
	}
	
	public ArrayList<Cidadao> getCidadaosDomicilio(long Cod_Dom){
		try{
			if (this.conta() > 0){
				bd = auxBd.getWritableDatabase();
				ArrayList<Cidadao> cidadaos = new ArrayList<Cidadao>();
				
				String colunas[] = { "_ID", "COD_CIDADAO", "COD_PRO", "NOME", "SOBRENOME", "SEXO", "DTNASC", "RG",
						"CPF", "CNS", "COD_DOMICILIO", "RESPONDEU_FVD" };
				//Não utilizar o comando WHERE
				Cursor c = bd.query("CIDADAO", colunas, "COD_DOMICILIO = " + String.valueOf(Cod_Dom), null, null, null, null);
				c.moveToFirst();
				
				do {
					Cidadao cid = new Cidadao();
					cid.set_id(c.getLong(c.getColumnIndex("_ID")));
					cid.setCod_cidadao(c.getLong(c.getColumnIndex("COD_CIDADAO")));
					cid.setCod_pro(c.getString(c.getColumnIndex("COD_PRO")));
					cid.setNome(c.getString(c.getColumnIndex("NOME")));
					cid.setSobrenome(c.getString(c.getColumnIndex("SOBRENOME")));
					cid.setSexo(c.getString(c.getColumnIndex("SEXO")));
					cid.setDtnasc(c.getString(c.getColumnIndex("DTNASC")));
					cid.setRg(c.getString(c.getColumnIndex("RG")));
					cid.setCpf(c.getString(c.getColumnIndex("CPF")));
					cid.setCns(c.getString(c.getColumnIndex("CNS")));
					cid.setCod_domicilio(c.getLong(c.getColumnIndex("COD_DOMICILIO")));
					cid.setRespondeu_fvd(c.getInt(c.getColumnIndex("RESPONDEU_FVD")));
					
					cidadaos.add(cid);
				} while (c.moveToNext());				
				
				return cidadaos;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de getCidadaosDomicilio (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...", "Erro no SQL de getCidadaosDomicilio (" + this.getClass() + ")");
			return null;
		} finally {
			bd.close();
		}
	}

}
