package com.focus.activities;

import java.util.ArrayList;

import com.focus.adapters.DomicilioVisitaAdapter;
import com.focus.dominio.DomicilioVisita;
import com.focus.e_sus_mobile.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListaDomicilios extends Activity {
	private ListView LV;
	private ArrayList<DomicilioVisita> list;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_domicilios);

		// Carregando Lista de Domicilios
		list = new DomicilioVisita(this).listaDomicilios();

		LV = (ListView) findViewById(R.id.lista);
		LV.setAdapter(new DomicilioVisitaAdapter(this, list));

		
		//As opções de OnClick estão no DomicilioVisitaAdapter
//		LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//			@Override
//			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
//				//Se não houver pacientes que residem naquele domicilio
//				if (new Cidadao(getBaseContext()).conta(list.get(pos).getCod_domicilio(), false) == 0 ){
//					Mensagens.msgSimples(ListaDomicilios.this, "Atenção...", "Nenhum cidadão vinculado a esse domicilio."
//							+ "\nNão é possível abrir o questionário!");
//				}
//				else if (new Cidadao(getBaseContext()).conta(list.get(pos).getCod_domicilio(), true) == 0){
//					Mensagens.msgSimples(ListaDomicilios.this, "Atenção...", "Há Fichas lançadas para TODOS os pacientes desse domicílio."
//							+ "\nNão é possível abrir o questionário!");			
//				}				
////				else if (list.get(pos).getVisitado() == 1){
////					Mensagens.msgSimples(ListaDomicilios.this, "Informação...", "Domicílio já visitado!",
////							android.R.drawable.ic_dialog_info);
////				}
//				else {
//					finish(); //fecho a tela de listagem
//					//Chamando a tela do Questionário e passando o codigo do Domicilio como parametro
//					Intent intent = new Intent(getBaseContext(), FichaVisitaDomiciliarC.class);
//						
//					Bundle params = new Bundle();
//					params.putLong("cod_domicilio", list.get(pos).getCod_domicilio());
//					intent.putExtras(params);
//						
//					startActivity(intent);					
//				}		
//				return true;				
//			}
//		});
	}
	
	public void voltarMenu(View v) {
		finish();
	}
}
