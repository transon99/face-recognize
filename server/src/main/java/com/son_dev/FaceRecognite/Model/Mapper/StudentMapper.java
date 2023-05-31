package com.son_dev.FaceRecognite.Model.Mapper;

import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import com.son_dev.FaceRecognite.Entity.StudentEntity;
import com.son_dev.FaceRecognite.Model.DTO.AttendanceDTO;
import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StudentMapper {
    @Autowired
    AttendanceMapper attendanceMapper;
    public StudentDTO toStudentDTO (StudentEntity student){
        StudentDTO tmp = new StudentDTO();
        tmp.setId(student.getId());
        tmp.setFullName(student.getFullName());
        tmp.setMSSV(student.getMssv());
        Set<AttendanceDTO> listAttendanceD = new HashSet<>();
        Set<AttendanceEntity> listAttendanceE =  student.getAttendances();
        listAttendanceE.forEach(item -> {
            AttendanceDTO attendanceDTO =  attendanceMapper.toDTO(item);
            listAttendanceD.add(attendanceDTO);
        });
        tmp.setAttendances(listAttendanceD);
        return tmp;
    }

    public  StudentEntity toStudentEntity (StudentDTO dto){
        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setMssv(dto.getMSSV());
        Set<AttendanceEntity> listAttendanceE =  new HashSet<>();
        Set<AttendanceDTO> listAttendanceD = dto.getAttendances();

        listAttendanceD.forEach(item -> {
            AttendanceEntity attendanceEntity =  attendanceMapper.toEntity(item);
            listAttendanceE.add(attendanceEntity);
        });
        entity.setAttendances(listAttendanceE);
        return entity;
    }

    public StudentEntity toStudentEntity(StudentDTO dto, StudentEntity entity) {
        entity.setMssv(dto.getMSSV());
        entity.setFullName(dto.getFullName());

        return entity;
    }

}
