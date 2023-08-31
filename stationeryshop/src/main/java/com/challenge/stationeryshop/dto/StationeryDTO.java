package com.challenge.stationeryshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationeryDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private Long quantity;
}
