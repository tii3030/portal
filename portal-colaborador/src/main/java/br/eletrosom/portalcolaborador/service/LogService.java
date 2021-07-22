package br.eletrosom.portalcolaborador.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eletrosom.portalcolaborador.entity.Log;
import br.eletrosom.portalcolaborador.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository repository;
	
	
	//REGISTRA LOG
		public void registrarLog(String tipo, String usuario) {

			 Long maxid = repository.findMaxId() == null ? 0 :
				 repository.findMaxId();
			 
			 Calendar calendar = Calendar.getInstance();//cria o obj calendar e atribui a hora e data do sistema
			 Date data = calendar.getTime();//transforma o obj calendar em obj Date

			 SimpleDateFormat sdhora = new SimpleDateFormat("HH:mm:ss");//cria um obj de formatação de hora
			 String hora = sdhora.format(data);//gera a string final formatada no estilo "HH:mm:ss"
			 
			 Log log = new Log(maxid+1,tipo,usuario,data,hora);	
			 
			 repository.save(log);
			 
			 System.out.println("Log - tipo: " + tipo + " usuario: " + usuario + " data: " + log.getData());

		}

}
