let likeBtn = document.getElementById('like-btn');
let likeCounter = document.getElementById('like-counter');
let likeCounterNumber = parseInt(likeCounter.innerHTML);
let saveBtn = document.getElementById('save-btn');
let followBtn = document.querySelector('.tiny-button');
let field = document.querySelector('.comment-sidebar textarea');
const toggleMenu = document.querySelector('.dropdown ul');
const sidebar = document.querySelector('.comment-sidebar');



function redLikeButton(){
    if(likeBtn.classList.contains("far")){
        likeBtn.classList.remove("far");
        likeBtn.classList.add("fas");
        likeCounterNumber += 1;
        likeCounter.innerHTML = likeCounterNumber;
    } else {
        likeBtn.classList.remove("fas");
        likeBtn.classList.add("far");
        likeCounterNumber -= 1;
        likeCounter.innerHTML = likeCounterNumber;
    }   
}

function save(){
    if(saveBtn.classList.contains("far")){
        saveBtn.classList.remove("far");
        saveBtn.classList.add("fas");
    } else {
        saveBtn.classList.remove("fas");
        saveBtn.classList.add("far");
    }   
}


function follow(){
    const bgColor = followBtn.style.backgroundColor;
    if(bgColor === "aquamarine"){
        followBtn.style.backgroundColor = "white";
        followBtn.innerHTML = 'Following';
    }else {
        followBtn.style.backgroundColor = "aquamarine";
        followBtn.innerHTML = 'Follow';
    }
    
}


function show(){
  
    sidebar.classList.toggle('active');
}

