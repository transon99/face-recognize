package com.son_dev.FaceRecognite.Repositories;

import com.son_dev.FaceRecognite.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositories extends JpaRepository<StudentEntity, Long> {
    StudentEntity findOneByMssv(String mssv);
}
