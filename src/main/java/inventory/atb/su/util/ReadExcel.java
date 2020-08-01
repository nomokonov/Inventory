package inventory.atb.su.util;

import inventory.atb.su.models.FromExcelData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {

    public static List<FromExcelData> ReadFromFile(String fileName) throws IOException {
        List<FromExcelData> listfromExcelData = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        inputStream.close();
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            FromExcelData fromExcelData = new FromExcelData(
                    row.getCell(1).getStringCellValue(), // 1	Наименование
                    row.getCell(2).getStringCellValue(), // 2	Инв. №
                    row.getCell(3).getNumericCellValue(), // 3	Кол -во
                    row.getCell(4).getStringCellValue(),// 4	Ед. из мер
                    row.getCell(5).getNumericCellValue(), // 5	Цена
                    row.getCell(11).getStringCellValue(), // 11	Тип ТМЦ
                    row.getCell(13).getStringCellValue(), // 13	Вид испо льзов
                    row.getCell(14).getStringCellValue(), // 14	Код группы
                    row.getCell(15).getStringCellValue(), // 15	Наимен группы
                    row.getCell(16).getStringCellValue(), // 16	МОЛ
                    row.getCell(17).getStringCellValue(), //  17  МОЛ-Подраздленеие
                    row.getCell(18).getStringCellValue(), // 18	Код подр.
                    row.getCell(19).getStringCellValue(), // 19	Подраз деление
                    row.getCell(23).getStringCellValue(), // 23	Вне сист учет
                    row.getCell(24).getDateCellValue(), // 24	Дата соз дания
                    row.getCell(25).getDateCellValue(), // 25	Дата опри ходов
                    row.getCell(26).getDateCellValue(), // 26	Дата ввода в экспл
                    row.getCell(27).getDateCellValue() //27	Дата списания
            );
            listfromExcelData.add(fromExcelData);
        }
        workbook.close();


        return listfromExcelData;
    }
}

