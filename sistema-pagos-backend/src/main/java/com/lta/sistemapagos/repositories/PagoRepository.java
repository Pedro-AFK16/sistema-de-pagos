package com.lta.sistemapagos.repositories;
import com.lta.sistemapagos.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long > {
        List<Pago> ByStudent
}
