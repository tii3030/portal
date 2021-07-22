package br.eletrosom.portalcolaborador.exporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.eletrosom.portalcolaborador.entity.SolicitacoesDiversas;
import br.eletrosom.portalcolaborador.model.EnvioProdutosModel;


/*
 * EXPORTA DOS DADOS DO ENVIO DE PRODUTOS PARA XLSX
 */
public class EnvioProdutosExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private EnvioProdutosModel envio;

	public EnvioProdutosExporter(EnvioProdutosModel envio) {
		this.envio = envio;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Envio Produtos");

		Row row = sheet.createRow(0);
		

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(18);
		style.setFont(font);
		
		createCell(row, 1, "Relatório de Envio de Produtos", style);
		
		row = sheet.createRow(1);
		createCell(row, 0, "", style);
		row = sheet.createRow(2);
		
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Produto", style);
		createCell(row, 1, "Filial", style);
		createCell(row, 2, "Data envio", style);
		createCell(row, 3, "Data solicitação", style);
		createCell(row, 4, "Funcionário", style);
		createCell(row, 5, "Quantidade", style);
		createCell(row, 6, "Data prevista", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		//if (sheet != null) {
		//	sheet.autoSizeColumn(columnCount);
		//}
		
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines(String tipo) {
		int rowCount = 3;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		// Calendar cal = Calendar.getInstance();

		for (SolicitacoesDiversas item : envio.getLista()) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			String envioFormatado = dateFormatter.format(envio.getDataEnvio());
			String solicitacaoFormatado = dateFormatter.format(item.getDataSolicitacao());
			String previsaoFormatado = item.getDataPrevisao() != null ? dateFormatter.format(item.getDataPrevisao())
					: "";

			createCell(row, columnCount++, tipo, style);
			createCell(row, columnCount++, item.getEmpresa().getCodigo(), style);
			createCell(row, columnCount++, envioFormatado, style);
			createCell(row, columnCount++, solicitacaoFormatado, style);
			createCell(row, columnCount++, item.getUsuario().getNome(), style);
			createCell(row, columnCount++, item.getQuantidade(), style);
			createCell(row, columnCount++, previsaoFormatado, style);

		}
	}

	public void export(HttpServletResponse response, String tipo) throws IOException, ServletException {
		writeHeaderLine();
		writeDataLines(tipo);

		ServletOutputStream outputStream = response.getOutputStream();
		try {
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
