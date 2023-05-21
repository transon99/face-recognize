import axiosClient from "./axiosClient";

const attendanceApi = {

    AttendanceAll: (list_student123) => {
        const url = `/attendanceStudent`;
        return axiosClient.put(url, list_student123);
    },

    getAttendanceList: () => {
        const url = ``;
        return axiosClient.get(url);
    }
};
export default attendanceApi;