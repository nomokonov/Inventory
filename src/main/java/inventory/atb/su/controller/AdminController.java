package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.repository.FromExcelDataRepository;
import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.util.MyUploadForm;
import inventory.atb.su.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/*")
public class  AdminController {
    private FromExcelDataService fromExcelDataService;

    @Autowired
    public AdminController(FromExcelDataService fromExcelDataService) {
        this.fromExcelDataService = fromExcelDataService;
    }

    @GetMapping("/")
    public String welcome(
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
        MyUploadForm.doUpload(request.getServletContext().getRealPath("upload"), myUploadForm.getFileDatas(), uploadedFiles, failedFiles);
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
