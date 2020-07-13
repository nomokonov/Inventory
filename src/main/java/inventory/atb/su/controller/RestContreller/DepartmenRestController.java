package inventory.atb.su.controller.RestContreller;

import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.service.FromExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmenRestController {
    private FromExcelDataService fromExcelDataService;

    @Autowired
    public DepartmenRestController(FromExcelDataService fromExcelDataService) {
        this.fromExcelDataService = fromExcelDataService;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employees
    // http://localhost:8080/SomeContextPath/employees.xml
    // http://localhost:8080/SomeContextPath/employees.json
    @RequestMapping(value = "/user/rest/departments", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE }
                    )
    @ResponseBody
    public List<DepartmentDTO> getEmployees() {
        List<DepartmentDTO> list = fromExcelDataService.getAllDepartments();
        return list;
    }
}
