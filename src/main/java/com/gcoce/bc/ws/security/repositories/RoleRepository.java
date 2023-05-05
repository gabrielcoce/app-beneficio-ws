package com.gcoce.bc.ws.security.repositories;

import com.gcoce.bc.ws.security.models.ERole;
import com.gcoce.bc.ws.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);
}
