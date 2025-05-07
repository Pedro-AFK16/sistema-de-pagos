package com.lta.sistemapagos.services;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lta.sistemapagos.repositories.PagoRepository;
import com.lta.sistemapagos.repositories.StudentRepository;
import com.lta.sistemapagos.entities.Pago;
import com.lta.sistemapagos.enums.TypePago;

import java.time.LocalDate;

import jakarta.persistence.criteria.Path;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Pago savePago(MultipartFile file, double quantity, TypePago type, LocalDate date, String codigoStudent){
        /*Creacion de una ruta donde se guardara el archivo 
         * System.getProperty("user.home")--> Obtiene la ruta del directiorio del usuario del s.o
         * Path.get--> crea un objeto apuntando a una carpeta llamada "enset/pagos" dentro del directorio
         * 
        */
        
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","pagos");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath)
        }
    }
}
