<template>
    <div class="admin">
        <add-breed class="component"/>
        <edit-breed class="component"/>
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
    }
    .component {
        margin-top: 20px;
        margin-bottom: 20px;
        border: 1px solid black;
        padding: 10px;
    }

</style>