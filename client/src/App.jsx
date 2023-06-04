import './App.css';
import { Routes, Route, NavLink } from 'react-router-dom';
import DetectByImg from './component/DetectByImg/DetectByImg';
import AttendanceList from './component/AttendanceList/AttendanceList';
import DetectByWebcam from './component/DetecByCam/DetectByWebcam';
import { ToastContainer } from 'react-toastify';
import { toast } from 'react-toastify';

import * as faceapi from 'face-api.js';
import { useEffect, useState } from 'react';
function App() {
  const [faceDescriptors, setFaceDescriptors] = useState([]);
  useEffect(() => {
    async function fetchData() {
      const faceDescriptors = await init();
      setFaceDescriptors(faceDescriptors);
    }
    fetchData();
  }, []);

  const init = async () => {
    await Promise.all([
      faceapi.nets.ssdMobilenetv1.loadFromUri('/models'),
      faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
      faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
    ]);
    const notify = () => toast('Loading modals successfully !!!');
    notify();
    const labels = [
      'Trần Thanh Sơn - 20229038',
      'Dương Quang Huy - 20229018',
      'Lại Thế Chung - 20229020',
      'Nguyễn Đình Duy - 20229036',
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

  return (
    <div className="container">
      <h1 className="header">ỨNG DỤNG NHẬN DIỆN KHUÔN MẶT VÀ ĐIỂM DANH</h1>
      <nav className="container-n">
        <ul className="list">
          <li className="item">
            <NavLink to="/byimg">Điểm danh bằng ảnh</NavLink>
          </li>
          <li className="item">
            <NavLink to="/bywebcam">Điểm danh bằng webcam</NavLink>
          </li>
          <li className="item">
            <NavLink to="/dsdiemdanh">Danh sách điểm danh</NavLink>
          </li>
        </ul>
        <img
          src="https://images.ctfassets.net/3viuren4us1n/5YzA7KGIWQEjt8KStZGlxd/85bde9966a9e9c4407396f424e46fc67/facial_recognition.jpg?fm=webp&w=828"
          alt=""
        />
      </nav>
      <ToastContainer />

      <Routes>
        <Route
          path="/byimg"
          element={<DetectByImg labeledFaceDescriptors={faceDescriptors} />}
        ></Route>
        <Route
          path="/bywebcam"
          element={<DetectByWebcam labeledFaceDescriptors={faceDescriptors} />}
        ></Route>
        <Route path="/dsdiemdanh" element={<AttendanceList />}></Route>
      </Routes>
    </div>
  );
}

export default App;
