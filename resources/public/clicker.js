function doClick(){
	d = document.getElementById("clicks");
	f = document.getElementById("inBar");
	var a = new XMLHttpRequest();
	a.open("POST", "click", true);
	a.send();
	a.onreadystatechange = (q =>{
		clicks = a.responseText;
		d.innerHTML = clicks;
		f.style.width = (100 / 10) * clicks + '%';
	});
}
