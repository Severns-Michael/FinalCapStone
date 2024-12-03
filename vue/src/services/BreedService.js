import axios from 'axios';
export default {
    getBreeds(){
        return axios.get('/breeds')
    },
    getBreedById(id){
        return axios.get(`/breeds/${id}`)
    },
    createBreed(breed){
        return axios.post('/breeds', breed)
    },
    updateBreed(breed){
        return axios.put(`/breeds/${breed.id}`, breed)
    },
    deleteBreed(id) {
        return axios.delete(`/breeds/${id}`)
    },
}