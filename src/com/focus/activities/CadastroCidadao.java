package com.focus.activities;

import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Mensagens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class CadastroCidadao extends Activity /*implements OnClickListener*/ {	
	
	/*private static final String NOME_BD = "FocusESUS.db";
	public static final int VERSAO_BD = 3;
	public static final String LOCAL_BANCO = "/FocusESUS/";*/

	int dataObito = 0;
	int cardiaca = 0;
	int rim = 0;
	int pulmao = 0;
	int higiene = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cidadao);

//---------------------------------------------------------------------------------------------		
		//PEGANDO AS IDS DOS CAMPOS QUE RECEBERAM MASCARAS
		EditText DtNasc = (EditText) findViewById(R.id.ED_DtNasc);//DATA NASCIMENTO
		EditText DtNaturalizacao = (EditText) findViewById(R.id.ED_DtNaturalizacao);//DATA NATURALIZAÇÃO
		EditText DtEntrada = (EditText) findViewById(R.id.ED_DtEntBr);//DATA ENTRADA NO BRASIL
		EditText DtObito = (EditText) findViewById(R.id.ED_DataObito);//DATA OBITO
		EditText TelefoneCel = (EditText) findViewById(R.id.ED_Celular);//TELEFONE-CELULAR
		EditText NumeroDO = (EditText) findViewById(R.id.ED_NumeroDO);//NUMERO D.O.
		
		//CRIANDO AS MASCARAS (LEMBRANDO QUE ESTA SENDO FEITO DESSE JEITO GRAÇAS AO ANDROIDMASK-1.0.0.JAR)
		MaskEditTextChangedListener maskDtNasc = new MaskEditTextChangedListener("##/##/####", DtNasc);
		MaskEditTextChangedListener maskDtNaturalizacao = new MaskEditTextChangedListener("##/##/####", DtNaturalizacao);
		MaskEditTextChangedListener maskDtEntrada = new MaskEditTextChangedListener("##/##/####", DtEntrada);
		MaskEditTextChangedListener maskDtObito = new MaskEditTextChangedListener("##/##/####", DtObito);
		MaskEditTextChangedListener maskTelefoneCel = new MaskEditTextChangedListener("(##)#####-####", TelefoneCel);
		MaskEditTextChangedListener maskNumeroDO = new MaskEditTextChangedListener("########-#", NumeroDO);
		
		//ADICIONANDO AS MASCARAS
		DtNasc.addTextChangedListener(maskDtNasc);
		DtNaturalizacao.addTextChangedListener(maskDtNaturalizacao);
		DtEntrada.addTextChangedListener(maskDtEntrada);
		TelefoneCel.addTextChangedListener(maskTelefoneCel);
		DtObito.addTextChangedListener(maskDtObito);
		NumeroDO.addTextChangedListener(maskNumeroDO);
//---------------------------------------------------------------------------------------------	
	
		//ABAS
		TabHost abas = (TabHost) findViewById(android.R.id.tabhost);
		abas.setup();
		//aba1
		TabSpec descritor = abas.newTabSpec("aba1");
		descritor.setContent(R.id.tab1);
		descritor.setIndicator(getString(R.string.aba_identCid));
		abas.addTab(descritor);
		//aba2 
		descritor = abas.newTabSpec("aba2");
		descritor.setContent(R.id.tab2);
		descritor.setIndicator(getString(R.string.aba_infoSocioDem));
		abas.addTab(descritor);
		//aba3
		descritor = abas.newTabSpec("aba3");
		descritor.setContent(R.id.tab3);
		descritor.setIndicator(getString(R.string.aba_condSaudeGeral));
		abas.addTab(descritor);
		//aba4
		descritor = abas.newTabSpec("aba4");
		descritor.setContent(R.id.tab4);
		descritor.setIndicator(getString(R.string.aba_CidSitRua));
		abas.addTab(descritor);

//--------------------------------------------------------------------------------------------
		
		//RADIO BUTTON DEFICIENCIA (OPÇÂO SIM)
		RadioButton rd00 = (RadioButton)findViewById(R.id.InfoDeficiencia01);
		rd00.setOnClickListener(new View.OnClickListener() {
			
			CheckBox cb_1 = (CheckBox)findViewById(R.id.CbAuditiva);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.CbIntelecto);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.CbVisual);
			CheckBox cb_4 = (CheckBox)findViewById(R.id.CbFisica);
			CheckBox cb_5 = (CheckBox)findViewById(R.id.CbOutraDef);
			TextView tv_1 = (TextView)findViewById(R.id.tv_InfoDeficienciaQual);
			
			@Override
			public void onClick(View v) {
				tv_1.setEnabled(true);
				cb_1.setEnabled(true);
				cb_2.setEnabled(true);
				cb_3.setEnabled(true);
				cb_4.setEnabled(true);
				cb_5.setEnabled(true);
			}
		});
		//RADIO BUTTON DEFICIENCIA (OPÇÂO NÃO)
		RadioButton rd01 = (RadioButton)findViewById(R.id.OriInfoDeficiencia02);
		rd01.setOnClickListener(new View.OnClickListener() {
			CheckBox cb_1 = (CheckBox)findViewById(R.id.CbAuditiva);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.CbIntelecto);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.CbVisual);
			CheckBox cb_4 = (CheckBox)findViewById(R.id.CbFisica);
			CheckBox cb_5 = (CheckBox)findViewById(R.id.CbOutraDef);
			TextView tv_1 = (TextView)findViewById(R.id.tv_InfoDeficienciaQual);
			@Override
			public void onClick(View v) {
				cb_1.setChecked(false);
				cb_2.setChecked(false);
				cb_3.setChecked(false);
				cb_4.setChecked(false);
				cb_5.setChecked(false);
				cb_1.setEnabled(false);
				cb_2.setEnabled(false);
				cb_3.setEnabled(false);
				cb_4.setEnabled(false);
				cb_5.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
		
//---------------------------------------------------------------------------------------------
		//ORIENTAÇÃO SEXUAl (OPÇÃO SIM)
		RadioButton rbsexo01 = (RadioButton)findViewById(R.id.OriSexual01);
		rbsexo01.setOnClickListener(new View.OnClickListener() {
			RadioButton rb_35 = (RadioButton)findViewById(R.id.OriSexualQual01);
			RadioButton rb_36 = (RadioButton)findViewById(R.id.OriSexualQual02);
			RadioButton rb_37 = (RadioButton)findViewById(R.id.OriSexualQual03);
			RadioButton rb_38 = (RadioButton)findViewById(R.id.OriSexualQual04);
			TextView tv_1 = (TextView)findViewById(R.id.tv_InfoOriSexualQual);
			@Override
			public void onClick(View v) {
				tv_1.setEnabled(true);
				rb_35.setEnabled(true);
				rb_36.setEnabled(true);
				rb_37.setEnabled(true);
				rb_38.setEnabled(true);
			}
		});
		//ORIENTAÇÃO SEXUAl (OPÇÃO NÃO)
		RadioButton rbsexo02 = (RadioButton)findViewById(R.id.OriSexual02);
		rbsexo02.setOnClickListener(new View.OnClickListener() {
			RadioButton rb_35 = (RadioButton)findViewById(R.id.OriSexualQual01);
			RadioButton rb_36 = (RadioButton)findViewById(R.id.OriSexualQual02);
			RadioButton rb_37 = (RadioButton)findViewById(R.id.OriSexualQual03);
			RadioButton rb_38 = (RadioButton)findViewById(R.id.OriSexualQual04);
			TextView tv_1 = (TextView)findViewById(R.id.tv_InfoOriSexualQual);
			@Override
			public void onClick(View v) {
				rb_35.setChecked(false);
				rb_36.setChecked(false);
				rb_37.setChecked(false);
				rb_38.setChecked(false);
				rb_35.setEnabled(false);
				rb_36.setEnabled(false);
				rb_37.setEnabled(false);
				rb_38.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
//---------------------------------------------------------------------------------------------
		//IDENTIDADE DO GENERO (OPÇÃO SIM)
			RadioButton rbgenero01 = (RadioButton)findViewById(R.id.OriGenero01);
			rbgenero01.setOnClickListener(new View.OnClickListener() {
				RadioButton rb_35 = (RadioButton)findViewById(R.id.GeneroQual01);
				RadioButton rb_36 = (RadioButton)findViewById(R.id.GeneroQual02);
				RadioButton rb_37 = (RadioButton)findViewById(R.id.GeneroQual03);
				RadioButton rb_38 = (RadioButton)findViewById(R.id.GeneroQual04);
				TextView tv_1 = (TextView)findViewById(R.id.tv_GeneroQual);
				@Override
				public void onClick(View v) {
					rb_35.setEnabled(true);
					rb_36.setEnabled(true);
					rb_37.setEnabled(true);
					rb_38.setEnabled(true);
					tv_1.setEnabled(true);
				}
			});
			//IDENTIDADE DO GENERO (OPÇÃO NÃO)
			RadioButton rbgenero02 = (RadioButton)findViewById(R.id.OriGenero02);
			rbgenero02.setOnClickListener(new View.OnClickListener() {
				RadioButton rb_35 = (RadioButton)findViewById(R.id.GeneroQual01);
				RadioButton rb_36 = (RadioButton)findViewById(R.id.GeneroQual02);
				RadioButton rb_37 = (RadioButton)findViewById(R.id.GeneroQual03);
				RadioButton rb_38 = (RadioButton)findViewById(R.id.GeneroQual04);
				TextView tv_1 = (TextView)findViewById(R.id.tv_GeneroQual);
				@Override
				public void onClick(View v) {
					rb_35.setChecked(false);
					rb_36.setChecked(false);
					rb_37.setChecked(false);
					rb_38.setChecked(false);
					rb_35.setEnabled(false);
					rb_36.setEnabled(false);
					rb_37.setEnabled(false);
					rb_38.setEnabled(false);
					tv_1.setEnabled(false);
				}
			});			
//---------------------------------------------------------------------------------------------
		//IDENTIDADE DO GENERO (OPÇÃO SIM)
			RadioButton comuTrad01 = (RadioButton)findViewById(R.id.ComuTradi01);
			comuTrad01.setOnClickListener(new View.OnClickListener() {
				EditText ed_1 = (EditText)findViewById(R.id.ED_PovoComuTradi);
				TextView tv_1 = (TextView)findViewById(R.id.tv_InfoPovoComuQual);
				@Override
				public void onClick(View v) {
					ed_1.setEnabled(true);
					tv_1.setEnabled(true);
				}
			});
			//IDENTIDADE DO GENERO (OPÇÃO NÃO)
			RadioButton comuTrad02 = (RadioButton)findViewById(R.id.ComuTradi02);
			comuTrad02.setOnClickListener(new View.OnClickListener() {
				EditText ed_1 = (EditText)findViewById(R.id.ED_PovoComuTradi);
				TextView tv_1 = (TextView)findViewById(R.id.tv_InfoPovoComuQual);
				@Override
				public void onClick(View v) {
					ed_1.setText("");
					ed_1.setEnabled(false);
					tv_1.setEnabled(false);
				}
			});
//---------------------------------------------------------------------------------------------
//OBITO (OPÇÃO SIM)
			boolean obito = false;
			RadioButton saidaCid01 = (RadioButton)findViewById(R.id.SaidaCid02);
			saidaCid01.setOnClickListener(new View.OnClickListener() {
				EditText ed_1 = (EditText)findViewById(R.id.ED_NumeroDO);
				EditText ed_2 = (EditText)findViewById(R.id.ED_DataObito);
				TextView tv_1 = (TextView)findViewById(R.id.tv_InfoSaidaCidadaoQual);
				TextView tv_2 = (TextView)findViewById(R.id.tv_InfoDtObito);
				TextView tv_3 = (TextView)findViewById(R.id.tv_NumeroDO);
				@Override
				public void onClick(View v) {
					ed_1.setEnabled(true);
					ed_2.setEnabled(true);
					tv_1.setEnabled(true);
					tv_2.setEnabled(true);
					tv_3.setEnabled(true);
					dataObito = 1;				
				}
			});
//OBITO (OPÇÃO NÃO)
			RadioButton saidaCid02 = (RadioButton)findViewById(R.id.SaidaCid01);
			saidaCid02.setOnClickListener(new View.OnClickListener() {
				EditText ed_1 = (EditText)findViewById(R.id.ED_NumeroDO);
				EditText ed_2 = (EditText)findViewById(R.id.ED_DataObito);
				TextView tv_1 = (TextView)findViewById(R.id.tv_InfoSaidaCidadaoQual);
				TextView tv_2 = (TextView)findViewById(R.id.tv_InfoDtObito);
				TextView tv_3 = (TextView)findViewById(R.id.tv_NumeroDO);
				@Override
				public void onClick(View v) {
					ed_1.setText("");
					ed_1.setEnabled(false);
					ed_2.setEnabled(false);
					ed_2.setText("");
					tv_1.setEnabled(false);
					tv_2.setEnabled(false);
					tv_3.setEnabled(false);
					dataObito = 0;
				}
			});
//---------------------------------------------------------------------------------------------
//GESTANTE (OPÇÃO SIM)
			RadioButton gestante01 = (RadioButton)findViewById(R.id.Gestante01);
			gestante01.setOnClickListener(new View.OnClickListener() {
				EditText ed_1 = (EditText)findViewById(R.id.ED_GestanteQual);
				TextView tv_1 = (TextView)findViewById(R.id.TV_GestanteQual);
				@Override
				public void onClick(View v) {
					ed_1.setEnabled(true);
					tv_1.setEnabled(true);
				}
			});
//GESTANTE (OPÇÃO NÃO)
			RadioButton gestante02 = (RadioButton)findViewById(R.id.Gestante02);
			gestante02.setOnClickListener(new View.OnClickListener() {
				EditText ed_1 = (EditText)findViewById(R.id.ED_GestanteQual);
				TextView tv_1 = (TextView)findViewById(R.id.TV_GestanteQual);
				@Override
				public void onClick(View v) {
					ed_1.setText("");
					ed_1.setEnabled(false);
					tv_1.setEnabled(false);
				}
			});
//---------------------------------------------------------------------------------------------
//CARDIACA (OPÇÃO SIM)
			RadioButton cardiaca01 = (RadioButton)findViewById(R.id.Cardiaca01);
			cardiaca01.setOnClickListener(new View.OnClickListener() {
				TextView tv_1 = (TextView)findViewById(R.id.tv_Cardiaca);
				CheckBox cb_1 = (CheckBox)findViewById(R.id.InsufCard);
				CheckBox cb_2 = (CheckBox)findViewById(R.id.CardOutra);
				CheckBox cb_3 = (CheckBox)findViewById(R.id.CardNSabe);
				@Override
				public void onClick(View v) {
					cb_1.setEnabled(true);
					cb_2.setEnabled(true);
					cb_3.setEnabled(true);
					tv_1.setEnabled(true);
					cardiaca = 1;
				}
			});
//CARDIACA (OPÇÃO NÃO)
			RadioButton cardiaca02 = (RadioButton)findViewById(R.id.Cardiaca02);
			cardiaca02.setOnClickListener(new View.OnClickListener() {
				TextView tv_1 = (TextView)findViewById(R.id.tv_Cardiaca);
				CheckBox cb_1 = (CheckBox)findViewById(R.id.InsufCard);
				CheckBox cb_2 = (CheckBox)findViewById(R.id.CardOutra);
				CheckBox cb_3 = (CheckBox)findViewById(R.id.CardNSabe);
				@Override
				public void onClick(View v) {
					cb_1.setChecked(false);
					cb_2.setChecked(false);
					cb_3.setChecked(false);
					cb_1.setEnabled(false);
					cb_2.setEnabled(false);
					cb_3.setEnabled(false);
					tv_1.setEnabled(false);
					cardiaca = 0;
				}
			});
			
//---------------------------------------------------------------------------------------------
//RIM (OPÇÃO SIM)
			RadioButton rim01 = (RadioButton)findViewById(R.id.Rim01);
			rim01.setOnClickListener(new View.OnClickListener() {
				TextView tv_1 = (TextView)findViewById(R.id.tv_RimQual);
				CheckBox cb_1 = (CheckBox)findViewById(R.id.RimInsufCard);
				CheckBox cb_2 = (CheckBox)findViewById(R.id.RimOutra);
				CheckBox cb_3 = (CheckBox)findViewById(R.id.RimNSabe);
				@Override
				public void onClick(View v) {
					cb_1.setEnabled(true);
					cb_2.setEnabled(true);
					cb_3.setEnabled(true);
					tv_1.setEnabled(true);
					rim = 1;
				}
			});
//RIM (OPÇÃO NÃO)
			RadioButton rim02 = (RadioButton)findViewById(R.id.Rim02);
			rim02.setOnClickListener(new View.OnClickListener() {
				TextView tv_1 = (TextView)findViewById(R.id.tv_RimQual);
				CheckBox cb_1 = (CheckBox)findViewById(R.id.RimInsufCard);
				CheckBox cb_2 = (CheckBox)findViewById(R.id.RimOutra);
				CheckBox cb_3 = (CheckBox)findViewById(R.id.RimNSabe);
				@Override
				public void onClick(View v) {
					cb_1.setChecked(false);
					cb_2.setChecked(false);
					cb_3.setChecked(false);
					cb_1.setEnabled(false);
					cb_2.setEnabled(false);
					cb_3.setEnabled(false);
					tv_1.setEnabled(false);
					rim = 0;
				}
			});
			
//---------------------------------------------------------------------------------------------
//PULMAO (OPÇÃO SIM)
		RadioButton pulmao01 = (RadioButton)findViewById(R.id.Respiratoria01);
		pulmao01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_RespiratoriaQual);
			CheckBox cb_1 = (CheckBox)findViewById(R.id.PulmaoInsufCard);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.PulmaoOutra);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.PulmaoNSabe);
			@Override
			public void onClick(View v) {
				cb_1.setEnabled(true);
				cb_2.setEnabled(true);
				cb_3.setEnabled(true);
				tv_1.setEnabled(true);
				pulmao = 1;
			}
		});
//PULMAO (OPÇÃO NÃO)
		RadioButton pulmao02 = (RadioButton)findViewById(R.id.Respiratoria02);
		pulmao02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_RespiratoriaQual);
			CheckBox cb_1 = (CheckBox)findViewById(R.id.PulmaoInsufCard);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.PulmaoOutra);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.PulmaoNSabe);			
			@Override
			public void onClick(View v) {
				cb_1.setChecked(false);
				cb_2.setChecked(false);
				cb_3.setChecked(false);
				cb_1.setEnabled(false);
				cb_2.setEnabled(false);
				cb_3.setEnabled(false);
				tv_1.setEnabled(false);
				pulmao = 0; 
			}
		});
		
//---------------------------------------------------------------------------------------------
//INTERNACAO (OPÇÃO SIM)
		RadioButton internacao01 = (RadioButton)findViewById(R.id.Internacao01);
		internacao01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_InternacaoQual);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Internacao);
			@Override
			public void onClick(View v) {
				ed_1.setEnabled(true);
				tv_1.setEnabled(true);
			}
		});
//INTERNACAO (OPÇÃO NÃO)
		RadioButton internacao02 = (RadioButton)findViewById(R.id.Internacao02);
		internacao02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_InternacaoQual);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Internacao);
			@Override
			public void onClick(View v) {
				ed_1.setText("");
				ed_1.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
//---------------------------------------------------------------------------------------------
//PLANTAS (OPÇÃO SIM)
		RadioButton planta01 = (RadioButton)findViewById(R.id.Planta01);
		planta01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_Plantas);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Plantas);
			@Override
			public void onClick(View v) {
				ed_1.setEnabled(true);
				tv_1.setEnabled(true);
			}
		});
//PLANTAS (OPÇÃO NÃO)
		RadioButton planta02 = (RadioButton)findViewById(R.id.Plantas02);
		planta02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_Plantas);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Plantas);
			@Override
			public void onClick(View v) {
				ed_1.setText("");
				ed_1.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
		
//---------------------------------------------------------------------------------------------
//INSTITUIÇÃO (OPÇÃO SIM)
		RadioButton instituicao01 = (RadioButton)findViewById(R.id.Instituicao01);
		instituicao01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_instituicao);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Instituicao);
			@Override
			public void onClick(View v) {
				ed_1.setEnabled(true);
				tv_1.setEnabled(true);
			}
		});
//INSTITUIÇÃO (OPÇÃO NÃO)
		RadioButton instituicao02 = (RadioButton)findViewById(R.id.Instituicao02);
		instituicao02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_instituicao);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Instituicao);
			@Override
			public void onClick(View v) {
				ed_1.setText("");
				ed_1.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
//---------------------------------------------------------------------------------------------
//VISITA ALGUM FAMILIAR (OPÇÃO SIM)
		RadioButton visita01 = (RadioButton)findViewById(R.id.Visita01);
		visita01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_visita);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Visita);
			@Override
			public void onClick(View v) {
				ed_1.setEnabled(true);
				tv_1.setEnabled(true);
			}
		});
//VISITA ALGUM FAMILIAR (OPÇÃO NÃO)
		RadioButton visita02 = (RadioButton)findViewById(R.id.Visita02);
		visita02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_visita);
			EditText ed_1 = (EditText)findViewById(R.id.ED_Visita);
			@Override
			public void onClick(View v) {
				ed_1.setText("");
				ed_1.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
//---------------------------------------------------------------------------------------------
//HIGIENE (OPÇÃO SIM)
		RadioButton higiene01 = (RadioButton)findViewById(R.id.Higiene01);
		higiene01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_Higiene);
			CheckBox cb_1 = (CheckBox)findViewById(R.id.Higi01);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.Higi02);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.Higi03);
			CheckBox cb_4 = (CheckBox)findViewById(R.id.Higi04);
			@Override
			public void onClick(View v) {
				cb_1.setEnabled(true);
				cb_2.setEnabled(true);
				cb_3.setEnabled(true);
				cb_4.setEnabled(true);
				tv_1.setEnabled(true);
				higiene = 1;
			}
		});
//HIGIENE (OPÇÃO NÃO)
		RadioButton higiene02 = (RadioButton)findViewById(R.id.Higiene02);
		higiene02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.tv_Higiene);
			CheckBox cb_1 = (CheckBox)findViewById(R.id.Higi01);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.Higi02);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.Higi03);
			CheckBox cb_4 = (CheckBox)findViewById(R.id.Higi04);
			@Override
			public void onClick(View v) {
				cb_1.setChecked(false);
				cb_2.setChecked(false);
				cb_3.setChecked(false);
				cb_4.setChecked(false);
				cb_1.setEnabled(false);
				cb_2.setEnabled(false);
				cb_3.setEnabled(false);
				cb_4.setEnabled(false);
				tv_1.setEnabled(false);
				higiene = 0;
			}
		});
		//---------------------------------------------------------------------------------------------
//SITUAÇÃO RUA (OPÇÃO SIM)
		RadioButton sitrua01 = (RadioButton)findViewById(R.id.SitRua01);
		sitrua01.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.TV_CidSitRuaQual);
			RadioButton rb_1 = (RadioButton)findViewById(R.id.TempRua01);
			RadioButton rb_2 = (RadioButton)findViewById(R.id.TempRua02);
			RadioButton rb_3 = (RadioButton)findViewById(R.id.SitRua03);
			RadioButton rb_4 = (RadioButton)findViewById(R.id.SitRua04);
			@Override
			public void onClick(View v) {
				rb_1.setEnabled(true);
				rb_2.setEnabled(true);
				rb_3.setEnabled(true);
				rb_4.setEnabled(true);
				tv_1.setEnabled(true);
			}
		});
//SITUAÇÃO RUA (OPÇÃO NÃO)
		RadioButton sitrua02 = (RadioButton)findViewById(R.id.SitRua02);
		sitrua02.setOnClickListener(new View.OnClickListener() {
			TextView tv_1 = (TextView)findViewById(R.id.TV_CidSitRuaQual);
			RadioButton rb_1 = (RadioButton)findViewById(R.id.TempRua01);
			RadioButton rb_2 = (RadioButton)findViewById(R.id.TempRua02);
			RadioButton rb_3 = (RadioButton)findViewById(R.id.SitRua03);
			RadioButton rb_4 = (RadioButton)findViewById(R.id.SitRua04);
			@Override
			public void onClick(View v) {
				rb_1.setChecked(false);
				rb_2.setChecked(false);
				rb_3.setChecked(false);
				rb_4.setChecked(false);
				rb_1.setEnabled(false);
				rb_2.setEnabled(false);
				rb_3.setEnabled(false);
				rb_4.setEnabled(false);
				tv_1.setEnabled(false);
			}
		});
//---------------------------------------------------------------------------------------------
		//BOTÃO SALVAR
		Button NextButton1 = (Button)findViewById(R.id.BtnSalvarCadCid);
		NextButton1.setOnClickListener(new View.OnClickListener() {
			
			//IDS
			ScrollView scroll_View = (ScrollView) findViewById(R.id.scroll_Aba_1);
			ScrollView scroll_View1 = (ScrollView) findViewById(R.id.scroll_Aba_2);
			ScrollView scroll_View2 = (ScrollView) findViewById(R.id.scroll_Aba_3);
			ScrollView scroll_View3 = (ScrollView) findViewById(R.id.scroll_Aba_4);
			EditText ed_1 = (EditText)findViewById(R.id.ed_NumMicroArea);
			TextView tv_1 = (TextView)findViewById(R.id.tv_CodMicroArea);
			//
			EditText ed_2 = (EditText)findViewById(R.id.ED_NomeComp);
			TextView tv_2 = (TextView)findViewById(R.id.tv_NomeComp);
			//
			EditText ed_3 = (EditText)findViewById(R.id.ED_DtNasc);
			TextView tv_3 = (TextView)findViewById(R.id.tv_DtNasc);
			//
			RadioButton rb_1 = (RadioButton)findViewById(R.id.Sexo01);
			RadioButton rb_2 = (RadioButton)findViewById(R.id.Sexo02);
			TextView tv_4 = (TextView)findViewById(R.id.tv_Sexo);
			//
			RadioButton rb_3 = (RadioButton)findViewById(R.id.Cor01);
			RadioButton rb_4 = (RadioButton)findViewById(R.id.Cor02);
			RadioButton rb_5 = (RadioButton)findViewById(R.id.Cor03);
			RadioButton rb_6 = (RadioButton)findViewById(R.id.Cor04);
			RadioButton rb_7 = (RadioButton)findViewById(R.id.Cor05);
			TextView tv_5 = (TextView)findViewById(R.id.tv_RacaCor);
			//
			EditText ed_4 = (EditText)findViewById(R.id.ED_Etnia);
			TextView tv_6 = (TextView)findViewById(R.id.tv_Etnia);
			//
			EditText ed_5 = (EditText)findViewById(R.id.ED_NomeMae);
			TextView tv_7 = (TextView)findViewById(R.id.tv_NomeMae);
			//
			EditText ed_6 = (EditText)findViewById(R.id.ED_NomePai);
			TextView tv_8 = (TextView)findViewById(R.id.tv_NomePai);
			//
			RadioButton rb_8 = (RadioButton)findViewById(R.id.Nacionalidade01);
			RadioButton rb_9 = (RadioButton)findViewById(R.id.Nacionalidade02);
			RadioButton rb_10 = (RadioButton)findViewById(R.id.Nacionalidade03);
			TextView tv_9 = (TextView)findViewById(R.id.tv_Nacionalidade);
			//
			EditText ed_7 = (EditText)findViewById(R.id.ED_PaisOrigem);
			TextView tv_10 = (TextView)findViewById(R.id.tv_PaisNasc);
			//
			EditText ed_8 = (EditText)findViewById(R.id.ED_DtNaturalizacao);
			TextView tv_11 = (TextView)findViewById(R.id.tv_DtNaturalizacao);
			//
			EditText ed_9 = (EditText)findViewById(R.id.ED_PortNaturalizacao);
			TextView tv_12 = (TextView)findViewById(R.id.tv_PortNaturalizacao);
			//
			EditText ed_10 = (EditText)findViewById(R.id.ED_MunUFNasc);
			TextView tv_13 = (TextView)findViewById(R.id.tv_MunUFNasc);
			//
			EditText ed_11 = (EditText)findViewById(R.id.ED_DtEntBr);
			TextView tv_14 = (TextView)findViewById(R.id.tv_DtEntBr);
			//FIM ABA1 - COMEÇO ABA 2
			RadioButton rb_11 = (RadioButton)findViewById(R.id.RbParen01);
			RadioButton rb_12 = (RadioButton)findViewById(R.id.RbParen02);
			RadioButton rb_13 = (RadioButton)findViewById(R.id.RbParen03);
			RadioButton rb_14 = (RadioButton)findViewById(R.id.RbParen04);
			RadioButton rb_15 = (RadioButton)findViewById(R.id.RbParen05);
			RadioButton rb_16 = (RadioButton)findViewById(R.id.RbParen06);
			RadioButton rb_17 = (RadioButton)findViewById(R.id.RbParen07);
			RadioButton rb_18 = (RadioButton)findViewById(R.id.RbParen08);
			RadioButton rb_19 = (RadioButton)findViewById(R.id.RbParen09);
			RadioButton rb_20 = (RadioButton)findViewById(R.id.RbParen10);
			TextView tv_15 = (TextView)findViewById(R.id.tv_Parentesco);
			//
			RadioButton rb_21 = (RadioButton)findViewById(R.id.RbFreq01);
			RadioButton rb_22 = (RadioButton)findViewById(R.id.RbFreq02);
			TextView tv_16 = (TextView)findViewById(R.id.tv_InfoFrequenta);
			//
			RadioButton rb_23 = (RadioButton)findViewById(R.id.InfoDeficiencia01);
			RadioButton rb_24 = (RadioButton)findViewById(R.id.OriInfoDeficiencia02);
			CheckBox cb_1 = (CheckBox)findViewById(R.id.CbAuditiva);
			CheckBox cb_2 = (CheckBox)findViewById(R.id.CbIntelecto);
			CheckBox cb_3 = (CheckBox)findViewById(R.id.CbVisual);
			CheckBox cb_4 = (CheckBox)findViewById(R.id.CbFisica);
			CheckBox cb_5 = (CheckBox)findViewById(R.id.CbOutraDef);
			TextView tv_17 = (TextView)findViewById(R.id.tv_InfoDeficiencia);
			//
			EditText ed_12 = (EditText)findViewById(R.id.ED_DataObito);
			EditText ed_13 = (EditText)findViewById(R.id.ED_NumeroDO);
			RadioButton rb_25 = (RadioButton)findViewById(R.id.SaidaCid01);
			RadioButton rb_26 = (RadioButton)findViewById(R.id.SaidaCid02);
			TextView tv_18 = (TextView)findViewById(R.id.tv_InfoSaidaCidadao);
			TextView tv_19 = (TextView)findViewById(R.id.tv_InfoSaidaCidadaoQual);
			TextView tv_20 = (TextView)findViewById(R.id.tv_NumeroDO);
			//FIM ABA 2 - COMEÇO ABA 3
			RadioButton rb_27 = (RadioButton)findViewById(R.id.SitRua01);
			RadioButton rb_28 = (RadioButton)findViewById(R.id.SitRua02);
			TextView tv_21 = (TextView)findViewById(R.id.TV_TituloAba4);
			//
			RadioButton rb_33 = (RadioButton)findViewById(R.id.OriSexual01);
			RadioButton rb_34 = (RadioButton)findViewById(R.id.OriSexual02);
			RadioButton rb_35 = (RadioButton)findViewById(R.id.OriSexualQual01);
			RadioButton rb_36 = (RadioButton)findViewById(R.id.OriSexualQual02);
			RadioButton rb_37 = (RadioButton)findViewById(R.id.OriSexualQual03);
			RadioButton rb_38 = (RadioButton)findViewById(R.id.OriSexualQual04);
			TextView tv_23 = (TextView)findViewById(R.id.tv_InfoOriSexual);
			TextView tv_24 = (TextView)findViewById(R.id.tv_InfoOriSexualQual);
			//FIM ABA 4 - COMEÇO DAS VALIDAÇõES DE CONDIÇÕES QUE SÂO DEPOIS DO SIM E NÃO
			//CARDIACA 
			TextView tv_x1 = (TextView)findViewById(R.id.tv_Cardiaca);
			CheckBox cb_x1 = (CheckBox)findViewById(R.id.InsufCard);
			CheckBox cb_x2 = (CheckBox)findViewById(R.id.CardOutra);
			CheckBox cb_x3 = (CheckBox)findViewById(R.id.CardNSabe);
			//RINS
			TextView tv_x2= (TextView)findViewById(R.id.tv_RimQual);
			CheckBox cb_x4 = (CheckBox)findViewById(R.id.RimInsufCard);
			CheckBox cb_x5 = (CheckBox)findViewById(R.id.RimOutra);
			CheckBox cb_x6 = (CheckBox)findViewById(R.id.RimNSabe);
			//PULMAO
			TextView tv_x3 = (TextView)findViewById(R.id.tv_RespiratoriaQual);
			CheckBox cb_x7 = (CheckBox)findViewById(R.id.PulmaoInsufCard);
			CheckBox cb_x8 = (CheckBox)findViewById(R.id.PulmaoOutra);
			CheckBox cb_x9 = (CheckBox)findViewById(R.id.PulmaoNSabe);
			//HIGIENE
			TextView tv_x4 = (TextView)findViewById(R.id.tv_Higiene);
			CheckBox cb_x10 = (CheckBox)findViewById(R.id.Higi01);
			CheckBox cb_x11 = (CheckBox)findViewById(R.id.Higi02);
			CheckBox cb_x12 = (CheckBox)findViewById(R.id.Higi03);
			CheckBox cb_x13 = (CheckBox)findViewById(R.id.Higi04);
			
			TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
			
			//*****************************************************************************
			
			@Override
			public void onClick(View v) {
				
			//***************************************************************************
				//MICROAREA
				if( (ed_1.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_1.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_1.setError("É necessário preencher este campo.");
							ed_1.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_1.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//NOME COMPLETO
				if( (ed_2.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_2.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_2.setError("É necessário preencher este campo.");
							ed_2.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_2.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//DATA NASCIMENTO
				if( (ed_3.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_3.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_3.setError("É necessário preencher este campo.");
							ed_3.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_3.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//SEXO
				if( rb_1.isChecked() || rb_2.isChecked()){} else {
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_4.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_2.setError("É necessário Selecionar uma opção.");
							rb_2.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_2.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//RAÇA COR
				if( rb_3.isChecked() || rb_4.isChecked() || rb_5.isChecked() || rb_6.isChecked() || rb_7.isChecked()){} else {
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_5.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_7.setError("É necessário Selecionar uma opção.");
							rb_7.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_7.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//ETNIA
				if( (ed_4.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_6.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_4.setError("É necessário preencher este campo.");
							ed_4.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_4.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//NOME MAE
				if( (ed_5.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_7.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_5.setError("É necessário preencher este campo.");
							ed_5.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_5.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//NOME PAI
				if( (ed_6.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_8.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_6.setError("É necessário preencher este campo.");
							ed_6.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_6.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//NACIONALIDADE
				if( rb_8.isChecked() || rb_9.isChecked() || rb_10.isChecked()){} else {
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_9.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_10.setError("É necessário Selecionar uma opção.");
							rb_10.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_10.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//PAIS ORIGEM
				if( (ed_7.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_10.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_7.setError("É necessário preencher este campo.");
							ed_7.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_7.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//NATURALIZAÇÃO
				if( (ed_8.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_11.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_8.setError("É necessário preencher este campo.");
							ed_8.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_8.setError(null);
					}}, 5000);
					return;
				}
				
				//******************************************************************************
				//PORT NATURALIZAÇÃO
				if( (ed_9.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_12.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_9.setError("É necessário preencher este campo.");
							ed_9.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_9.setError(null);
					}}, 5000);
					return;
				}
				
				//******************************************************************************
				//UF NASCIMENTO
				if( (ed_10.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_13.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_10.setError("É necessário preencher este campo.");
							ed_10.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_10.setError(null);
					}}, 5000);
					return;
				}
				
				//******************************************************************************
				//DATA NASCIMENTO BRASIL
				if( (ed_11.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_14.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_11.setError("É necessário preencher este campo.");
							ed_11.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_11.setError(null);
					}}, 5000);
					return;
				}
				
				//******************************************************************************
				//--------------ABA2-------------------------
				//PARENTESCO
				if( rb_11.isChecked() || rb_12.isChecked() || rb_13.isChecked() || rb_14.isChecked() || rb_15.isChecked() || rb_16.isChecked() || rb_17.isChecked() || rb_18.isChecked() || rb_19.isChecked() || rb_20.isChecked()){} else {
				
			    	tabHost.setup();
			    	tabHost.setCurrentTab(1);
				
					new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
							scroll_View1.scrollTo(0, (int)tv_15.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_20.requestFocus();
							rb_20.setError("É necessário Selecionar uma opção.");
					}}, 200);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_20.setError(null);
					}}, 5000);
					return;
				}				
				//******************************************************************************
				//FREQUENTA
				if(rb_21.isChecked() || rb_22.isChecked()){} else {
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View1.scrollTo(0, (int)tv_16.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_22.setError("É necessário Selecionar uma opção.");
							rb_22.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_22.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//DEFICIENCIA 
				if(rb_23.isChecked() || rb_24.isChecked()){} else {
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View1.scrollTo(0, (int)tv_17.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_24.setError("É necessário Selecionar uma opção.");
							rb_24.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_24.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//SAIDA CIDADÃO
				if(rb_25.isChecked() || rb_26.isChecked()){} else {
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View1.scrollTo(0, (int)tv_18.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_26.setError("É necessário Selecionar uma opção.");
							rb_26.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_26.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//DATA OBITO
				if(dataObito == 1) {
				if( (ed_12.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_19.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_12.setError("É necessário preencher este campo.");
							ed_12.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_12.setError(null);
					}}, 5000);
					return;
				}}
				//******************************************************************************
				/*if( (ed_13.getText().toString().equals(""))){
					new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View.scrollTo(0, (int)tv_20.getY());
					}}, 0);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_13.setError("É necessário preencher este campo.");
							ed_13.requestFocus();
					}}, 100);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							ed_13.setError(null);
					}}, 5000);
					return;
				}*/
				//******************************************************************************
				//SITUAÇÃO DE RUA
				if(rb_27.isChecked() || rb_28.isChecked()){} else {
					
			    	tabHost.setup();
			    	tabHost.setCurrentTab(3);
			    	
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_28.setError("É necessário Selecionar uma opção.");
							rb_28.requestFocus();
					}}, 0);
			    	new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View3.scrollTo(0, (int)tv_21.getY());
					}}, 200);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							rb_28.setError(null);
					}}, 5000);
					return;
				}
				//******************************************************************************
				//CARDIADA
				if(cardiaca == 1){
				if(cb_x1.isChecked() || cb_x2.isChecked() || cb_x3.isChecked()){} else {
					
			    	tabHost.setup();
			    	tabHost.setCurrentTab(2);
			    	
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							cb_x3.setError("É necessário Selecionar uma opção.");
							cb_x3.requestFocus();
					}}, 0);
			    	new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View2.scrollTo(0, (int)tv_x1.getY());
					}}, 200);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							cb_x3.setError(null);
					}}, 5000);
					return;
				}}
				//******************************************************************************
				//RIM
				if(rim == 1){
				if(cb_x4.isChecked() || cb_x5.isChecked() || cb_x6.isChecked()){} else {
					
			    	tabHost.setup();
			    	tabHost.setCurrentTab(2);
			    	
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							cb_x6.setError("É necessário Selecionar uma opção.");
							cb_x6.requestFocus();
					}}, 0);
			    	new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View2.scrollTo(0, (int)tv_x2.getY());
					}}, 200);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							cb_x6.setError(null);
					}}, 5000);
					return;
				}}
				//******************************************************************************
				//PULMAO
				if(pulmao == 1){
				if(cb_x7.isChecked() || cb_x8.isChecked() || cb_x9.isChecked()){} else {
					
			    	tabHost.setup();
			    	tabHost.setCurrentTab(2);
			    	
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							cb_x9.setError("É necessário Selecionar uma opção.");
							cb_x9.requestFocus();
					}}, 0);
			    	new android.os.Handler().postDelayed(
					new Runnable() {
						public void run() {
							scroll_View2.scrollTo(0, (int)tv_x3.getY());
					}}, 200);
					new android.os.Handler().postDelayed(
						new Runnable() {
						public void run() {
							cb_x9.setError(null);
					}}, 5000);
					return;
				}}
				//******************************************************************************
				//HIGIENE
				if(higiene == 1){
					if(cb_x10.isChecked() || cb_x11.isChecked() || cb_x12.isChecked() || cb_x13.isChecked()){} else {
						
				    	tabHost.setup();
				    	tabHost.setCurrentTab(3);
				    	
						new android.os.Handler().postDelayed(
							new Runnable() {
							public void run() {
								cb_x13.setError("É necessário Selecionar uma opção.");
								cb_x13.requestFocus();
						}}, 0);
				    	new android.os.Handler().postDelayed(
						new Runnable() {
							public void run() {
								scroll_View3.scrollTo(0, (int)tv_x4.getY());
						}}, 200);
						new android.os.Handler().postDelayed(
							new Runnable() {
							public void run() {
								cb_x13.setError(null);
						}}, 5000);
						return;
					}}
				//*******************************************************************************
				//MENSAGEM DE CONFIRMAÇÃO SE REALMENTE DESEJA SALVAR
				AlertDialog.Builder builder = new AlertDialog.Builder(CadastroCidadao.this);
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
				//********************************************************************************
				
			}
		});
	}

//----------------------------------------------------------------------------------------------------------------
	
	//CRIANDO A TABELA PARA INSERIR O CADASTRO CIDADAO
	public void CriarTabela(){
		SQLiteDatabase db = null;
		try{
			db = openOrCreateDatabase("FocusESUS.db", Context.MODE_PRIVATE, null);
			//TABELA E CAMPOS
			StringBuilder sql = new StringBuilder();
			sql.append("CREATE TABLE IF NOT EXISTS FICHA_CIDADAO(");
			sql.append("_ID INTEGER PRIMARY KEY AUTOINCREMENT,");
			sql.append("CNS VARCHAR(30),");// ?
			sql.append("CNS_RESP VARCHAR(30),");
			sql.append("NUMERO VARCHAR(5),");//NUMERO DA MICROAREA
			sql.append("NOME VARCHAR(100),");
			sql.append("APELIDO VARCHAR(50),");//NOME SOCIAL
			sql.append("DTNASC SMALLDATE,"); 
			sql.append("SEXO CHAR(1),");
			sql.append("COD_RACACOR VARCHAR(2),");
			sql.append("COD_ETNIA INTEGER,");
			sql.append("PISNUM VARCHAR(13),");
			sql.append("MAE VARCHAR(100),");
			sql.append("PAI VARCHAR(100),");
			sql.append("NACIONALIDADE CHAR(1),");
			sql.append("PAIS_NASCIMENTO VARCHAR(50),");
			sql.append("DTNATURALIZACAO DATETIME,");
			sql.append("NPORTARIA VARCHAR(20),");
			sql.append("CODUF varchar(50),");//SIGLA PAIS NASCIMENTO
			sql.append("DT_ENTRADA_PAIS SMALLDATETIME,");
			sql.append("FONECONTATO VARCHAR(20),");
			sql.append("EMAIL VARCHAR(100),");
			sql.append("RELACAO_PARENTESCO INTEGER,");
			sql.append("OCUPACAO VARCHAR(50),");
			sql.append("CURSO_FREQUENTOU ÏNTEGER,");
			sql.append("SITUACAO_MERCADO INTEGER,");
			sql.append("ORIENTACAO_SEXUAL_QUAL INTEGER,");
			sql.append("OBITO SMALLDATETIME,");
			sql.append("DTOBITO SMALLDATETIME,");
			sql.append("NUMERO_DO varchar(30),");//?
			sql.append("PESO VARCHAR(30),");

			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append("");
			sql.append(")");
			
			db.execSQL(sql.toString());
		} catch(Exception ex) {
			Toast.makeText(getApplicationContext(), "Houve um erro", Toast.LENGTH_LONG).show();
		} finally {
			db.close();
		}
	}
	//********************************************************************************************************
	//TABELA QUE SALVA AS RESPOSTAS SIM E NÃO E SE SIM, QUAL
	public void CriarTabelaCDS(){
		SQLiteDatabase db = null;
		try{
			db = openOrCreateDatabase("FocusESUS.db", Context.MODE_PRIVATE, null);
			//TABELA E CAMPOS
			StringBuilder sql = new StringBuilder();
			sql.append("CREATE TABLE IF NOT EXISTS RESPOSTA_CDS_INDIVIDUAL(");
			sql.append("_CODIGO INTEGER PRIMARY KEY AUTOINCREMENT,");
			sql.append("COD_CIDADAO INTEGER,");
			sql.append("COD_PERGUNTA INTEGER,");
			sql.append("COD_PERGUNTA_ITEM INTEGER,");
			sql.append("RESPOSTA_SN BIT,");
			sql.append("RESPOSTA_DS VARCHAR(100),");
			sql.append("RESPOSTA_DT SMALLDATETIME,");
			sql.append(")");
			db.execSQL(sql.toString());
		} catch(Exception ex) {
			Toast.makeText(getApplicationContext(), "Houve um erro ao criar a tabela RESPOSTA_CDS_INDIVIDUAL", Toast.LENGTH_LONG).show();
		} finally {
			Toast.makeText(getApplicationContext(), "Tabela RESPOSTA_CDS_INDIVIDUAL criada com sucesso", Toast.LENGTH_LONG).show();
			db.close();
		}
	}
//---------------------------------------------------------------------------------------------	
	public void voltarMenu(View v) {
		finish();
	}
//---------------------------------------------------------------------------------------------	
}
