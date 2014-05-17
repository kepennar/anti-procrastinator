
package org.kepennar.aproc.metrics.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "fillColor",
    "strokeColor",
    "data"
})
public class Dataset {

    @JsonProperty("fillColor")
    private String fillColor;
    @JsonProperty("strokeColor")
    private String strokeColor;
    @JsonProperty("data")
    @Valid
    private List<Float> data = new ArrayList<>();

    @JsonProperty("fillColor")
    public String getFillColor() {
        return fillColor;
    }

    @JsonProperty("fillColor")
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @JsonProperty("strokeColor")
    public String getStrokeColor() {
        return strokeColor;
    }

    @JsonProperty("strokeColor")
    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    @JsonProperty("data")
    public List<Float> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Float> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
