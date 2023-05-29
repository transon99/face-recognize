package com.son_dev.FaceRecognite.Services;

import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {
    List<StudentDTO> findAll();

    StudentDTO attendance(StudentDTO studentDTO);
}
