import axios from 'axios';
export default {
    getYesTraits(userId) {
        return axios.get(`/traits/${userId}/include`)
    },
    getNoTraits(userId) {
        return axios.get(`/traits/${userId}/exclude`)
    },
    updateYesTraits(userId) {
        return axios.put(`/traits/${userId}/include`)
    },
    updateNoTraits(userId) {
        return axios.put(`/traits/${userId}/exclude`)
    }
}