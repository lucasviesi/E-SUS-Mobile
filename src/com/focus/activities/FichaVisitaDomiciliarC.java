package com.focus.activities;

import java.util.ArrayList;
import java.util.Calendar;

import com.focus.dominio.Cidadao;
import com.focus.dominio.EsusFichaVisitaDom;
import com.focus.dominio.Usuario;
import com.focus.e_sus_mobile.R;
import com.focus.funcoes.Funcoes;
import com.focus.funcoes.Mensagens;
import com.focus.outros.VarConst;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class FichaVisitaDomiciliarC extends Activity {
	//Qtd de Motivos Visita
	private final int QTD_MTV = 36; 
	
	//Variaveis globais
	private long xCod_Domicilio;
	private long xCod_Cidadao;
	private long xCod_Prof;
	private Calendar dateAndTime = Calendar.getInstance();
	ArrayList<Cidadao> cidadosDoDominicio;
	private boolean receberfoco;
	private int indexDesfecho = 1; //fica salvo o index de "Visita Realizada"
		
	//Variaveis Auxiliares
	private String auxS;
	private int auxI;
	
	//Tipo Imovel
	private Spinner sp_tipoImovel;
	
	// Informações sobre o Profissional
	private TextView tv_NomeProf;
	private TextView tv_cnsProf;
	private TextView tv_CPFProf;
	private TextView tv_cboProf;
	private TextView tv_cnes_unidadeProf;
	private TextView tv_cod_equipeProf;
	private TextView tv_NomeEquipeProf;	
	private TextView tv_dataVisita;
	private TextView tv_horaVisita;
	private Button btn_trocaHora;

	// Informações sobre o Paciente
	private Spinner SP_NomePac;
	private TextView TV_NumProntuario;
	private TextView TV_CNSPac;
	private TextView TV_DtNascPac;
	private TextView TV_RGPac;
	private TextView TV_CPFPac;

	//Sexo
	private EditText ed_SexoPac;
	
	//Visita Compartilhada
	private CheckBox cb_VisitaComp;
	
	//Area/MicroArea
	private EditText ed_CodArea;
	private EditText ed_DescriArea;	
	private EditText ed_NumMicroArea;
	private EditText ed_DescriMicroArea;	
	private CheckBox cb_ForaArea;
	
	//Antropometria
	private EditText ed_AntroPeso;
	private EditText ed_AntroAltura;

	//RadioGroups
	private RadioGroup gbTurno;		//Turno
	private RadioGroup gbDesfecho;	//Desfecho
	
	//LinearLayout
	private LinearLayout ll_gbMotivoVisita; //Motivo da Visita
	private LinearLayout ll_Antropometria;  //Antropometria
	private LinearLayout ll_TipoImovel; 	//Tipo Imovel
	private LinearLayout ll_infoVisita;		//Info da Visita
	private LinearLayout ll_infoProf;		//Info do Profissional
	private LinearLayout ll_infoPac;		//Info do Paciente
	private LinearLayout ll_desfecho;		//Desfecho
	private LinearLayout ll_BuscaAtiva;		//Busca Ativa
	private LinearLayout ll_Acompanhamento;	//Acompanhamento
	
	private ScrollView scrollMaster;

	//TimePickerDialog, Construção
	private TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			
			auxS = Funcoes.zeroEsquerda(dateAndTime.get(Calendar.HOUR_OF_DAY), 2);//hora
			auxS +=":";
			auxS += Funcoes.zeroEsquerda(dateAndTime.get(Calendar.MINUTE), 2);//minuto
			tv_horaVisita.setText(auxS);
		}
	}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ficha_visita_domiciliar_c);
				
		Intent intent = getIntent();
		if (intent != null){
			Bundle params = intent.getExtras();
			
			if (params != null){
				xCod_Domicilio = params.getLong("cod_domicilio");
			}
		}
		xCod_Cidadao = -1;
		
		//Variavel usada no OnSelectedItem do Tipo de Imovel, para a primeira vez (quando eu dou o Create) ñ jogar foco em lugar nenhum 
		receberfoco = false;
		
		//Setando componentes
		scrollMaster = (ScrollView)findViewById(R.id.scroll_master);
		
		//LinearLayouts
		ll_infoProf = (LinearLayout)findViewById(R.id.ll_infoProf);
		ll_infoPac = (LinearLayout)findViewById(R.id.ll_infoPac);
		ll_infoVisita = (LinearLayout)findViewById(R.id.ll_infoVisita);
		ll_TipoImovel = (LinearLayout)findViewById(R.id.ll_TipoImovel);
		ll_Antropometria = (LinearLayout)findViewById(R.id.ll_Antropometria);
		ll_desfecho = (LinearLayout)findViewById(R.id.ll_desfecho);
		ll_gbMotivoVisita = (LinearLayout) findViewById(R.id.gb_MotivoVisita);
		ll_BuscaAtiva = (LinearLayout) findViewById(R.id.ll_BuscaAtiva);
		ll_Acompanhamento = (LinearLayout) findViewById(R.id.ll_Acompanhamento);
		
		//Area / MicroArea
		ed_CodArea = (EditText)findViewById(R.id.ed_CodArea);
		ed_DescriArea = (EditText)findViewById(R.id.ed_DescriArea);	
		ed_NumMicroArea = (EditText)findViewById(R.id.ed_NumMicroArea);
		ed_DescriMicroArea = (EditText)findViewById(R.id.ed_DescriMicroArea);				
		cb_ForaArea = (CheckBox)findViewById(R.id.CB_MicroA_FA);
		
		//Antropometria
		ed_AntroPeso = (EditText)findViewById(R.id.ed_AntroPeso);
		ed_AntroAltura = (EditText)findViewById(R.id.ed_AntroAltura);

		//Tipo Imovel
		sp_tipoImovel = (Spinner)findViewById(R.id.spinner_TipoImovel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipoImovel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipoImovel.setAdapter(adapter);
        
        sp_tipoImovel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ajustaItensByTipoImovel(position, Funcoes.GetIndexRadioGroup(gbDesfecho), receberfoco);
				receberfoco = true; //indicando q a partir de agora, quando selecionar o item, recebe o foco
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
        });
        
		//Profissional
		tv_NomeProf = (TextView) findViewById(R.id.ED_Nome_Prof);
		tv_cnsProf = (TextView) findViewById(R.id.ED_CNS_Prof);
		tv_CPFProf = (TextView) findViewById(R.id.ED_CPF_Prof);
		tv_cboProf = (TextView) findViewById(R.id.ED_CBO_Prof);
		tv_cnes_unidadeProf = (TextView) findViewById(R.id.ED_Cod_CNES_Uni);
		tv_cod_equipeProf = (TextView) findViewById(R.id.ED_CodEquipe);
		tv_NomeEquipeProf = (EditText) findViewById(R.id.ED_NomeEquipeProf); 
		tv_dataVisita = (TextView) findViewById(R.id.ED_DataVisita);
		tv_horaVisita = (TextView) findViewById(R.id.ED_HoraVisita);
		btn_trocaHora = (Button) findViewById(R.id.btn_TrocaHora);
		
		//Paciente
		SP_NomePac = (Spinner) findViewById(R.id.Spinner_PacNome);
		TV_NumProntuario = (TextView) findViewById(R.id.ED_NumProntuario);
		TV_CNSPac = (TextView) findViewById(R.id.ED_CNSPac);
		TV_DtNascPac = (TextView) findViewById(R.id.ED_DtNascPac);
		TV_RGPac = (TextView) findViewById(R.id.ED_RGPac);
		TV_CPFPac = (TextView) findViewById(R.id.ED_CPFPac);
		//Sexo
		ed_SexoPac = (EditText) findViewById(R.id.ed_SexoPac);
//		gbSexoPac = (RadioGroup) findViewById(R.id.gbSexoPac);
		cb_VisitaComp = (CheckBox) findViewById(R.id.CB_VisitaCompartilhada);
		
		//Turno
		gbTurno = (RadioGroup) findViewById(R.id.gbTurno);
		
		gbDesfecho = (RadioGroup) findViewById(R.id.gbDesfecho);
		
		//Abre o TimePickerDialog
		btn_trocaHora.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) { 
				new TimePickerDialog(FichaVisitaDomiciliarC.this, t,
						dateAndTime.get(Calendar.HOUR_OF_DAY),
	                    dateAndTime.get(Calendar.MINUTE),
	                    true).show();
	        }
		});

		//CARGA DAS INFORMAÇÕES
		cargaInfoProfissional();
		
		//Paciente
		cargaPacientesNoSpinner();
	}

	public void cargaPacientesNoSpinner(){
		Cidadao c = new Cidadao(this);
		cidadosDoDominicio = c.getCidadaosDomicilio(xCod_Domicilio);
		
		String[] nomes = new String[cidadosDoDominicio.size()+1];
		int i = 1;
		
		//Na Posição 0, tem uma especie de "Hint"
		nomes[0] = "Selecione um Paciente...";
		for (Cidadao cid : cidadosDoDominicio) {
			nomes[i] = cid.getNome();
			i++;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SP_NomePac.setAdapter(adapter);
		SP_NomePac.setSelection(0);
		
		SP_NomePac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {			
				//Se tiver selecionado (Não estiver no Hint)
				if (SP_NomePac.getSelectedItemPosition() != 0){
					//Como a posição 0 de nomes, é tipo um Hint. No ArrayList NÃO HÁ ESSA POSIÇÃO. Logo, há 1 elemento a menos no Array
					position--;
					
					//Verificando se o cidadão selecionado já ñ respondeu uma ficha
					if (cidadosDoDominicio.get(position).getRespondeu_fvd() == 1){
						Mensagens.msgSimples(FichaVisitaDomiciliarC.this, "Atenção...", "Já há uma Ficha lançada para esse paciente."
								+ "\nSelecione outro paciente!");
						SP_NomePac.setSelection(0);
					}
					else {
						xCod_Cidadao = cidadosDoDominicio.get(position).getCod_cidadao(); 
						TV_NumProntuario.setText(String.valueOf(cidadosDoDominicio.get(position).getCod_pro()));
						
						TV_CNSPac.setText(Funcoes.mascaraCNS(cidadosDoDominicio.get(position).getCns()));
						TV_DtNascPac.setText(Funcoes.ajustaDataDisplay(cidadosDoDominicio.get(position).getDtnasc()));
						TV_RGPac.setText(cidadosDoDominicio.get(position).getRg());
						TV_CPFPac.setText(cidadosDoDominicio.get(position).getCpf());
					 
						if (cidadosDoDominicio.get(position).getSexo().equals("F")){
							ed_SexoPac.setText("Feminino");
						} else {
							ed_SexoPac.setText("Masculino");
						}
					}					
				} 
				else {
					TV_NumProntuario.setText("");
					TV_CNSPac.setText("");
					TV_DtNascPac.setText("");
					TV_RGPac.setText("");
					TV_CPFPac.setText("");
					ed_SexoPac.setText("");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				 
			}			
		});	
	}
	
	public void cargaInfoProfissional(){
		//Profissional
		Usuario u = new Usuario(getApplicationContext()).getDadosUsuario();
		xCod_Prof = u.getCod_prof();
		tv_NomeProf.setText(u.getNome());
		tv_cnsProf.setText(Funcoes.mascaraCNS(u.getCns_prof()));		
		tv_CPFProf.setText(u.getCpf_prof());
		tv_cboProf.setText(u.getCbo_prof());
		tv_cnes_unidadeProf.setText(String.valueOf(u.getCod_cnes_unidade()));
		tv_NomeEquipeProf.setText(String.valueOf(u.getNome_equipe()));		
		tv_cod_equipeProf.setText(String.valueOf(u.getCod_ine()));
		tv_dataVisita.setText(Funcoes.getDataAtual());
		tv_horaVisita.setText(Funcoes.getHoraAtual(false));	
		//Area e MicroArea
		ed_CodArea.setText(u.getCod_area_esus_equipe());
		ed_DescriArea.setText(u.getNome_area_equipe());	
		ed_NumMicroArea.setText(u.getMicroArea().getNumero());
		ed_DescriMicroArea.setText(u.getMicroArea().getNome());		
	}
	
	public void DesativaMotivoVisita(View v){
		if (indexDesfecho != Funcoes.GetIndexRadioGroupEsus(gbDesfecho)){
			if (Funcoes.GetIndexRadioGroupEsus(gbDesfecho) != 1){
				//NÃO está em "Visita Realizada". Logo, limpar e desativar Motivos Visita
				limpaMotVisita();
				desativaMotVisita(false);
				EnabledClearAntropometria(false, true);
				indexDesfecho = Funcoes.GetIndexRadioGroupEsus(gbDesfecho);				
			} else {
				//ESTA em "Visita Realizada". Logo, ativar Motivos Visita , de acordo com o Tipo do Imovel selecionado
				ajustaItensByTipoImovel(sp_tipoImovel.getSelectedItemPosition(), Funcoes.GetIndexRadioGroup(gbDesfecho), false);
				indexDesfecho = Funcoes.GetIndexRadioGroupEsus(gbDesfecho);
			}
		}
	}
	
	private void desativaMotVisita(boolean status){
		//Percorrendo todos os Motivos Visita
		for (int i = 1; i <= QTD_MTV; i++){
			CheckBox check = (CheckBox) ll_gbMotivoVisita.findViewWithTag("cbMV" + Funcoes.zeroEsquerda(i, 2));
			check.setEnabled(status);
		}
	}
	
	private void limpaMotVisita(){
		//Percorrendo todos os Motivos Visita
		for (int i = 1; i <= QTD_MTV; i++){
			CheckBox check = (CheckBox) ll_gbMotivoVisita.findViewWithTag("cbMV" + Funcoes.zeroEsquerda(i, 2));
			check.setChecked(false);
		}
	}
	
	public void cancelar(View V){
		finish();
		Intent intent = new Intent(getBaseContext(), ListaDomicilios.class);
		startActivity(intent);
	}
	
	public boolean salvarFicha(View v){
		try{
			
			if(!validarCampoSalvar()){
				return false;
			}
			
			EsusFichaVisitaDom f = new EsusFichaVisitaDom(this);
			
			//informações do Paciente
			int xTipoImovel = sp_tipoImovel.getSelectedItemPosition();
			if (xTipoImovel == 1 || xTipoImovel == 7 || xTipoImovel == 8 || xTipoImovel == 9 || xTipoImovel == 10
					|| xTipoImovel == 11 || xTipoImovel == 13){
				//Obrigatório informar Cidadao
				f.setCod_cidadao(xCod_Cidadao);
				f.setCod_pro(TV_NumProntuario.getText().toString());
				//
				auxS = TV_CNSPac.getText().toString();
				auxS = auxS.replaceAll(" ", ""); 
				f.setCns_pac(auxS);
				f.setDtnasc(Funcoes.padraoData(TV_DtNascPac.getText().toString()));
				f.setSexo(Funcoes.getSiglaSexo(ed_SexoPac.getText().toString()));
				f.setCid_respondeu_fvd(1); //Indicando que esse cidadao respondeu à ficha
				//
			} else {
				//Obrigatório não informar Cidadao
				f.setCod_cidadao(-1);
				f.setCod_pro(null);
				f.setCns_pac(null);
				f.setDtnasc(null);
				f.setSexo(null);
				f.setCid_respondeu_fvd(-1);//como ñ tem cidadão, ele ñ respondeu
			}
			
//			//-1 indica que é nulo. É tratado na hora de salvar no banco
			f.setCod_turno(Funcoes.GetIndexRadioGroupEsus(gbTurno));
			f.setCod_desfecho(Funcoes.GetIndexRadioGroupEsus(gbDesfecho));
			f.setCod_prof(xCod_Prof);
			//
			auxS = tv_cnsProf.getText().toString();
			auxS = auxS.replaceAll(" ", ""); 
			//
			f.setCns_prof(auxS);
			f.setCpf_prof(tv_CPFProf.getText().toString());
			f.setCbo_prof(tv_cboProf.getText().toString());
			f.setCnes(Long.parseLong(tv_cnes_unidadeProf.getText().toString()));
			f.setCod_ine(tv_cod_equipeProf.getText().toString());
			//Data da Visita + Hora da Visita
			f.setData_visita(Funcoes.padraoData(tv_dataVisita.getText().toString()));
			f.setData_visita(f.getData_visita() + " " + tv_horaVisita.getText().toString() + ":00");

			f.setVisita_compartilha(cb_VisitaComp.isChecked());
			f.setCod_usu_salvou(VarConst.cod_usu_logado);
			f.setNome_usu_salvou(VarConst.usuario_logado.toUpperCase());
			f.setMaquina_salvou("TABLET");
			f.setDt_hr_salvou(Funcoes.padraoData(Funcoes.getDataHoraAtual(true)));
			
			//Campos Novos Atualização
			f.setFora_area(cb_ForaArea.isChecked());
			if (cb_ForaArea.isChecked()){
				//Fora de Area, NÃO marcada. Ñ salva as info de area
				f.setCod_area(-1);
				f.setCod_area_esus(null);
				f.setCod_micro(-1);
				f.setNum_micro(null);
			} else {
				//Fora de Area ñ marcado. Salvar as info das cod area
				f.setCod_area(VarConst.vcod_area_equipe);
				f.setCod_area_esus(VarConst.vcod_area_esus_equipe);
				f.setCod_micro(VarConst.vcod_micro);
				f.setNum_micro(VarConst.vnumero_microarea);				
			}
			//
			f.setCod_tipo_imovel(Integer.parseInt(Funcoes.desagrupa(sp_tipoImovel.getSelectedItem().toString(), "-")));			
			f.setPeso(Funcoes.stringToDouble(ed_AntroPeso.getText().toString()));
			f.setAltura(Funcoes.stringToDouble(ed_AntroAltura.getText().toString()));			
			
			//Utilizado para setar o comicilio como VISITADO na table de domicilios
			f.setCod_domicilio(xCod_Domicilio);
			
			//MOTIVOS
			for (int i = 1; i <= QTD_MTV; i++){
				CheckBox check = (CheckBox) ll_gbMotivoVisita.findViewWithTag("cbMV" + Funcoes.zeroEsquerda(i, 2));
				if (check.isChecked()){
					f.setMotivos(i);
				}
			}
			
			if (f.salvar(f) == -1){
				//erro ao salvar
				Mensagens.msgSimples(this, "Atenção...", "Erro ao salvar dados no Banco!");
				return false;
			}
			
			finish();
			Intent intent = new Intent(getBaseContext(), ListaDomicilios.class);
			startActivity(intent);
			return true;
			
		} catch (Exception ex){
			ex.printStackTrace();
			Log.e("ErroSQL", "Erro ao Salvar Ficha (Classe " + this.getClass() + "). Message: "
					+ ex.getMessage());
			Mensagens.msgSimples(this, "Erro...", "Erro ao Salvar Ficha  (" + this.getClass() + ")");
			return false;
		}	
	}
	
	public boolean validarCampoSalvar(){
		//PROFISSIONAL
		//CNS
		if(tv_cnsProf.getText().equals("")){
			Mensagens.msgSimples(this, "Atenção...", "Número do CNS é obrigatório!");
			scrollMaster.scrollTo(0, ll_infoProf.getTop() + tv_cnsProf.getTop());
			tv_cnsProf.requestFocus();
			return false;
		}
		
		//CPF
		if(tv_CPFProf.getText().equals("")){
			Mensagens.msgSimples(this, "Atenção...", "CPF é obrigatório!");
			scrollMaster.scrollTo(0, ll_infoProf.getTop() + tv_CPFProf.getTop());
			tv_CPFProf.requestFocus();
			return false;
		}
		
		//CBO
		if(tv_cboProf.getText().equals("")){
			Mensagens.msgSimples(this, "Atenção...", "CBO é obrigatório!");
			scrollMaster.scrollTo(0, ll_infoProf.getTop() + tv_cboProf.getTop());
			tv_cboProf.requestFocus();
			return false;
		}
		
		//CNES Unidade
		if(tv_cnes_unidadeProf.getText().equals("")){
			Mensagens.msgSimples(this, "Atenção...", "Cód. CNES Unidade é obrigatório!");
			scrollMaster.scrollTo(0, ll_infoProf.getTop() + tv_cnes_unidadeProf.getTop());
			tv_cnes_unidadeProf.requestFocus();
			return false;
		}
		
		//Data Visita
		if(tv_dataVisita.getText().equals("")){
			Mensagens.msgSimples(this, "Atenção...", "Data da Visita é obrigatória!");
			scrollMaster.scrollTo(0, ll_infoProf.getTop() + tv_dataVisita.getTop());
			tv_dataVisita.requestFocus();
			return false;
		}
		
		//Hora Visita
		if(tv_horaVisita.getText().equals("")){
			Mensagens.msgSimples(this, "Atenção...", "Hora da Visita é obrigatória!");
			scrollMaster.scrollTo(0, ll_infoProf.getTop() + tv_horaVisita.getTop());			
			tv_horaVisita.requestFocus();
			return false;
		}
		
		//Tipo do Imóvel
		if (sp_tipoImovel.getSelectedItemPosition() == 0){
			Mensagens.msgSimples(this, "Atenção...", "Selecione o Tipo de Imóvel!");
			scrollMaster.scrollTo(0, ll_TipoImovel.getTop()); //rolando scroll para a posição do item
			sp_tipoImovel.requestFocus();
			return false;
		}
			
		//Turno
		if (Funcoes.GetIndexRadioGroup(gbTurno) == -1) {
			Mensagens.msgSimples(this, "Atenção...", "Selecione o turno da visita!");
			scrollMaster.scrollTo(0, ll_infoVisita.getTop() + gbTurno.getTop());
			gbTurno.requestFocus();
			return false;
		}
		
		//Peso
		if (!ed_AntroPeso.getText().equals("")){
			try {
				if (Funcoes.stringToDouble(ed_AntroPeso.getText().toString()) > 0){
					if (Funcoes.stringToDouble(ed_AntroPeso.getText().toString()) < 0.5){
						Mensagens.msgSimples(this, "Atenção...", "Peso do paciente não pode ser menor que 0.5kg!");
						scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_Antropometria.getTop());
						ed_AntroPeso.requestFocus();
						return false;			
					}
					
					if (Funcoes.stringToDouble(ed_AntroPeso.getText().toString()) > 500){
						Mensagens.msgSimples(this, "Atenção...", "Peso do paciente não pode ser maior que 500kg!");
						scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_Antropometria.getTop());
						ed_AntroPeso.requestFocus();
						return false;			
					}			
				}		
			} catch (Exception e) {
				Mensagens.msgSimples(this, "Atenção...", "Peso informado é inválido!");
				scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_Antropometria.getTop());
				ed_AntroPeso.setText("");
				ed_AntroPeso.requestFocus();
				return false;				
			}			
		}
		
		//Altura
		if (!ed_AntroAltura.getText().equals("")){
			try{
				if (Funcoes.stringToDouble(ed_AntroAltura.getText().toString()) > 0){
					if (Funcoes.stringToDouble(ed_AntroAltura.getText().toString()) < 20){
						Mensagens.msgSimples(this, "Atenção...", "Altura do paciente não pode ser menor que 20cm!");
						scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_Antropometria.getTop());
						ed_AntroAltura.requestFocus();
						return false;			
					}
					
					if (Funcoes.stringToDouble(ed_AntroAltura.getText().toString()) > 250){
						Mensagens.msgSimples(this, "Atenção...", "Altura do paciente não pode ser maior que 250cm!");
						scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_Antropometria.getTop());
						ed_AntroAltura.requestFocus();
						return false;			
					}			
				}
			} catch (Exception e) {
				Mensagens.msgSimples(this, "Atenção...", "Altura informada é inválida!");
				scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_Antropometria.getTop());
				ed_AntroAltura.setText("");
				ed_AntroAltura.requestFocus();
				return false;				
			}
		}		
				
		//DESFECHO
		auxI = Funcoes.GetIndexRadioGroupEsus(gbDesfecho);
		if (auxI == -1){ //Não selecionou
			Mensagens.msgSimples(this, "Atenção...", "Desfecho é obrigatório!");
			scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_desfecho.getTop());
			gbDesfecho.requestFocus();
			return false;			
		}
//		else if (auxI == 3){ //Ausente
//			//Seleciona o Primeiro Pac da lista
//			SP_NomePac.setSelection(1);
//			xCod_Cidadao = cidadosDoDominicio.get(0).getCod_cidadao(); 
//			TV_NumProntuario.setText(String.valueOf(cidadosDoDominicio.get(0).getCod_pro()));
//			
//			TV_CNSPac.setText(cidadosDoDominicio.get(0).getCns());
//			TV_DtNascPac.setText(Funcoes.ajustaDataDisplay(cidadosDoDominicio.get(0).getDtnasc()));
//			TV_RGPac.setText(cidadosDoDominicio.get(0).getRg());
//			TV_CPFPac.setText(cidadosDoDominicio.get(0).getCpf());
//		
//			if (cidadosDoDominicio.get(0).getSexo().equals("F")){
//				ed_SexoPac.setText("Feminino");
//			} else {
//				ed_SexoPac.setText("Masculino");
//			}			
//		}
//		else if (auxI != 3){ //Visita Realizada ou Recusada (1 ou 2)
		else { //Visita Realizada ou Recusada ou Ausente (1 ou 2 ou 3)
			//PACIENTE
			boolean infoPac;
			int xTipoImovel = sp_tipoImovel.getSelectedItemPosition();
			if (xTipoImovel == 1 || xTipoImovel == 7 || xTipoImovel == 8 || xTipoImovel == 9 || xTipoImovel == 10
					|| xTipoImovel == 11 || xTipoImovel == 13){
				infoPac = true;
			} else {
				infoPac = false;
			}
			
			if (infoPac && SP_NomePac.getSelectedItemPosition() == 0){				
				Mensagens.msgSimples(this, "Atenção...", "Paciente é obrigatório!");
				scrollMaster.scrollTo(0, ll_infoPac.getTop());
				SP_NomePac.requestFocus();
				return false;			
			}
			
			//Verificando se Paciente já ñ respondeu a ficha
			if (infoPac && cidadosDoDominicio.get(SP_NomePac.getSelectedItemPosition()-1).getRespondeu_fvd() == 1){
				Mensagens.msgSimples(FichaVisitaDomiciliarC.this, "Atenção...", "Já há uma Ficha lançada para esse paciente."
						+ "\nSelecione outro paciente!");				
				scrollMaster.scrollTo(0, ll_infoPac.getTop());
				SP_NomePac.setSelection(0);
				SP_NomePac.requestFocus();
				return false;							
			} 			
			
			//Data Nasc
			if (TV_DtNascPac.getText().equals("")){
				Mensagens.msgSimples(this, "Atenção...", "Data de Nascimento é obrigatória!");
				scrollMaster.scrollTo(0, ll_infoPac.getTop() + TV_DtNascPac.getTop());
				TV_DtNascPac.requestFocus();
				return false;
			}
			
			//Sexo
			if (ed_SexoPac.equals("")){
				Mensagens.msgSimples(this, "Atenção...", "Sexo do Paciente é obrigatório!");
				scrollMaster.scrollTo(0, ll_infoPac.getTop() + ed_SexoPac.getTop());
				ed_SexoPac.requestFocus();
				return false;
			}
			
			if (auxI == 1){
				int selecionado = 0;
				//Está em "Visita Realizada". Logo, "Motivo Visita" deve ser preenchido

				//Percorrendo todos os Motivos Visita
				for (int i = 1; i <= QTD_MTV; i++){
					CheckBox check = (CheckBox) ll_gbMotivoVisita.findViewWithTag("cbMV" + Funcoes.zeroEsquerda(i, 2));
					if(check.isChecked()){
						selecionado = 1;
						break;
					}
				}
				if (selecionado == 0){
					Mensagens.msgSimples(this, "Atenção...", "Motivo da Visita é obrigatório!");
					scrollMaster.scrollTo(0, ll_infoVisita.getTop() + ll_gbMotivoVisita.getTop());
					return false;			
				}
			}
		}		
		return true;
	}

	//Desabilita e/ou limpa a Antropometria
	private void EnabledClearAntropometria(boolean pEnabled, boolean pClear){
		TextView tv_AntroPeso   = (TextView)findViewById(R.id.tv_AntroPeso);
		TextView tv_AntroAltura = (TextView)findViewById(R.id.tv_AntroAltura);
		EditText ed_AntroPeso   = (EditText)findViewById(R.id.ed_AntroPeso);
		EditText ed_AntroAltura = (EditText)findViewById(R.id.ed_AntroAltura);
		ll_Antropometria.setEnabled(pEnabled);
		tv_AntroPeso.setEnabled(pEnabled);
		tv_AntroAltura.setEnabled(pEnabled);
		if(pClear){
			ed_AntroPeso.setText("");
			ed_AntroAltura.setText("");			
		}
		ed_AntroPeso.setEnabled(pEnabled);
		ed_AntroAltura.setEnabled(pEnabled);		
		
	}
	
	private void SetEnabledArea(boolean pEnabled, boolean pClear, boolean pEnabledForaArea){
		//TextViews...
//		((TextView)findViewById(R.id.tv_CodArea)).setEnabled(pEnabled);
//		((TextView)findViewById(R.id.tv_DescriArea)).setEnabled(pEnabled);
//		((TextView)findViewById(R.id.tv_CodMicroArea)).setEnabled(pEnabled);
//		((TextView)findViewById(R.id.tv_DescriMicroArea)).setEnabled(pEnabled);		
//		ed_CodArea.setEnabled(pEnabled);
//		ed_DescriArea.setEnabled(pEnabled);
//		ed_NumMicroArea.setEnabled(pEnabled);
//		ed_DescriMicroArea.setEnabled(pEnabled);
		if (pClear){
			ed_CodArea.setText("");
			ed_DescriArea.setText("");
			ed_NumMicroArea.setText("");
			ed_DescriMicroArea.setText("");			
		} else {
			ed_CodArea.setText(VarConst.vcod_area_esus_equipe);
			ed_DescriArea.setText(VarConst.vnome_area_equipe);
			ed_NumMicroArea.setText(VarConst.vnumero_microarea);
			ed_DescriMicroArea.setText(VarConst.vnome_microarea);
		}
		cb_ForaArea.setEnabled(pEnabledForaArea);
	}	

	public void foraArea(View V){
		CheckBox cb = (CheckBox)V;
		SetEnabledArea(!cb.isChecked(), cb.isChecked(), true); //habilita area/microarea		
		if (cb.isChecked()){
			ed_CodArea.setText("FA");
		}
		cb_VisitaComp.requestFocus();
	}
	
	public void ajustaItensByTipoImovel(int pPosition, int pIndexDesfecho, boolean pSetFocus){	
		//Se o index do Desfecho for != de "Visita Realizada", ñ faço nada
		if ((pIndexDesfecho == 0) || (pIndexDesfecho == -1)){
			//Selecione...
			if (pPosition == 0){
				SP_NomePac.setEnabled(true); 		//habilita prontuario
				cb_ForaArea.setChecked(false);
				SetEnabledArea(false, false, false); //desabilita area/microarea
				Funcoes.CheckedEnabledCheckBox_InLinearLayout(ll_gbMotivoVisita, false, false, 1, QTD_MTV); //travando motivo visita
				EnabledClearAntropometria(false, true); //travando antropometria
			}
			//01.Domicilio, 07.Abrigo, 08.Instituição de Longa Permanencia para Idosos, 09.Unidade Prisional, 
			//10. Unidade de Medida Socio Educativa, 11.Delegacia, 99.Outros(posição 13)
			else if (pPosition == 1 || pPosition == 7 || pPosition == 8 || pPosition == 9 || pPosition == 10 
					|| pPosition == 11  || pPosition == 13){
				SP_NomePac.setEnabled(true); //habilita prontuario
				cb_ForaArea.setChecked(false);				
				SetEnabledArea(false, false, false); //desabilita area/microarea				
				EnabledClearAntropometria(true, true); //destravando antropometria
				Funcoes.CheckedEnabledCheckBox_InLinearLayout(ll_gbMotivoVisita, false, true, 1, QTD_MTV); //habilitando motivo visita
			}
			//02.Comercio, 03.Terreno Baldio, 04.Ponto Estrategico, 05.Escola, 06.Creche, 12.Estabelecimento Religioso 
			else if (pPosition == 2 || pPosition == 3 || pPosition == 4 || pPosition == 5 || pPosition == 6 || pPosition == 12){
				SP_NomePac.setSelection(0);
				SP_NomePac.setEnabled(false); //desabilita prontuario
				cb_ForaArea.setChecked(false);				
				SetEnabledArea(true, false, true); //habilita area/microarea				
				Funcoes.CheckedEnabledCheckBox_InLinearLayout(ll_gbMotivoVisita, false, true, 1, QTD_MTV); //habilitando motivo visita
				Funcoes.CheckedEnabledCheckBox_InLinearLayout(ll_BuscaAtiva, false, false, 3, ll_BuscaAtiva.getChildCount()-1); //desabilitar Busca Ativa
				Funcoes.CheckedEnabledCheckBox_InLinearLayout(ll_Acompanhamento, false, false, 7, ll_Acompanhamento.getChildCount()-1); //desabilitar Acompanhamento
				EnabledClearAntropometria(false, true); //desabilitar Antropometria
				
				//Visita periódica
				CheckBox cbVPeriodica = (CheckBox)findViewById(R.id.CB_MV_VisitaPeriodica);
				cbVPeriodica.setEnabled(false);
				cbVPeriodica.setChecked(false);

				//Egresso de internação
				CheckBox cbVEgresso = (CheckBox)findViewById(R.id.CB_MV_Egresso);
				cbVEgresso.setEnabled(false);
				cbVEgresso.setChecked(false);
				
				cbVPeriodica = null;
				cbVEgresso = null;
			}
			if (pSetFocus){
				scrollMaster.scrollTo(0, ll_TipoImovel.getTop());
				SP_NomePac.requestFocus();	
			}
		}
	}
}
