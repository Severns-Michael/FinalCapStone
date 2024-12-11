import axios from 'axios';
export default {
    promote(user) {
        return axios.post('/promote', {
            id: user.id,
            username: user.username,
        });
    },

    demote(user) {
        return axios.post('/demote', {
            id: user.id,
            username: user.username,
        });
    },
getUsers() {
    return axios.get('/users')
},

    getUserById(id) {
    return axios.get(`/users/${id}`)
    },
}