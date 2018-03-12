package com.focus.dominio;

import com.focus.banco.Handle_SQLite;
import com.focus.outros.VarConst;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Usuario {
	//Variaveis Globais - Banco
	private SQLiteDatabase bd;
	private Handle_SQLite auxBD;

	private long _id;
	private long cod_usuario;
	private String usuario;
	private String senha;
	private String nome;
	private String area;
	private String micro_area;
	private String data_hora_salvou;
	private long cod_cnes_unidade;
	private String unidade;
	private long cod_prof;
	private String cns_prof;
	private String cpf_prof;
	private String cbo_prof;
	private String cod_ine;
	private String nome_equipe;
	private long cod_area_equipe;
	private String nome_area_equipe;
	private String cod_area_esus_equipe;
	private MicroArea microArea;

	public Usuario() {

	}

	public Usuario(Context ctx) {
//		Handle_SQLite auxBDd = new Handle_SQLite(ctx);
//		bd = auxBd.getWritableDatabase();
		auxBD = new Handle_SQLite(ctx);
	}

	// INICIO GETTERS e SETTERS
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMicro_area() {
		return micro_area;
	}

	public void setMicro_area(String micro_area) {
		this.micro_area = micro_area;
	}

	public String getData_hora_salvou() {
		return data_hora_salvou;
	}

	public void setData_hora_salvou(String data_hora_salvou) {
		this.data_hora_salvou = data_hora_salvou;
	}
	
	public long getCod_cnes_unidade() {
		return cod_cnes_unidade;
	}

	public void setCod_cnes_unidade(long cod_cnes_unidade) {
		this.cod_cnes_unidade = cod_cnes_unidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public long getCod_prof() {
		return cod_prof;
	}

	public void setCod_prof(long cod_prof) {
		this.cod_prof = cod_prof;
	}

	public String getCns_prof() {
		return cns_prof;
	}

	public void setCns_prof(String cns_prof) {
		this.cns_prof = cns_prof;
	}

	public String getCpf_prof() {
		return cpf_prof;
	}

	public void setCpf_prof(String cpf_prof) {
		this.cpf_prof = cpf_prof;
	}

	public String getCbo_prof() {
		return cbo_prof;
	}

	public void setCbo_prof(String cbo_prof) {
		this.cbo_prof = cbo_prof;
	}

	public String getCod_ine() {
		return cod_ine;
	}

	public void setCod_ine(String cod_ine) {
		this.cod_ine = cod_ine;
	}
	
	public String getNome_equipe() {
		return nome_equipe;
	}

	public void setNome_equipe(String nome_equipe) {
		this.nome_equipe = nome_equipe;
	}

	public long getCod_area_equipe() {
		return cod_area_equipe;
	}

	public void setCod_area_equipe(long cod_area_equipe) {
		this.cod_area_equipe = cod_area_equipe;
	}

	public String getNome_area_equipe() {
		return nome_area_equipe;
	}

	public void setNome_area_equipe(String nome_area_equipe) {
		this.nome_area_equipe = nome_area_equipe;
	}

	public String getCod_area_esus_equipe() {
		return cod_area_esus_equipe;
	}

	public void setCod_area_esus_equipe(String cod_area_esus_equipe) {
		this.cod_area_esus_equipe = cod_area_esus_equipe;
	}
	
	public MicroArea getMicroArea() {
		return microArea;
	}

	public void setMicroArea(MicroArea microArea) {
		this.microArea = microArea;
	}	
	
	// FIM GETTERS e SETTERS

	public int conta(){
		bd = auxBD.getWritableDatabase();	
		try{
			String colunas[] = { "_ID" };
			Cursor c = bd.query("USUARIO", colunas, null, null, null, null, null);
			return c.getCount();			
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro em conta (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			return -1;
		} 
		finally {
			bd.close();
		}
	}

	public Usuario getDadosUsuario() {
		try {
			if (this.conta() > 0) {
				bd = auxBD.getWritableDatabase();
				String colunas[] = { "_ID", "COD_USUARIO", "USUARIO", "SENHA", "NOME", "AREA", "MICRO_AREA",
						"DATA_HORA_SALVOU", "COD_CNES_UNIDADE", "UNIDADE", "COD_PROF", "CNS_PROF", "CPF_PROF", "CBO_PROF",
						"COD_INE", "NOME_EQUIPE", "COD_AREA_EQUIPE", "NOME_AREA_EQUIPE", "COD_AREA_ESUS_EQUIPE" };
				Cursor c = bd.query("USUARIO", colunas, null, null, null, null, null);
				c.moveToFirst();

				Usuario u = new Usuario();
				u.set_id(c.getLong(c.getColumnIndex("_ID")));
				u.setCod_usuario(c.getLong(c.getColumnIndex("COD_USUARIO")));
				u.setUsuario(c.getString(c.getColumnIndex("USUARIO")));
				u.setSenha(c.getString(c.getColumnIndex("SENHA")));
				u.setNome(c.getString(c.getColumnIndex("NOME")));
				u.setArea(c.getString(c.getColumnIndex("AREA")));
				u.setMicro_area(c.getString(c.getColumnIndex("MICRO_AREA")));
				u.setData_hora_salvou(c.getString(c.getColumnIndex("DATA_HORA_SALVOU")));
				u.setCod_cnes_unidade(c.getLong(c.getColumnIndex("COD_CNES_UNIDADE")));
				u.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));
				u.setCod_prof(c.getLong(c.getColumnIndex("COD_PROF")));
				u.setCns_prof(c.getString(c.getColumnIndex("CNS_PROF")));
				u.setCpf_prof(c.getString(c.getColumnIndex("CPF_PROF")));
				u.setCbo_prof(c.getString(c.getColumnIndex("CBO_PROF")));
				u.setCod_ine(c.getString(c.getColumnIndex("COD_INE")));
				u.setNome_equipe(c.getString(c.getColumnIndex("NOME_EQUIPE")));
				u.setCod_area_equipe(c.getLong(c.getColumnIndex("COD_AREA_EQUIPE")));
				u.setNome_area_equipe(c.getString(c.getColumnIndex("NOME_AREA_EQUIPE")));
				u.setCod_area_esus_equipe(c.getString(c.getColumnIndex("COD_AREA_ESUS_EQUIPE")));
				//
				VarConst.vcod_area_equipe = u.getCod_area_equipe();
				VarConst.vnome_area_equipe = u.getNome_area_equipe();
				VarConst.vcod_area_esus_equipe = u.getCod_area_esus_equipe();
				//
				MicroArea m = new MicroArea(auxBD.getWritableDatabase()).getDadosMicroArea(c.getLong(c.getColumnIndex("MICRO_AREA")));
				u.setMicroArea(m);
				//
				VarConst.vcod_micro = m.getCod_micro();
				VarConst.vcod_area_micro = m.getCod_area();
				VarConst.vnome_microarea = m.getNome();
				VarConst.vnumero_microarea = m.getNumero();				
				//
				return u;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;

		}
		finally {
			bd.close();
		}
	}

	public int logar(String usu, String senha) {
		//Nesse metodo, tenho que abrir o banco depois do do conta(). Sei lá pq. Antes não esta funcionando
		try {
			if (this.conta() > 0) {
				bd = auxBD.getWritableDatabase();
				//Devo usar usuario e senha em maiusculo (toUpperCase, pois no delphi, é exportado em maiusculo)
				String colunas[] = { "_ID", "COD_USUARIO", "NOME" };
				Cursor c = bd.query("USUARIO", colunas, "USUARIO = '" + usu.toUpperCase() 
									+ "' AND SENHA = '" + senha.toUpperCase() + "' ", null,
						null, null, null);
				c.moveToFirst();
				
				if (c.getCount() > 0) {
					// Usuario Valido
					// Preenchendo informações sobre o usuário que logou na VarConst
					VarConst.cod_usu_logado = c.getLong(c.getColumnIndex("COD_USUARIO"));
					VarConst.nome_usu_logado = c.getString(c.getColumnIndex("NOME"));
					VarConst.usuario_logado = usu;
					return 1;
				} else {
					// Usuario Invalido
					return 0;
				}
			}
			// Sem usuarios
			return -1;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Erro SQL
			return -9;
		}
		finally {
			bd.close();
		}
	}
}
