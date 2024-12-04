<template>
  <div class="container">
    <trait-switch-box class="traitSwitcher"></trait-switch-box>
    <div class="sliders">
      <slider sliderType="Dog Height"></slider>
      <slider sliderType="Dog Weight"></slider>
      <age-slider></age-slider>
    </div>
    <span class="saveBtn"><button class="save" v-on:click.prevent="updateUserPreferences"> Save Changes </button></span>
  </div>
</template>

<script>
import Slider from "../components/Slider.vue"
import TraitSwitchBox from "@/components/TraitSwitchBox.vue";
import AgeSlider from "../components/AgeSlider.vue";
import UserPreferencesService from "../services/UserPreferencesService";

export default {
  components: {
    TraitSwitchBox,
    Slider,
    AgeSlider
  },
  methods: {
    updateUserPreferences() {
      UserPreferencesService.updateYesTraits(this.$store.state.currentUser.id);
      UserPreferencesService.updateNoTraits(this.$store.state.currentUser.id);
    }
  }
}

</script>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
  }
  .traitSwitcher {
    margin: 70px;
  }
  .sliders {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;

  }
  .saveBtn {
  flex-basis: 100%;
  flex-grow: 1;
  text-align: center;
}
.save {
  margin-top: 20px;
  margin-bottom: 20px;
  width: 25%;
  height: 100%;
}
</style>