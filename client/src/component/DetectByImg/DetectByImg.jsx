import { useEffect, useRef, useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import moment from 'moment';
import 'react-toastify/dist/ReactToastify.css';
import * as faceapi from 'face-api.js';
import './DetectByImg.css';
import attendanceApi from '../../api/attendanceApi';

export default function DetectByImg() {
  const inputElement = useRef();
  const containerE = useRef();
  let [faceMatcher, setFaceMatcher] = useState({});
  // const [currentDay, setCurrentDay] = useState('');
  let [studentAttendance, setStudentAttendance] = useState([]);
  var date = moment();

  const initTrainingData = async () => {
    const labels = [
      'Fukada Eimi - 20229038',
      'Rina Ishihara - 20229018',
      'Takizawa Laura - 20229037',
      'Yua Mikami - 20229036',
    ];

    const faceDescriptors = [];
    for (const label of labels) {
      const descriptors = [];
      for (let i = 1; i <= 4; i++) {
        const img = await faceapi.fetchImage(`/data/${label}/${i}.jpeg`);
        const detection = await faceapi
          .detectSingleFace(img)
          .withFaceLandmarks()
          .withFaceDescriptor();
        descriptors.push(detection.descriptor);
      }
      const notify = () => toast('Tranning data successfully !!!');
      notify();
      faceDescriptors.push(
        new faceapi.LabeledFaceDescriptors(label, descriptors)
      );
    }
    return faceDescriptors;
  };

  const init = async () => {
    await Promise.all([
      faceapi.nets.ssdMobilenetv1.loadFromUri('/models'),
      faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
      faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
    ]);
    const notify = () => toast('Loading modals successfully !!!');
    notify();
    const trainingData = await initTrainingData();
    setFaceMatcher(new faceapi.FaceMatcher(trainingData, 0.6));
  };
  useEffect(() => {
    init();
  }, []);

  const selectImg = async () => {
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
      console.log(detection);
      const box = detection.detection.box;
      const drawBox = new faceapi.draw.DrawBox(box, {
        label: faceMatcher.findBestMatch(detection.descriptor),
      });
      drawBox.draw(canvas);
      var currentDate = date.format('YYYY-MM-DD');
      console.log('day', currentDate);
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
      <ToastContainer />
    </div>
  );
}
