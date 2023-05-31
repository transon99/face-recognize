package com.son_dev.FaceRecognite.Controller;

import com.son_dev.FaceRecognite.Model.DTO.StudentDTO;
import com.son_dev.FaceRecognite.Model.ResponseObj.ResponseObject;
import com.son_dev.FaceRecognite.Services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students")
@CrossOrigin(origins = "http://localhost:5173/")
public class StudentController {

    @Autowired
    IStudentService studentService;
    @GetMapping()
    ResponseEntity<ResponseObject> getStudent(){
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke","Query students successfully",studentService.findAll()));
    };

    @PostMapping()
    ResponseEntity<ResponseObject> addStudent(@RequestBody StudentDTO student){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke","Query students successfully",studentService.attendance(student)));
    };

    @PutMapping("/attendanceStudent")
    ResponseEntity<ResponseObject> attendanceStudent(@RequestBody StudentDTO student){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke","Query students successfully",studentService.attendance(student)));
    };

}
