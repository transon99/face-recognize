package com.son_dev.FaceRecognite.Services;

import com.son_dev.FaceRecognite.Model.DTO.AttendanceDateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAttendaceDateService {
    List<AttendanceDateDTO> findAll();

    String addDate(AttendanceDateDTO date);

    String deleteDate(Long id);

    AttendanceDateDTO updateDate(AttendanceDateDTO attendanceDateDTO);
}
