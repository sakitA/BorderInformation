package org.demo.cze.borderinformation;

import org.demo.cze.borderinformation.service.BorderInformationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BorderInformationApplicationTests {
	private static BorderInformationService service;

	@BeforeAll
	static void initialize() {
		service = new BorderInformationService();
	}

	@Test
	void testSameOriginAndDestination() {
		// Act.
		List<String> result = service.getBorderInfo("CZE", "CZE");

		// Asser.
		assertEquals(Collections.singletonList("CZE"), result);
	}

	@Test
	void testNoRouteExists() {
		// Act.
		List<String> result = service.getBorderInfo("USA", "CZE");

		// Assert.
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testRouteExists() {
		// Arrange.
		List<String> expectedRoute = Arrays.asList("CZE", "POL", "RUS", "AZE");

		// Act.
		List<String> actualRoute = service.getBorderInfo("CZE", "AZE");

		// Assert.
		assertEquals(expectedRoute, actualRoute);
	}

	@Test
	void testCaseInsensitiveRouteExists() {
		// Arrange.
		List<String> expectedRoute = Arrays.asList("CZE", "AUT");

		// Act.
		List<String> actualRoute = service.getBorderInfo("CzE", "aut");

		// Assert.
		assertEquals(expectedRoute, actualRoute);
	}
}
