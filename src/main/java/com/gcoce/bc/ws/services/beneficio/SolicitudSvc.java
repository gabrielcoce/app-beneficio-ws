package com.gcoce.bc.ws.services.beneficio;

import com.gcoce.bc.ws.dto.beneficio.ActualizarSolicitudDto;
import com.gcoce.bc.ws.dto.beneficio.SolicitudDto;
import com.gcoce.bc.ws.entities.beneficio.Solicitud;
import com.gcoce.bc.ws.exceptions.BeneficioException;
import com.gcoce.bc.ws.exceptions.RecordNotFoundException;
import com.gcoce.bc.ws.payload.response.SuccessResponse;
import com.gcoce.bc.ws.repositories.beneficio.SolicitudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Gabriel Coc Estrada
 * @since 20/05/2023
 */
@Service
@Transactional(transactionManager = "beneficioTransactionManager")
public class SolicitudSvc {

    private final SolicitudRepository solicitudRepository;

    private final AuthSvc authSvc;

    public SolicitudSvc(SolicitudRepository solicitudRepository, AuthSvc authSvc) {
        this.solicitudRepository = solicitudRepository;
        this.authSvc = authSvc;
    }

    public ResponseEntity<?> createSolicitudSvc(SolicitudDto solicitudDto, String token) {
        String message;
        if (!authSvc.existsUserSvc(solicitudDto.getUsuarioSolicita())) {
            throw new BeneficioException("Usuario no existe.");
        }

        if (checkActiveReqSvc(solicitudDto.getUsuarioSolicita())) {
            throw new BeneficioException("Usuario cuenta con una solicitud en proceso.");
        }
        if (authSvc.validateUserToken(token, solicitudDto.getUsuarioSolicita())) {
            throw new BeneficioException("Usuario ingresado no corresponde a usuario logueado.");
        }
        try {
            final Solicitud solicitud = Solicitud.createdReqFromDto(solicitudDto);
            solicitudRepository.save(solicitud);
            message = String.format("Solicitud %s a sido registrada exitosamente.", solicitud.getNoSolicitud());
            return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, message, true));
        } catch (BeneficioException e) {
            throw new BeneficioException("No se pudo crear la solicitud.");
        }
    }

    public boolean checkActiveReqSvc(String usuarioSolicita) {
        List<Solicitud> solicitud = solicitudRepository.checkActiveReq(usuarioSolicita);
        return !solicitud.isEmpty();
    }

    public boolean existsByNoSolicitudSvc(String noSolicitud) {
        return solicitudRepository.existsByNoSolicitud(noSolicitud);
    }

    public Solicitud obtenerSolicitud(String noSolicitud) {
        return solicitudRepository.getSolicitudByNoSolicitud(noSolicitud)
                .orElseThrow(() -> new RecordNotFoundException("Solicitud no encontrada."));
    }

    public  ResponseEntity<?> updateSolicitud(ActualizarSolicitudDto solicitudDto) {
        String message;
        Solicitud solicitud = new Solicitud();
        solicitud = solicitudRepository.findById(solicitudDto.getNoSolicitud()).orElse(null);

        if (solicitud != null) {
            solicitud.setEstadoSolicitud(solicitudDto.getNuevoEstado());
            solicitud.setUserUpdated(solicitudDto.getUserUpdated());
            solicitud.setUpdatedAt(new Date());
            solicitudRepository.save(solicitud);
            message = String.format("Se actualizo correctamente la solicitud No. ");
            return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, message, true));
        } else {
            message = String.format("No se encontro ninguna solicitud para actualizar");
            throw new BeneficioException(message);
        }
    }


}
