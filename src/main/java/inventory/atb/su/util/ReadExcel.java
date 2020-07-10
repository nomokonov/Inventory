package inventory.atb.su.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.repository.FromExcelDataRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadExcel {
//    @Autowired
//    private static FromExcelDataRepository fromExcelDataRepository;

    public static List<FromExcelData> ReadFromFile(String fileName) throws IOException {
        List<FromExcelData> listfromExcelData = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            System.out.print(cell.getColumnIndex() + "\t");
            System.out.print(cell.getStringCellValue());
            System.out.println("");
        }

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            // Get iterator to all cells of current row
            cellIterator = row.cellIterator();
            FromExcelData fromExcelData = new FromExcelData(
                    row.getCell(1).getStringCellValue(), // 1	Наименование
                    row.getCell(2).getStringCellValue(), // 2	Инв. №
                    row.getCell(3).getNumericCellValue(), // 3	Кол -во
                    row.getCell(4).getStringCellValue(),// 4	Ед. из мер
                    row.getCell(5).getNumericCellValue(), // 5	Цена
                    row.getCell(11).getStringCellValue(), // 11	Тип ТМЦ
                    row.getCell(12).getStringCellValue(), // 12	Вид испо льзов
                    row.getCell(13).getStringCellValue(), // 13	Код группы
                    row.getCell(14).getStringCellValue(), // 14	Наимен группы
                    row.getCell(15).getStringCellValue(), // 15	МОЛ
                    row.getCell(16).getStringCellValue(), // 16	Код подр.
                    row.getCell(17).getStringCellValue(), // 17	Подраз деление
                    row.getCell(18).getStringCellValue(), // 18	Место нахож дение
                    row.getCell(22).getStringCellValue(), // 22	Вне сист учет
                    row.getCell(23).getDateCellValue(), // 23	Дата соз дания
                    row.getCell(24).getDateCellValue(), // 24	Дата опри ходов
                    row.getCell(25).getDateCellValue(), // 25	Дата ввода в экспл
                    row.getCell(26).getDateCellValue(), //26	Дата списания
                    row.getCell(47).getStringCellValue() //47	Старый инв. №
            );
            listfromExcelData.add(fromExcelData);
//            fromExcelDataRepository.save(fromExcelData);
        }
        return listfromExcelData;
    }
}

