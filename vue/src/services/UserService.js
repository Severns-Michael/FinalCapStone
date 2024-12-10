import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});
export default {
promote(user) {
    return http.post('/promote', user)
},

demote(user) {
    return http.post('/demote', user)
},

getUsers() {
    return http.get('/users')
},

    getUserById(id) {
    return http.get(`/users/${id}`)
    },
}