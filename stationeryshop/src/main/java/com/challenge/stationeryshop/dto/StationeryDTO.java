package com.challenge.stationeryshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationeryDTO {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Digits(integer = 9, fraction = 2)
    private BigDecimal price;

    @Min(value = 0)
    private Long quantity;
}
