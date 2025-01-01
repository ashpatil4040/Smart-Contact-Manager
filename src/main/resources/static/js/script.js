console.log("Script loaded");

let currentTheme= getTheme();
console.log(currentTheme);
//initial theme

document.addEventListener("DOMContentLoaded", ()=>{
    changeTheme();
});


//TODO: Add a function to change the theme
function changeTheme(){
  //set to web page
  changePageTheme(currentTheme,currentTheme);
  
  //set the listener for the change theme button
   const changeThemeButton= document.querySelector("#theme_change_button");
   const oldTheme= currentTheme;
   
   changeThemeButton.addEventListener('click', ()=>{
    console.log( "button clicked");
    if(currentTheme==="light"){ 
        currentTheme="dark";
    }else{
        currentTheme="light";
    }

    changePageTheme(currentTheme,oldTheme);
});
}

//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

//get theme from local storage
function getTheme(){
    let theme= localStorage.getItem("theme");
    return theme ? theme : "light";
}

//change current page theme
function changePageTheme(theme,oldTheme){
    
     //local storage update
     setTheme(currentTheme);
     //remove the old theme
     document.querySelector('html').classList.remove(oldTheme);
     //add the new theme
     document.querySelector('html').classList.add(theme);
     //change the button text
     document.querySelector("#theme_change_button").querySelector('span').textContent= theme =="light" ? "Dark" : "Light";
 
}