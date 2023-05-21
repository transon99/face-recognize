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
//        StudentEntity studentEntity = new StudentEntity();
//        if (studentDTO.getId() != null){
//            StudentEntity oldStudentEntity = studentRepositories.findOneByMssv(studentDTO.getMSSV());
//            studentEntity = studentMapper.toStudentEntity(studentDTO, oldStudentEntity);
//        }else{
//            studentEntity  = studentMapper.toStudentEntity(studentDTO);
//        }

        StudentEntity studentEntity = studentRepositories.save(studentMapper.toStudentEntity(studentDTO));
        StudentDTO result = studentMapper.toStudentDTO(studentEntity);
        return result;
    }
}
