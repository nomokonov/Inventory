package inventory.atb.su.controller;

import inventory.atb.su.util.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Controller
public class DownloadController {
    private static final String DEFAULT_FILE_NAME = "template.xlsx";

    @Value("${config.upload-path}")
    public String UPLOAD_PATH;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, UPLOAD_PATH + File.separator + fileName);
        File file = new File(UPLOAD_PATH + File.separator + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

}
