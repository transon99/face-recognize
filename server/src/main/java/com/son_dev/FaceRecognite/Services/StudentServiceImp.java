package com.son_dev.FaceRecognite.Services;

import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import com.son_dev.FaceRecognite.Entity.StudentEntity;
import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import com.son_dev.FaceRecognite.Model.Mapper.StudentMapper;
import com.son_dev.FaceRecognite.Repositories.AttendanceRepositories;
import com.son_dev.FaceRecognite.Repositories.StudentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudentServiceImp implements  IStudentService{

    @Autowired
    AttendanceRepositories attendanceRepositories;
    @Autowired
    StudentRepositories studentRepositories;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<StudentDTO> findAll() {
        List<StudentDTO> result = new ArrayList<>();
        List<StudentEntity> studentEntities = studentRepositories.findAll();
        for (StudentEntity item: studentEntities){
            StudentDTO studentDTO = studentMapper.toStudentDTO(item);
            result.add(studentDTO);
        }
        return result;
    }

    @Override
    public StudentDTO attendance(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        StudentEntity oldStudentEntities = studentRepositories.findOneByMssv(studentDTO.getMSSV());
        if (oldStudentEntities.getId() != null){
            studentEntity = studentMapper.toStudentEntity(studentDTO, oldStudentEntities);
        }else {
            studentEntity = studentMapper.toStudentEntity(studentDTO);
        }
        studentEntity = studentRepositories.save(studentEntity);
        StudentDTO result = studentMapper.toStudentDTO(studentEntity);

        return result;
    }
}
