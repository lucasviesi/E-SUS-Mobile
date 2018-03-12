package com.focus.funcoes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Mensagens {
	public static void msgSimples(Context ctx, String title, String mensagem) {
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ctx);
		dlgAlert.setTitle(title);
		dlgAlert.setMessage(mensagem);
		dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}

	public static void msgSimples(Context ctx, String title, String mensagem, int icon) {
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ctx);
		dlgAlert.setTitle(title);
		dlgAlert.setMessage(mensagem);
		dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		dlgAlert.setIcon(icon);
		dlgAlert.create().show();
	}

	public static void msgSimplesYesNo(Context ctx, String title, String mensagem,
			DialogInterface.OnClickListener click) {
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ctx);
		dlgAlert.setTitle(title);
		dlgAlert.setMessage(mensagem);
		dlgAlert.setNegativeButton("Não", null);
		dlgAlert.setPositiveButton("Sim", click);
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}

	public static void msgSimplesYesNo(Context ctx, String title, String mensagem, int icon,
			DialogInterface.OnClickListener click) {
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ctx);
		dlgAlert.setTitle(title);
		dlgAlert.setMessage(mensagem);
		dlgAlert.setNegativeButton("Não", null);
		dlgAlert.setPositiveButton("Sim", click);
		dlgAlert.setCancelable(true);
		dlgAlert.setIcon(icon);
		dlgAlert.create().show();
	}

	public static void desejaRealmenteSair(Context ctx, String title, String message) {
		if (title == null || title.equals("") || title.equals("title")) {
			title = "Atenção...";
		}
		if (message == null || message.equals("") || title.equals("title")) {
			message = "Deseja Realmente Sair?";
		}

		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ctx);
		dlgAlert.setTitle(title);
		dlgAlert.setMessage(message);
		dlgAlert.setNegativeButton("Não", null);
		dlgAlert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				System.exit(1);
			}
		});
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}	
}
