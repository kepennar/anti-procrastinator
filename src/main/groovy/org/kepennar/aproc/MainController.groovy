package org.kepennar.aproc;

import static org.springframework.web.bind.annotation.RequestMethod.GET

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MainController {

    @RequestMapping(method=GET, value="{name}")
    String home(@PathVariable String name) {
		return "Hello ${name}!"
    }

}