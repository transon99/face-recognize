package com.son_dev.FaceRecognite.Repositories;

import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepositories extends JpaRepository<AttendanceEntity,Long> {
    AttendanceEntity findOneByAttendanceDay(String attendanceDay);
}
