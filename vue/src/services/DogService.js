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

    getDogsNo() {
        return axios.get("/userdogsno")
    },
    getDogsYes() {
        return axios.get("/userdogsyes")
    },
    addToDogsNo() {
        return axios.post("/userdogsno")
    },
    addToDogsYes() {
        return axios.post("/userdogsyes")
    },

    // calls to pic api

    getBreedPic(breedName) {
        return http.get(`/${breedName}/images/random`)
    },
    getSubBreedPic(breedName, subBreed) {
        return http.get(`/${breedName}/${subBreed}/images/random`)
    }
}

