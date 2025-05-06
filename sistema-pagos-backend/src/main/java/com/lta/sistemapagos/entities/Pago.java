package com.lta.sistemapagos.entities;

import java.time.LocalDate;

import com.lta.sistemapagos.enums.PagoStatus;
import com.lta.sistemapagos.enums.TypePago;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Pago {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;    
    private LocalDate date;
    private double amount;
    private TypePago type;
    private PagoStatus status;
    private String file;
    @ManyToOne
    private Student student;
    
}
