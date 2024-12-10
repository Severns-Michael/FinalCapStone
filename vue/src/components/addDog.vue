
<template>
  <div class="form">
    <form v-on:submit.prevent="addDog()">
      <label for="breedName">Dog Name: </label>
      <input id="breedName" type="text" v-model="this.newDog.dogName"/>
      <button type="submit" class="btn btn-light">Submit</button>
    </form>
  </div>
</template>


<script>
import dogService from "../services/DogService";
export default {
  data() {
    return {
      newDog: {
        dogName: '',
        breedId: '',
        age: '',
        gender: '',
        agencyId: '',
        img: ''
      }
    }
  },
  methods: {
    addDog() {
      dogService.createDog(this.newDog).then(response => {
        if (response.status === 201) {
          this.newDog = {
            dogName: '',
            breedId: '',
            age: '',
            gender: '',
            agencyId: '',
            img: ''
          };
        }
      })
          .catch(error => {
            console.log(error);
          });
    }
  }
}
</script>



<style scoped>
form {
  display: flex;
}
label {
  margin: 10px;
}

</style>