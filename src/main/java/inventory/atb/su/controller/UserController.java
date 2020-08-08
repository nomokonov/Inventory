package inventory.atb.su.controller;

import inventory.atb.su.models.FromExcelData;
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

	@GetMapping("/login")
	public String loginPage(){
    	return  "login";
	}

	@GetMapping( "/")
	public String defRoute(){
    	return "redirect:/user/";
	}
    @GetMapping( "/user")
    public String welcome(@RequestParam(defaultValue = "0") Integer page,
						  @RequestParam(defaultValue = "20") Integer pageSize,
						  @RequestParam(defaultValue = "name") String sortBy,
						  @RequestParam(required = false) Optional<String> codeDepartment,
						  @RequestParam(required = false) Optional<String> mol,
						  @RequestParam(required = false) Optional<String> name,
							@AuthenticationPrincipal LdapUserDetails userDetails,
			Principal user,
			Model model) throws InvalidNameException {
		String cn = ldapService.getCN(userDetails);
		if (mol.isPresent() && !mol.get().equals("?")){
			model.addAttribute("mol",mol.get());
		}
		if (codeDepartment.isPresent() && !codeDepartment.get().equals("?")){
			model.addAttribute("codeDepartment",codeDepartment.get());
		}
		if (name.isPresent() && !name.get().isEmpty()){
			model.addAttribute("name",name.get());
		}


		Page<FromExcelData> result = fromExcelDataService.getAllByFilter(mol,page,pageSize,sortBy,codeDepartment, name);
		List<DepartmentDTO> allDepartments = fromExcelDataService.getAllDepartments();
		List<String> allMols = fromExcelDataService.getAllMols();
		model.addAttribute("allDepartments",allDepartments);
		model.addAttribute("allMols", allMols);
		model.addAttribute("dataList",result.getContent());
		model.addAttribute("page",result.getPageable().getPageNumber());
		model.addAttribute("pageSize",result.getPageable().getPageSize());
		model.addAttribute("totalPages",result.getTotalPages());
		model.addAttribute("totalElements",result.getTotalElements());
		model.addAttribute("user", user);
        return "user";
    }


}
