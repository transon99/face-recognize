import axiosClient from "./axiosClient";

const attendanceDateApi = {

    addAttendanceDate: (date) => {
        const url = `/attendance-date`;
        return axiosClient.post(url, date);
    },

    getAttendanceDay: () => {
        const url = `/attendance-date`;
        return axiosClient.get(url);
    }
};
export default attendanceDateApi;