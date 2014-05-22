package org.kepennar.aproc

import org.junit.runner.RunWith

import cucumber.api.junit.Cucumber

@RunWith(Cucumber)
@cucumber.api.CucumberOptions(
    format=["pretty", "html:build/reports/cucumberTest/cucumber"],
    strict=true,
    features=["src/cucumberTest/cucumber"],
    glue=["src/cucumberTest/groovy/org/kepennar/aproc/steps"],
    tags = ["~@ignore"]
)
class RunCukes {}