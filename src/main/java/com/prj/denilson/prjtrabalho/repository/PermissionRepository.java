package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
