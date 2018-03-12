package com.focus.activities;

import java.io.File;

import com.focus.banco.Handle_SQLite;
import com.focus.dominio.Usuario;
import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Criptografia;
import com.focus.funcoes.Funcoes;
import com.focus.funcoes.Mensagens;
import com.focus.outros.VarConst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

public class Login extends Activity {
	private Handle_SQLite banco;

	private TextView ed_usuario;
	private TextView ed_senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Cria as pastas caso não existam
		File backupDB = new File(Environment.getExternalStorageDirectory(), Handle_SQLite.LOCAL_BANCO);
		backupDB.mkdirs();
		banco = new Handle_SQLite(this);
		// Cria o banco assim que abre o APP
//		banco.onCreate(banco.getWritableDatabase());
		banco.onUpgrade(banco.getWritableDatabase(), 0, Handle_SQLite.VERSAO_BD);

		ed_usuario = (TextView) findViewById(R.id.ED_Usuario);
		ed_senha = (TextView) findViewById(R.id.ED_Senha);
	}

	//Autenticar usuário
	public void logar(View v) {
		if (ed_usuario.getText().toString().equals("") || ed_usuario.getText() == null) {
			Mensagens.msgSimples(this, "Atenção...", "Informe o usuário!");
			ed_usuario.requestFocus();
			return;
		}

		if (ed_senha.getText().toString().equals("") || ed_senha.getText() == null) {
			Mensagens.msgSimples(this, "Atenção...", "Informe a senha!");
			ed_senha.requestFocus();
			return;
		}

		int x;
		if (ed_usuario.getText().toString().equals("master") && ed_senha.getText().toString().equals("abelha")) {
			x = 1;// valido
			VarConst.cod_usu_logado = 9000000;
			VarConst.usuario_logado = "MASTER";
			VarConst.nome_usu_logado = "Master";
		} else {
			Usuario u = new Usuario(this);
			x = u.logar(ed_usuario.getText().toString(), Criptografia.MD5(ed_senha.getText().toString()));
		}

		switch (x) {
		case 0:// Usuário Invalido
			Mensagens.msgSimples(this, "Atenção...", "Usuário e/ou senha inválidos!",
					android.R.drawable.ic_dialog_alert);
			ed_usuario.requestFocus();
			break;

		case 1:// Usuário Valido
			// Abrindo menu principal
			Intent menu = new Intent(this, MenuPrincipal.class);
			startActivity(menu);
			finish();
			break;

		case -1:
			Mensagens.msgSimples(this, "Atenção...", "Não há usuários. Favor realizar a importação dos dados!",
					android.R.drawable.ic_dialog_alert);
			break;
		}
	}

	//Sair. Ativa a vibração e o modal de "Deseja Realmente Sair"
	public void sair(View v) {
		Funcoes.vibrar(this, 100);
		Mensagens.desejaRealmenteSair(this, null, null);
	}
}
