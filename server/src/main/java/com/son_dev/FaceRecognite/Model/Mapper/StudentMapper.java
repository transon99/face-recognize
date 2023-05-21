package com.son_dev.FaceRecognite.Model.Mapper;

import com.son_dev.FaceRecognite.Entity.StudentEntity;
import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDTO toStudentDTO (StudentEntity student){
        StudentDTO tmp = new StudentDTO();
        tmp.setId(student.getId());
        tmp.setFullName(student.getFullName());
        tmp.setMSSV(student.getMssv());
        tmp.setAttendances(student.getAttendances());
        return tmp;
    }

    public  StudentEntity toStudentEntity (StudentDTO dto){
        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setMssv(dto.getMSSV());
        entity.setAttendances(dto.getAttendances());
        return entity;
    }

    public StudentEntity toStudentEntity(StudentDTO dto, StudentEntity entity) {
        entity.setMssv(dto.getMSSV());
        entity.setFullName(dto.getFullName());
        entity.setAttendances(dto.getAttendances());
        return entity;
    }

}
