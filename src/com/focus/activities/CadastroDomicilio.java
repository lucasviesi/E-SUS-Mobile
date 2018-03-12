package com.focus.activities;

import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Mensagens;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CadastroDomicilio extends Activity {	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_domicilio);
	
		TabHost abas = (TabHost) findViewById(android.R.id.tabhost);
		abas.setup();
		
		TabSpec descritor = abas.newTabSpec("aba1");
		descritor.setContent(R.id.tab1);
		descritor.setIndicator(getString(R.string.aba_endPermanencia));
		abas.addTab(descritor);
		 
		descritor = abas.newTabSpec("aba2");
		descritor.setContent(R.id.tab2);
		descritor.setIndicator(getString(R.string.aba_condMoradia));
		abas.addTab(descritor);
		
		descritor = abas.newTabSpec("aba3");
		descritor.setContent(R.id.tab3);
		descritor.setIndicator(getString(R.string.aba_FamiliasDom));
		abas.addTab(descritor);
		
		descritor = abas.newTabSpec("aba4");
		descritor.setContent(R.id.tab4);
		descritor.setIndicator(getString(R.string.aba_InstPermanencia));
		abas.addTab(descritor);
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
