<template>
  <div class="container">
    <trait-switch-box class="traitSwitcher"></trait-switch-box>
    <div class="sliders">
      <slider sliderType="Dog Height"></slider>
      <slider sliderType="Dog Weight"></slider>
      <age-slider></age-slider>
    </div>
  </div>
</template>

<script>
import Slider from "../components/Slider.vue"
import TraitSwitchBox from "@/components/TraitSwitchBox.vue";
import AgeSlider from "../components/AgeSlider.vue";

export default {
  components: {
    TraitSwitchBox,
    Slider,
    AgeSlider
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

</style>