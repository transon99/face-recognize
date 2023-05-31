package com.son_dev.FaceRecognite.Repositories;

import com.son_dev.FaceRecognite.Entity.AttendanceDateEntity;
import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDateRepositories extends JpaRepository<AttendanceDateEntity,Long> {
    void deleteById(Long id);

    AttendanceDateEntity findOneById(Long id);
}
