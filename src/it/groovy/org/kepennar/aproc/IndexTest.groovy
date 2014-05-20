package org.kepennar.aproc

import static org.junit.Assert.*

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Test
import org.kepennar.test.support.AbstractIntegrationTests
import org.springframework.test.web.servlet.MvcResult
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IndexTest extends AbstractIntegrationTests {

	@Test
	void showIndex() {
		MvcResult result = mockMvc.perform(get("/site")).andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith("text/html")).andReturn()
		
		Document document = Jsoup.parse(result.getResponse().getContentAsString())
		assertThat(document.select("ul.nav li.active").text(), equalTo("Home"));
	}
}
