package com.gcoce.bc.ws.controllers.beneficio;

import com.gcoce.bc.ws.dto.beneficio.ActualizarCuentaDto;
import com.gcoce.bc.ws.dto.beneficio.ActualizarSolicitudDto;
import com.gcoce.bc.ws.dto.beneficio.CuentaDto;
import com.gcoce.bc.ws.services.beneficio.CuentaSvc;
import com.gcoce.bc.ws.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gabriel Coc Estrada
 * @since 27/05/2023
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/beneficio/cuenta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CuentaController {

    private final CuentaSvc cuentaSvc;

    public CuentaController(CuentaSvc cuentaSvc) {
        this.cuentaSvc = cuentaSvc;
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody CuentaDto cuentaDto, @RequestHeader(value = Constants.AUTHORIZATION, required = false) String token) {
        return cuentaSvc.crearCuentaSvc(cuentaDto, token);
    }

    @PostMapping("/actualizar-cuenta")
    public ResponseEntity<?> actualizarCuenta(@Valid @RequestBody ActualizarCuentaDto cuentaDto) {
        return cuentaSvc.updateCuenta((cuentaDto));
    }
}
