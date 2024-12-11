<template>
  <div class="home">
    <header class="header">
      <div class="header-container">
        <h1>Find the BEST breed for YOU!</h1>
      </div>
    </header>

    <!-- Swiper Section Placeholder waiting for flops-->
    <section class="swiper-section">
      <div class="swiper-container">
        <div class="fh-box">

        </div>
      </div>
    </section>

    <!-- Register Button that is routed to 'register' -->
    <div class="register-button-container">
      <button class="register-button" v-on:click="this.$router.push({name:'register'})">Register</button>
    </div>

    <!-- Tagline Section -->
    <section class="tagline">
      <div class="tagline-container">
        <p>Connecting You to the Perfect Breed and a Furever Friend</p>
      </div>
    </section>

    <!-- Value Statement Section good to go. Can edit it if need be. -->
    <section class="value-statement">
      <div class="value-container">
        <h2>Our Mission</h2>
        <p>
          At FoundHound, we are here to help individuals and families find the perfect dog breed to fit their home and lifestyle, while ensuring every dog is connected to a loving, responsible family. We believe that understanding a breed’s unique traits, temperament, and needs is key to creating lifelong bonds between pets and their people.
          We value the individuality of every dog and the diversity of breeds, recognizing that each one has something special to offer. By connecting prospective adopters with trusted adoption agencies and rescue organizations, we empower people to make informed decisions and find their ideal canine companion.
          Our platform is dedicated to bridging the gap between those seeking a specific breed and the agencies or shelters that care for them. Whether you’re looking for an energetic playmate, a loyal protector, or a calm companion, we’re here to guide you in finding the right match.
          With compassion, expertise, and a commitment to responsible adoption, we strive to help every dog find a home where they are cherished, understood, and loved. Together, we’re making a difference, one perfect match at a time.
        </p>
      </div>
    </section>
  </div>
  
</template>

<script>
export default {
  data(){

  },
  components:{
  },
  name: "HomePage",
};
</script>

<style scoped>

.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  width: 90%;
}
.header {
  text-align: center;
  margin-top: 20px;
}
.header-container {
  padding: 15px;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 10px;
  border: 1px solid var(--purp5);
  background-color: var(--purp3);
  color: white;
}
.header-container h1 {
  font-size: 2.5em;
  color: white;
  text-align: center;
}
.tagline {
  margin: 30px 0;
  text-align: center;
  font-size: 2em;
}
.tagline-container {
  padding: 15px;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 10px;
  border: 1px solid var(--purp5);
  background-color: var(--purp3);
  color: white;
  font-style: italic;
}
.value-statement {
  margin-top: auto;
  padding: 15px;
  background-color: var(--purp3);
  color: white;
  border-radius: 20px;
  width: 80%;
}
.value-container {
  padding: 15px;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 10px;
  border: 1px solid var(--purp5);
}
.register-button-container {
  margin: 20px;
  text-align: center;
}
.register-button {
  padding: 10px 20px;
  font-size: 1em;
  color: white;
  background-color: var(--purp3);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.register-button:hover {
  background-color: var(--purp5);
}
.swiper-section{
  width: 100%;
}
</style>