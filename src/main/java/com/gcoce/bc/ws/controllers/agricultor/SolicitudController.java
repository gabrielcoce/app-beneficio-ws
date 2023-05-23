package com.gcoce.bc.ws.controllers.agricultor;

import com.gcoce.bc.ws.dto.agricultor.SolicitudDto;
import com.gcoce.bc.ws.services.beneficio.SolicitudSvc;
import com.gcoce.bc.ws.utils.Constants;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gabriel Coc Estrada
 * @since 20/05/2023
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/agricultor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//@RolesAllowed("ROLE_AGRICULTOR")
public class SolicitudController {

    private final SolicitudSvc solicitudSvc;

    public SolicitudController(SolicitudSvc solicitudSvc) {
        this.solicitudSvc = solicitudSvc;
    }

    @PostMapping("/crear-solicitud")
    public ResponseEntity<?> createSolicitud(@Valid @RequestBody SolicitudDto solicitudDto, @RequestHeader(value = Constants.AUTHORIZATION, required = false) String token) {
        return solicitudSvc.createSolicitudSvc((solicitudDto), token);
    }
}
