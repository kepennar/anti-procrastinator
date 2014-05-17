
package org.kepennar.aproc.metrics.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "labels",
    "datasets"
})
public class Metrics {

    @JsonProperty("labels")
    @Valid
    private List<String> labels = new ArrayList<String>();
    @JsonProperty("datasets")
    @Valid
    private List<Dataset> datasets = new ArrayList<Dataset>();

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("datasets")
    public List<Dataset> getDatasets() {
        return datasets;
    }

    @JsonProperty("datasets")
    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
