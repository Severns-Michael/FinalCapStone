<template>
  <div class="home">
    <header class="header">
      <div class="header-container">
        <h1>Find YOUR Furever Friend!</h1>
      </div>
    </header>

<!--Swiper Section Placeholder waiting for flops-->
    <div class="swiper-section fh-row">
      <div class="swipe-demo" id="no-div-demo">
        Not for me...
      </div>

      <div class="swipe-demo swipe-dog-demo" id="dog-div">

        <!--    <div class="card drag-card" draggable="true" id="drag-card" ondragstart="pickupHandler(event)"-->
        <!--         ondragover="hoverHandler(event)" ondrop="dropHandler(event)">-->
        <!--      <img draggable="false" class="card-img-top" :src="this.currentBreed.imgpath"/>-->
        <!--      <div class="card-body">-->
        <!--        <h2 v-text="this.currentBreed.officialName"></h2>-->
        <!--        <ul>-->
        <!--          <li v-for="trait in this.currentBreed.traits" v-text="trait.traitName"></li>-->
        <!--        </ul>-->
        <!--      </div>-->
        <!--    </div>-->
<!--        <swipe-card :swipedBreed="this.swipedBreed" :currentBreed="this.currentBreed" @mouseup="checkSwipe"/>-->
        <!--      <div class="loading"></div>-->
        <div class="card drag-card drag-card-demo" draggable="false" id="drag-card-demo" >
          <img draggable="false" class="card-img-top dog-card-img" src=""/>
          <div class="card-body">
            <h2 v-text="`current breed name`"></h2>
            <ul>
              <li>trait 1</li>
              <li>trait 2</li>
            </ul>
          </div>
        </div>

      </div>
      <div class="swipe-demo" id="yes-div-demo" v-text="`I Love These!`">

      </div>
    </div>

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
import DogService from "@/services/DogService";

let clicking = 0;
let cursor = {
  posX: window.innerWidth / 2,
  posY: window.innerHeight / 2
}
let theCardDemo = {
  dom: null,
  posX: null,
  posY: null
}
let yesboxdemo = {
  dom: null,
  top: null,
  left: null,
  right: null,
  bottom: null,
}
let noboxdemo = {
  dom: null,
  top: null,
  left: null,
  right: null,
  bottom: null,
}
let currentCursor = {
  posX: 0,
  posY: 0
};
let distance = {
  deltaX: 0,
  deltaY: 0
}


document.addEventListener('mousedown', (ev) => {
  clicking++;
  console.log('MOUSEDOWN')
  document.getElementById('drag-card-demo').style.position = 'relative'
  console.log('SEACRHING FOR THE DEMOCARD @ev.target.parentnode')
  console.log(ev.target)
  if (ev.target.parentNode.classList.contains('drag-card-demo')) {
    console.log('found DragCard')
    cursor = {
      posX: ev.clientX,
      posY: ev.clientY
    }
    console.log('setting the cards DOM')
    console.log('++++++++++++++++++++++++++++')
    theCardDemo = {
      dom: ev.target.parentNode,
      posX: ev.target.getBoundingClientRect().left,
      posY: ev.target.getBoundingClientRect().top
    }
    console.log('printing theCard');
    console.log(theCardDemo)
  }
});
document.addEventListener('mousemove', (ev) => {


  console.log('MOUSE MOVE')
  // console.log('printing the targets dom property');
  // console.log(theCard.dom);
  if (theCardDemo.dom != null) {
    // console.log('mousemoveevent')
    // console.log(theCard.dom)
    currentCursor = {
      posX: ev.clientX,
      posY: ev.clientY
    };
    distance = {
      deltaX: currentCursor.posX - cursor.posX,
      deltaY: currentCursor.posY - cursor.posY
    };
    theCardDemo.dom.style.left = (distance.deltaX) + 'px';
    theCardDemo.dom.style.top = (distance.deltaY) + 'px';
    if (currentCursor.posX > yesboxdemo.left && clicking) {
      console.log('================check this====================')
      console.log(currentCursor.posX);
      console.log('is greater than')
      console.log(yesboxdemo.left)
      console.log('correct?')
      theCardDemo.dom.classList.add('thinking-yes');


    }
  }
  if (currentCursor.posX < yesboxdemo.left && clicking) {
    theCardDemo.dom.classList.remove('thinking-yes');
  }
  if (currentCursor.posX < noboxdemo.right && clicking) {
    theCardDemo.dom.classList.add('thinking-no');

  }
  if (currentCursor.posX > noboxdemo.right && clicking) {
    theCardDemo.dom.classList.remove('thinking-no');
  }


});
document.addEventListener('mouseup', (ev) => {
  clicking--;
  console.log('EVENT TARGET')
  console.log(ev)
  console.log('MOUSE UP')

  console.log('theCardDemo')
  console.log(theCardDemo)
  if (currentCursor.posX > noboxdemo.right) {
    theCardDemo.dom.classList.remove('thinking-no');
    resetCardPos()

  }
  if (currentCursor.posX < yesboxdemo.left) {
    theCardDemo.dom.classList.remove('thinking-yes');
    resetCardPos()
  }
  console.log('deselecting the dom for theCard')
  currentCursor = {
    posX: 0,
    posY: 0
  }
  distance = {
    deltaX: 0,
    deltaY: 0
  }
  resetCardPos()
  theCardDemo = {
    dom: null,
    posX: 0,
    posY: 0,
  };

})

function resetCardPos() {
  const dogDiv = document.getElementById("dog-div");
  document.getElementById("drag-card-demo").style.position = 'relative';
  document.getElementById("drag-card-demo").style.top = 0;
  document.getElementById("drag-card-demo").style.left = 0;
  document.getElementById("drag-card-demo").classList.remove('thinking-yes');
  document.getElementById("drag-card-demo").classList.remove('thinking-no');
  dogDiv.appendChild(document.getElementById("drag-card-demo"));
  console.log("called resetCardPos");
}








//------------------------------------------------------------------------
import SwipeCard from "@/components/SwipeCard.vue";
import BreedService from "@/services/BreedService";

export default {
  data(){

  },
  components:{
    // SwipeCard
  },
  mounted: function() {
    yesboxdemo.dom = document.getElementById("yes-div-demo");
    yesboxdemo.top = yesboxdemo.dom.offsetTop;
    yesboxdemo.bottom = yesboxdemo.dom.offsetTop + yesboxdemo.dom.offsetHeight;
    yesboxdemo.left = yesboxdemo.dom.offsetLeft;
    yesboxdemo.right = yesboxdemo.dom.offsetLeft + yesboxdemo.dom.offsetWidth;
    console.log('yesbox');
    console.log(yesboxdemo);

    noboxdemo.dom = document.getElementById("no-div-demo");
    noboxdemo.top = noboxdemo.dom.offsetTop;
    noboxdemo.bottom = noboxdemo.dom.offsetTop + noboxdemo.dom.offsetHeight;
    noboxdemo.left = noboxdemo.dom.offsetLeft;
    noboxdemo.right = noboxdemo.dom.offsetLeft + noboxdemo.dom.offsetWidth;
    console.log('nobox');
    console.log(noboxdemo);
  },
  methods: {
    setCurrentBreedImage() {
      if (!this.currentBreed.subBreed) {
        DogService.getBreedPic(this.currentBreed.breedName).then(
            response => {
              this.swipedBreed.img = response.data.message;
            }
        );
      } else if (this.currentBreed.subBreed) {
        DogService.getSubBreedPic(this.currentBreed.breedName, this.currentBreed.subBreed).then(
            response => {
              this.swipedBreed.img = response.data.message;
            }
        )
      }
    },
    getRandomBreedList() {
      BreedService.getRandomBreeds().then(response => {
        this.randomBreedList = response.data;
        BreedService.getBreedById(this.randomBreedList.pop().breedId).then(
            response => {
              this.currentBreed = response.data
              this.swipedBreed.breedId = this.currentBreed.breedId;
              this.setCurrentBreedImage();
            }
        );
      });
    },
    getNextBreed() {
      BreedService.getBreedById(this.randomBreedList.pop().breedId).then(
          response => {
            this.currentBreed = response.data
            this.swipedBreed.breedId = this.currentBreed.breedId;
            this.setCurrentBreedImage();
          }
      );
      console.log('getNextBreed()')
    },
    swipeYes(e, breed) {
      console.log('you swiped YES');
      console.log(this.swipedBreed);


          this.swipedBreed = {
            userId: this.$store.state.user.id
          };
          this.currentBreed = {};
          this.getNextBreed()
          resetCardPos();


    },
    swipeNo(e, breed) {
      console.log('you swiped NO');
      this.swipedBreed = {...this.swipedBreed, breedId: breed.breedId};
      this.swipedBreed.swipeYes = false;
      console.log(this.swipedBreed);
      DogService.addToSwipedBreeds(this.swipedBreed).then(response => {
        console.log('addToSwipedBreeds().then()')
        if (response.status === 201) {
          this.swipedBreed = {
            userId: this.$store.state.user.id
          };
          this.currentBreed = {};
          this.getNextBreed()
          resetCardPos();
        }
      });
    },
    checkSwipe(ev) {
      ev.preventDefault();
      console.log('CHECKSWIPE EVENT')
      console.log(ev)
      console.log('print nobox')
      console.log(noboxdemo)
      console.log('print yesbox')
      console.log(yesboxdemo)
      if (ev.clientX < noboxdemo.right) {
        this.swipeNo(ev, this.swipedBreed)
      }
      if (ev.clientX > yesboxdemo.left) {
        this.swipeYes(ev, this.swipedBreed)
      } else {
        resetCardPos();
      }
    },
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