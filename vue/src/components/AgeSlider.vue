<template>
    <div class="card-conteiner">
      <div class="card-content">
        <div class="card-title">Dog Age</div>
        <div class="values">
          <div><span id="current-age">1</span> year(s)</div>
        </div>
        <div class="rangeslider">
          <input
              class="input-ranges"
              id="age-slider"
              type="range"
              min="0"
              max="25"
              step="1"
              value="1"
          />
        </div>
        <div class="slider-labels">
          <span>0</span>
          <span>25</span>
        </div>
      </div>
    </div>
  </template>

  <script>
  export default {
    mounted() {
      const slider = document.getElementById("age-slider");
      const currentAge = document.getElementById("current-age");
      // Update displayed age based on slider value
      slider.addEventListener("input", (e) => {
        currentAge.textContent = e.target.value;
      });
    },
  };
  </script>

  <style scoped>
  .card-conteiner {
    cursor: default;
    --color-primary: #275EFE;
    --color-headline: #3F4656;
    --color-text: #99A3BA;
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
  
  
  
  
  
  
  