import { useEffect, useRef, useState } from 'react';
import { BsCheckSquareFill } from 'react-icons/bs';
import { toast } from 'react-toastify';

import './AttendanceList.css';
import moment from 'moment';
import attendanceDateApi from '../../api/attendanceDateApi';
import attendanceApi from '../../api/attendanceApi';

export default function AttendanceList() {
  const [studentList, setStudentList] = useState([]);
  const [date, setDate] = useState('');
  const [listDate, setListDate] = useState([]);

  const inputElement = useRef();

  useEffect(() => {
    const fetchStudentList = async () => {
      try {
        const response = await attendanceApi.getAttendanceList();
        console.log('Fetch products successfully: ', response);
        setStudentList(response.data);
      } catch (error) {
        console.log('Failed to data student list: ', error);
      }
    };
    fetchStudentList();
  }, []);

  useEffect(() => {
    const fetchDateList = async () => {
      try {
        const response = await attendanceDateApi.getAttendanceDay();
        console.log('Fetch data successfully: ', response);
        setListDate(response.data);
      } catch (error) {
        console.log('Failed to fetch date list: ', error);
      }
    };
    fetchDateList();
  }, []);

  const selectDate = () => {
    setDate(moment(inputElement.current.value).format('DD-MM-YYYY'));
  };

  const addDate = async () => {
    console.log(date);
    const payload = {
      attendanceDate: date,
    };
    try {
      const response = await attendanceDateApi.addAttendanceDate(payload);
      console.log('Fetch products successfully: ', response);
      const notify = () =>
        toast(`Đã thêm ngày ${payload.attendanceDate} thành công`);
      notify();
    } catch (error) {
      console.log('Failed to Attendance: ', error);
    }
  };

  console.log(listDate);

  return (
    <div className="container-dd">
      <div className="date">
        <input
          ref={inputElement}
          type="date"
          name="date"
          id="date"
          onChange={selectDate}
        />
        <button className="add-date" onClick={addDate}>
          Thêm ngày
        </button>
      </div>
      <table className="styled-table">
        <thead>
          <tr>
            <th>Name</th>
            {listDate.map((day, i) => (
              <th key={i}>{day.attendanceDate}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {studentList.map((student, i) => (
            <tr key={i}>
              <td>{student.fullName}</td>
              {student.attendances.map((day, i) => (
                <td key={i}>{day ? <BsCheckSquareFill /> : <></>}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
