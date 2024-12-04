<template>
    <div class="admin">
        <label>Add a Breed</label>
        <add-breed class="component"/>
        <Label>Edit a Breed</Label>
        <edit-breed class="component"/>
        <label>Delete a Breed</label>
        <delete-breed class="component"/>
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
    .component {
        margin-bottom: 40px;
        border: 1px solid black;
        padding: 10px;
    }

</style>