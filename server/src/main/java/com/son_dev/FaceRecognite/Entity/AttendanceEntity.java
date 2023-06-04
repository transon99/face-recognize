package com.son_dev.FaceRecognite.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "attendance_day")
    private String attendanceDay;

    @ManyToMany(mappedBy = "attendances", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<StudentEntity> students;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceEntity that)) return false;
        return Objects.equals(attendanceDay, that.attendanceDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash( attendanceDay);
    }
}
