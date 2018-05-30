$(document).ready(function(){
	var current;
	var search = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	
	$.get('SearchServlet', {'search': search}, function(data){
		console.log(data.videos);
//		$("#myVideo").attr('src', data.video.videoURL);
//		$("h1").text(data.video.name);
//		$("h1").after('<div id="published">Published on <span id="date">'+data.video.date+
//				'</span> by <a href="profile.html?id='+data.video.owner.username +'" id="user"><b>'+data.video.owner.username+'</b></a></div>')
////		$("#date").text(data.video.date);
//		$("#views").text(data.video.views + ' views');
//		$("#opisVidea").text(data.video.description);
//		$("#brojLajkova").text(data.video.likes);
//		$("#brojDislajkova").text(data.video.dislikes);
		
		
		for(c in data.videos){
			$('#searched').append('<div id="videoHome">'+
					'<div id="zaThumb">'+
					'<div class="thumbnailWrapper">'+
						'<a href="video.html"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
					'</div>'+
				'</div>'+
				'<a href="video.html" id="naslovRecomm"><b>'+data.videos[c].name+'</b></a><br>'+
				'<a href="profile.html" id="userRecomm">pero</a>'+
				'<span id="viewsRecomm">1000 views</span>'+
				'<span id="dateRecomm">06.02.2018.</span>'+
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
	
	


});