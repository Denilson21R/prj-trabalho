package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Animal;
import com.prj.denilson.prjtrabalho.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(value = "SELECT p FROM Permission p WHERE id_user = ?1")
    Optional<Permission> findPermissionByUser(Long user);
}
