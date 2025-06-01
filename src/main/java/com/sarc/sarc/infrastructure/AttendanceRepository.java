package com.sarc.sarc.infrastructure;

import com.sarc.sarc.core.domain.entities.Attendance;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{

}
