<template>
    <div class="container" id="admin-page">

      <div class="admin-block" id="add">
        <h2>Add a Breed</h2>
        <add-breed />
      </div>

      <div class="admin-block" id="edit">
        <h2>Edit a Breed</h2>
        <edit-breed />
      </div>

      <div class="admin-block" id="delete">
        <h2>Delete a Breed</h2>
        <delete-breed />
      </div>

    </div>
</template>

<script>
import AddBreed from '../components/AddBreed.vue';
import EditBreed from '../components/EditBreed.vue';
import DeleteBreed from '../components/DeleteBreed.vue';

export default {
    components: {
        AddBreed,
        EditBreed,
        DeleteBreed
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