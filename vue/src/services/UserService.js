import axios from 'axios';
export default {
promote(user) {
    return axios.post('/promote', user)
},

demote(user) {
    return axios.post('/demote', user)
},

getUsers() {
    return axios.get('/users')
},

    getUserById(id) {
    return axios.get(`/users/${id}`)
    },
}