package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.InvMovings;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.InvalidNameException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/invmovings/*")
public class InvMovingscontroller {
    private FromExcelDataService fromExcelDataService;
    private LDAPService ldapService;

    @Autowired
    public InvMovingscontroller(FromExcelDataService fromExcelDataService, LDAPService ldapService) {
        this.fromExcelDataService = fromExcelDataService;
        this.ldapService = ldapService;
    }

    @GetMapping( "/")
    public String getAllWithInvMovings(@RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "20") Integer pageSize,
                          @RequestParam(defaultValue = "mol") String sortBy,
                          @AuthenticationPrincipal LdapUserDetails userDetails,
                          Principal user,
                          Model model) throws InvalidNameException {
        String cn = ldapService.getCN(userDetails);

        Page<InvMovings> result = fromExcelDataService.getAllWithInvMovings(page,pageSize,sortBy);
        model.addAttribute("mol",cn);
        model.addAttribute("dataList",result.getContent());
        model.addAttribute("page",result.getPageable().getPageNumber());
        model.addAttribute("pageSize",result.getPageable().getPageSize());
        model.addAttribute("totalPages",result.getTotalPages());
        model.addAttribute("totalElements",result.getTotalElements());
        model.addAttribute("user", user);
        return "invmovings";
    }
}
