import { useEffect, useState } from 'react';
import { BsCheckSquareFill } from 'react-icons/bs';
import AttendanceAll from '../../api/attendanceApi';
import './AttendanceList.css';

export default function AttendanceList() {
  const [studentList, setStudentList] = useState([]);
  console.log(studentList);
  const listDay = ['19/05/2023'];
  // const listStudent = [
  //   {
  //     name: 'Son',
  //     mssv: '20229038',
  //     diemDanh: ['11/5/2023', '12/5/2023', '13/5/2023'],
  //   },
  //   {
  //     name: 'Chung',
  //     mssv: '20229038',
  //     diemDanh: ['11/5/2023', '12/5/2023', '13/5/2023', '14/5/2023'],
  //   },
  //   {
  //     name: 'Huy',
  //     mssv: '20229038',
  //     diemDanh: ['11/5/2023', '', '13/5/2023'],
  //   },
  //   {
  //     name: 'Duy',
  //     mssv: '20229038',
  //     diemDanh: ['11/5/2023', '12/5/2023', '13/5/2023'],
  //   },
  // ];

  useEffect(() => {
    const fetchProductList = async () => {
      try {
        const response = await AttendanceAll.getAttendanceList();
        console.log('Fetch products successfully: ', response);
        setStudentList(response.data);
      } catch (error) {
        console.log('Failed to fetch student list: ', error);
      }
    };
    fetchProductList();
  }, []);

  return (
    <table className="styled-table">
      <thead>
        <tr>
          <th>Name</th>
          {listDay.map((day, i) => (
            <th key={i}>{day}</th>
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
  );
}
