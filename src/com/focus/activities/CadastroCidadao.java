package com.focus.activities;

import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Mensagens;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CadastroCidadao extends Activity {	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cidadao);
		
		TabHost abas = (TabHost) findViewById(android.R.id.tabhost);
		abas.setup();
		
		TabSpec descritor = abas.newTabSpec("aba1");
		descritor.setContent(R.id.tab1);
		descritor.setIndicator(getString(R.string.aba_identCid));
		abas.addTab(descritor);
		 
		descritor = abas.newTabSpec("aba2");
		descritor.setContent(R.id.tab2);
		descritor.setIndicator(getString(R.string.aba_infoSocioDem));
		abas.addTab(descritor);
		
		descritor = abas.newTabSpec("aba3");
		descritor.setContent(R.id.tab3);
		descritor.setIndicator(getString(R.string.aba_condSaudeGeral));
		abas.addTab(descritor);
		
		descritor = abas.newTabSpec("aba4");
		descritor.setContent(R.id.tab4);
		descritor.setIndicator(getString(R.string.aba_CidSitRua));
		abas.addTab(descritor);
		
		//spinner responsável familiar
		String[] array_RespFam = new String[3];
		array_RespFam[0] = "Selecionar...";
		array_RespFam[1] = "Sim";
		array_RespFam[2] = "Não";
        Spinner sRF = (Spinner) findViewById(R.id.spinner_RespFamiliar);
        ArrayAdapter adapterRF= new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_RespFam);
        sRF.setAdapter(adapterRF);
        //
        
      //spinner Sexo
      String[] array_Sexo = new String[3];
      array_Sexo[0] = "Selecionar...";
      array_Sexo[1] = "Feminino";
      array_Sexo[2] = "Masculino";
      Spinner sSexo = (Spinner) findViewById(R.id.spinner_Sexo);
      ArrayAdapter adapterSexo= new ArrayAdapter(this,
      android.R.layout.simple_spinner_item, array_Sexo);
      sSexo.setAdapter(adapterSexo);
      //
      
    //spinner Raça Cor
      String[] array_RacaCor = new String[6];
      array_RacaCor[0] = "Selecionar...";
      array_RacaCor[1] = "Branca";
      array_RacaCor[2] = "Preta";
      array_RacaCor[3] = "Parda";
      array_RacaCor[4] = "Amarela";
      array_RacaCor[5] = "Indígena";
      Spinner sRC = (Spinner) findViewById(R.id.spinner_RacaCor);
      ArrayAdapter adapterRacaCor= new ArrayAdapter(this,
      android.R.layout.simple_spinner_item, array_RacaCor);
      sRC.setAdapter(adapterRacaCor);
      //
      
    //spinner Nacionalidade
      String[] array_Nacionalidade = new String[4];
      array_Nacionalidade[0] = "Selecionar...";
      array_Nacionalidade[1] = "Brasileira";
      array_Nacionalidade[2] = "Naturalizado";
      array_Nacionalidade[3] = "Estrangeiro";
      Spinner sNac = (Spinner) findViewById(R.id.spinner_Nacionalidade);
      ArrayAdapter adapterNacionalidade= new ArrayAdapter(this,
      android.R.layout.simple_spinner_item, array_Nacionalidade);
      sNac.setAdapter(adapterNacionalidade);
      //
	}
	
	public void voltarMenu(View v) {
		finish();
	}
	
	public void Salvar(View v) {
		DialogInterface.OnClickListener fechar = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		};
		Mensagens.msgSimplesYesNo(this, "Atenção", "Deseja realmente Salvar os Dados Informados?", fechar);
	}
}
