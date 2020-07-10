package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.repository.FromExcelDataRepository;
import inventory.atb.su.util.MyUploadForm;
import inventory.atb.su.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
public class AdminController {
    private FromExcelDataRepository fromExcelDataRepository;

    @Autowired
    public AdminController(FromExcelDataRepository fromExcelDataRepository) {
        this.fromExcelDataRepository = fromExcelDataRepository;
    }

    @GetMapping("/")
    public String welcome(
            Principal user,
            Map<String, Object> model) {

        model.put("user", user);
        MyUploadForm myUploadForm = new MyUploadForm();
        model.put("myUploadForm", myUploadForm);
        return "admin.html";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadOneFileHandlerPOST(HttpServletRequest request, //
                                           Model model, //
                                           @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) throws IOException {

        ArrayList<File> uploadedFiles = new ArrayList<File>();
        ArrayList<String> failedFiles = new ArrayList<String>();
        MyUploadForm.doUpload(request.getServletContext().getRealPath("upload"), myUploadForm.getFileDatas(), uploadedFiles, failedFiles);
        model.addAttribute("uploadedFiles", uploadedFiles);
        if (myUploadForm.getClear_table()) {
            fromExcelDataRepository.deleteAll();
        }
        List<FromExcelData> fromExcelDataList = ReadExcel.ReadFromFile(uploadedFiles.get(0).getAbsolutePath());
        fromExcelDataRepository.saveAll(fromExcelDataList);
        model.addAttribute("failedFiles", failedFiles);
        return "uploadResult";
    }

}
