package com.son_dev.FaceRecognite.Controller;

import com.son_dev.FaceRecognite.Model.DTO.AttendanceDateDTO;
import com.son_dev.FaceRecognite.Model.ResponseObj.ResponseObject;
import com.son_dev.FaceRecognite.Services.IAttendaceDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/attendance-date")
@CrossOrigin(origins = "http://localhost:5173/")
public class AttendanceDateController {

    @Autowired
    IAttendaceDateService attendaceDateService;
    @GetMapping()
    ResponseEntity<ResponseObject> getAllDate (){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke","Query date successfully",attendaceDateService.findAll()));
    }

    @PostMapping()
    ResponseEntity<ResponseObject> addDate (@RequestBody AttendanceDateDTO date){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke",attendaceDateService.addDate(date),""));
    }

    @PutMapping (value = "/{id}")
    ResponseEntity<ResponseObject> updateDate (@RequestBody AttendanceDateDTO attendanceDateDTO, @PathVariable Long id) {
        attendanceDateDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke","Update date successfully !!!",attendaceDateService.updateDate(attendanceDateDTO)));
    }

    @DeleteMapping (value = "/{id}")
    ResponseEntity<ResponseObject> deleteDate (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("oke",attendaceDateService.deleteDate(id),""));
    }
}
