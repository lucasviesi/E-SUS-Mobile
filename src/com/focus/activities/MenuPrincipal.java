package com.focus.activities;

import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Funcoes;
import com.focus.funcoes.Mensagens;
import com.focus.outros.VarConst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuPrincipal extends Activity {
	private TextView TV_UsuaLogado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);

		TV_UsuaLogado = (TextView) findViewById(R.id.TV_UsuLogado);
		TV_UsuaLogado.append(" " + VarConst.nome_usu_logado);
	}

	public void sair(View v) {
		Funcoes.vibrar(this, 100);
		Mensagens.desejaRealmenteSair(this, null, null);
	}

	public void ListaDomicilios(View v) {
		startActivity(new Intent(this, ListaDomicilios.class));
		// finish();
	}
	
	public void CadastroDomicilio(View v) {
		startActivity(new Intent(this, CadastroDomicilio.class));
		// finish();
	}
	
	public void CadastroCidadao(View v) {
		startActivity(new Intent(this, CadastroCidadao.class));
		// finish();
	}

	public void FichaVisitaDomiciliarC(View v) {
		startActivity(new Intent(this, FichaVisitaDomiciliarC.class));
		// finish();
	}
}
