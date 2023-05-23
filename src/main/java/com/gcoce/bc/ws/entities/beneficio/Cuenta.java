package com.gcoce.bc.ws.entities.beneficio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * @author Gabriel Coc Estrada
 * @since 22/05/2023
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cuentas", schema = "beneficio_ws")
public class Cuenta {

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cuenta_id")
    private UUID cuentaId;*/

    @Id
    @Column(name = "no_cuenta")
    private String noCuenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_solicitud")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@Column(name = "no_solicitud")
    private Solicitud solicitud;

    @Column(name = "estado_solicitud")
    private Integer estadoSolicitud;

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


}
