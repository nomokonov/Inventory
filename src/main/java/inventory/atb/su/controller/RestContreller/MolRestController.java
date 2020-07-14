package inventory.atb.su.controller.RestContreller;

import inventory.atb.su.service.FromExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MolRestController {
    private FromExcelDataService fromExcelDataService;

    @Autowired

    public MolRestController(FromExcelDataService fromExcelDataService) {
        this.fromExcelDataService = fromExcelDataService;
    }

    // URL:
    // http://localhost:8080/user/rest/mols
    // http://localhost:8080/user/rest/mols.xml
    // http://localhost:8080/user/rest/mols.json
    @RequestMapping(value = "/user/rest/mols", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE }
    )
    @ResponseBody
    public List<String> getEmployees() {
        List<String> list = fromExcelDataService.getAllMols();
        return list;
    }
}
