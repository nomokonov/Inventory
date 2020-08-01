package inventory.atb.su.controller;

import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.naming.InvalidNameException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class GetDocMovings {
    private FromExcelDataService fromExcelDataService;
    private LDAPService ldapService;

    @Autowired
    public GetDocMovings(FromExcelDataService fromExcelDataService, LDAPService ldapService) {
        this.fromExcelDataService = fromExcelDataService;
        this.ldapService = ldapService;
    }


    @GetMapping("/invmovings/getdocsmovings")
    public String welcome(
            @AuthenticationPrincipal LdapUserDetails userDetails,
            Principal user,
            Model model) throws InvalidNameException, IOException {
        String cn = ldapService.getCN(userDetails);

        List<String> fileList = fromExcelDataService.GetDocsMovings();
        model.addAttribute("user", user);
        model.addAttribute("fileList", fileList);
        return "docsmovings";
    }
}
