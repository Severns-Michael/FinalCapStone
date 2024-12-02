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

            <div class="listbox">
                <p>current traits</p>
                <p v-for="trait in traits" v-bind:key="trait.id"> {{ trait.name }} </p>
                <!-- need to make this out of just traits for this breed -->
            </div>
        
            <div class="listbox">
                <p>all traits</p>
                <p v-for="trait in traits" v-bind:key="trait.id"> {{ trait.name }} </p>
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
        flex-direction: row;
        flex-wrap: wrap;
        
    }
    .listBox {
        border: solid 1px black;
    }
    label {
        margin: 10px;
    }
    #breed {
       flex-basis: 100%;
    }
    #traits {
        flex-basis: 100%;
        
    }
</style>