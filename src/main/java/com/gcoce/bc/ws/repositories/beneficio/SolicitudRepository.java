package com.gcoce.bc.ws.repositories.beneficio;

import com.gcoce.bc.ws.entities.beneficio.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Gabriel Coc Estrada
 * @since 20/05/2023
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {

    boolean existsByNoSolicitud(String noSolicitud);

    @Query(value = "select * from beneficio_ws.solicitud s " +
            "where s.usuario_solicita =:usuarioSolicita and s.estado_solicitud in(1,2)",
            nativeQuery = true)
    List<Solicitud> checkActiveReq(@Param("usuarioSolicita") String usuarioSolicita);

    Optional<Solicitud> getSolicitudByNoSolicitud(String noSolicitud);
}
