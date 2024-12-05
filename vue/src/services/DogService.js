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

    // user accepted and rejected dog methods

    getSwipedBreeds() {
        return axios.get("/swiped")
    },
    addToSwipedBreeds() {
        return axios.post("/swiped")
    },

    // calls to pic api

    getBreedPic(breedName) {
        return http.get(`/${breedName}/images/random`)
    },
    getSubBreedPic(breedName, subBreed) {
        return http.get(`/${breedName}/${subBreed}/images/random`)
    }
}

