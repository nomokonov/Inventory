package inventory.atb.su.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

	@GetMapping("/user")
	public String welcome(
//            @AuthenticationPrincipal User user,
			Principal user,
			Map<String, Object> model) {

		model.put("user", user);
		return "user.html";
	}
}
