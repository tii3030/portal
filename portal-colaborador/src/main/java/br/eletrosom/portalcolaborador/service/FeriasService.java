package br.eletrosom.portalcolaborador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eletrosom.portalcolaborador.entity.Ferias;
import br.eletrosom.portalcolaborador.model.ListaFerias;
import br.eletrosom.portalcolaborador.repository.FeriasRepository;

@Service
public class FeriasService {

	@Autowired
	private FeriasRepository repositorio;

	@Autowired
	private UsuarioService usuarioService;

	// RETORNA PROXIMO ID DE PRODUTO
	public Long buscaId() {
		Long maxid = repositorio.findMaxId() == null ? 0 : repositorio.findMaxId();
		return (maxid + 1);
	}

	// SALVA FERIAS
	public Ferias salvarFerias(Ferias ferias) {

		if (ferias.getId() == null) {
			ferias.setId(buscaId());
		}
		
		return repositorio.saveAndFlush(ferias);
	}

	// RETORNA LISTA DE FERIAS FILTRADO POR USUARIO
	public List<Ferias> listaFeriosPorUsuario(String codigo) {

		List<Ferias> lista = repositorio.findAllByUsuario(codigo);

		return lista;
	}
	
	public Ferias buscaFeriasUsuarioPendente(String codigo) {

		return repositorio.findAllByUsuarioPendente(codigo);
		
	}
	
	public Ferias buscaFeriasPorId(Long id) {

		return repositorio.findFeriasById(id);
		
	}

	public ListaFerias listarFeriasPorGestor(String codigo) {

		String codigoEmp = usuarioService.getUsuario(codigo).getFilial().getCodigo();

		List<Ferias> lista = repositorio.findAllByObsAndCodges(codigoEmp, codigo);

		//List<Ferias> lista = repositorio.findAll();
		
		ListaFerias listaFerias = new ListaFerias();
		listaFerias.setGestor(codigo);
		listaFerias.setLista(lista);

		return listaFerias;

	}

}
