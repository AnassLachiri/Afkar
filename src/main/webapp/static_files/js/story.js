let likeBtn = document.querySelector('.comment i');
let likeBtn2 = document.querySelector('.reply i');
let likeCounter = document.querySelector('.ccc span');
let likeCounterNumber = parseInt(likeCounter.innerHTML);
// let saveBtn = document.getElementById('save-btn');
let followBtn = document.querySelector('.tiny-button');
let field = document.querySelector('.comment-sidebar textarea');
//const toggleMenu = document.querySelector('.dropdown ul');

function redLikeButton(){
    if(likeBtn.classList.contains("far")){
        likeBtn.classList.remove("far");
        likeBtn.classList.add("fas");
    } else {
        likeBtn.classList.remove("fas");
        likeBtn.classList.add("far");
    }   
}

function redLikeButton2(){
    if(likeBtn2.classList.contains("far")){
        likeBtn2.classList.remove("far");
        likeBtn2.classList.add("fas");
    } else {
        likeBtn2.classList.remove("fas");
        likeBtn2.classList.add("far");
    }   
}

/*function save(){
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

*/


function reply(){
    const replyDialog = document.querySelector('.reply-dialog');

    replyDialog.classList.toggle('active');
}

function reply(id, uuid){

    console.log(id, uuid);

    const commentSection = document.querySelector('.comment-section')

    const callingSpan = document.getElementById(id);

    const parent = callingSpan.parentNode.parentNode.parentNode; 

    let orderofParent = 0;


    for(i = 0; i < commentSection.children.length; i++){
        if(parent === commentSection.children[i]){
           orderofParent = i;
            break;
        }
    }

   

    let replyDialog = document.createElement('div');
    replyDialog.classList.add('reply-dialog');
    replyDialog.innerHTML = `<form  method="post" action="reply_create">
    <textarea name="content"></textarea>
    <input type="hidden" name="comment_id" value="${id}">
    <input type="hidden" name="story_uuid" value="${uuid}">
                
    <input type="submit" value=" Repondre ">
    </form>`;


    for(i = 0; i < commentSection.children.length; i++){
        if(commentSection.children[i].matches('.reply-dialog')){
            commentSection.removeChild(commentSection.children[i]);
           return;
        }
    }

    commentSection.insertBefore(replyDialog,commentSection.children[orderofParent+1])



}