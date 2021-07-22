package br.eletrosom.portalcolaborador.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/*
 * UTIL PARA MANIPULAÇÃO DE ARQUIVOS
 */
public class Arquivo {

	public void upload(String pasta, String nomeDoArquivo, InputStream arquivoCarregado) throws IOException {
		String caminhoArquivo = pasta + "/" + nomeDoArquivo;
		File novoArquivo = new File(caminhoArquivo);
		FileOutputStream saida = new FileOutputStream(novoArquivo);
		copiar(arquivoCarregado, saida);
	}

	private void copiar(InputStream origem, OutputStream destino) {
		int bite = 0;
		byte[] tamanhoMaximo = new byte[1024 * 2056]; // 2056KB
		try {
			while ((bite = origem.read(tamanhoMaximo)) >= 0) {
				destino.write(tamanhoMaximo, 0, bite);
			}
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}

}
