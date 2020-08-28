package net.olu.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("dashboard")
public class WebsiteUIController {

	@GetMapping(value="/websiteUI")
	public String websiteUI(@RequestParam(name="operation", required=false) String operation
			,ModelMap mp) {
		//list of admin proudcts
		
		
		//get form 
//		mp.put("product")
		
		return "dashboard.websiteUI";
	}
	@GetMapping(value="/websiteUI_process")
	public String HandleWebsiteUI() {
		
		return "redirect:/dashboard/website?operation=updated";
	}
	
}
