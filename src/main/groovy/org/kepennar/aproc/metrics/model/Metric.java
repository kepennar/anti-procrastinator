package org.kepennar.aproc.metrics.model;

public class Metric {

	private final String name;
	private final Number value;
	public Metric(String name, Number number) {
		this.name = name;
		this.value = number;
	}
	public String getName() {
		return name;
	}
	public Number getValue() {
		return value;
	}
	
	
	
	
}
