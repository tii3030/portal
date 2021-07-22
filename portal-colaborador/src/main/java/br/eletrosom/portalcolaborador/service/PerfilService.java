package br.eletrosom.portalcolaborador.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eletrosom.portalcolaborador.entity.Perfil;
import br.eletrosom.portalcolaborador.model.PerfilModel;
import br.eletrosom.portalcolaborador.repository.PerfilRepository;

/*
 * SERVIÇOS DE PERFIL
 */
@Service
@Transactional
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	/** CONSULA OS PERFIS CADASTRADOS */
	@Transactional(readOnly = true)
	public List<PerfilModel> consultarPerfis() {

		List<PerfilModel> perfilModel = new ArrayList<PerfilModel>();

		/* CONSULTA TODOS OS GRUPOS */
		List<Perfil> perfilEntity = this.perfilRepository.findAll();

		perfilEntity.forEach(perfil -> {
			perfilModel.add(new PerfilModel(perfil.getId(), perfil.getNome()));
		});

		return perfilModel;
	}

}
