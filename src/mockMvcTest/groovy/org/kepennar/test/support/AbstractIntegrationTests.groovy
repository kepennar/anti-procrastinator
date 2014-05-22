package org.kepennar.test.support


import javax.inject.Inject

import org.junit.Before
import org.junit.runner.RunWith
import org.kepennar.aproc.Application
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public abstract class AbstractIntegrationTests {

	protected MockMvc mockMvc

	@Inject
	WebApplicationContext wac

	@Before
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
	}
}