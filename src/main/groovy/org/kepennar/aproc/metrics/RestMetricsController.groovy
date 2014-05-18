package org.kepennar.aproc.metrics;

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.web.bind.annotation.RequestMethod.GET

import java.util.concurrent.Callable

import javax.inject.Inject

import org.kepennar.aproc.metrics.model.Metric
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/metrics")
class RestMetricsController {
	final MetricsService service;
	
	@Inject
	RestMetricsController(MetricsService service) {
		this.service = service;
	}
	
	
    @RequestMapping(method = GET, value="counters")
    Callable<List<Metric>> getCounters() {
		return {
			service.getCounters().get();			
		};		
    }
	
	@RequestMapping(method = GET, value="gauges")
	Callable<List<Metric>> getGauges() {
		return {
			service.getGauges().get();
		};		
	}

}