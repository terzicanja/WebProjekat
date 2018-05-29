$(document).ready(function(){
	var current;
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	
	$.get('VideoServlet', {'id': id}, function(data){
		console.log(data.video);
		console.log(data.comments);
		$("#myVideo").attr('src', data.video.videoURL);
		$("h1").text(data.video.name);
		$("h1").after('<div id="published">Published on <span id="date">'+data.video.date+
				'</span> by <a href="profile.html?id='+data.video.owner.username +'" id="user"><b>'+data.video.owner.username+'</b></a></div>')
//		$("#date").text(data.video.date);
		$("#views").text(data.video.views + ' views');
		$("#opisVidea").text(data.video.description);
		$("#brojLajkova").text(data.video.likes);
		$("#brojDislajkova").text(data.video.dislikes);
		
		
		for(c in data.comments){
			$('#komPoStr').after('<div class="komentar">'+
					'<a href="profile.html?id='+data.comments[c].author.username+'" id="user"><b>'+data.comments[c].author.username+'</b></a>'+
					'<div id="dislikeRating">8</div>'+
					'<a href="#" id="dislike"><i class="material-icons">thumb_down</i></a>'+
					'<a href="#" id="like"><i class="material-icons">thumb_up</i></a>'+
					'<div id="likeRating">23</div><br>'+
					'<div class="comment">'+data.comments[c].content+'</div>'+
					'<!--<button id="like"><i class="material-icons">thumb_up</i></button>-->'+
					'<span id="dateComm">10.02.2016.</span>'+
				'</div>');
		}
		
	});
	
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
	
	
	
	
/*
	var pageSize = 10;
	var koliko = $(".komentar").length;
	console.log(koliko);
	var pageCount = 50/pageSize;
	for(var i=0; i<pageCount; i++){
		$("#pagination").append('<a href="#pagination">'+(i+1)+'</a>');
	}
	$("#pagination").find("a").first().addClass("active");
	showPage = function(page){
		$(".komentar").hide();
		console.log('proveraaa');
		//$(".komentar").css('display', 'none');
		$(".komentar").each(function(n){
			if(n>=pageSize * (page - 1) && n<pageSize * page){
				//$(this).show();
				$(this).css('display', 'block');
			}
		});
	}
	showPage(1);*/


	/*function brKomentara(){
		var brKom = document.getElementById('selectBroj');
		var broj = brKom.options[brKom.selectedIndex].value;
		$.ajax({
		url: 'https://jsonplaceholder.typicode.com/comments',
		type: 'GET',
		dataType: 'json',
		success: function(response){
			
			
			var comm = document.getElementsByClassName('comments');
			var com = comm[0];
			for (var i = 0; i <broj; i++) {
				var komentar = response[i];
				var user = komentar.email;
				var kom = komentar.body;
				var newDiv = $('<div class="komentar"><a href="profile.html" id="user"><b>' + user +
					'</b></a><div id="dislikeRating">8</div><a href="#" id="dislike"><i class="material-icons">thumb_down</i></a><a href="#" id="like"><i class="material-icons">thumb_up</i></a><div id="likeRating">23</div><br><div class="comment">' +kom+ 
					'</div><span id="dateComm">10.02.2016.</span></div>');
				$('#pagination').before(newDiv);
			}
		},
		error: function(request, message, error){
			alert('greska: ' + error);
		}
		});
	}
	/*$.ajax({
		url: 'https://jsonplaceholder.typicode.com/comments',
		type: 'GET',
		dataType: 'json',
		success: function(response){
			
			
			var comm = document.getElementsByClassName('comments');
			var com = comm[0];
			for (var i = 0; i <30; i++) {
				var komentar = response[i];
				var user = komentar.email;
				var kom = komentar.body;
				var newDiv = $('<div class="komentar"><a href="profile.html" id="user"><b>' + user +
					'</b></a><div id="dislikeRating">8</div><a href="#" id="dislike"><i class="material-icons">thumb_down</i></a><a href="#" id="like"><i class="material-icons">thumb_up</i></a><div id="likeRating">23</div><br><div class="comment">' +kom+ 
					'</div><span id="dateComm">10.02.2016.</span></div>');
				$('#pagination').before(newDiv);
				//var newDiv = $('<div></div>');
				var newDiv = document.createElement('div');
				//newDiv.addClass('komentar');
				newDiv.className = 'komentar';
				//var aUser = $('<a></a>');
				var aUser = document.createElement('a');
				//aUser.attr('id', 'user');
				aUser.setAttribute('id', 'user');
				//var commentDiv = $('<div></div>');
				var commentDiv = document.createElement('div');
				//commentDiv.addClass('comment');
				commentDiv.className = 'comment';


				var komentar = response[i];
				//aUser.text(komentar.email);
				aUser.title = komentar.email;
				//aUser.attr('href','profile.html');
				aUser.href = 'profile.html';
				//commentDiv.text(komentar.body);
				commentDiv.title = komentar.body;

				com.prepend(newDiv[i]);
				//com.appendChild(newDiv[i]);
				//$('.comments').prepend(newDiv[i]);
				$(newDiv[i]).prepend(aUser[i]);
				$(aUser[i]).after(commentDiv[i]);
				console.log("i je => " + i);
				//$('#user').append(commentDiv[i]);
}

				//var newDiv = $('<div></div>');
				newDiv.addClass('comment');

				var komentar = response[i];
				newDiv.text(komentar.body);
				$('#likeRating').append(newDiv[i]);
			}
		},
		error: function(request, message, error){
			alert('greska: ' + error);
		}
	});*/
});