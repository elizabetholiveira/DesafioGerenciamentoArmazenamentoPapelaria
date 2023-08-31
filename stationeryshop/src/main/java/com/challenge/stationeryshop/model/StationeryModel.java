package com.challenge.stationeryshop.model;

import com.challenge.stationeryshop.dto.StationeryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_STATIONERIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationeryModel {

    //Attributes

    //ID tem que ser autoincrementado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Nome não pode estar em branco
    @Column(nullable = false)
    @NotBlank
    private String name;

    //Descrição é opcional
    private String description;

    //Preço tem que ter 2 casas decimais
    @Column(nullable = false)
    @NotBlank
    @Digits(integer = 9, fraction = 2)
    private BigDecimal price;

    //Quantidade é contada em unidades, então só pode ser inteiro
    private Long quantity;
}
