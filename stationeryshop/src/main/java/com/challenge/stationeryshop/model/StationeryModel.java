package com.challenge.stationeryshop.model;

import com.challenge.stationeryshop.dto.StationeryDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    //@Column(nullable = false)
    //@NotNull
    @NotBlank(message = "name-1") //Só esse já faz o trabalho de não deixar branco nem nulo
    private String name;

    //Descrição é opcional
    private String description;

    //Preço tem que ter 2 casas decimais
    //@Column(nullable = false)
    @NotNull(message = "price-1")
    @Digits(integer = 9, fraction = 2, message = "price-2")
    private BigDecimal price;

    //Quantidade é contada em unidades, então só pode ser inteiro
    @Min(value = 0, message = "quantity-1")
    private Long quantity;
}
