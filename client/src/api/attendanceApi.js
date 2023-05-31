import axiosClient from "./axiosClient";

const attendanceApi = {

    AttendanceAll: (list_student123) => {
        const url = `/students/attendanceStudent`;
        return axiosClient.put(url, list_student123);
    },

    getAttendanceList: () => {
        const url = `/students`;
        return axiosClient.get(url);
    }
};
export default attendanceApi;