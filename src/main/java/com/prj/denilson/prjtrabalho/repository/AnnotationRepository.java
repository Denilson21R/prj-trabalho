package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
    @Query(value = "SELECT n FROM Annotation n WHERE n.schedule.id = ?1")
    List<Annotation> findAnnotationBySchedule(Long id);
}
