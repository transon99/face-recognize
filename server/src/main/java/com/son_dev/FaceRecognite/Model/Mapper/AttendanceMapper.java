package com.son_dev.FaceRecognite.Model.Mapper;

import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import com.son_dev.FaceRecognite.Entity.StudentEntity;
import com.son_dev.FaceRecognite.Model.DTO.AttendanceDTO;
import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {
    public AttendanceDTO toDTO (AttendanceEntity entity){
        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(entity.getId());
        dto.setAttendanceDay(entity.getAttendanceDay());
        return dto;
    }

    public  AttendanceEntity toEntity (AttendanceDTO dto){
        AttendanceEntity entity = new AttendanceEntity();
        entity.setId(dto.getId());
        entity.setAttendanceDay(dto.getAttendanceDay());
        return entity;
    }

}
