package org.kepennar.aproc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan("org.kepennar.aproc")
class Application {
    static void main(String[] args){
        new SpringApplication(Application).run(args)
    }
}