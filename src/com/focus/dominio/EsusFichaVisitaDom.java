package com.focus.dominio;

import java.util.ArrayList;
import java.util.List;

import com.focus.banco.Handle_SQLite;
import com.focus.funcoes.FuncoesBD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EsusFichaVisitaDom {
	//Variaveis Globais - Banco
	private SQLiteDatabase bd;
	private Handle_SQLite auxBD;
	
	private long _id;
	private long cod_cidadao;
	private long cod_turno;
	private long cod_desfecho;
	private long cod_domicilio; //Utilizo pra ñ ter que buscar na table de cidadao (ñ é exportado)
	private long cod_prof;
	private String cns_prof;
	private String cpf_prof;
	private String cbo_prof;
	private long cnes;
	private String cod_ine;
	private String data_visita;
	private String cod_pro;
	private String cns_pac;
	private String dtnasc;
	private String sexo;
	private boolean visita_compartilha;
	private long cod_usu_salvou;
	private String nome_usu_salvou;
	private String maquina_salvou;
	private String dt_hr_salvou;
	private long cod_area;
	private String cod_area_esus;
	private long cod_micro;
	private String num_micro;
	private boolean fora_area;
	private long cod_tipo_imovel;
	private double peso;
	private double altura;
	private int cid_respondeu_fvd;
	
	
	private List<Integer> motivos = new ArrayList<Integer>();

	public EsusFichaVisitaDom(Context ctx) {
		auxBD = new Handle_SQLite(ctx);
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

	public long getCod_turno() {
		return cod_turno;
	}

	public void setCod_turno(long cod_turno) {
		this.cod_turno = cod_turno;
	}

	public long getCod_desfecho() {
		return cod_desfecho;
	}

	public void setCod_desfecho(long cod_desfecho) {
		this.cod_desfecho = cod_desfecho;
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

	public long getCnes() {
		return cnes;
	}

	public void setCnes(long cnes) {
		this.cnes = cnes;
	}

	public String getCod_ine() {
		return cod_ine;
	}

	public void setCod_ine(String cod_ine) {
		this.cod_ine = cod_ine;
	}

	public String getData_visita() {
		return data_visita;
	}

	public void setData_visita(String data_visita) {
		this.data_visita = data_visita;
	}

	public String getCod_pro() {
		return cod_pro;
	}

	public void setCod_pro(String cod_pro) {
		this.cod_pro = cod_pro;
	}

	public String getCns_pac() {
		return cns_pac;
	}

	public void setCns_pac(String cns_pac) {
		this.cns_pac = cns_pac;
	}

	public String getDtnasc() {
		return dtnasc;
	}

	public void setDtnasc(String dtnasc) {
		this.dtnasc = dtnasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isVisita_compartilha() {
		return visita_compartilha;
	}

	public void setVisita_compartilha(boolean visita_compartilha) {
		this.visita_compartilha = visita_compartilha;
	}

	public long getCod_usu_salvou() {
		return cod_usu_salvou;
	}

	public void setCod_usu_salvou(long cod_usu_salvou) {
		this.cod_usu_salvou = cod_usu_salvou;
	}

	public String getNome_usu_salvou() {
		return nome_usu_salvou;
	}

	public void setNome_usu_salvou(String nome_usu_salvou) {
		this.nome_usu_salvou = nome_usu_salvou;
	}

	public String getMaquina_salvou() {
		return maquina_salvou;
	}

	public void setMaquina_salvou(String maquina_salvou) {
		this.maquina_salvou = maquina_salvou;
	}

	public String getDt_hr_salvou() {
		return dt_hr_salvou;
	}

	public void setDt_hr_salvou(String dt_hr_salvou) {
		this.dt_hr_salvou = dt_hr_salvou;
	}
	
	public List<Integer> getMotivos(){
		return motivos;
	}
	
	public void setMotivos(int cod_motivo){
		motivos.add(cod_motivo);		
	}
	
	public long getCod_domicilio(){
		return cod_domicilio;
	}
	
	public void setCod_domicilio(long cod_domicilio){
		this.cod_domicilio = cod_domicilio;
	}
	
	public long getCod_area() {
		return cod_area;
	}

	public void setCod_area(long cod_area) {
		this.cod_area = cod_area;
	}

	public String getCod_area_esus() {
		return cod_area_esus;
	}

	public void setCod_area_esus(String cod_area_esus) {
		this.cod_area_esus = cod_area_esus;
	}

	public long getCod_micro() {
		return cod_micro;
	}

	public void setCod_micro(long cod_micro) {
		this.cod_micro = cod_micro;
	}

	public String getNum_micro() {
		return num_micro;
	}

	public void setNum_micro(String num_micro) {
		this.num_micro = num_micro;
	}

	public boolean isFora_area() {
		return fora_area;
	}

	public void setFora_area(boolean fora_area) {
		this.fora_area = fora_area;
	}

	public long getCod_tipo_imovel() {
		return cod_tipo_imovel;
	}

	public void setCod_tipo_imovel(long cod_tipo_imovel) {
		this.cod_tipo_imovel = cod_tipo_imovel;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public int cid_respondeu_fvd() {
		return cid_respondeu_fvd;
	}
	
	public void setCid_respondeu_fvd(int cid_respondeu_fvd) {
		this.cid_respondeu_fvd = cid_respondeu_fvd;
	}	
	// FIM GETTERS e SETTERS

	public int conta() {
		bd = auxBD.getWritableDatabase();
		try {
			String colunas[] = { "_ID" };
			Cursor c = bd.query("ESUS_FICHA_VISITA_DOM", colunas, null, null, null, null, null);
			return c.getCount();

		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro ao contar Fichas de Visita Domiciliar (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			return -1;
		} finally {
			bd.close();
		}
	}
	
	public long salvar(EsusFichaVisitaDom f){
		//Se <> -1, funcionou (retorna o ID do registro)
		int ocorreuErro = 0; //Não
		
		bd = auxBD.getWritableDatabase(); //Abrindo o banco
		bd.beginTransaction(); //Iniciando a transação			
		
		try{
			ContentValues v = new ContentValues();
			
			//Salvando os dados na table "ESUS_Ficha_Visita_Dom"
			v.put("cod_cidadao", FuncoesBD.NumberToSQL(f.getCod_cidadao(), false) );
			v.put("cod_turno", FuncoesBD.NumberToSQL(f.getCod_turno(), false) );
			v.put("cod_desfecho", FuncoesBD.NumberToSQL(f.getCod_desfecho(), false) );
			v.put("cod_domicilio", FuncoesBD.NumberToSQL(f.getCod_domicilio(), false) );			
			v.put("cod_prof", FuncoesBD.NumberToSQL(f.getCod_prof(), false) );
			v.put("cns_prof", FuncoesBD.textToSQL(f.getCns_prof(), false) );
			v.put("cpf_prof", FuncoesBD.textToSQL(f.getCpf_prof(), false) );
			v.put("cbo_prof", FuncoesBD.textToSQL(f.getCbo_prof(), false) );
			v.put("cnes", FuncoesBD.NumberToSQL(f.getCnes(), false) );
			v.put("cod_ine", FuncoesBD.textToSQL(f.getCod_ine(), false) );
			v.put("data_visita", FuncoesBD.dateTimeToSQL(f.getData_visita(), false) );
			v.put("cod_pro", FuncoesBD.textToSQL(f.getCod_pro(), false) );
			v.put("cns_pac", FuncoesBD.textToSQL(f.getCns_pac(), false) );
			v.put("dtnasc", FuncoesBD.dateToSQL(f.getDtnasc(), false) );
			v.put("sexo", FuncoesBD.textToSQL(f.getSexo(), false) );
			v.put("visita_compartilha", FuncoesBD.booleanToSQL(f.isVisita_compartilha()) );
			v.put("cod_usu_salvou", FuncoesBD.NumberToSQL(f.getCod_usu_salvou(), false) );
			v.put("nome_usu_salvou", FuncoesBD.textToSQL(f.getNome_usu_salvou(), false) );
			v.put("maquina_salvou", FuncoesBD.textToSQL(f.getMaquina_salvou(), false) );
			v.put("dt_hr_salvou", FuncoesBD.dateTimeToSQL(f.getDt_hr_salvou(), false) );
			v.put("cod_area", FuncoesBD.NumberToSQL(f.getCod_area(), false)); //long
			v.put("cod_area_esus", FuncoesBD.textToSQL(f.getCod_area_esus(), false)); //String
			v.put("cod_micro", FuncoesBD.NumberToSQL(f.getCod_micro(), false)); //long
			v.put("num_micro", FuncoesBD.textToSQL(f.getNum_micro(), false)); //String
			v.put("fora_area", FuncoesBD.booleanToSQL(f.isFora_area())); //boolean
			v.put("cod_tipo_imovel", FuncoesBD.NumberToSQL(f.getCod_tipo_imovel(), false)); //long
			v.put("peso", FuncoesBD.doubleToSQL(f.getPeso(), 0, false)); //double
			v.put("altura", FuncoesBD.doubleToSQL(f.getAltura(), 0, false)); //double
						
			long _idFVDSalva = bd.insert("ESUS_FICHA_VISITA_DOM", null, v);
			if (_idFVDSalva == -1){
				ocorreuErro = 1;//Sim
			}
			
			//Salvando os dados em "FVD_Motivo_Visita"
			for (int i = 0; i < f.getMotivos().size(); i++){
				v.clear();
				v.put("_ID_FICHA_VISITA_DOM", _idFVDSalva);
				v.put("COD_ESUS_MOTIVO_VISITA", f.getMotivos().get(i));
					
				if(bd.insert("FVD_MOTIVO_VISITA", null, v) == -1){
					ocorreuErro = 1; //Sim
				}
			}//for
			
			//Marcando Domicilio como visitado
			v.clear();
			v.put("VISITADO", 1);
			if (bd.update("DOMICILIO_VISITA", v, "COD_DOMICILIO = " + cod_domicilio, null) == 0){ 
				//Tem que atualizar 1 domicilio. Se não atualizou ( == 0) --> ERRO 
				ocorreuErro = 1;
			}
			
			//Marcando que cidadao respondeu
			if (f.getCod_cidadao() != -1){ //se tem Cod_Cidadao
				v.clear();
				v.put("RESPONDEU_FVD", 1);
				if (bd.update("CIDADAO", v, "COD_CIDADAO = " + f.getCod_cidadao(), null) == 0){ 
					//Tem que atualizar 1 cidadao. Se não atualizou ( == 0) --> ERRO 
					ocorreuErro = 1;
				}
			}

			if (ocorreuErro == 0){
				//Não
				bd.setTransactionSuccessful(); //Marcando trasação como concluida com sucesso				
			}			
			
			return _idFVDSalva;
		}
		catch (Exception ex){
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro ao salvar Ficha de Visita Domiciliar (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			return -1;
		} finally {
			//Fechando a transação
			bd.endTransaction();
			//Se ao salvar, ocorreu erro, não executa o metodo setTransactionSucessful().
			//Assim quando chega no endTransaction(), ele descarta as informações
			
			bd.close(); //Fechando o banco
			
			if(ocorreuErro == 1){
				return -1;
			}
		}
	}
}
