package com.son_dev.FaceRecognite.Services.impl;

import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import com.son_dev.FaceRecognite.Entity.StudentEntity;
import com.son_dev.FaceRecognite.Model.DTO.AttendanceDTO;
import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import com.son_dev.FaceRecognite.Model.Mapper.AttendanceMapper;
import com.son_dev.FaceRecognite.Model.Mapper.StudentMapper;
import com.son_dev.FaceRecognite.Repositories.AttendanceDateRepositories;
import com.son_dev.FaceRecognite.Repositories.StudentRepositories;
import com.son_dev.FaceRecognite.Services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class StudentServiceImp implements IStudentService {

    @Autowired
    AttendanceDateRepositories attendanceRepositories;
    @Autowired
    StudentRepositories studentRepositories;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    AttendanceMapper attendanceMapper;

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
        if (oldStudentEntities != null){
            Set<AttendanceEntity> listAttendanceEntity = oldStudentEntities.getAttendances();
            Set<AttendanceDTO> listAttendanceDTO =  studentDTO.getAttendances();
            listAttendanceDTO.forEach(item -> {
                listAttendanceEntity.add(attendanceMapper.toEntity(item));
            });
            studentEntity = studentMapper.toStudentEntity(studentDTO, oldStudentEntities);
            studentEntity.setAttendances(listAttendanceEntity);
        }else {
            studentEntity = studentMapper.toStudentEntity(studentDTO);
        }
        studentEntity = studentRepositories.save(studentEntity);
        StudentDTO result = studentMapper.toStudentDTO(studentEntity);

        return result;
    }
}
