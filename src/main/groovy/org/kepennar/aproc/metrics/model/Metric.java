package org.kepennar.aproc.metrics.model;

public class Metric {

	private final String name;
	private final Long value;
	public Metric(String name, Long number) {
		this.name = name;
		this.value = number;
	}
	public String getName() {
		return name;
	}
	public Long getValue() {
		return value;
	}
	
	
	
	
}
