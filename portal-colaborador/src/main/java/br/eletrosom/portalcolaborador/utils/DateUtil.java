package br.eletrosom.portalcolaborador.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/*
 * METODOS PARA MAINUPULAÇÃO DE DATAS
 */
public class DateUtil {

	// CONVERTA DATA PARA O FORMATO dd-MM-yyyy, PARA APRESENTAÇÃO NA TELA
	public String converteDateView(Date data) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dataFormatada = dateFormat.format(data);

		return dataFormatada;
	}

	// CONVERTA DATA PARA O FORMATO yyyy-MM-dd PARA REGISTRO NO BANCO DE DADOS
	public String converteDateBanco(Date data) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(data);

		return dataFormatada;
	}
	
	// CONVERTA String PARA O FORMATO yyyy-MM-dd PARA REGISTRO NO BANCO DE DADOS
	public Date stringToDateBd(String data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date dataFormatada = formato.parse(data);

		return dataFormatada;
	}

	// CONVERTE DATA COMO STRING PARA OBJETO DATE
	public Date stringToDate(String data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date dataFormatada = formato.parse(data);

		return dataFormatada;
	}

	// SOMA DIAS EM UMA DATA
	public String somaData(String data, int dias) {
		String DataAlterada = "";
		String FormatoDaData = "dd/MM/yyyy";
		try {
			SimpleDateFormat format = new SimpleDateFormat(FormatoDaData);
			java.sql.Date Data = new java.sql.Date(format.parse(data).getTime());

			Calendar ob = Calendar.getInstance();
			ob.setTime(Data);
			ob.add(Calendar.DATE, +dias);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatoDaData);
			DataAlterada = simpleDateFormat.format(ob.getTime());
		} catch (Exception e) {
			return "Data Inválida";// caso passe a data fora do formato
		}
		return DataAlterada;

	}
	
	public Date somaDiasData(String data, int dias) throws ParseException {
		Date dataEntrega = stringToDateBd(data);
		//String FormatoDaData = "yyyy-MM-dd";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataEntrega);
			cal.add(Calendar.DATE, dias);
			dataEntrega = cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			// caso passe a data fora do formato
		}
		return dataEntrega;

	}

	// RETORNA OS ULTIMOS 12 MESES EM REMALÇAO A DATA ATUAL
	public List<String> buscaUltimosDozeMeses() {

		Calendar hoje = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		hoje.setTime(new Date());

		Integer mes = hoje.get(Calendar.MONTH);
		Integer ano = hoje.get(Calendar.YEAR);

		List<String> datas = new ArrayList<>();

		for (int i = 1; i <= 12; i++) {
			if (mes == 12) {
				datas.add("Dezembro/" + ano);
				mes = mes - 1;

			} else if (mes == 11) {
				datas.add("Novembro/" + ano);
				mes = mes - 1;
			} else if (mes == 10) {
				datas.add("Outubro/" + ano);
				mes = mes - 1;
			} else if (mes == 9) {
				datas.add("Setembro/" + ano);
				mes = mes - 1;
			} else if (mes == 8) {
				datas.add("Agosto/" + ano);
				mes = mes - 1;
			} else if (mes == 7) {
				datas.add("Julho/" + ano);
				mes = mes - 1;
			} else if (mes == 6) {
				datas.add("Junho/" + ano);
				mes = mes - 1;
			} else if (mes == 5) {
				datas.add("Maio/" + ano);
				mes = mes - 1;
			} else if (mes == 4) {
				datas.add("Abril/" + ano);
				mes = mes - 1;
			} else if (mes == 3) {
				datas.add("Março/" + ano);
				mes = mes - 1;
			} else if (mes == 2) {
				datas.add("Fevereiro/" + ano);
				mes = mes - 1;
			} else if (mes == 1) {
				datas.add("Janeiro/" + ano);
				mes = 12;
				ano = ano - 1;
			}

		}

		return datas;

	}

	// RETORNA UMA LISTA COM O PRIMEIRO E O ULTIMO DIA DO MES, APARTIR DE MES/ANO
	public List<String> buscaInicioEFimMes(String mesAno) {
		List<String> datas = new ArrayList<>();

		String x[] = mesAno.split("/");

		switch (x[0]) {
		case "Janeiro":
			datas.add("01/01/" + x[1]);
			datas.add("31/01/" + x[1]);
			break;
		case "Fevereiro":
			datas.add("01/02/" + x[1]);
			datas.add("28/02/" + x[1]);
			break;
		case "Março":
			datas.add("01/03/" + x[1]);
			datas.add("31/03/" + x[1]);
			break;
		case "Abril":
			datas.add("01/04/" + x[1]);
			datas.add("30/04/" + x[1]);
			break;
		case "Maio":
			datas.add("01/05/" + x[1]);
			datas.add("31/05/" + x[1]);
			break;
		case "Junho":
			datas.add("01/06/" + x[1]);
			datas.add("30/06/" + x[1]);
			break;
		case "Julho":
			datas.add("01/07/" + x[1]);
			datas.add("31/07/" + x[1]);
			break;
		case "Agosto":
			datas.add("01/08/" + x[1]);
			datas.add("31/08/" + x[1]);
			break;
		case "Setembro":
			datas.add("01/09/" + x[1]);
			datas.add("30/09/" + x[1]);
			break;
		case "Outubro":
			datas.add("01/10/" + x[1]);
			datas.add("31/10/" + x[1]);
			break;
		case "Novembro":
			datas.add("01/11/" + x[1]);
			datas.add("30/11/" + x[1]);
			break;
		case "Dezembro":
			datas.add("01/12/" + x[1]);
			datas.add("31/12/" + x[1]);
			break;
		}

		return datas;
	}
	
	// RETORNA OS ULTIMOS 3 ANOS EM RELAÇÃO A DATA ATUAL
		public List<String> buscaUltimosTresAnos() {
			
			Calendar hoje = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
			hoje.setTime(new Date());
			Integer ano = hoje.get(Calendar.YEAR);

			List<String> anos = new ArrayList<>();
			
			anos.add("" + (ano-1));
			anos.add("" + (ano-2));
			anos.add("" + (ano-3));
			
			return anos;
			
		}



}
