package com.son_dev.FaceRecognite.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;

    private String fullName;

    private String MSSV;

    private Set<AttendanceDTO> attendances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDTO that)) return false;
        return Objects.equals(fullName, that.fullName) && Objects.equals(MSSV, that.MSSV) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash( fullName, MSSV);
    }
}
