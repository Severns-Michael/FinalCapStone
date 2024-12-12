<template>
  <div class="swipe" id="no-div">
 Not for me...
  </div>

  <div class="swipe swipe-dog" id="dog-div">

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
    <swipe-card :swipedBreed="this.swipedBreed" :currentBreed="this.currentBreed" @mouseup="checkSwipe"/>
    <!--      <div class="loading"></div>-->

  </div>
  <div class="swipe" id="yes-div" v-text="`I Love ${this.currentBreed.officialName}s!`">

  </div>


</template>

<script>
let clicking = 0;
let cursor = {
  posX: window.innerWidth / 2,
  posY: window.innerHeight / 2
}
let theCard = {
  dom: null,
  posX: null,
  posY: null
}
let yesbox = {
  dom: null,
  top: null,
  left: 1300,
  right: null,
  bottom: null,
}
let nobox = {
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
document.addEventListener('DOMContentLoaded', () => {



})

document.addEventListener('mousedown', (ev) => {
  clicking++;
  console.log('MOUSEDOWN')
  document.getElementById('drag-card').style.position = 'relative'

  if (ev.target.parentNode.classList.contains('drag-card')) {
    console.log('found DragCard')
    cursor = {
      posX: ev.clientX,
      posY: ev.clientY
    }
    theCard = {
      dom: ev.target.parentNode,
      posX: ev.target.getBoundingClientRect().left,
      posY: ev.target.getBoundingClientRect().top
    }
    console.log('printing theCard');
    console.log(theCard)
  }
});
document.addEventListener('mousemove', (ev) => {


  // console.log('MOUSE MOVE')
  // console.log('printing the targets dom property');
  // console.log(theCard.dom);
  if (theCard.dom != null) {
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
    theCard.dom.style.left = (distance.deltaX) + 'px';
    theCard.dom.style.top = (distance.deltaY) + 'px';
    if (yesbox.left !== null && (currentCursor.posX > yesbox.left && clicking)) {
      console.log('================check this====================')
      console.log(currentCursor.posX);
      console.log('is greater than')
      console.log(yesbox.left)
      console.log('correct?')
      theCard.dom.classList.add('thinking-yes');


    }
  }
  if (currentCursor.posX < yesbox.left && clicking) {
    theCard.dom.classList.remove('thinking-yes');
  }
  if (currentCursor.posX < nobox.right && clicking) {
    theCard.dom.classList.add('thinking-no');

  }
  if (currentCursor.posX > nobox.right && clicking) {
    theCard.dom.classList.remove('thinking-no');
  }


});
document.addEventListener('mouseup', (ev) => {
  clicking--;
  console.log('EVENT TARGET')
  console.log(ev)
  console.log('MOUSE UP')
  if (currentCursor.posX > nobox.right) {
    theCard.dom.classList.remove('thinking-no');

  }
  if (currentCursor.posX < yesbox.left) {
    theCard.dom.classList.remove('thinking-yes');
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
  theCard = {
    dom: null,
    posX: 0,
    posY: 0,
  };

})

function resetCardPos() {
  const dogDiv = document.getElementById("dog-div");
  document.getElementById("drag-card").style.position = 'relative';
  document.getElementById("drag-card").style.top = 0;
  document.getElementById("drag-card").style.left = 0;
  document.getElementById("drag-card").classList.remove('thinking-yes');
  document.getElementById("drag-card").classList.remove('thinking-no');
  dogDiv.appendChild(document.getElementById("drag-card"));
  console.log("called resetCardPos");
}


//-----------------------------------------
import BreedService from "@/services/BreedService";
import DogService from "../services/DogService";
import SwipeCard from "@/components/SwipeCard.vue";


export default {
  beforeMount() {
    this.getRandomBreedList();
  },
  mounted: function () {
    // document.getElementById('drag-card').style.position = 'fixed';
    yesbox.dom = document.getElementById("yes-div");
    yesbox.top = yesbox.dom.offsetTop;
    yesbox.bottom = yesbox.dom.offsetTop + yesbox.dom.offsetHeight;
    yesbox.left = yesbox.dom.offsetLeft;
    yesbox.right = yesbox.dom.offsetLeft + yesbox.dom.offsetWidth;
    console.log('yesbox');
    console.log(yesbox);

    nobox.dom = document.getElementById("no-div");
    nobox.top = nobox.dom.offsetTop;
    nobox.bottom = nobox.dom.offsetTop + nobox.dom.offsetHeight;
    nobox.left = nobox.dom.offsetLeft;
    nobox.right = nobox.dom.offsetLeft + nobox.dom.offsetWidth;
    console.log('nobox');
    console.log(nobox);
  },
  data() {
    return {
      swipedBreed: {
        userId: this.$store.state.user.id
      },
      currentBreed: {},
      randomBreedList: [],
    }
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
      this.swipedBreed = {...this.swipedBreed, breedId: breed.breedId};
      this.swipedBreed.swipeYes = true;
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
      console.log(nobox)
      console.log('print yesbox')
      console.log(yesbox)
      if (ev.clientX < nobox.right) {
        this.swipeNo(ev, this.swipedBreed)
      }
      if (ev.clientX > yesbox.left) {
        this.swipeYes(ev, this.swipedBreed)
      } else {
        resetCardPos();
      }
    },
  },
  components: {
    SwipeCard
  },


}

</script>

<style>

/* .container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.loading {
  height: 120px;
  width: 120px;
  border: 5px solid;
  border-color: white transparent white transparent;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg)
  }
} */

</style>