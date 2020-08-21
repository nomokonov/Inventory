package inventory.atb.su.util;

import inventory.atb.su.models.FromExcelData;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MappingFromXml  implements XSSFBSheetHandler.SheetContentsHandler {
    private List<FromExcelData> result = new ArrayList<>();
    private FromExcelData fromExcelData = null;
    private int lineNumber = 0;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yy");

    private int minColumns = 25;

    private PrintStream output = System.out;

    public MappingFromXml(List<FromExcelData> list) {
        this.result = list;
    }

    @Override
    public void startRow(int i) {
        lineNumber = i;
        fromExcelData = new FromExcelData();
    }

    @Override
    public void endRow(int i) {
        result.add(fromExcelData);
        fromExcelData = null;
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        int columnIndex = (new CellReference(cellReference)).getCol();

        if(lineNumber > 0){
            switch (columnIndex) {
                case 1: { // 1	Наименование
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setName(formattedValue);
                    break;
                }
                case 2: {// 2	Инв. №
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setInvNumber(formattedValue);
                    break;
                }
                case 3: {// 3	Кол -во
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setQuantity(Double.parseDouble(formattedValue.replace(",",".")));
                    break;
                }
                case 4: {// 4	Ед. из мер
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setUnit(formattedValue);
                    break;
                }
                case 5: {// 5	Цена
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setPrice(Double.parseDouble(formattedValue.replace(',','.')));
                    break;
                }
                case 11: {// 11	Тип ТМЦ
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setTypeTMC(formattedValue);
                    break;
                }
                case 13: {/// 13	Вид испо льзов
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setTypeUtilize(formattedValue);
                    break;
                }
                case 14: {// 14	Код группы
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setCodeGroup(formattedValue);
                    break;
                }
                case 15: {// 15	Наимен группы
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setNameGroup(formattedValue);
                    break;
                }
                case 16: {// 16	МОЛ
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setMol(formattedValue);
                    break;
                }
                case 17: {//  17  МОЛ-Подраздленеие
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setMolCodeDepartment(formattedValue);
                    break;
                }
                case 18: {// 18	Код подр.
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setCodeDepartment(formattedValue);
                    break;
                }
                case 19: {// 19	Подраз деление
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setNameDepartment(formattedValue);
                    break;
                }
                case 23: {// 23	Вне сист учет
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setNonSystem(formattedValue);
                    break;
                }
                case 24: {// 24	Дата соз дания
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setDateCreation(LocalDate.parse(formattedValue, dtf));
                    break;
                }
                case 25: {/// 25	Дата опри ходов
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setDatePostings(LocalDate.parse(formattedValue, dtf));
                    break;
                }
                case 26: {// 26	Дата ввода в экспл
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setDateExploit(LocalDate.parse(formattedValue, dtf));
                    break;
                }
                case 27: {//27	Дата списания
                    if (formattedValue != null && !formattedValue.isEmpty())
                        fromExcelData.setDateClosin(LocalDate.parse(formattedValue, dtf));
                    break;
                }
            }
        }
    }

    @Override
    public void headerFooter(String s, boolean b, String s1) {

    }

    @Override
    public void endSheet() {

    }

    @Override
    public void hyperlinkCell(String s, String s1, String s2, String s3, XSSFComment xssfComment) {

    }
}
