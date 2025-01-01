console.log("Script loaded");

let currentTheme= getTheme();

//initial theme
changeTheme(currentTheme);

//TODO: Add a function to change the theme
function changeTheme(){
  //set to web page
  changePageTheme(currentTheme,currentTheme);

  //set the listener for the change theme button
   const changeThemeButton= document.querySelector('#theme_change_button')
   
   //change the button text
   changeThemeButton.querySelector('span').textContent= currentTheme=="light" ? "Dark" : "Light";
   const oldTheme= currentTheme;
   changeThemeButton.addEventListener('click', ()=>{
   
    if(currentTheme==="light"){ 
        currentTheme="dark";
    }else{
        currentTheme="light";
    }

   
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
     changeThemeButton.querySelector('span').textContent= theme =="light" ? "Dark" : "Light";
 
}