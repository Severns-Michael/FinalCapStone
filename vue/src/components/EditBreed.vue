<template>
    <form> 
        <div class="breed">
            <label>Breed: </label>
            <select>
                <option v-for="breed in breeds" v-bind:key="breed.id">
                    {{ breed.name }}
                </option>
            </select>
        </div>
        <div class="traits">
          <p class = "title">current traits</p>
          <p class = "title">all traits</p>
            <div class="listbox">

              <ul>
                <li>trait 1</li>
                <li>trait 2</li>
                <li>trait 3</li>
                <li v-for="trait in traits" v-bind:key="trait.id" ></li> </ul>
                <!-- need to make this out of just traits for this breed -->
            </div>
        
            <div class="listbox">
                <ul>
                  <li>trait 1</li>
                  <li>trait 2</li>
                  <li>trait 3</li>
                <li v-for="trait in traits" v-bind:key="trait.id" ></li> </ul>
            </div>
        </div>
    </form>

</template>

<script>

export default {
    
}

</script>

<style scoped>
    form {
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
      width: 70%;

        
    }
    .listbox {
      justify-content: space-between;
      display: flex;
      flex-direction: row;
      flex-wrap: nowrap;
      margin: 5px;

    }
    label {
        margin: 10px;
    }
    .breed {
       flex-basis: 100%;
      text-align: center;
    }
    .traits {
      flex-basis: 50%;
      justify-content: space-evenly;
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
    }
    .title{
      text-decoration: underline;
      flex-basis: 50%;
      text-align: center;
    }
    ul{
      border: black solid 1px;
      display: flex;
      flex-direction: column;
      order: 6;
      height: 100px;
      list-style: none;
    }
    li{
      flex-basis: 80%;

    }

</style>