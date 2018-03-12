package com.focus.adapters;

import java.util.ArrayList;

import com.focus.activities.FichaVisitaDomiciliarC;
import com.focus.dominio.Cidadao;
import com.focus.dominio.DomicilioVisita;
import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Funcoes;
import com.focus.funcoes.Mensagens;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class DomicilioVisitaAdapter extends BaseAdapter {
	private Context ctx;
	private ArrayList<DomicilioVisita> lista;
	private AlertDialog.Builder builder;	
	
	public Dialog onCreateDialog(long cod_dom) {
		builder = new AlertDialog.Builder(ctx);
		
	    ArrayList<Cidadao> cidadosDoDominicio = new Cidadao(ctx).getCidadaosDomicilio(cod_dom);
		String[] pacsDom = new String[cidadosDoDominicio.size()];
		int i = 0;
		for (Cidadao cid : cidadosDoDominicio) {
			if (cid.getRespondeu_fvd() == 1) {
				pacsDom[i] = Funcoes.priPalavraMaiuscula( cid.getNome() ) + " (Possui Ficha Lançada)";				
			} else {
				pacsDom[i] = Funcoes.priPalavraMaiuscula( cid.getNome() );				
			}
			i++;
		};
		
		builder.setTitle(R.string.pacsDom);
	    builder.setItems(pacsDom, new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int which) {

            }	    	
        });
	    
	    builder.setNegativeButton(R.string.btnFechar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
//				Fecha sozinho
            }
	    });
	    return builder.create();
	}	
		

	public DomicilioVisitaAdapter(Context ctx, ArrayList<DomicilioVisita> lista) {
		this.ctx = ctx;
		this.lista = lista;
	}

	// Tamanho da lista
	@Override
	public int getCount() {
		return lista.size();
	}

	// Item no posição. Havera muitos erros, se passar uma posição que não
	// existe
	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	// Esse metodo é chamado para cada Item da sua lista
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		
		DomicilioVisita d = lista.get(position);
		View layout;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.domicilio, null);
		} else {
			layout = convertView;
		}
		
		TextView TV_CodDom = (TextView) layout.findViewById(R.id.Cod_Dom);
		TV_CodDom.setText("Domícilio: " + String.valueOf(d.getCod_domicilio()));
		
		TextView TV_End = (TextView) layout.findViewById(R.id.Endereco);
		if(d.getComplemento() == null || d.getComplemento().equals("")){
			TV_End.setText(d.getTipo_logradouro() + " " + d.getLogradouro() + ", " + d.getNumero());
		}
		else {
			TV_End.setText(d.getTipo_logradouro() + d.getLogradouro() + ", " + d.getNumero() + " (" + d.getComplemento() + ")");
		}

		TextView TV_Bairro = (TextView) layout.findViewById(R.id.Bairro);
		TV_Bairro.setText("Bairro: " + d.getBairro());
		
		Button btn_1 = (Button) layout.findViewById(R.id.btn_1);
		
		layout.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				//Se não houver pacientes que residem naquele domicilio
				if (new Cidadao(ctx).conta(lista.get(position).getCod_domicilio(), false) == 0 ){
					Mensagens.msgSimples(ctx, "Atenção...", "Nenhum cidadão vinculado a esse domicilio."
							+ "\nNão é possível abrir o questionário!");
				}
				else if (new Cidadao(ctx).conta(lista.get(position).getCod_domicilio(), true) == 0){
					Mensagens.msgSimples(ctx, "Atenção...", "Há Fichas lançadas para TODOS os pacientes desse domicílio."
							+ "\nNão é possível abrir o questionário!");			
				}				
//				else if (list.get(pos).getVisitado() == 1){
//					Mensagens.msgSimples(ListaDomicilios.this, "Informação...", "Domicílio já visitado!",
//							android.R.drawable.ic_dialog_info);
//				}
				else {
					((Activity) ctx).finish(); //fecho a tela de listagem
					//Chamando a tela do Questionário e passando o codigo do Domicilio como parametro
					Intent intent = new Intent(ctx, FichaVisitaDomiciliarC.class);
						
					Bundle params = new Bundle();
					params.putLong("cod_domicilio", lista.get(position).getCod_domicilio());
					intent.putExtras(params);
						
					((Activity) ctx).startActivity(intent);					
				}		
				return true;				
			}
		});
		
		btn_1.setOnClickListener( new OnClickListener() {
			public void onClick(View v) {
				onCreateDialog(lista.get(position).getCod_domicilio());
				builder.show();
			}
		});		

		if (lista.get(position).getVisitado() == 1) {
			layout.setBackgroundResource(R.drawable.item_list_selector_visitado);			
		} else {
			layout.setBackgroundResource(R.drawable.item_list_selector);
		}

		return layout;
	}

}
