<template>
  <div class="home">
    <header class="header">
      <h1>Welcome to FoundHound</h1>
    </header>

    <!-- Swiper Section -->
    <!-- Add swiper component here -->


    <div class="register-button-container">
      <button class="register-button">Register</button>
    </div>

    
    <section class="tagline">
      <p>FoundHound: Connecting You to the Perfect Breed and a Forever Friend</p>
    </section>

    
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
.header h1 {
  font-size: 2.5em;
  color: white;
}
.tagline {
  margin: 30px 0;
  text-align: center;
  font-size: 1.5em;
  color: white;
  font-weight: bold;
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
  font-size: 1.2em;
  padding: 15px;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 10px;
  border: 1px solid var(--purp5);
}
.value-container h2 {
  font-size: 3em;
  font-weight: bold;
  color: white;
  text-align: left;
  margin-bottom: 20px;
}
</style>
