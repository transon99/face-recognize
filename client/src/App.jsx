import './App.css';
import { Routes, Route, NavLink } from 'react-router-dom';
import DetectByImg from './component/DetectByImg/DetectByImg';
import AttendanceList from './component/AttendanceList/AttendanceList';
import DetectByWebcam from './component/DetecByCam/DetectByWebcam';
function App() {
  return (
    <div className="container">
      <h1 className="header">FACE DETECTION</h1>
      <nav>
        <ul className="list">
          <li className="item">
            <NavLink to="/byimg">Detect by Img</NavLink>
          </li>
          <li className="item">
            <NavLink to="/bywebcam">Detect by Webcam</NavLink>
          </li>
          <li className="item">
            <NavLink to="/dsdiemdanh">Danh sách điểm danh</NavLink>
          </li>
        </ul>
      </nav>

      <Routes>
        <Route path="/byimg" element={<DetectByImg />}></Route>
        <Route path="/bywebcam" element={<DetectByWebcam />}></Route>
        <Route path="/dsdiemdanh" element={<AttendanceList />}></Route>
      </Routes>
    </div>
  );
}

export default App;
