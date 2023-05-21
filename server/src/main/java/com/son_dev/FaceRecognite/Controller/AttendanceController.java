package com.son_dev.FaceRecognite.Controller;

import com.son_dev.FaceRecognite.Model.ResponseObj.ResponseObject;
import com.son_dev.FaceRecognite.Repositories.StudentRepositories;
import com.son_dev.FaceRecognite.Services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/Attendance")
public class AttendanceController {


    @Autowired
    private IStudentService studentService;

    @PostMapping
    public ResponseEntity<ResponseObject> AddStudent () {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("oke", "Query students successfully", studentService.findAll())
        );
    }
}
