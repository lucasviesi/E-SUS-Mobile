package com.focus.activities;

import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Mensagens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroDomicilio extends Activity {	

	//COLOQUEI ESSES TRAÇOS (//-----) ORGANIZANDO O CÓDIGO E INDICANDO A SEPARAÇÃO DE COMANDOS
	//COLOQUEI ESSES ASTERISTICO (/*****/) ORGANIZANDO O CÓDIGO, MAS FAZENDO PARTE DO MESMO COMANDO DE CÓDIGO
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_domicilio);

		//PEGANDO AS IDS DOS CAMPOS QUE RECEBERAM MASCARAS
		EditText CEP = (EditText) findViewById(R.id.ED_Cep);//CEP
		EditText TelResidencia = (EditText) findViewById(R.id.ed_TelResidencia);//TEL RESIDENCIA
		EditText TelContato = (EditText) findViewById(R.id.ed_TelContato);//TEL CONTATO
		EditText TelContatoInst = (EditText) findViewById(R.id.ED_InstPermaTel);//TEL CONTATO ABA 4
		EditText DtNasc01 = (EditText) findViewById(R.id.ED_FamiData);//DATA NASCIMENTO OPÇÃO 1 - ABA 3
		EditText DtNasc02 = (EditText) findViewById(R.id.ED_FamiData01);//DATA NASCIMENTO OPÇÃO 2 - ABA 3
		EditText DtNasc03 = (EditText) findViewById(R.id.ED_FamiData02);//DATA NASCIMENTO OPÇÃO 3 - ABA 3
		EditText DtNasc04 = (EditText) findViewById(R.id.ED_FamiData03);//DATA NASCIMENTO OPÇÃO 4 - ABA 3
		EditText DtReside01 = (EditText) findViewById(R.id.ED_FamiReside);//RESIDE DESDE OPÇÃO 1 - ABA 3
		EditText DtReside02 = (EditText) findViewById(R.id.ED_FamiReside01);//RESIDE DESDE OPÇÃO 2 - ABA 3
		EditText DtReside03 = (EditText) findViewById(R.id.ED_FamiReside02);//RESIDE DESDE OPÇÃO 3 - ABA 3
		EditText DtReside04 = (EditText) findViewById(R.id.ED_FamiReside03);//RESIDE DESDE OPÇÃO 4 - ABA 3
		
		//CRIANDO AS MASCARAS (LEMBRANDO QUE ESTA SENDO FEITO DESSE JEITO GRAÇAS AO ANDROIDMASK-1.0.0.JAR)
		MaskEditTextChangedListener maskCEP = new MaskEditTextChangedListener("#####-###", CEP);
		MaskEditTextChangedListener maskTelResidencia = new MaskEditTextChangedListener("(##)#####-####", TelResidencia);
		MaskEditTextChangedListener maskTelContato = new MaskEditTextChangedListener("(##)#####-####", TelContato);
		MaskEditTextChangedListener maskTelContatoInst = new MaskEditTextChangedListener("(##)#####-####", TelContatoInst);
		MaskEditTextChangedListener maskDtNasc01 = new MaskEditTextChangedListener("##/##/####", DtNasc01);
		MaskEditTextChangedListener maskDtNasc02 = new MaskEditTextChangedListener("##/##/####", DtNasc02);
		MaskEditTextChangedListener maskDtNasc03 = new MaskEditTextChangedListener("##/##/####", DtNasc03);
		MaskEditTextChangedListener maskDtNasc04 = new MaskEditTextChangedListener("##/##/####", DtNasc04);
		MaskEditTextChangedListener maskDtReside01 = new MaskEditTextChangedListener("##/####", DtReside01);
		MaskEditTextChangedListener maskDtReside02 = new MaskEditTextChangedListener("##/####", DtReside02);
		MaskEditTextChangedListener maskDtReside03 = new MaskEditTextChangedListener("##/####", DtReside03);
		MaskEditTextChangedListener maskDtReside04 = new MaskEditTextChangedListener("##/####", DtReside04);

		
		
		//ADICIONANDO AS MASCARAS
		CEP.addTextChangedListener(maskCEP);
		TelResidencia.addTextChangedListener(maskTelResidencia);
		TelContato.addTextChangedListener(maskTelContato);
		TelContatoInst.addTextChangedListener(maskTelContatoInst);
		DtNasc01.addTextChangedListener(maskDtNasc01);
		DtNasc02.addTextChangedListener(maskDtNasc02);
		DtNasc03.addTextChangedListener(maskDtNasc03);
		DtNasc04.addTextChangedListener(maskDtNasc04);
		DtReside01.addTextChangedListener(maskDtReside01);
		DtReside02.addTextChangedListener(maskDtReside02);
		DtReside03.addTextChangedListener(maskDtReside03);
		DtReside04.addTextChangedListener(maskDtReside04);
//---------------------------------------------------------------------------
		
		//ABAS DAS FICHAS
		TabHost abas = (TabHost) findViewById(android.R.id.tabhost);
		abas.setup();
		//ABA1
		TabSpec descritor = abas.newTabSpec("aba1");
		descritor.setContent(R.id.tab1);
		descritor.setIndicator(getString(R.string.aba_endPermanencia));
		abas.addTab(descritor);
		//ABA2 
		descritor = abas.newTabSpec("aba2");
		descritor.setContent(R.id.tab2);
		descritor.setIndicator(getString(R.string.aba_condMoradia));
		abas.addTab(descritor);
		//ABA3
		descritor = abas.newTabSpec("aba3");
		descritor.setContent(R.id.tab3);
		descritor.setIndicator(getString(R.string.aba_FamiliasDom));
		abas.addTab(descritor);
		//ABA4
		descritor = abas.newTabSpec("aba4");
		descritor.setContent(R.id.tab4);
		descritor.setIndicator(getString(R.string.aba_InstPermanencia));
		abas.addTab(descritor);
		//FIM DAS ABAS DAS FICHAS
		
//--------------------------------------------------------------------------------------
		
		/*EVENTO CLICK RADIO BUTTON ANIMAIS OPÇÃO SIM */
		RadioButton rb1 = (RadioButton)findViewById(R.id.Animais01);
		rb1.setOnClickListener(new View.OnClickListener() {
			RadioButton rb1 = (RadioButton)findViewById(R.id.Animais01);
			EditText ed1 = (EditText)findViewById(R.id.ED_AnimaisQtd);
			CheckBox cb1 = (CheckBox)findViewById(R.id.Cbgato);
			CheckBox cb2 = (CheckBox)findViewById(R.id.CbCachorro);
			CheckBox cb3 = (CheckBox)findViewById(R.id.CbPassaro);
			CheckBox cb4 = (CheckBox)findViewById(R.id.CbAniOutros);
			@Override
			public void onClick(View v) {
				if((rb1.isChecked())) {
					cb1.setEnabled(true);
					cb2.setEnabled(true);
					cb3.setEnabled(true);
					cb4.setEnabled(true);
					ed1.setEnabled(true);
				}
			}
		});//FIM DO EVENTO CLICK ANIMAIS
		
		//****************************************************************************
		
		/*EVENTO CLICK RADIO BUTTON ANIMAIS OPÇÃO NÃO */
		RadioButton rb2 = (RadioButton)findViewById(R.id.Animais02);		
		rb2.setOnClickListener(new View.OnClickListener() {
			RadioButton rb2 = (RadioButton)findViewById(R.id.Animais02);
			EditText ed1 = (EditText)findViewById(R.id.ED_AnimaisQtd);
			CheckBox cb1 = (CheckBox)findViewById(R.id.Cbgato);
			CheckBox cb2 = (CheckBox)findViewById(R.id.CbCachorro);
			CheckBox cb3 = (CheckBox)findViewById(R.id.CbPassaro);
			CheckBox cb4 = (CheckBox)findViewById(R.id.CbAniOutros);
			@Override
			public void onClick(View v) {
				if((rb2.isChecked())) {
					cb1.setEnabled(false);
					cb2.setEnabled(false);
					cb3.setEnabled(false);
					cb4.setEnabled(false);
					cb1.setChecked(false);
					cb2.setChecked(false);
					cb3.setChecked(false);
					cb4.setChecked(false);
					ed1.setEnabled(false);
				}
			}
		});// FIM DO EVENTO CLICK ANIMAIS 

//--------------------------------------------------------------------------------------
		
		/*EVENTO CLICK NO BOTÃO SALVAR -- FAZER AQUI AS VALIDAÇÕES DOS CAMPOS*/
		//COLOQUEI ESSES TRAÇOS (//----) ORGANIZANDO O CÓDIGO, MAS NÂO QUER DIZER QUE SÂO COMANDOS DIFERENTES
		Button NextButton = (Button)findViewById(R.id.BtnSalvarCadDom);
		NextButton.setOnClickListener(new View.OnClickListener() {
			
			//IDS
			EditText t1 = (EditText) findViewById(R.id.ED_Cep);
			EditText t2 = (EditText) findViewById(R.id.ED_Municipio);
			EditText t4 = (EditText) findViewById(R.id.ED_UF);
			EditText t5 = (EditText) findViewById(R.id.ED_Bairro);
			EditText t6 = (EditText) findViewById(R.id.ED_TpLogradouro);
			EditText t7 = (EditText) findViewById(R.id.ed_NomeLogradouro);
			EditText t8 = (EditText) findViewById(R.id.ed_Numero);
			EditText t9 = (EditText) findViewById(R.id.ed_NumMicroArea);
			EditText t10 = (EditText) findViewById(R.id.ed_Imovel);
			ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_Aba2);
			ScrollView scrollView1 = (ScrollView) findViewById(R.id.scroll_Aba1);
			RadioButton rb1 = (RadioButton)findViewById(R.id.Moradia01);
			RadioButton rb2 = (RadioButton)findViewById(R.id.Moradia02);
			RadioButton rb3 = (RadioButton)findViewById(R.id.Moradia03);
			RadioButton rb4 = (RadioButton)findViewById(R.id.Moradia04);
			RadioButton rb5 = (RadioButton)findViewById(R.id.Moradia05);
			RadioButton rb6 = (RadioButton)findViewById(R.id.Moradia06);
			RadioButton rb7 = (RadioButton)findViewById(R.id.Moradia07);
			RadioButton rb8 = (RadioButton)findViewById(R.id.Moradia08);
			RadioButton rb9 = (RadioButton)findViewById(R.id.Localizacao01);
			RadioButton rb10 = (RadioButton)findViewById(R.id.Localizacao02);
			TextView tv1 = (TextView)findViewById(R.id.tv_SitLocalizacao);
			TextView tv2 = (TextView)findViewById(R.id.TV_CEP);
			TextView tv3 = (TextView)findViewById(R.id.TV_Municipio);
			TextView tv4 = (TextView)findViewById(R.id.TV_Uf);
			TextView tv5 = (TextView)findViewById(R.id.TV_Bairro);
			TextView tv6 = (TextView)findViewById(R.id.TV_Logradouro);
			TextView tv7 = (TextView)findViewById(R.id.TV_nomeLogradouro);
			TextView tv8 = (TextView)findViewById(R.id.tv_SitMoradia);
			TextView tv9 = (TextView)findViewById(R.id.TV_Numero);
			TextView tv10 = (TextView)findViewById(R.id.tv_CodMicroArea);
			TextView tv11 = (TextView)findViewById(R.id.tv_Imovel);
			
			TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
			
			//******************************************************************
			
			@Override
			public void onClick(View v) {
				
				//************************************************************************
				//CEP
				if( (t1.getText().toString().equals(""))){
					t1.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv2.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t1.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//MUNICIPIO
				if( (t2.getText().toString().equals(""))){
					t2.setError("É necessário preencher o campo.");
					t2.requestFocus();
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv3.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t2.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//UF
				if( (t4.getText().toString().equals(""))){
					t4.requestFocus();
					t4.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv4.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t4.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//BAIRRO
				if( (t5.getText().toString().equals(""))){
					t5.requestFocus();
					t5.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv5.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t5.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//TIPO DE LOGRADOURO
				if( (t6.getText().toString().equals(""))){
					t6.requestFocus();
					t6.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv6.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t6.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//NOME LOGRADOURO
				if( (t7.getText().toString().equals(""))){
					t7.requestFocus();
					t7.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv7.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t7.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//NUMERO 
				if( (t8.getText().toString().equals(""))){
					t8.requestFocus();
					t8.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv9.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t8.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//MICROAREA
				if( (t9.getText().toString().equals(""))){
					t9.requestFocus();
					t9.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv10.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t9.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//************************************************************************
				//TIPO IMOVEL
				if( (t10.getText().toString().equals(""))){
					t10.requestFocus();
					t10.setError("É necessário preencher o campo.");
					scrollView1.postDelayed(new Runnable() {
					     public void run() {
					          scrollView1.scrollTo(0, (int)tv11.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								t10.setError(null);
							}
						}, 
					5000);
					return;
				}
				
				//*******************************************************************
				//VERIFICANDO SE OS RADIOBUTTONS ESTÃO MARCADOS "SITUAÇÃO DE MORADIA" 
				if((rb1.isChecked()) || (rb2.isChecked()) || (rb3.isChecked()) || (rb4.isChecked()) || (rb5.isChecked()) || (rb6.isChecked()) || (rb7.isChecked()) || (rb8.isChecked())) {
				} else {
			    	tabHost.setup();
			    	tabHost.setCurrentTab(1);
					
					rb8.setError("É necessario ao menos selecionar uma opção.");
					rb8.requestFocus();
					scrollView.postDelayed(new Runnable() {//EVENTO SCROLL QUE LEVA A TELA PARA ONDE ESTÁ O ERRO
					     public void run() {
					          scrollView.scrollTo(0, (int)tv8.getY());
					     }
					}, 0);
					new android.os.Handler().postDelayed(//POST DELAYED COMANDO QUE VAI ACIONA O EVENTO COM UM TEMPO DEFINIDO
						new Runnable() {
							public void run() {
								rb8.setError(null);
							}
						}, 
					5000);
					
					return;
				}
				
				//*********************************************************************
				
				//VERIFICANDO SE OS RADIOBUTTONS ESTÃO MARCADOS "LOCALIZAÇÃO"
				if((rb9.isChecked()) || (rb10.isChecked())){} else {
					rb10.setError("É necessario ao menos selecionar uma opção.");
					rb10.requestFocus();
					scrollView.postDelayed(new Runnable() {
					     public void run() {
					          scrollView.scrollTo(0, (int)tv1.getY());
					     }
					}, 0);
					//
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								rb10.setError(null);
							}
						}, 
					5000);			
					return;
				}
				
				//*******************************************************************
				AlertDialog.Builder builder = new AlertDialog.Builder(CadastroDomicilio.this);
				builder.setTitle("Inserir Informações");
				builder.setMessage("Deseja realmente salvar os dados informados?");
				builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {	
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
				builder.setNegativeButton("Não", null);
				builder.create();
				builder.show();
			} 
				/*
				 VERIRICAR O TOAST PARA N FICAR REPETINDO ELE
				  private Toast mToast = null;				 
					if (mToast != null) mToast.cancel();
					mToast = Toast.makeText(CadastroDomicilio.this, "É necessário Preencher Todos os campos.", Toast.LENGTH_SHORT);
					mToast.show();
				}*/
			
		});//FIM DO EVENTO CLICK DO BOTÃO SALVAR
		
//------------------------------------------------------------------------------------------------			
	}
	
	public void voltarMenu(View v) {
		finish();
	}
//--------------------------------------------------------------------------------------
	
}
