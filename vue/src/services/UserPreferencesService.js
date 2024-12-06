import axios from 'axios';
export default {
    getYesTraits() {
        return axios.get(`/traits/include`)
    },
    getNoTraits() {
        return axios.get(`/traits/exclude`)
    },
    updateYesTraits(wantedTraits) {
        return axios.put(`/traits/include`, wantedTraits)
    },
    updateNoTraits(unwantedTraits) {
        return axios.put(`/traits/exclude`, unwantedTraits)
    }
}