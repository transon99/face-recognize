import { useRef, useState } from 'react';
import moment from 'moment';
import 'react-toastify/dist/ReactToastify.css';
import * as faceapi from 'face-api.js';
import './DetectByImg.css';
import attendanceApi from '../../api/attendanceApi';

export default function DetectByImg(labeledFaceDescriptors) {
  const inputElement = useRef();
  const containerE = useRef();
  let [studentAttendance, setStudentAttendance] = useState([]);
  var date = moment();

  const selectImg = async () => {
    const faceMatcher = new faceapi.FaceMatcher(
      labeledFaceDescriptors.labeledFaceDescriptors
    );
    const imgFile = inputElement.current.files[0];

    const img = await faceapi.bufferToImage(imgFile);
    const canvas = faceapi.createCanvasFromMedia(img);

    containerE.current.innerHTML = '';
    containerE.current.append(img);
    containerE.current.append(canvas);

    const size = {
      width: img.width,
      height: img.height,
    };
    faceapi.matchDimensions(canvas, size);

    const detections = await faceapi
      .detectAllFaces(img)
      .withFaceLandmarks()
      .withFaceDescriptors();
    const resizeDetections = faceapi.resizeResults(detections, size);
    const student_list = [];

    for (const detection of resizeDetections) {
      const box = detection.detection.box;
      const drawBox = new faceapi.draw.DrawBox(box, {
        label: faceMatcher.findBestMatch(detection.descriptor),
      });
      drawBox.draw(canvas);
      var currentDate = date.format('YYYY-MM-DD');
      const studentDetected = drawBox.options.label._label;
      const arr = studentDetected.split(' - ');
      student_list.push({
        fullName: arr[0],
        mssv: arr[1],
        attendances: [{ attendanceDay: currentDate }],
      });
      setStudentAttendance(student_list);
    }
  };

  const Attendance = async () => {
    try {
      for (const student of studentAttendance) {
        console.log(student);
        const response = await attendanceApi.AttendanceAll(student);

        console.log('Fetch products successfully: ', response);
      }
    } catch (error) {
      console.log('Failed to Attendance: ', error);
    }
  };

  return (
    <div>
      <input
        type="file"
        className="file-input"
        onChange={selectImg}
        ref={inputElement}
      />
      <div className="container" ref={containerE}></div>
      <div className="btn_dd" onClick={Attendance}>
        Điểm danh
      </div>
    </div>
  );
}
