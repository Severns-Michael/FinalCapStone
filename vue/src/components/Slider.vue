<template>
  <div class="card-conteiner">
    <div class="card-content">
      <div class="card-title">{{sliderType}}</div>
      <div class="values">
        <div><span id="current-size">Small</span></div>
      </div>
      <div class="rangeslider">
        <input
            class="input-ranges"
            id="size-slider"
            type="range"
            min="1"
            max="3"
            step="1"
            value="1"
        />
      </div>
      <div class="slider-labels">
        <span>Small</span>
        <span>Medium</span>
        <span>Large</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: [
    'sliderType'
  ],
  mounted() {
    const slider = document.getElementById("size-slider");
    const currentSize = document.getElementById("current-size");

    // Map slider values to size labels
    const sizeLabels = {
      1: "Small",
      2: "Medium",
      3: "Large",
    };

    // Update displayed size based on slider value
    slider.addEventListener("input", (e) => {
      currentSize.textContent = sizeLabels[e.target.value];
    });
  },
};
</script>

<style scoped>
.card-conteiner {
  cursor: default;
  --color-primary: #275efe;
  --color-headline: #3f4656;
  --color-text: #99a3ba;
  margin-bottom: 50px;
}

.card-content {
  width: 100%;
  max-width: 312px;
  padding: 36px 32px;
  background: #fff;
  border-radius: 10px;
}

.card-title {
  font-size: 32px;
  font-weight: 700;
  color: var(--color-headline);
  margin-bottom: 10px;
  ;
}

.values {
  margin-bottom: 20px;
  font-size: 18px;
  color: var(--color-primary);
}

.rangeslider {
  margin: 20px 0;
}

.input-ranges {
  width: 100%;
  -webkit-appearance: none;
  appearance: none;
  background: var(--color-headline);
  height: 6px;
  border-radius: 5px;
  outline: none;
}

.input-ranges::-webkit-slider-thumb {
  width: 20px;
  height: 20px;
  background: #fff;
  border: 2px solid var(--color-primary);
  border-radius: 50%;
  cursor: pointer;
  transition: 0.3s;
}

.input-ranges::-moz-range-thumb {
  width: 20px;
  height: 20px;
  background: #fff;
  border: 2px solid var(--color-primary);
  border-radius: 50%;
  cursor: pointer;
  transition: 0.3s;
}

.slider-labels {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: var(--color-text);
}
</style>
