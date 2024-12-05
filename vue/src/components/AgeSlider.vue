<template>
  <div class="card-container-1">
    <div class="card-content-1">
      <div class="card-title">Life Span</div>
      <!-- Display dynamic values -->
      <div class="slider-values">
        From <span id="min-size">Small</span> to <span id="max-size">Large</span>
      </div>
      <!-- Slider track and inputs -->
      <div class="slider-container-1">
        <div class="slider-track-1"></div>
        <input
            type="range"
            id="slider-min"
            class="input-range"
            min="1"
            max="3"
            step="1"
            value="1"
        />
        <input
            type="range"
            id="slider-max"
            class="input-range"
            min="1"
            max="3"
            step="1"
            value="3"
        />
      </div>
      <!-- Labels for the slider -->
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
  mounted() {
    const minSlider = document.getElementById("slider-min");
    const maxSlider = document.getElementById("slider-max");
    const minSize = document.getElementById("min-size");
    const maxSize = document.getElementById("max-size");
    const track = document.querySelector(".slider-track");

    const sizeLabels = {
      1: "Small",
      2: "Medium",
      3: "Large",
    };

    const updateSlider = () => {
      const minValue = parseInt(minSlider.value, 10);
      const maxValue = parseInt(maxSlider.value, 10);

      // Prevent overlap
      if (minValue >= maxValue) {
        minSlider.value = maxValue;
      } else if (maxValue <= minValue) {
        maxSlider.value = minValue;
      }

      // Update displayed values
      minSize.textContent = sizeLabels[minSlider.value];
      maxSize.textContent = sizeLabels[maxSlider.value];

      // Update track styling
      const percentMin = ((minSlider.value ) / 25) * 100;
      const percentMax = ((maxSlider.value ) / 25) * 100;
      track.style.background = `linear-gradient(to right, #3f4656 ${percentMin}%, #275EFE ${percentMin}%, #275EFE ${percentMax}%, #3f4656 ${percentMax}%)`;
    };

    // Add event listeners
    minSlider.addEventListener("input", updateSlider);
    maxSlider.addEventListener("input", updateSlider);

    // Initialize slider
    updateSlider();
  },
};
</script>

<style scoped>
.card-container-1 {
  cursor: default;
  --color-primary: #275efe;
  --color-headline: #3f4656;
  --color-text: #99a3ba;
  margin-bottom: 50px;
}

.card-content-1 {
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
  text-align: center;
}

.slider-values {
  font-size: 18px;
  color: var(--color-primary);
  margin-bottom: 15px;
  text-align: center;
}

.slider-container-1 {
  position: relative;
  width: 100%;
  height: 6px;
  margin: 20px 0;
}

.slider-track-1 {
  position: absolute;
  height: 6px;
  width: 100%;
  border-radius: 5px;
  background: var(--color-headline);
  z-index: 1;
}

.input-range {
  position: absolute;
  width: 100%;
  height: 6px;
  border-radius: 5px;
  margin: 0;
  background: none;
  -webkit-appearance: none;
  appearance: none;
  z-index: 2;
  pointer-events: none;
}

.input-range::-webkit-slider-thumb {
  position: relative;
  pointer-events: auto;
  width: 20px;
  height: 20px;
  background: #fff;
  border: 2px solid var(--color-primary);
  border-radius: 50%;
  cursor: pointer;
  appearance: none;
}

.input-range::-moz-range-thumb {
  position: relative;
  pointer-events: auto;
  width: 20px;
  height: 20px;
  background: #fff;
  border-radius: 50%;
  cursor: pointer;
  border: 2px solid var(--color-primary);
}

.slider-labels {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: var(--color-text);
}
</style>
