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
    updateBreed(breed, currentTraits){
        return axios.put(`/breeds/${breed.breedId}`, breed, currentTraits)
    },
    deleteBreed(id) {
        return axios.delete(`/breeds/${id}`)
    },
}