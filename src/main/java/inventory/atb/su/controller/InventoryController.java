package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/inventory/")
public class InventoryController {
    private FromExcelDataService fromExcelDataService;
    private LDAPService ldapService;

    @Autowired
    public InventoryController(FromExcelDataService fromExcelDataService, LDAPService ldapService) {
        this.fromExcelDataService = fromExcelDataService;
        this.ldapService = ldapService;
    }

    //    @GetMapping("/find/")
    @GetMapping("/")
    public String InventoryFind(
            @RequestParam(required = false) Optional<String> findInvNumber,
            @RequestParam(required = false) Optional<String> codeDepartment,
//            @AuthenticationPrincipal LdapUserDetails userDetails,
            Principal user,
            Model model) throws InvalidNameException {
//        String cn = ldapService.getCN(userDetails);
        model.addAttribute("user", user);

        List<DepartmentDTO> allDepartments = fromExcelDataService.getAllDepartments();
        List<String> mols = fromExcelDataService.getAllMols();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allMols", mols);
        if (codeDepartment.isPresent() && !codeDepartment.get().isEmpty()){
            model.addAttribute("codeDepartment", codeDepartment.get());
        }
        if (findInvNumber.isPresent()){
            Optional<FromExcelData> invNumber = fromExcelDataService.getByInvNumber(findInvNumber.get());
            if (invNumber.isPresent()) {
                model.addAttribute("mol", invNumber.get().getMol());
                model.addAttribute("invNumber", invNumber.get());

            } else {
                model.addAttribute("message", "Введеный Инв.№ не найден");
            }
        }
        return "inventory";
    }

    @PostMapping("/")
    public String MoveToDepartment(@RequestParam Long id,
                                @AuthenticationPrincipal LdapUserDetails userDetails,
                                Principal user,
                                @RequestParam("mol") String mol,
                                @RequestParam("codeDepartment") String codeDeparment,
                                Model model) {
        FromExcelData frpFromExcelData = fromExcelDataService.update(id, mol, codeDeparment);

        model.addAttribute("user", user);
        List<DepartmentDTO> allDepartments = fromExcelDataService.getAllDepartments();
        List<String> mols = fromExcelDataService.getAllMols();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allMols", mols);
            model.addAttribute("codeDepartment", codeDeparment);
        if (id >= 0) {
            Optional<FromExcelData> invNumber = fromExcelDataService.getById(id);
            if (invNumber.isPresent()) {
                model.addAttribute("mol", invNumber.get().getMol());
                model.addAttribute("invNumber", invNumber.get());

            } else {
                model.addAttribute("message", "Введеный Инв.№ не найден");
            }
        }else{
            model.addAttribute("message", "ошибка при изменении позиции");
        }

        return "inventory";
    }
}
