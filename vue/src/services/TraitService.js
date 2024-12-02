import axios from "axios";
export default {
    getTraits(){
        return axios.get("/traits")
    },
    getTraitById(id){
        return axios.get(`/traits/${id}`)
    },
}