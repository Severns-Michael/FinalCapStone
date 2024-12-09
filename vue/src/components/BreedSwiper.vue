<template>
  <div class="swipe" ondragover="hoverHandler(event)" ondrop="dropHandler(event)" id="yes-div">yes div</div>

  <div class="swipe">
    dog div
    <div class="card" draggable="true" id="drag-card" ondragstart="pickupHandler(event)">
      <img draggable="false" class="card-img-top" src="https://cdn.discordapp.com/attachments/1035341798123507714/1111050653867970721/RDT_20230524_1411203361442537406158442.jpg?ex=6758b98e&is=6757680e&hm=275d57b778512e5718d573e575b1de679a64eb9be342f434c7acf48b4363e6f1&"/>
      <div class="card-body">
        <p>this is a funny guy</p>
      </div>
    </div>
  </div>
  <div class="swipe" ondragover="hoverHandler(event)" ondrop="dropHandler(event), printHappy()">
    no div
  </div>

</template>

<script>
</script>

<style>

</style>