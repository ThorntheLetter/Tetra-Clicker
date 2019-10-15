var curClicks, goalNumber;
var userClicks = 0;
var goals = [5,10,20,50,100,500,1000,1000000];
var pathname = window.location.pathname;
var angeryTime = 0;

setCurrent();
setInterval(setCurrent, 1000);

function doClick(){
	let r = new XMLHttpRequest();
	clickImages();
	r.open("POST", pathname + "/click", true);
	//r.open("POST", "https://thornhub.org/clicker/click", true);
	//r.setRequestHeader("X-CSRF-Token", token);
	//r.withCredentials = true;
	r.send();
	r.onreadystatechange = q =>{
		if(r.readyState == 4){
			if(r.responseText == 0){
				angeryTime += 10;
			}else{
				userClicks = userClicks + 1;
				document.getElementById("userClicks").innerHTML = "Your Contribution: " + userClicks;
			}
		}
	};
}


function clickImages(){
	d = document.getElementById("button");
	if(angeryTime > 0){
		angeryTime -= 1;
		d.src = pathname + "/tetra_angery_mo.png";
		setTimeout(w =>{
			d.src = pathname + "/tetra_angery.png";
		}, 250);
	}else{
		d.src = pathname + "/tetra_smug_mo.png";
		setTimeout(w =>{
			d.src = pathname + "/tetra_smug.png";
		}, 250);
	}
}


function setCurrent(){
	let r = new XMLHttpRequest();
	r.open("GET", pathname + "/click", true);
	r.send();
	r.onreadystatechange = (q =>{
		if(r.readyState == 4){
			curClicks = r.responseText;
			document.getElementById("currentClicks").innerHTML = curClicks;
			goalNumber = setGoal(curClicks);
			document.getElementById("inBar").style.width = (100 / goals[goalNumber]) * curClicks + '%';
		}
	});
}


function setGoal(clicks){
	var gNumber = 0;
	for(var i = 0; i < goals.length; i++){
		if(clicks < goals[i]){
			gNumber = i;
			break;
		}

	}	
	if(goalNumber === gNumber){
		return gNumber;
	}
	document.getElementById("inBar").className = 'g' + gNumber;
	document.getElementById("button").style.animationDuration = 2 / (1 + gNumber) + "s";

	document.getElementById("goalClicks").innerHTML = goals[gNumber];

	return gNumber;
}
