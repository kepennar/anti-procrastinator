package org.kepennar.aproc

import static org.junit.Assert.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Test
import org.kepennar.test.support.AbstractIntegrationTests
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult

class IndexTest extends AbstractIntegrationTests {

	@Test
	void showIndex() {
		MvcResult result = mockMvc.perform(get("/site")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith("text/html")).andReturn()
		
		Document document = Jsoup.parse(result.getResponse().getContentAsString())
		assertEquals(document.select("ul.nav li.active").first().text(), "Home");
		assertEquals(document.select("div.jumbotron > h1").first().text(), "Bonjour!")
	}
}
