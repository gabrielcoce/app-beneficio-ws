package com.gcoce.bc.ws.controllers.beneficio;

import com.gcoce.bc.ws.dto.beneficio.ParcialidadDto;
import com.gcoce.bc.ws.services.beneficio.ParcialidadSvc;
import com.gcoce.bc.ws.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gabriel Coc Estrada
 * @since 29/05/2023
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/beneficio/parcialidad", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ParcialidadController {
    @Autowired
    private ParcialidadSvc parcialidadSvc;
    @PostMapping("/crear-parcialidad")
    public ResponseEntity<?> createParcialidad(@Valid @RequestBody ParcialidadDto parcialidadDto, @RequestHeader(value = Constants.AUTHORIZATION, required = false) String token){
        return parcialidadSvc.createParcialidadSvc(parcialidadDto, token);
    }
}
