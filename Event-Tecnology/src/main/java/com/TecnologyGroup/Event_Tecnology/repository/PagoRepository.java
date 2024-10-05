package com.TecnologyGroup.Event_Tecnology.repository;

import com.TecnologyGroup.Event_Tecnology.model.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    // Consultar pagos por usuario
    List<Pago> findByUsuario_Id(Integer userId);

    // Consultar pagos por evento
    List<Pago> findByEvento_Id(Integer eventId);

    // Consultar pagos por inscripción
    List<Pago> findByInscripcion_Id(Integer inscripcionId);

    // Consultar pagos realizados en un rango de fechas
    @Query("SELECT p FROM Pago p WHERE p.fechaPago BETWEEN :fechaInicio AND :fechaFin")
    List<Pago> findPagosByFechaPagoBetween(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    // Consultar pagos por método de pago
    List<Pago> findByMetodoPago(String metodoPago);
}
