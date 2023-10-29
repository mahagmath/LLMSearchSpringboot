package com.maha.mongo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "category",
    "description"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromptResponse {
	@JsonProperty("code")
    @Valid
	private String code;
	@JsonProperty("category")
    @Valid
	private String category;
	@JsonProperty("description")
    @Valid
	private String description;
	

}
