let clicking=0;
let cursor = {
    posX: window.innerWidth / 2,
    posY: window.innerHeight / 2
}
let theCard = {
    dom: null,
    posX: null,
    posY: null
}
let yesbox={
    dom:null,
    top:null,
    left:null,
    right:null,
    bottom:null,
}
let nobox={
    dom:null,
    top:null,
    left:null,
    right:null,
    bottom:null,
}
let currentCursor ={
    posX: 0,
    posY: 0
};
let distance={
    deltaX:0,
    deltaY:0
}
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('drag-card').style.position = 'relative';
    yesbox.dom=document.getElementById("yes-div");
    yesbox.top=yesbox.dom.offsetTop;
    yesbox.bottom=yesbox.dom.offsetTop+yesbox.dom.offsetHeight;
    yesbox.left=yesbox.dom.offsetLeft;
    yesbox.right=yesbox.dom.offsetLeft+yesbox.dom.offsetWidth;
    console.log('yesbox');
    console.log(yesbox);

    nobox.dom=document.getElementById("no-div");
    nobox.top=nobox.dom.offsetTop;
    nobox.bottom=nobox.dom.offsetTop+nobox.dom.offsetHeight;
    nobox.left=nobox.dom.offsetLeft;
    nobox.right=nobox.dom.offsetLeft+nobox.dom.offsetWidth;
    console.log('nobox');
    console.log(nobox);


})

document.addEventListener('mousedown', (ev) => {
    clicking++;
    console.log('MOUSEDOWN')
    document.getElementById('drag-card').style.position = 'fixed'

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
    console.log('MOUSE MOVE')
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
    theCard.dom.style.left = (theCard.posX + distance.deltaX) + 'px';
    theCard.dom.style.top = (theCard.posY + distance.deltaY) + 'px';
    }
    if(currentCursor.posX>yesbox.left&&clicking){
        theCard.dom.classList.add('thinking-yes');

    }
    if(currentCursor.posX<yesbox.left&&clicking){
        theCard.dom.classList.remove('thinking-yes');
    }
    if(currentCursor.posX<nobox.right&&clicking){
        theCard.dom.classList.add('thinking-no');

    }
    if(currentCursor.posX>nobox.right&&clicking){
        theCard.dom.classList.remove('thinking-no');
    }


});
document.addEventListener('mouseup', (ev) => {
    clicking--;
    console.log('EVENT TARGET')
    console.log(ev)

    console.log('MOUSE UP')
    if(currentCursor.posX>nobox.right){
        theCard.dom.classList.remove('thinking-no');

    }
    if(currentCursor.posX<yesbox.left){
        theCard.dom.classList.remove('thinking-yes');
    }

    theCard={
        dom:null,
        posX: ev.clientX,
        posY: ev.clientY,
    };
})
function resetCardPos(){
    const dogDiv=document.getElementById("dog-div");
    document.getElementById("drag-card").style.position = 'relative';
    document.getElementById("drag-card").classList.remove('thinking-yes');
    document.getElementById("drag-card").classList.remove('thinking-no');
    dogDiv.appendChild(document.getElementById("drag-card"));
    console.log("called resetCardPos");
}


