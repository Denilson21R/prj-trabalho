package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT s FROM Schedule s WHERE s.company.id = ?1")
    List<Schedule> findSchedulesByCompany(long id);

    @Query(value = "SELECT s FROM Schedule s WHERE s.animal.owner.id = ?1")
    List<Schedule> findSchedulesByAnimalOwner(Long user);
}
