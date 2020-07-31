package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import inventory.atb.su.util.MyUploadForm;
import inventory.atb.su.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                                           @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) throws IOException {

        ArrayList<File> uploadedFiles = new ArrayList<File>();
        ArrayList<String> failedFiles = new ArrayList<String>();
        MyUploadForm.doUpload(UPLOAD_PATH, myUploadForm.getFileDatas(), uploadedFiles, failedFiles);
        model.addAttribute("uploadedFiles", uploadedFiles);
        model.addAttribute("user", user);
        if (myUploadForm.getClear_table()) {
            fromExcelDataService.deleteAll();
        }
        List<FromExcelData> fromExcelDataList = ReadExcel.ReadFromFile(uploadedFiles.get(0).getAbsolutePath());
        fromExcelDataService.saveAll(fromExcelDataList);
        model.addAttribute("obj_count", fromExcelDataList.size());
        model.addAttribute("failedFiles", failedFiles);
        return "uploadResult";
    }

}
