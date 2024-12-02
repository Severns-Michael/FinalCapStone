<template>
    <div>
        <div>
            <label>Breed: </label>
            <select>
                <option v-for="breed in breeds" v-bind:key="breed.id">
                    {{ breed.name }}
                </option>
            </select>
        </div>
        <button v-on:click="this.deleteBreed()">Delete Breed</button>
    </div>
</template>

<script>
export default {

    data() {
        
    },
    
    methods: {
        deleteBreed() {

        }
    }
}

</script>

<style scoped>
    label {
        margin: 10px;
    }
</style>