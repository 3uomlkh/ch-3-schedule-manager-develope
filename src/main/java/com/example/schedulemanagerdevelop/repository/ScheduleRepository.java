package com.example.schedulemanagerdevelop.repository;

import com.example.schedulemanagerdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
