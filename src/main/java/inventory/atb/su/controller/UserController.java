package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.service.FromExcelDataService;
import inventory.atb.su.service.LDAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.InvalidNameException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private FromExcelDataService fromExcelDataService;
    private LDAPService ldapService;

    @Autowired
    public UserController(FromExcelDataService fromExcelDataService, LDAPService ldapService) {
        this.fromExcelDataService = fromExcelDataService;
		this.ldapService = ldapService;
	}

    @GetMapping("/user")
    public String welcome(@RequestParam(defaultValue = "0") Integer page,
						  @RequestParam(defaultValue = "10") Integer pageSize,
						  @RequestParam(defaultValue = "name") String sortBy,
						  @RequestParam(required = false) Optional<String> codeDepartment,
			@AuthenticationPrincipal LdapUserDetails userDetails,
			Principal user,
			Model model) throws InvalidNameException {
		String cn = ldapService.getCN(userDetails);
		List<FromExcelData> dataList = fromExcelDataService.getAllByMol(cn,page,pageSize,sortBy,codeDepartment);
		model.addAttribute("dataList",dataList);
		model.addAttribute("user", user);
        return "user";
    }


}
