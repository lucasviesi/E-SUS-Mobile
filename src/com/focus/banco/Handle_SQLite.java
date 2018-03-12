package com.focus.banco;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;

import com.focus.funcoes.FuncoesBD;
import com.focus.funcoes.Mensagens;

public class Handle_SQLite extends SQLiteOpenHelper {
	// Definindo nome e versão do banco
	private static final String NOME_BD = "FocusESUS.db";
	public static final int VERSAO_BD = 3;
	public static final String LOCAL_BANCO = "/FocusESUS/";
	private Context ctx;
	private String query;

	// Construtor
	public Handle_SQLite(Context context) {
		// Definindo onde o banco será criado
		super(context, Environment.getExternalStorageDirectory().getAbsolutePath() + LOCAL_BANCO + NOME_BD, null, VERSAO_BD);
		ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ------------------------ \\
		// Criando Tabelas - INICIO \\
		// ------------------------ \\
		// USUARIO
		try {
			query = "CREATE TABLE IF NOT EXISTS USUARIO ( "
					+ " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " COD_USUARIO INTEGER, " 
					+ " USUARIO VARCHAR(50), " 
					+ " SENHA VARCHAR(50), "
					+ " NOME VARCHAR(100), " 
					+ "	AREA VARCHAR(80), " 
					+ "	MICRO_AREA VARCHAR(40), "
					+ "	DATA_HORA_SALVOU DATETIME, " 
					+ "	COD_CNES_UNIDADE INTEGER, " 
					+ "	UNIDADE VARCHAR(100), "
					+ "	COD_PROF INTEGER, " 
					+ "	CNS_PROF VARCHAR(18), " 
					+ "	CPF_PROF VARCHAR(14), "
					+ "	CBO_PROF VARCHAR(6), " 
					+ "	COD_INE VARCHAR(10)" 
					+ " );";
			db.execSQL(query);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de Criação da Tabela USUARIO (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...", "Erro no SQL de Criação da Tabela USUARIO (" + this.getClass() + ")");
		}

		// DOMICILIO_VISITA
		try {
			query = "CREATE TABLE IF NOT EXISTS DOMICILIO_VISITA ( "
					+ " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " COD_DOMICILIO INTEGER, " 
					+ " TIPO_LOGRADOURO VARCHAR(50), " 
					+ " LOGRADOURO VARCHAR(100), "
					+ " NUMERO VARCHAR(10), " 
					+ " COMPLEMENTO VARCHAR(20), " 
					+ " BAIRRO VARCHAR(100), "
					+ " LATITUDE FLOAT, " 
					+ " LONGITUDE FLOAT, " 
					+ " ORDEM_VISITA INTEGER, "
					+ " VISITADO BIT "
					+ " ); ";
			db.execSQL(query);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de Criação da Tabela DOMICILIO_VISITA (Classe " + this.getClass()
					+ "). Message: " + ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...",
					"Erro no SQL de Criação da Tabela DOMICILIO_VISITA (" + this.getClass() + ")");
		}

		// CIDADAO
		try {
			query = "CREATE TABLE IF NOT EXISTS CIDADAO ( " 
					+ " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " COD_DOMICILIO INTEGER, "
					+ " COD_CIDADAO INTEGER, " 
					+ " COD_PRO VARCHAR(8), "
					+ " NOME VARCHAR(100), " 
					+ " SOBRENOME VARCHAR(50), "
					+ " SEXO CHAR(1), " 
					+ " DTNASC DATE, " 
					+ " RG VARCHAR(20), "
					+ " CPF VARCHAR(15), " 
					+ " CNS VARCHAR(20) "
					+ " ); ";
			db.execSQL(query);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de Criação da Tabela CIDADAO (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...", "Erro no SQL de Criação da Tabela CIDADAO (" + this.getClass() + ")");
		}

		// ESUS_FICHA_VISITA_DOM
		try {
			query = "CREATE TABLE IF NOT EXISTS ESUS_FICHA_VISITA_DOM ( "
					+ " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "	COD_CIDADAO INTEGER, "
					+ "	COD_TURNO INTEGER, "
					+ "	COD_DESFECHO INTEGER, "
					+ "	COD_DOMICILIO INTEGER, "
					+ "	COD_PROF INTEGER, "
					+ "	CNS_PROF VARCHAR(18), " 
					+ "	CPF_PROF VARCHAR(14), " 
					+ "	CBO_PROF VARCHAR(6), "
					+ "	CNES INTEGER, " 
					+ "	COD_INE VARCHAR(10), " 
					+ "	DATA_VISITA DATETIME, "
					+ "	COD_PRO VARCHAR(8), " 
					+ "	CNS_PAC VARCHAR(18), "
					+ "	DTNASC DATE, " 
					+ "	SEXO VARCHAR(5), " 
					+ "	VISITA_COMPARTILHA BIT, " 
					+ "	COD_USU_SALVOU INTEGER, " 
					+ "	NOME_USU_SALVOU VARCHAR(30), "
					+ "	MAQUINA_SALVOU VARCHAR(80), " 
					+ "	DT_HR_SALVOU DATETIME " 
					+ " ); ";
			db.execSQL(query);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de Criação da Tabela ESUS_FICHA_VISITA_DOM (Classe " + this.getClass()
					+ "). Message: " + ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...",
					"Erro no SQL de Criação da Tabela ESUS_FICHA_VISITA_DOM (" + this.getClass() + ")");
		}

		// FVD_MOTIVO_VISITA
		try {
			query = "CREATE TABLE IF NOT EXISTS FVD_MOTIVO_VISITA ( " 
					+ " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "	_ID_FICHA_VISITA_DOM INTEGER, "
					+ "	COD_ESUS_MOTIVO_VISITA INTEGER "
					+ " ); ";
			db.execSQL(query);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro no SQL de Criação da Tabela FVD_MOTIVO_VISITA (Classe " + this.getClass() + "). Message: " + ex.getMessage());
			Mensagens.msgSimples(ctx, "Erro...",
					"Erro no SQL de Criação da Tabela FVD_MOTIVO_VISITA (" + this.getClass() + ")");
		}
		// --------------------- \\
		// Criando Tabelas - FIM \\
		// --------------------- \\		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
		if (newVersion >= 2){
			//Alterando table: ESUS_FICHA_VISITA_DOM - INICIO \\			
			try {			
				//COD_AREA
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "COD_AREA")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD COD_AREA INTEGER; ";
					db.execSQL(query);
				}
				
				//COD_AREA_ESUS
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "COD_AREA_ESUS")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD COD_AREA_ESUS TEXT; ";
					db.execSQL(query);
				}
				
				//COD_MICRO
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "COD_MICRO")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD COD_MICRO INTEGER; ";
					db.execSQL(query);
				}
				
				//NUM_MICRO
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "NUM_MICRO")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD NUM_MICRO TEXT; ";
					db.execSQL(query);
				}
				
				//FORA_AREA
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "FORA_AREA")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD FORA_AREA INTEGER; ";
					db.execSQL(query);
				}				
				
				//COD_TIPO_IMOVEL
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "COD_TIPO_IMOVEL")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD COD_TIPO_IMOVEL INTEGER; ";
					db.execSQL(query);
				}
				
				//PESO
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "PESO")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD PESO REAL; ";
					db.execSQL(query);
				}
				
				//ALTURA
				if (!isFieldExist(db, "ESUS_FICHA_VISITA_DOM", "ALTURA")){
					query = " ALTER TABLE ESUS_FICHA_VISITA_DOM "
							+ " ADD ALTURA REAL; ";
					db.execSQL(query);
				}
			} catch (Exception e){
				e.printStackTrace();
				Log.e("ErroSQL", "Erro SQL Alter Table ESUS_FICHA_VISITA_DOM (Classe " + this.getClass() + "). Message: " + e.getMessage());
				Mensagens.msgSimples(ctx, "Erro...", "Erro SQL Alter Table ESUS_FICHA_VISITA_DOM (" + this.getClass() + ")");					
			}
			//Alterando table: ESUS_FICHA_VISITA_DOM - FIM \\

			
			//Alterando table: USUARIO - INICIO \\
			try {			
				//NOME_EQUIPE
				if (!isFieldExist(db, "USUARIO", "NOME_EQUIPE")){
					query = " ALTER TABLE USUARIO "
							+ " ADD NOME_EQUIPE TEXT; ";
					db.execSQL(query);
				}
				
				//COD_AREA_EQUIPE
				if (!isFieldExist(db, "USUARIO", "COD_AREA_EQUIPE")){
					query = " ALTER TABLE USUARIO "
							+ " ADD COD_AREA_EQUIPE INTEGER; ";
					db.execSQL(query);
				}
				
				//NOME_AREA_EQUIPE
				if (!isFieldExist(db, "USUARIO", "NOME_AREA_EQUIPE")){
					query = " ALTER TABLE USUARIO "
							+ " ADD NOME_AREA_EQUIPE TEXT; ";
					db.execSQL(query);
				}
				
				//COD_AREA_ESUS_EQUIPE
				if (!isFieldExist(db, "USUARIO", "COD_AREA_ESUS_EQUIPE")){
					query = " ALTER TABLE USUARIO "
							+ " ADD COD_AREA_ESUS_EQUIPE TEXT; ";
					db.execSQL(query);
				}
			} catch (Exception e){
				e.printStackTrace();
				Log.e("ErroSQL", "Erro SQL Alter Table USUARIO (Classe " + this.getClass() + "). Message: " + e.getMessage());
				Mensagens.msgSimples(ctx, "Erro...", "Erro SQL Alter Table USUARIO (" + this.getClass() + ")");					
			}
			//Alterando table: USUARIO - FIM \\
			
			
			//Criando table: MICRO_AREA - INICIO \\
			try {
				query = " CREATE TABLE IF NOT EXISTS MICRO_AREA ( "
						+ " COD_MICRO INTEGER, "
						+ " COD_AREA INTEGER, "
						+ " NOME TEXT, "
						+ " NUMERO TEXT "
						+ " ); ";
				db.execSQL(query);				
			} catch (Exception e){
				e.printStackTrace();
				Log.e("ErroSQL", "Erro SQL Create Table MICRO_AREA (Classe " + this.getClass() + "). Message: " + e.getMessage());
				Mensagens.msgSimples(ctx, "Erro...", "Erro SQL Create Table MICRO_AREA (" + this.getClass() + ")");					
			}
			//Criando table: MICRO_AREA - FIM \\
			
			
			//Criando table: CONFIG - INICIO \\
			try {
				query = " CREATE TABLE IF NOT EXISTS CONFIG ( "
						+ " VERSION_CODE INTEGER, "
						+ " VERSION_NAME TEXT "
						+ " ); ";
				db.execSQL(query);				
			} catch (Exception e){
				e.printStackTrace();
				Log.e("ErroSQL", "Erro SQL Create Table CONFIG (Classe " + this.getClass() + "). Message: " + e.getMessage());
				Mensagens.msgSimples(ctx, "Erro...", "Erro SQL Create Table CONFIG (" + this.getClass() + ")");					
			}
			//Criando table: CONFIG - FIM \\
			
			//Inserindo versão: CONFIG - INICIO \\
			try {
		        //classe responsável por pegar todas as informações contidas no
		        //AndroidManifest.XML
		        PackageInfo pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
		        
				Cursor res = db.rawQuery(" SELECT VERSION_CODE FROM CONFIG; ", null);
				if (res.getCount() == 0) {//insert
					query = " INSERT INTO CONFIG VALUES ( " 
							+ FuncoesBD.NumberToSQL(pinfo.versionCode, false)
							+ " , " + FuncoesBD.textToSQL(pinfo.versionName, false)
							+ " ) ";
				} else if (res.getCount() == 1) {//update
					query = " UPDATE CONFIG "
							+ " SET VERSION_CODE = " + FuncoesBD.NumberToSQL(pinfo.versionCode, false)
							+ " , VERSION_NAME = " + FuncoesBD.textToSQL(pinfo.versionName, false)
							+ " ; ";					
				}
				db.execSQL(query);
			} catch (Exception e){
				e.printStackTrace();
				Log.e("ErroSQL", "Erro Save Table CONFIG (Classe " + this.getClass() + "). Message: " + e.getMessage());
				Mensagens.msgSimples(ctx, "Erro...", "Erro Save Version Table CONFIG (" + this.getClass() + ")");					
			}
			//Inserindo versão: CONFIG - FIM \\			
		}//if (newVersion >= 2){
		
		if (newVersion >= 3){
			//Alterando table: CIDADAO - INICIO \\
			try {	        
				//RESPONDEU_FVD (Ficha Visita Domiciliar)
				if (!isFieldExist(db, "CIDADAO", "RESPONDEU_FVD")){
					query = " ALTER TABLE CIDADAO "
							+ " ADD RESPONDEU_FVD BIT DEFAULT 0; ";
					db.execSQL(query);
				}
			} catch (Exception e){
				e.printStackTrace();
				Log.e("ErroSQL", "Erro SQL Alter Table CIDADAO (Classe " + this.getClass() + "). Message: " + e.getMessage());
				Mensagens.msgSimples(ctx, "Erro...", "Erro SQL Alter Table CIDADAO (" + this.getClass() + ")");					
			}
			//Alterando table: CIDADAO - FIM \\	
		}
	}
	
	// This method will return if your table exist a field or not
	public boolean isFieldExist(SQLiteDatabase db, String tableName, String fieldName) {
	     boolean isExist = true;
	     Cursor res = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 1",null);
	     int value = res.getColumnIndex(fieldName);

	     if(value == -1)
	     {
	          isExist = false;
	     }
	     return isExist;
	}

}
