var curClicks, goalNumber;
var userClicks = 0;
var goals = [5,10,20,50,100,500,1000,1000000];
var pathname = window.location.pathname;

setCurrent();
setInterval(setCurrent, 1000);

function doClick(token){
	userClicks = userClicks + 1;
	document.getElementById("userClicks").innerHTML = "Your Contribution: " + userClicks;
	var r = new XMLHttpRequest();
	r.open("POST", pathname + "/click", true);
	r.setRequestHeader("X-CSRF-Token", token);
	r.send();
}

function setCurrent(){
	var r = new XMLHttpRequest();
	r.open("GET", pathname + "/click", true);
	r.send();
	r.onreadystatechange = (q =>{
		curClicks = r.responseText;
		document.getElementById("currentClicks").innerHTML = curClicks;
		goalNumber = setGoal(curClicks);
		document.getElementById("inBar").style.width = (100 / goals[goalNumber]) * curClicks + '%';
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

	document.getElementById("goalClicks").innerHTML = goals[gNumber];

	return gNumber;
}
