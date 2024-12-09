<template>
  <ul class="nav">
    <li class="nav-item">
      <a class="nav-link active" aria-current="page" href="#">Breeds</a>
      <div class="container" id="admin-page"></div>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Dogs</a>
      <div class="container" id="dog-page"></div>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Users</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled">Disabled</a>
    </li>
  </ul>
  <div class="container" id="admin-page">

    <div class=" fh-box admin-box" id="add">
      <h2>Add a Breed</h2>
      <add-breed />
    </div>

    <div class="fh-box admin-box" id="edit">
      <h2>Edit a Breed</h2>
      <edit-breed />
    </div>

    <div class="fh-box admin-box" id="delete">
      <h2>Delete a Breed</h2>
      <delete-breed />
    </div>
  </div>

  <div class="container" id="dog-page"></div>

  <div class="fh-box admin-box" id="add">
    <h2>Add a Dog</h2>
    <add-dog/>
  </div>

  <div class="fh-box admin-box" id="add">
    <h2>Edit a Dog</h2>
    <edit-dog/>
  </div>

  <div class="container" id="user-page"></div>
  <div class="container" id="disabled-page"></div>




</template>

<script>
import AddBreed from '../components/AddBreed.vue';
import EditBreed from '../components/EditBreed.vue';
import DeleteBreed from '../components/DeleteBreed.vue';
import addDog from "@/components/addDog.vue";
import EditDog from '../components/EditDog.vue';


export default {
    components: {
        AddBreed,
        EditBreed,
        DeleteBreed,
        addDog,
        EditDog
    }
}

</script>

<style scoped>
    .admin {
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
        align-items: center;
        justify-content: space-evenly;
        margin-top: 40px;
    }

</style>