package com.lta.sistemapagos.services;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lta.sistemapagos.repositories.PagoRepository;
import com.lta.sistemapagos.repositories.StudentRepository;
import com.lta.sistemapagos.entities.Pago;
import com.lta.sistemapagos.entities.Student;
import com.lta.sistemapagos.enums.PagoStatus;
import com.lta.sistemapagos.enums.TypePago;

import java.time.LocalDate;
import java.util.UUID;
import java.io.IOException;
import java.nio.file.Files;
/*import jakarta.persistence.criteria.Path;
*/
import java.nio.file.Path;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class PagoService {
    /*Autowired para utilizar los dos repositorios */
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Pago savePago(MultipartFile file, double quantity, TypePago type, LocalDate date, String codigoStudent){
        /*Crea una ruta donde se guarda el archivo
         * System.getProperty("user.home")--> Obtiene la ruta del directiorio del usuario del s.o
         * Path.get--> crea un objeto apuntando a una carpeta llamada "enset/pagos" dentro del directorio
         * quedando esto asi "/home/usuario/enset-data/pagos"
         * Luego verifica que no exista, y sino la crea
        */
        
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","pagos");
            /*
             * Aca tenia Files.createDirectories(folderPath), pero lo tuve que cambiar
             * utilizando un try catch o un throw, ya que me salia un IOException
             * que es un error que java me pide manejar, ya que pueden ocurrir en tiempo
             * de ejecucion
             */
       
        if (!Files.exists(folderPath)) {
        try {
            Files.createDirectories(folderPath);
            System.out.println("Carpeta creada en: " + folderPath);
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
        }
        } else {
            System.out.println("La carpeta ya existe en: " + folderPath);
        }

        /*
         * Generamos un identificador, para no tener conflictos al crear el archivo
         * Luego, creamos una ruta para el archivo pdf, que se guardara en enset/data
         *
         */
        String fileName = UUID.randomUUID().toString();
        
        Path filePath = Paths.get(System.getProperty("user.home"),"enset-data","pagos",fileName+".pdf");
        
        /*
         * Esto se hace para leer los datos de los archivos, desde la solicitud http
         * y luego los copia en el archivo filepath
         */
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace(); // o mostrar un mensaje al usuario
        };
        
        Student student = studentRepository.findByCodigo(codigoStudent);

        Pago pago = Pago.builder()
        .type(type)
        .status(PagoStatus.CREATED)
        .date(date)
        .student(student)
        .quantity(quantity)
        .file(filePath.toUri().toString())
        .build();

    
        return pagoRepository.save(pago);
    }



}
