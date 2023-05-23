package com.gcoce.bc.ws.entities.beneficio;

import com.gcoce.bc.ws.dto.agricultor.SolicitudDto;
import com.gcoce.bc.ws.dto.agricultor.UpSolicitudDto;
import com.gcoce.bc.ws.utils.Constants;
import com.gcoce.bc.ws.utils.Fechas;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Gabriel Coc Estrada
 * @since 17/05/2023
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "solicitudes", schema = "beneficio_ws")
public class Solicitud implements Serializable {

   /* @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "solicitud_id")
    private UUID solicitudId;*/

    @Id
    @Column(name = "no_solicitud")
    private String noSolicitud;

    @Column(name = "tipo_solicitud")
    private Integer tipoSolicitud;

    @Column(name = "estado_solicitud")
    private Integer estadoSolicitud;

    @Column(name = "usuario_solicita")
    private String usuarioSolicita;

    @Column(name = "peso_total")
    private Integer pesoTotal;

    @Column(name = "cantidad_parcialidades")
    private Integer cantidadParcialidades;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "user_updated")
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public static Solicitud createdReqFromDto(SolicitudDto solicitudDto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setNoSolicitud(Constants.generateManagement(solicitudDto.getTipoSolicitud()));
        solicitud.setTipoSolicitud(solicitudDto.getTipoSolicitud());
        solicitud.setEstadoSolicitud(Constants.SOLICITUD_CREADA);
        solicitud.setUsuarioSolicita(solicitudDto.getUsuarioSolicita());
        solicitud.setPesoTotal(solicitudDto.getPesoTotal());
        solicitud.setCantidadParcialidades(solicitudDto.getCantidadParcialidades());
        solicitud.setUserCreated(Constants.SYSTEM_USER);
        solicitud.setCreatedAt(Fechas.setTimeZoneDateGT(new Date()));
        return solicitud;
    }

    public static Solicitud updatedFromDto(UpSolicitudDto upSolicitudDto, Integer status) {
        Solicitud solicitud = new Solicitud();
        solicitud.setEstadoSolicitud(status);
        solicitud.setUserUpdated(upSolicitudDto.getUserLogin());
        solicitud.setUpdatedAt(Fechas.setTimeZoneDateGT(new Date()));
        return solicitud;
    }
}
