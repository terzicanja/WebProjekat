$(document).ready(function(e){
	
	$.get('LoggedInServlet', function(data) {
//		eventAuth = data.auth;
		
		var loggedInUser = data.loggedInUser;
		var status = data.status;
		
		if (status == "loggedIn"){
			if(loggedInUser.role == "ADMIN"){
				$('#sign').append('<a href="#" id="profile">' + loggedInUser.username + '</a> <span>/</span> <a href="admin.html" id="profile">All users</a>'+
				'<a href="#" id="profile">Edit profile</a> <span>/</span> <a href="LogoutServlet" id="signout">Sign out</a>');
			} else {
				$('#sign').append('<a href="#" id="profile">' + loggedInUser.username + '</a> <span>/</span> '+
				'<a href="#" id="profile">Edit profile</a> <span>/</span> <a href="LogoutServlet" id="signout">Sign out</a>');
			}
			
		} else {
			$('#sign').append('<a href="register.html" id="register">Register</a><span>/</span><a href="login.html" id="signin">Sign in</a>');
		}
		
		
	});
	
	
	
	$(function(){
		var slika = $('#slika');
		var backgrounds = new Array(
			'url("images/clouds.jpg")',
			'url("images/header.jpeg")',
			'url("images/movie.jpg")');
		var current = 0;
		
		
		
//		$('#sign').append('<a href="register.html" id="register">Register</a><span>/</span><a href="login.html" id="signin">Sign in</a>')
		
		
		

		function nextBackground(){
			/*current++;

			$('#slika').fadeOut(100, function(){
				$('#slika').css({
					'background-image' : "url('"+backgrounds[current]+"')"
				});
				$('#slika').fadeIn(100);
			});*/


			/*slika.css(
				'background',
				backgrounds[current = ++current%backgrounds.length]);
			setTimeout(nextBackground, 10000);*/
		}
		//setTimeout(nextBackground, 10000);
		//slika.css('background', backgrounds[0]);
	});


	$("#loginForm").submit(function(e){
		e.preventDefault();

		//var uname = document.getElementById('uname');
		//var pw = document.getElementById('pw');

		var user = $("input[name=username]").val().trim();
		var pass = $("input[name=password]").val().trim();
		console.log(user);
		console.log(pass);

		/*function store() {
    		localStorage.setItem('uname', uname.value);
    		localStorage.setItem('pw', pw.value);
		}*/

		//function check() {
			//alert('radi');
			var storedName = localStorage.getItem('uname');
    		var storedPw = localStorage.getItem('pw');
    		console.log(storedName);
    		console.log(storedPw);

    		for(var i=0; i<localStorage.length; i++) {
    			var key = localStorage.key(i);
    			var value = localStorage[key];
    			console.log(key + " => " + value);
			}


    		if(user == storedName && pass == storedPw) {
    			alert('You are loged in.');
    			window.location.replace(
							"home.html?user=" + user
						);

    		}else{
    			alert('Molim vas unesite potrebne podatke.');
    		}
		//}
	});

	$("#registerForm").submit(function(e){
		
		var usname = document.getElementById('uname');
		var psw = document.getElementById('pw');
		if(usname !=null || usname!="" || psw!=null || psw!=""){
			//alert('Uspesno ste se registrovali');
			localStorage.setItem('uname', uname.value);
    		localStorage.setItem('pw', pw.value);
		}else{
			alert('Molim vas unesite potrebne podatke.');
		}

		
	});

	function validateForm(){
        var a=document.forms["Form"]["username"].value;
        var b=document.forms["Form"]["password"].value;
        if (a==null || a=="" || b==null || b=="")
        {
            alert("Molim vas unesite potrebne podatke.");
            return false;
        }
    }
});