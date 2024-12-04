import axios from 'axios';
export default {
    getYesTraits(userId) {
        return axios.get(`/traits/${userId}/include`)
    },
    getNoTraits(userId) {
        return axios.get(`/traits/${userId}/exclude`)
    },
    updateYesTraits(userId, wantedTraits) {
        return axios.put(`/traits/${userId}/include`)
    },
    updateNoTraits(userId, unwantedTraits) {
        return axios.put(`/traits/${userId}/exclude`)
    }
}