package com.son_dev.FaceRecognite.Services.impl;

import com.son_dev.FaceRecognite.Entity.AttendanceDateEntity;
import com.son_dev.FaceRecognite.Model.DTO.AttendanceDateDTO;
import com.son_dev.FaceRecognite.Model.Mapper.AttendanceDateMapper;
import com.son_dev.FaceRecognite.Repositories.AttendanceDateRepositories;
import com.son_dev.FaceRecognite.Services.IAttendaceDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AttendaceServiceImpl implements IAttendaceDateService {
    @Autowired
    AttendanceDateMapper attendanceDateMapper;

    @Autowired
    AttendanceDateRepositories attendanceDateRepositories;
    @Override
    public List<AttendanceDateDTO> findAll() {
        List<AttendanceDateDTO> result = new ArrayList<>();
        List<AttendanceDateEntity> attendanceDateEntities = attendanceDateRepositories.findAll();
        attendanceDateEntities.forEach((item -> {
            AttendanceDateDTO attendanceDateDTO = attendanceDateMapper.toDTO(item);
            result.add(attendanceDateDTO);
        }));
        return result;
    }

    @Override
    public String addDate(AttendanceDateDTO dateDTO) {
        AttendanceDateEntity attendanceDateEntity = attendanceDateMapper.toEntity(dateDTO);
        attendanceDateRepositories.save(attendanceDateEntity);

        return "Add a date successfully!!!";
    }

    @Override
    public String deleteDate(Long id) {
        attendanceDateRepositories.deleteById(id);
        return "Delete a date succsessfully !!!";
    }

    @Override
    public AttendanceDateDTO updateDate(AttendanceDateDTO attendanceDateDTO) {
        //Tìm attendanceDateEntity cũ
        AttendanceDateEntity oldAttendanceDateEntity = attendanceDateRepositories.findById(attendanceDateDTO.getId()).get();
        AttendanceDateEntity attendanceDateEntity = attendanceDateMapper.toEntity(attendanceDateDTO,oldAttendanceDateEntity);
        attendanceDateEntity = attendanceDateRepositories.save(attendanceDateEntity);
        AttendanceDateDTO result = attendanceDateMapper.toDTO(attendanceDateEntity);
        return result;
    }
}
