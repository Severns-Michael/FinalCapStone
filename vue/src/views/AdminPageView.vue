<template>
<!--  <ul class="nav">-->
<!--    <li class="badge badge-pill badge-light">-->
<!--      <a class="badge badge-pill badge-light" aria-current="page" href="#">Breeds</a>-->
<!--      <div class="container" id="admin-page"></div>-->
<!--    </li>-->
<!--    <li class="nav-item">-->
<!--      <a class="nav-link" href="#">Dogs</a>-->
<!--      <div class="container" id="dog-page"></div>-->
<!--    </li>-->
<!--    <li class="nav-item">-->
<!--      <a class="nav-link" href="#">Users</a>-->
<!--    </li>-->
<!--    <li class="nav-item">-->
<!--      <a class="nav-link disabled">Disabled</a>-->
<!--    </li>-->
<!--  </ul>-->
  <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
    <li class="nav-item btn btn-light" role="presentation">
      <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-breeds" type="button" role="tab" aria-controls="pills-breeds" aria-selected="true">Manage Breeds</button>
    </li>
    <li class="nav-item btn btn-light" role="presentation">
      <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-dogs" type="button" role="tab" aria-controls="pills-dogs" aria-selected="false">Manage Dogs</button>
    </li>
    <li class="nav-item btn btn-light" role="presentation">
      <button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-users" type="button" role="tab" aria-controls="pills-users" aria-selected="false">Manage Users</button>
    </li>

  </ul>
  <div class="tab-content" id="pills-tabContent">

<!--  MANAGE BREEDS  -->
    <div class="tab-pane fade show active" id="pills-breeds" role="tabpanel" aria-labelledby="pills-home-tab" tabindex="0">
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


<!--  MANAGE DOGS  -->
<div
  class="tab-pane fade"
  id="pills-dogs"
  role="tabpanel"
  aria-labelledby="pills-profile-tab"
  tabindex="0"
>
  <div class="fh-box admin-box">
    <div class="dog-management-container">
      <!-- Add a Dog -->
      <div class="dog-management-section">
        <h3>Add a Dog</h3>
        <add-dog />
      </div>

      <!-- Edit a Dog -->
      <div class="dog-management-section">
        <h3>Edit a Dog</h3>
        <edit-dog />
      </div>

      <!-- Delete a Dog -->
      <div class="dog-management-section">
        <h3>Delete a Dog</h3>
        <delete-dog />
      </div>
    </div>
  </div>
</div>

<!--  MANAGE USERS  -->
    <div class="tab-pane fade" id="pills-users" role="tabpanel" aria-labelledby="pills-contact-tab" tabindex="0">

    </div>

  </div>





</template>

<script>
import AddBreed from '../components/AddBreed.vue';
import EditBreed from '../components/EditBreed.vue';
import DeleteBreed from '../components/DeleteBreed.vue';
import addDog from "../components/addDog.vue";
import EditDog from '../components/EditDog.vue';
import DeleteDog from '../components/DeleteDog.vue';


export default {
    components: {
        AddBreed,
        EditBreed,
        DeleteBreed,
        addDog,
        EditDog,
        DeleteDog,
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
    .form-container {
  display: flex;
  justify-content: space-between; 
  align-items: flex-start; 
  gap: 20px; 
  width: 100%;
  margin-top: 20px;
}


.form-column {
  flex: 1;
}

.middle-column {
  flex: 1.5; 
}


.admin-box {
  display: flex;
  flex-direction: column;
  align-items: center;
}


.admin-box {
  min-height: 300px; 
}

.fh-box.admin-box {
  background-color: var(--purp5); 
  border: 1px solid var(--purp3);
  border-radius: 10px;
  padding: 10px;
  text-align: center;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
  color: white;
}


.dog-management-container {
  display: flex;
  flex-direction: row; 
  align-items: flex-start; 
  gap: 40px; 
}


.dog-management-section {
  flex: 1;
  text-align: center;
}

/* Section headers */
.dog-management-section h3 {
  font-size: 1.2em;
  margin-bottom: 20px;
  color: white;
}
    

</style>