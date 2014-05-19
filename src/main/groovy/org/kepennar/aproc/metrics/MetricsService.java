package org.kepennar.aproc.metrics;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

import javax.inject.Inject;

import org.kepennar.aproc.metrics.model.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
	
	private final MetricRepository repo;
	private final SimpMessagingTemplate template;
		
	private static final Function<org.springframework.boot.actuate.metrics.Metric<?>, Metric> toMetric = m -> {
		String name = m.getName().substring(15);
		return new Metric(name, m.getValue().longValue());
	};
	
	@Inject
	public MetricsService(MetricRepository repo, SimpMessagingTemplate template) {
		this.repo = repo;
		this.template = template;
	}
	
	
	@Scheduled(fixedDelay=2000)
	public void broadcastMetrics() {
		try {
			template.convertAndSend("/topic/counters", getCounters().get());
			template.convertAndSend("/topic/gauges", getGauges().get());
		} catch (MessagingException | InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}		
	}
	
	@Async
	public Future<List<Metric>> getCounters() {
		return new AsyncResult<>(getMetricsMatching("counter.status\\..*"));
	}
	
	@Async
	public Future<List<Metric>> getGauges() {
		return new AsyncResult<>(getMetricsMatching("gauge.response\\..*"));
	}
	
	private List<Metric> getMetricsMatching(String regex) {
		return newArrayList(repo.findAll())
				.stream().filter(m -> {
					return m.getName().matches(regex);
				})
				.map(toMetric)				
				.sorted(comparing(m -> m.getValue(), reverseOrder()))
			.collect(toList());
	}
}
