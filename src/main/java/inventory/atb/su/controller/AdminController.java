package inventory.atb.su.controller;

import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import inventory.atb.su.util.MyUploadForm;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin/*")
public class  AdminController {
    @Value("${config.upload-path}")
    public  String UPLOAD_PATH;
    private FromExcelDataService fromExcelDataService;
    private LDAPService ldapService;

    @Autowired
    public AdminController(FromExcelDataService fromExcelDataService, LDAPService ldapService) {
        this.fromExcelDataService = fromExcelDataService;
        this.ldapService = ldapService;
    }

    @GetMapping("/")
    public String uploadDataForm(
            Principal user,
            Model model) {

        model.addAttribute("user", user);
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);
        return "admin";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadOneFileHandlerPOST(HttpServletRequest request,
                                           Principal user,
                                           Model model,
                                           @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) throws IOException, OpenXML4JException, SAXException {

        ArrayList<File> uploadedFiles = new ArrayList<File>();
        ArrayList<String> failedFiles = new ArrayList<String>();
        MyUploadForm.doUpload(UPLOAD_PATH, myUploadForm.getFileDatas(), uploadedFiles, failedFiles);
        model.addAttribute("uploadedFiles", uploadedFiles);
        model.addAttribute("user", user);
        if (myUploadForm.getClear_table()) {
            fromExcelDataService.deleteAll();
        }
        int lines = fromExcelDataService.getFromExcelData(uploadedFiles);
        model.addAttribute("obj_count", lines);
        model.addAttribute("failedFiles", failedFiles);
        return "uploadResult";
    }

}
