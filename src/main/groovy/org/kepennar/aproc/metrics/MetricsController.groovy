package org.kepennar.aproc.metrics;

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

import javax.inject.Inject

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("site/metrics")
class MetricsController {
	
    @RequestMapping(method = GET)
    String list(Model model) {
		
		model.addAttribute("currentMenu", "metrics");
		return "/metrics/metrics";
    }

}