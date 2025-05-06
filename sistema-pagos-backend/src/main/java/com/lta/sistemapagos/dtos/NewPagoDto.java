package com.lta.sistemapagos.dtos;

import java.time.LocalDate;

import com.lta.sistemapagos.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPagoDto {
    private double quantity;
    private TypePago typePago;
    private LocalDate date;
    private String codigoStudent;
}
