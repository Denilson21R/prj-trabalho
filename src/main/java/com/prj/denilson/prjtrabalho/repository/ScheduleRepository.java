package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT s FROM Schedule s WHERE s.company.id = ?1 AND s.date >= ?2 AND s.date <= ?3 AND (s.scheduleStatus = 0 OR s.scheduleStatus = 1) ORDER BY s.date")
    List<Schedule> findSchedulesByCompany(long id, LocalDateTime initialHour, LocalDateTime finalHour);

    @Query(value = "SELECT s FROM Schedule s WHERE s.animal.owner.id = ?1 AND s.scheduleStatus <> 3 ORDER BY s.date")
    List<Schedule> findSchedulesByAnimalOwner(Long user);

    @Query(value = "SELECT count(s) FROM Schedule s WHERE s.company.id = ?1 AND s.date >= ?2 AND s.date <= ?3 AND s.scheduleStatus = 0")
    Integer findQttdSchedulesCompany(long id, LocalDateTime initialHour, LocalDateTime finalHour);

    @Query(value = "SELECT count(s) FROM Schedule s WHERE s.animal.owner.id = ?1 AND s.date >= ?2 AND s.date <= ?3 AND s.scheduleStatus = 0")
    Integer findQttdSchedulesUser(long id, LocalDateTime initialHour, LocalDateTime finalHour);
}
