window.addEventListener("DOMContentLoaded", () => {
const dragElement = document.getElementById("drag-card");
dragElement.addEventListener('dragstart', pickupHandler);
});


function pickupHandler(ev) {
    ev.dataTransfer.setData("text/plain", ev.target.id);
}

function hoverHandler(ev) {
    ev.preventDefault();
    ev.dataTransfer.dropEffect = "move";
}

function dropHandler(ev) {
    ev.preventDefault();
    const data = ev.dataTransfer.getData("text/plain");
    ev.target.appendChild(document.getElementById(data));

}

function printHappy() {
    console.log("YAY")
}
function dontresetCardPos(){
    const dogDiv=document.getElementById("dog-div");
    dogDiv.appendChild(document.getElementById("drag-card"));
    console.log("resetCardPos");
}
