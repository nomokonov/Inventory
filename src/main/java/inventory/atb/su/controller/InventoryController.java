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
//            @AuthenticationPrincipal LdapUserDetails userDetails,
            Principal user,
            Model model) throws InvalidNameException {
//        String cn = ldapService.getCN(userDetails);
        model.addAttribute("user", user);

        List<DepartmentDTO> allDepartments = fromExcelDataService.getAllDepartments();
        List<String> mols = fromExcelDataService.getAllMols();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allMols", mols);
        if (findInvNumber.isPresent()){
            Optional<FromExcelData> invNumber = fromExcelDataService.getByInvNumber(findInvNumber.get());
            if (invNumber.isPresent()) {
                model.addAttribute("mol", invNumber.get().getMol());
                model.addAttribute("codeDepartment", invNumber.get().getCodeDepartment());
                model.addAttribute("invNumber", invNumber.get());

            } else {
                model.addAttribute("message", "Введеный Инв.№ не найден");
            }
        } else{
            model.addAttribute("message", "Начало");
        }



        return "inventory";
    }
}
