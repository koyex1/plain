package net.olu.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Account;
import net.olu.services.AccountService;

@Controller
@RequestMapping("dashboard")
public class ProfileController {

	@Autowired
	AccountService accountService;

	@GetMapping
	public String index() {
		return "dashboard.index"; /*
									 * prefix-view suffix-jsp
									 */
	}

	@GetMapping(value = "/profile")
	public String Profile(Authentication auth, @RequestParam(name = "operation", required = false) String operation,
			ModelMap mp) {
		mp.put("details", accountService.findByUsername(auth.getName()));
		if (operation != null) {
			if (operation.equals("updated")) {
				mp.put("operation", "Successfully Updated Profile Info");
			}
		}

		return "dashboard/profile";
	}

	@PostMapping(value = "/profile_submit")
	public String HandleProfile(@ModelAttribute("details") Account account) {

		account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
		accountService.save(account);

		return "redirect:/dashboard/profile?operation=updated";
	}

}