package inventory.atb.su.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class WriteExcel {
    @Value("${config.upload-path}")
    public  String UPLOAD_PATH;

    public  void  writePicturePOI( BufferedImage bImage, int col, int row) {

        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("barcodes");

            ByteArrayOutputStream byteArrayImg = new ByteArrayOutputStream();
            ImageIO.write(bImage, "PNG", byteArrayImg);

            int pictureIdx =sheet.getWorkbook().addPicture(byteArrayImg.toByteArray(),
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
            System.out.println("File xlsx get ready - " + UPLOAD_PATH + File.separator + "barcodes.xlsx" );


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
