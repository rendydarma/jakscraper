package com.jakmall.jakscraper.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jakmall.jakscraper.dto.file.FileReqDto;
import com.jakmall.jakscraper.dto.file.FileResDto;
import com.jakmall.jakscraper.dto.product.ProductResDto;

@Service
public class ExcelService {

	@Autowired
	private ProductService productService;

	public List<FileResDto> insertTemplate(List<FileReqDto> files) {
		final List<FileResDto> responses = new ArrayList<>();

		for (FileReqDto f : files) {
			try {
				byte[] fileBytes = Base64.getDecoder().decode(f.getFileContent());
				File file = new File(f.getFileName() + ".xlsx");
				FileOutputStream os = new FileOutputStream(file);
				os.write(fileBytes);

				XSSFWorkbook book = new XSSFWorkbook(file);
				XSSFSheet sheet = book.getSheetAt(0);
				for (int i = 3; i < sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					ProductResDto product = productService.getProduct(row.getCell(10).getStringCellValue());
					if (product.getSku() != null) {
						System.out.println(product.getSku());
						row.getCell(7).setCellValue(product.getPrice().toString());
						if (product.getInStock()) {
							row.getCell(11).setCellValue("Aktif");
							row.getCell(8).setCellValue("10");
						} else {
							row.getCell(11).setCellValue("Nonaktif");
						}
					}else {
						row.getCell(11).setCellValue("Nonaktif");						
					}
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
					row.createCell(14).setCellValue(LocalDateTime.now().format(formatter));

				}

				os.close();
				file.delete();
				
				File fileDownload = new File(f.getFileName() + ".xlsx");
				FileOutputStream osDownload = new FileOutputStream(fileDownload);
				book.write(osDownload);
				byte[] fileDownloadBytes = FileUtils.readFileToByteArray(fileDownload);
				osDownload.write(fileDownloadBytes);
				book.close();
				osDownload.close();
				fileDownload.delete();

				FileResDto response = new FileResDto();
				response.setFileName(f.getFileName());
				response.setFileContent(Base64.getEncoder().encodeToString(fileDownloadBytes));
				responses.add(response);

			} catch (IOException | InvalidFormatException e) {
				e.printStackTrace();
			}

		}
		return responses;
	}
}
