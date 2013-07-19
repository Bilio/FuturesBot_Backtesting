package excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class GenExcelMonth {
	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("�п�J�~���GEX:Version\n");
		String YYMMDD = null;
		try {
			YYMMDD = input.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = "201204";
		GenExcelMonth.writeExcel(name);
	}
	public static void writeExcel(String version){
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;

		try {
			fs = new POIFSFileSystem(new FileInputStream("Regression.xls"));
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(1);
		HSSFCell cell = null;

		File folder = new File("D:\\Runtime\\" + version);
		File[] listOfFiles = folder.listFiles();
		BufferedReader in;
		cell = row.createCell(0);
		cell.setCellValue(version);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				try {
					in = new BufferedReader(new FileReader("D:\\Runtime\\"
							+ version + "\\" + listOfFiles[i].getName()));
					int count = 0;
					String s1;
					int currentcount = 0;
					while ((s1 = in.readLine()) != null) {
						String[] tmp = s1.split(":");
						if (tmp[0].equals("current")) { //current:35, price:80.0
							String[] tmp1 = tmp[1].split(",");
							if (!tmp1[0].equals("0")){
								currentcount = Math.abs(Integer.parseInt(tmp1[0]));
							} else
								count = count + (currentcount * 2);
						} else if (tmp[0].equals("Win")) {
							row = sheet.createRow(i + 2);
							cell = row.createCell(0);
							cell.setCellValue(new Double(
									listOfFiles[i].getName().split("_")[0]));
							cell = row.createCell(1);
							cell.setCellValue(new Double(tmp[1]));
						} else if (tmp[0].equals("Lost")) {
							cell = row.createCell(2);
							cell.setCellValue(new Double(tmp[1]));
						} else if (tmp[0].equals("Total")) {
							cell = row.createCell(3);
							cell.setCellValue(new Double(tmp[1]));
							cell = row.createCell(4);
							cell.setCellValue(new Double(count));
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		try {
			wb.write(new FileOutputStream("D:\\Runtime\\" + version + ".xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeExcelTotal(String version){
		for (int size = 200; size <= 1600; size = size * 2) {
			POIFSFileSystem fs = null;
			HSSFWorkbook wb = null;

			try {
				fs = new POIFSFileSystem(new FileInputStream("total.xls"));
				wb = new HSSFWorkbook(fs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(size);
			int j = 1;
			for(int m = 5; m<=30;m++){
				row = sheet.getRow(j);
				int i = 1;
				for(int n = 5; n<=30;n++){
					String name = version + size + "IN" + m + "OUT" + n;
					double total = readExcel(name);
					cell = row.createCell(i);
					cell.setCellValue(total);
					i++;
				}
				j++;
			}
			try {
				wb.write(new FileOutputStream("D:\\Runtime\\total" + size + ".xls"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void writeExcelTotalA(String version, int size1, int size2, int m1, int m2, int n1, int n2){
		for (int size = size1; size <= size2; size = size * 2) {
			POIFSFileSystem fs = null;
			HSSFWorkbook wb = null;

			try {
				fs = new POIFSFileSystem(new FileInputStream("total.xls"));
				wb = new HSSFWorkbook(fs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(size);
			int j = 1;
			for(int m = m1; m<=m2;m++){
				row = sheet.getRow(j);
				int i = 1;
				for(int n = n1; n<=n2;n++){
					String name = version + size + "IN" + m + "OUT" + n;
					double total = readExcel(name);
					cell = row.createCell(i);
					cell.setCellValue(total);
					i++;
				}
				j++;
			}
			try {
				wb.write(new FileOutputStream("D:\\Runtime\\total" + version + size + ".xls"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void writeExcelTotalB(String version, int size1, int size2, int x1, int x2, int m1, int m2){
		for (int size = size1; size <= size2; size = size * 2) {
			POIFSFileSystem fs = null;
			HSSFWorkbook wb = null;

			try {
				fs = new POIFSFileSystem(new FileInputStream("totalSval.xls"));
				wb = new HSSFWorkbook(fs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(size);
			int j = 1;
			for(int x = x1; x<=x2;x++){
				row = sheet.getRow(j);
				int i = 1;
				for(int m = m1; m<=m2;m++){
					String name = version + size + "IN" + m + "VAL" + x;
					double total = readExcel(name);
					cell = row.createCell(i);
					cell.setCellValue(total);
					i++;
				}
				j++;
			}
			try {
				wb.write(new FileOutputStream("D:\\Runtime\\total" + version + size + ".xls"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static double readExcel(String filename) {
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;

		try {
			fs = new POIFSFileSystem(new FileInputStream("D:\\Runtime\\" + filename + ".xls"));
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		int i;
		int lastrow = sheet.getLastRowNum();
		double total = 0;
		for (i = 2; i <= lastrow; i++) {
			row = sheet.getRow(i);
			// cellNum = row.getLastCellNum();
			cell = row.getCell(3);
			
			total = total + cell.getNumericCellValue();
		}
		return total;
	}
	
	private static double readExcel1(String filename) {
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;

		try {
			fs = new POIFSFileSystem(new FileInputStream("D:\\Runtime\\" + filename + ".xls"));
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		//int i;
		double total = 0;
		row = sheet.getRow(2);
			// cellNum = row.getLastCellNum();
		cell = row.getCell(3);
		total = cell.getNumericCellValue();
		return total;
	}
}
