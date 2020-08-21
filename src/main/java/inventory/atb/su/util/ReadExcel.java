package inventory.atb.su.util;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.security.WebSecurityConfig;
import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {

    static Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    public static List<FromExcelData> ReadFromFile(String fileName) throws IOException, OpenXML4JException, SAXException {

        List<FromExcelData> listfromExcelData = new ArrayList<>();

        OPCPackage xlsxPackage = OPCPackage.open(fileName, PackageAccess.READ);
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        if (iter.hasNext()) {
            try (InputStream stream = iter.next()) {
                processSheet(styles, strings, new MappingFromXml(listfromExcelData), stream);
            }
        }


        return  listfromExcelData;
//        return getFromExcelDataOld(fileName, listfromExcelData);
    }

    private static void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, MappingFromXml mappingFromXml, InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(
                    styles, null, strings, mappingFromXml, formatter, false);

            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch(ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    private static List<FromExcelData> getFromExcelDataOld(String fileName, List<FromExcelData> listfromExcelData) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);


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
        inputStream.close();

        logger.info("upload lines = " + listfromExcelData.size());
        return listfromExcelData;
    }
}

