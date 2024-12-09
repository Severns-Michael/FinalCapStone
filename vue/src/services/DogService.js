import axios from 'axios';

const http = axios.create({
    baseURL: "https://dog.ceo/api/breed"
  });

export default {
    getAllDogs() {
        return axios.get("/dogs")
    },
    getDogById(id) {
        return axios.get(`/dogs/${id}`)
    },
    updateDog() {
        return axios.put("/dogs")
    },
    getRandomDog() {
        return axios.get("/dogs/random")
    },
    createDog(dog){
        return axios.post("/dogs", dog)
    },
    deleteDog(id) {
        return axios.delete(`/dogs/${id}`)
    },

    // user accepted and rejected dog methods

    getSwipedBreeds() {
        return axios.get("/swiped")
    },
    addToSwipedBreeds(swipedBreed) {
        return axios.post("/swiped", swipedBreed)
    },

    // calls to pic api

    getBreedPic(breedName) {
        return http.get(`/${breedName}/images/random`)
    },
    getSubBreedPic(breedName, subBreed) {
        return http.get(`/${breedName}/${subBreed}/images/random`)
    },


    
}

