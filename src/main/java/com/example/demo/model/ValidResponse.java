package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidResponse {
	@JsonProperty
	private String status;
	@JsonProperty
	private Object data;

	public void Build(String status, Object data) {
		this.status = status;;
		this.data =  data;
	}
}
