package com.son_dev.FaceRecognite.Model.Mapper;

import com.son_dev.FaceRecognite.Entity.AttendanceDateEntity;
import com.son_dev.FaceRecognite.Entity.StudentEntity;
import com.son_dev.FaceRecognite.Model.DTO.AttendanceDateDTO;
import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class AttendanceDateMapper {
    public AttendanceDateDTO toDTO (AttendanceDateEntity dateEntity){
        AttendanceDateDTO attendanceDateDTO = new AttendanceDateDTO();
        attendanceDateDTO.setId(dateEntity.getId());
        attendanceDateDTO.setAttendanceDate(dateEntity.getAttendanceDate());
        return attendanceDateDTO;
    }

    public  AttendanceDateEntity toEntity (AttendanceDateDTO dto){
        AttendanceDateEntity entity = new AttendanceDateEntity();
        entity.setId(dto.getId());
        entity.setAttendanceDate(dto.getAttendanceDate());
        return entity;
    }

    public AttendanceDateEntity toEntity(AttendanceDateDTO dto, AttendanceDateEntity entity) {
        entity.setId(dto.getId());
        entity.setAttendanceDate(dto.getAttendanceDate());
        return entity;
    }
}
