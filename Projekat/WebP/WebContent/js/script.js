$(document).ready(function(){

	/*$("div #searchbtn").click(function(){
		$(".searchbar").animate({
			width: '500px'
		});
		$(".searchbar").css({
			borderRadius: '30px'
		});
		$("#searchbtn").before("<input placeholder='Search...' class='srchinput'></input>");
	});*/

	$("#profilbtn").click(function(){
		$(".container").prepend("<div class='zalogin'><a href='login.html' class='login'>Login</a><br><a href=#>Registracija</a></div>")
	});

	$("#menubtn").click(function(){
		$("nav").slideToggle()
	//	$("header").append("<nav><ul><li><a href=#>Music</a></li><li><a href=#>Comedy</a></li></ul></nav>")
	});

	$("#register").click(function(){
		window.location.replace('login.html');
		$("#loginForm").css({'display': 'block'});
		//window.location.replace('login.html')
	});

});