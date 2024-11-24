console.log("Script loaded..");

let currentTheme = getTheme();
changeTheme(currentTheme);


//To Do
function changeTheme() {
	console.log(currentTheme);
	//set to web page
	//document.querySelector("html").classList.add(currentTheme);
	changePageTheme(currentTheme, currentTheme);
	//To change the theme when click on light theme
	const changeThemeButton = document.querySelector('#theme_change_button');
	// change the text of the theme button
	//changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? "dark" : "Light"
	changeThemeButton.addEventListener("click", (event) => {
			let oldTheme = currentTheme;

		console.log("change theme button clicked");
		//Remove current theme
		// document.querySelector('html').classList.remove(currentTheme);

		if (currentTheme == "dark") {
			// theme ko light krna hai
			currentTheme = "light"
		} else {
			// theme to dark
			currentTheme = "dark";
		}
		changePageTheme(currentTheme, oldTheme);
	})
}

//set theme to set local storage
function setTheme(theme) {
	localStorage.setItem("theme", theme);


}

//get theme from local storgae
function getTheme() {
	let theme = localStorage.getItem("theme");
	//  if(theme) return theme;
	// else return "light";
	return theme ? theme : "light";
}

function changePageTheme(theme, oldTheme) {
	// local storage main update karenge
	setTheme(currentTheme);
	//Remove current theme
	if(oldTheme){
			document.querySelector("html").classList.remove(oldTheme);
	}

	//set current theme
	document.querySelector("html").classList.add(theme);


	// change the text of the theme button
	document.querySelector("#theme_change_button").querySelector("span").textContent = theme == "light" ? "dark" : "Light"

}
