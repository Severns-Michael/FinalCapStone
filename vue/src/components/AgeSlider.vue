<template>
  <div class="card-container">
    <div class="card-content">
      <div class="card-title-size">Size Range</div>

      <div class="slider-values">
        From <span id="min-size">Small</span> to <span id="max-size">Large</span>
      </div>

      <div class="slider-container">
        <div class="slider-track"></div>
        <input
            type="range"
            id="slider-min-size"
            class="input-range"
            min="1"
            max="3"
            step="1"
            value="1"
        />
        <input
            type="range"
            id="slider-max-size"
            class="input-range"
            min="1"
            max="3"
            step="1"
            value="3"
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
  mounted() {
    const minSlider = document.getElementById("slider-min-size");
    const maxSlider = document.getElementById("slider-max-size");
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

      if (minValue >= maxValue) {
        minSlider.value = maxValue;
      } else if (maxValue <= minValue) {
        maxSlider.value = minValue;
      }

      minSize.textContent = sizeLabels[minSlider.value];
      maxSize.textContent = sizeLabels[maxSlider.value];

      const percentMin = ((minValue - minSlider.min) / (minSlider.max - minSlider.min)) * 100;
      const percentMax = ((maxValue - maxSlider.min) / (maxSlider.max - maxSlider.min)) * 100;
      track.style.background = `linear-gradient(to right, #8a2cac ${percentMin}%, #a04ebd ${percentMin}%, #a04ebd ${percentMax}%, #8a2cac ${percentMax}%)`;
    };

    minSlider.addEventListener("input", updateSlider);
    maxSlider.addEventListener("input", updateSlider);

    updateSlider();
  },
};
</script>
<style scoped>
.card-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 312px;
  margin: auto;
  padding: 10px;
  box-sizing: border-box;
}
.card-content {
  width: 100%;
  padding: 36px 16px;
  max-width: 100%;
  background: var(--color-primary);
  border-radius: 10px;
  box-sizing: border-box;
}
.card-title-size {
  font-size: 32px;
  font-weight: 700;
  color: white;
  margin-bottom: 10px;
  text-align: center;
}
.slider-values {
  font-size: 18px;
  color: white;
  margin-bottom: 15px;
  text-align: center;
}
.slider-container {
  position: relative;
  width: 100%;
  height: 6px;
  margin: 20px 0;
}
.slider-track-size {
  position: absolute;
  width: 100%;
  height: 6px;
  border-radius: 5px;
  background: var(--color-headline);
  z-index: 2;
  overflow: hidden;
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
  background: white;
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
  background: white;
  border-radius: 50%;
  cursor: pointer;
  border: 2px solid var(--color-primary);
}
.slider-labels {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: white;
}
</style>
