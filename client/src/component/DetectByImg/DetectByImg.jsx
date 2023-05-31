import { useRef, useState } from 'react';
import moment from 'moment';
import 'react-toastify/dist/ReactToastify.css';
import * as faceapi from 'face-api.js';
import './DetectByImg.css';
import attendanceApi from '../../api/attendanceApi';
import { ToastContainer } from 'react-toastify';
import { toast } from 'react-toastify';

export default function DetectByImg(labeledFaceDescriptors) {
  const inputElement = useRef();
  const containerE = useRef();
  let [studentAttendance, setStudentAttendance] = useState([]);
  var date = moment();

  const selectImg = async () => {
    const faceMatcher = new faceapi.FaceMatcher(
      labeledFaceDescriptors.labeledFaceDescriptors,
      0.5
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
      var currentDate = date.format('DD-MM-YYYY');
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
    for (const student of studentAttendance) {
      try {
        console.log(student);

        if (student.fullName !== 'unknown') {
          const response = await attendanceApi.AttendanceAll(student);
          console.log('Fetch products successfully: ', response);
          const notify = () => toast(`Đã điểm danh ${student.fullName}`);
          notify();
        }
      } catch (error) {
        console.log('Failed to Attendance: ', error);
      }
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
      <ToastContainer />
    </div>
  );
}
