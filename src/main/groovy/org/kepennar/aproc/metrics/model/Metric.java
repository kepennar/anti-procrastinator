package org.kepennar.aproc.metrics.model;

public class Metric {

	private final String name;
	private final String value;
	public Metric(String name, String number) {
		this.name = name;
		this.value = number;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	
	
	
	
}
