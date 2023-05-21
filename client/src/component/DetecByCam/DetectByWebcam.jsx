import * as faceapi from 'face-api.js';
import './DetectByCam.css';
import { useEffect, useRef, useState } from 'react';
import { toast } from 'react-toastify';

export default function DetectByWebcam() {
  const videoRef = useRef();
  const containerRef = useRef();
  let [faceMatcher, setFaceMatcher] = useState({});

  // LOAD FROM USEEFFECT
  useEffect(() => {
    startVideo();
    videoRef && init();
  }, []);

  // OPEN YOU FACE WEBCAM
  const startVideo = () => {
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then((currentStream) => {
        videoRef.current.srcObject = currentStream;
      })
      .catch((err) => {
        console.log(err);
      });
  };
  // LOAD MODELS FROM FACE API

  // const loadModels = () => {
  //   Promise.all([
  //     // THIS FOR FACE DETECT AND LOAD FROM YOU PUBLIC/MODELS DIRECTORY
  //     faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
  //     faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
  //     faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
  //     faceapi.nets.faceExpressionNet.loadFromUri('/models'),
  //   ]).then(() => {
  //     faceMyDetect();
  //   });
  // };

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
      faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
      faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
      faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
      faceapi.nets.faceExpressionNet.loadFromUri('/models'),
    ]);
    const notify = () => toast('Loading modals successfully !!!');
    notify();
    const trainingData = await initTrainingData();
    setFaceMatcher(new faceapi.FaceMatcher(trainingData, 0.6));
  };

  const faceDetect = () => {
    const video = videoRef.current;
    const canvas = faceapi.createCanvasFromMedia(video);
    containerRef.current.append(canvas);

    const displaySize = {
      width: video.videoWidth,
      height: video.videoHeight,
    };

    setInterval(async () => {
      const detects = await faceapi.detectAllFaces(
        video,
        new faceapi.TinyFaceDetectorOptions()
      );

      const resizeDetections = faceapi.resizeResults(detects, displaySize);
      canvas
        .getContext('2d')
        .clearRect(0, 0, displaySize.width, displaySize.height);
      faceapi.draw.drawDetections(canvas, resizeDetections);

      // const student_list = [];

      for (const detection of resizeDetections) {
        const box = detection.detection.box;
        const drawBox = new faceapi.draw.DrawBox(box, {
          label: faceMatcher.findBestMatch(detection.descriptor),
        });
        drawBox.draw(canvas);
        // var currentDate = date.format('YYYY-MM-DD');
        // console.log('day', currentDate);
        // const studentDetected = drawBox.options.label._label;
        // const arr = studentDetected.split(' - ');
        // student_list.push({
        //   fullName: arr[0],
        //   mssv: arr[1],
        //   attendances: [{ attendanceDay: currentDate }],
        // });
        // setStudentAttendance(student_list);
      }
    }, 3000);
  };
  return (
    <div className="myapp">
      <h1>FAce Detection</h1>
      <div className="container-cam" ref={containerRef}>
        <div className="appvide">
          <video
            crossOrigin="anonymous"
            ref={videoRef}
            onPlaying={faceDetect}
            autoPlay
          ></video>
        </div>
      </div>
    </div>
  );
}
