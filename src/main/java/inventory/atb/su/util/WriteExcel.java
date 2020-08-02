package inventory.atb.su.util;

import inventory.atb.su.models.InvMovings;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class WriteExcel {
    @Value("${config.upload-path}")
    public String UPLOAD_PATH;

    public void writePicturePOI(BufferedImage bImage, int col, int row) {

        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("barcodes");

            ByteArrayOutputStream byteArrayImg = new ByteArrayOutputStream();
            ImageIO.write(bImage, "PNG", byteArrayImg);

            int pictureIdx = sheet.getWorkbook().addPicture(byteArrayImg.toByteArray(),
                    sheet.getWorkbook().PICTURE_TYPE_PNG);
            byteArrayImg.close();
            //Returns an object that handles instantiating concrete classes
            CreationHelper helper = wb.getCreationHelper();

            //Creates the top-level drawing patriarch.
            Drawing drawing = sheet.createDrawingPatriarch();

            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner for the image
            anchor.setCol1(col);
            anchor.setRow1(row);

            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //Reset the image to the original size
            pict.resize();

            FileOutputStream fileOut = null;
            fileOut = new FileOutputStream(UPLOAD_PATH + File.separator + "barcodes.xlsx");
            wb.write(fileOut);
            fileOut.close();
            System.out.println("File xlsx get ready - " + UPLOAD_PATH + File.separator + "barcodes.xlsx");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getDocument(List<InvMovings> listForExcel, String oldDep, String newDep, String oldMol, String newMol) throws IOException {
        String oldDepShortName = oldDep.replace("(ВХО) Операционный офис ", "");
        String newDepShortName = newDep.replace("(ВХО) Операционный офис ", "");
        //load templates
        File file = ResourceUtils.getFile("classpath:templates/excel/template.xlsx");

        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet templateSheet = workbook.getSheetAt(0);
        //for result
        XSSFSheet resultSheet = workbook.createSheet("result");

        inputStream.close();
        String dateNow = new SimpleDateFormat("dd.MM.y").format(Calendar.getInstance().getTime());
//push data
        getCellByName(templateSheet, "N10").setCellValue("10");  //№ Документа
        getCellByName(templateSheet, "P10").setCellValue(dateNow); //Дата
        getCellByName(templateSheet, "A15").setCellValue(oldDepShortName); // А15  подр-е отправитель
        getCellByName(templateSheet, "F15").setCellValue(newDepShortName); // F15  подр-е получатель

        getCellByName(templateSheet, "G25").setCellValue(oldMol); // G25   Мол отпустил
        getCellByName(templateSheet, "G29").setCellValue(newMol); // F29  МОЛ получил
        getCellByName(templateSheet, "O22").setCellValue(listForExcel.size()); // O22  итого
        getCellByName(templateSheet, "O23").setCellValue(listForExcel.size()); // O23  Итого по накл


        for (int i = 1; i < 21; i++) {
            resultSheet.createRow(i).copyRowFrom(templateSheet.getRow(i - 1), new CellCopyPolicy());
        }


        int rowCount = 0;
        for (InvMovings item : listForExcel) {

            getCellByName(templateSheet, "A21").setCellValue(item.getFromExcelData().getName()); // A21   Нименование
            getCellByName(templateSheet, "F21").setCellValue(item.getFromExcelData().getInvNumber()); // F21  инв №
            if (rowCount < listForExcel.size()) {
                int indexRow = getCellByName(templateSheet, "A21").getRowIndex();
                resultSheet.createRow(21 + rowCount).copyRowFrom(templateSheet.getRow(indexRow), new CellCopyPolicy());
                rowCount++;
            }
        }

        for (int i = 21; i < 31; i++) {
            resultSheet.createRow(i + rowCount).copyRowFrom(templateSheet.getRow(i), new CellCopyPolicy());
        }
        for (int i = 0; i < 20; i++) {
            resultSheet.setColumnWidth(i, templateSheet.getColumnWidth(i));
        }
        workbook.removeSheetAt(0);

        String fileName =  oldDepShortName + "_to_" + newDepShortName + "__" + dateNow + "_.xlsx";

        FileOutputStream out = new FileOutputStream( UPLOAD_PATH + File.separator + fileName);
        System.out.println("File xlsx get ready - " + UPLOAD_PATH + File.separator + fileName);
        workbook.write(out);
        out.close();

        return fileName;
    }

    private static XSSFCell getCellByName(XSSFSheet sheet, String cellname) {
        CellReference ref = new CellReference(cellname);
        int row = ref.getRow();
        int col = ref.getCol();
        return sheet.getRow(row).getCell(col);

    }


}
