package com.son_dev.FaceRecognite.Model.DTO;

import com.son_dev.FaceRecognite.Entity.AttendanceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;

    private String fullName;

    private String MSSV;

    private Set<AttendanceEntity> attendances;
}
