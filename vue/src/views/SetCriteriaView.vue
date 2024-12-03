<template>
  <div class="container">
    <trait-switch-box class="traitSwitcher"></trait-switch-box>
    <slider></slider>
  </div>
</template>

<script>
import Slider from "../components/Slider.vue"
import TraitSwitchBox from "@/components/TraitSwitchBox.vue";
export default {
  components: {
    TraitSwitchBox,
    Slider,
  }
}


</script>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
  }
  .traitSwitcher {
    margin: 100px;
  }
</style>