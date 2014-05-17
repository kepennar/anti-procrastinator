package org.kepennar.aproc.metrics

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

import javax.inject.Inject

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/metrics")
class RestMetricsController {

	final MetricsService service
	
	@Inject
	RestMetricsController(MetricsService metricsService) {
		this.service = metricsService
	}
	@RequestMapping(method=GET, value="usage")
	public getUsageMetrics() {
		return Optional.ofNullable(service.getUsageMetrics())
			.map({m -> new ResponseEntity<>(m, OK)})
			.orElse(new ResponseEntity<>(NOT_FOUND));
		
	}
}
