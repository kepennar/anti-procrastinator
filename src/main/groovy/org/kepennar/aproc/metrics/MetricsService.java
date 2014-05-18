package org.kepennar.aproc.metrics;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

import javax.inject.Inject;

import org.kepennar.aproc.metrics.model.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
	private final MetricRepository repo;
	private Function<org.springframework.boot.actuate.metrics.Metric<?>, Metric> toMetric = m -> {
		String name = m.getName().substring(15);
		return new Metric(name, m.getValue());
	};
	
	@Inject
	public MetricsService(MetricRepository repo) {
		this.repo = repo;
	}
	
	@Async
	public Future<List<Metric>> getCounters() {
		List<Metric> metrics = newArrayList(repo.findAll())
			.stream().filter(m-> {
				return m.getName().matches("counter.status\\..*");
			}).map(toMetric)
		.collect(toList());
		return new AsyncResult<>(metrics);
	}
	
	@Async
	public Future<List<Metric>> getGauges() {
		 		
		 List<Metric> metrics = newArrayList(repo.findAll())
				.stream().filter(m-> {
					return m.getName().matches("gauge.response\\..*");
				}).map(toMetric)
			.collect(toList());
		 return new AsyncResult<>(metrics);
	}
}
