<template>
  <div class="card-container">
    <div class="card-content">
      <div class="card-title">Life Span</div>
      <div class="values">
        From <span id="min-age">0</span> year(s) to <span id="max-age">25</span> year(s)
      </div>
      <div class="slider-container">
        <div class="slider-track"></div>
        <input
          type="range"
          id="slider-min"
          class="input-range"
          min="0"
          max="25"
          step="1"
          value="0"
        />
        <input
          type="range"
          id="slider-max"
          class="input-range"
          min="0"
          max="25"
          step="1"
          value="25"
        />
      </div>
      <div class="slider-labels">
        <span>0</span>
        <span>5</span>
        <span>10</span>
        <span>15</span>
        <span>20</span>
        <span>25</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    const minSlider = document.getElementById("slider-min");
    const maxSlider = document.getElementById("slider-max");
    const minAge = document.getElementById("min-age");
    const maxAge = document.getElementById("max-age");
    const track = document.querySelector(".slider-track");
    const updateSlider = () => {
      const minValue = parseInt(minSlider.value, 10);
      const maxValue = parseInt(maxSlider.value, 10);
      
      if (minValue >= maxValue) {
        minSlider.value = maxValue;
      } else if (maxValue <= minValue) {
        maxSlider.value = minValue ;
      }
      
      minAge.textContent = minSlider.value;
      maxAge.textContent = maxSlider.value;
      
      const percentMin = ((minSlider.value ) / 25) * 100;
      const percentMax = ((maxSlider.value ) / 25) * 100;
      
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
.card-title {
  font-size: 32px;
  font-weight: 700;
  color: white;
  margin-bottom: 10px;
  text-align: center;
}
.values {
  margin-bottom: 20px;
  font-size: 18px;
  color: white;
  text-align: center;
}
.slider-container {
  position: relative;
  width: 100%;
  height: 6px;
  margin: 20px 0;
}
.slider-track {
  position: absolute;
  width: 100%;
  height: 6px;
  border-radius: 5px;
  background: var(--color-headline);
  z-index: 1;
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