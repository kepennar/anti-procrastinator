package org.kepennar.aproc;

import static org.springframework.web.bind.annotation.RequestMethod.GET

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/site")
class MainController {

	@RequestMapping(method = GET)
	String getCurrentMenu(Model model) {
			model.addAttribute("menuItems", null);
			return "/home";
		}
	
    @RequestMapping(method=GET, value="{name}")
    @ResponseBody String home(@PathVariable String name) {
		return "Hello ${name}!"
    }

}