package org.kepennar.aproc.metrics;

import java.util.List;

import javax.inject.Inject;

import org.kepennar.aproc.metrics.model.Dataset;
import org.kepennar.aproc.metrics.model.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class MetricsService {
	private final static Logger LOG = LoggerFactory.getLogger(MetricsService.class);
	private final MetricRepository metricRepository;
	
	@Inject
	public MetricsService(MetricRepository metricRepository) {
		this.metricRepository = metricRepository;
	}
	
	public Metrics getUsageMetrics() {
		
		
		
		Dataset dataset = new Dataset();
		List<Dataset> datasets = Lists.newArrayList(dataset);
		dataset.setFillColor("rgba(220,220,220,0.5)");
		dataset.setStrokeColor("rgba(220,220,220,1)");
		Metrics metrics = new Metrics();
		metrics.setDatasets(datasets);
		
		Lists.newArrayList(metricRepository.findAll())
			.stream().filter(m -> {
				return m.getName().matches(".*(counter.status.|gauge.response.).*");
			}).forEach(m -> {
				metrics.getLabels().add(m.getName());
				dataset.getData().add(Float.valueOf(String.valueOf(m.getValue())));
			});
		
		return metrics;
	}
}
