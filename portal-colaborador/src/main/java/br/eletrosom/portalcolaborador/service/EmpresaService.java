package br.eletrosom.portalcolaborador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eletrosom.portalcolaborador.entity.Empresa;
import br.eletrosom.portalcolaborador.repository.EmpresaRepository;

/*
 * SERVIÇOS DE EMPRESA
 */
@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	//RETORNA LISTA COM TODAS EMPRESAS ATIVAS
	public List<Empresa> listarEmpresasAtivas(){
		
		return repository.findAllAtiva();
	}
	
	//RETORNA EMPRESA POR CODIGO
	public Empresa buscarPorCodigo(String codigo) {
		return repository.findByCodigo(codigo);
	}
}
