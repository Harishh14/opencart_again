package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class exu {
	

		public String path;
		public FileInputStream fis;
		public FileOutputStream fos;
		public Workbook workbook;
		public Sheet sheet;
		public Row row;
		public Cell cell;

		public exu(String path) {
			this.path = path;
		}

		// ================= READ DATA =================

		public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);

			DataFormatter formatter = new DataFormatter();
			String data = formatter.formatCellValue(cell);

			workbook.close();
			fis.close();

			return data;
		}

		public int getRowCount(String sheetName) throws IOException {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			workbook.close();
			fis.close();
			return rowCount;
		}
		public int getCellCount(String sheetName,int roww) throws IOException {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int colcount = sheet.getRow(roww).getLastCellNum();
			workbook.close();
			fis.close();
			return colcount;
		}

		// ================= WRITE DATA =================

		public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {

			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			row = sheet.getRow(rowNum);
			if (row == null)
				row = sheet.createRow(rowNum);

			cell = row.createCell(colNum);
			cell.setCellValue(data);

			fos = new FileOutputStream(path);
			workbook.write(fos);

			workbook.close();
			fis.close();
			fos.close();
		}

		// ================= PASS / FAIL WITH COLOR =================

		public void setResultWithColor(String sheetName, int rowNum, int colNum, String result) throws IOException {

			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			row = sheet.getRow(rowNum);
			if (row == null)
				row = sheet.createRow(rowNum);

			cell = row.createCell(colNum);
			cell.setCellValue(result);

			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);

			if (result.equalsIgnoreCase("PASS")) {
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				font.setColor(IndexedColors.WHITE.getIndex());
			} else if (result.equalsIgnoreCase("FAIL")) {
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				font.setColor(IndexedColors.WHITE.getIndex());
			}

			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFont(font);
			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);

			workbook.close();
			fis.close();
			fos.close();
		}
	}

