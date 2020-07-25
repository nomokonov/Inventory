package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.InvalidNameException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class InvNumberController {
    private FromExcelDataService fromExcelDataService;
    private LDAPService ldapService;

    @Autowired
    public InvNumberController(FromExcelDataService fromExcelDataService, LDAPService ldapService) {
        this.fromExcelDataService = fromExcelDataService;
        this.ldapService = ldapService;
    }

    @GetMapping("/user/{id}")
    public String InvNumber(
            @PathVariable Long id,
            @AuthenticationPrincipal LdapUserDetails userDetails,
            Principal user,
            Model model) throws InvalidNameException {
//        String cn = ldapService.getCN(userDetails);
        model.addAttribute("user", user);

        List<DepartmentDTO> allDepartments = fromExcelDataService.getAllDepartments();
        List<String> mols = fromExcelDataService.getAllMols();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allMols", mols);

        Optional<FromExcelData> invNumber = fromExcelDataService.getById(id);
        if (invNumber.isPresent()) {
            model.addAttribute("mol", invNumber.get().getMol());
            model.addAttribute("codeDepartment", invNumber.get().getCodeDepartment());
            model.addAttribute("invNumber", invNumber.get());
        } else {
            model.addAttribute("message","Введеный Инв.№ не найден");
        }

        return "invnumber";
    }

    @GetMapping("/user/find/")
    public String FindInvNumber(
            @RequestParam("invnumber") String findInvNumber,
//            @AuthenticationPrincipal LdapUserDetails userDetails,
            Principal user,
            Model model) throws InvalidNameException {
//        String cn = ldapService.getCN(userDetails);
        model.addAttribute("user", user);

        List<DepartmentDTO> allDepartments = fromExcelDataService.getAllDepartments();
        List<String> mols = fromExcelDataService.getAllMols();
        model.addAttribute("allDepartments", allDepartments);
        model.addAttribute("allMols", mols);

        Optional<FromExcelData> invNumber = fromExcelDataService.getByInvNumber(findInvNumber);
        if (invNumber.isPresent()) {
            model.addAttribute("mol", invNumber.get().getMol());
            model.addAttribute("codeDepartment", invNumber.get().getCodeDepartment());
            model.addAttribute("invNumber", invNumber.get());

        } else {
            model.addAttribute("message","Введеный Инв.№ не найден");
        }

        return "invnumber";
    }

    @PostMapping("/user/{id}")
    public String EditInvNumber(@PathVariable Long id,
                                @AuthenticationPrincipal LdapUserDetails userDetails,
                                Principal user,
                                @RequestParam("mol") String mol,
                                @RequestParam("codeDepartment") String codeDeparment,
                                Model model     ) {
        model.addAttribute("user", user);
         FromExcelData frpFromExcelData = fromExcelDataService.update(id,mol,codeDeparment);
        return "redirect:/user/" + id + "/";
    }


}
