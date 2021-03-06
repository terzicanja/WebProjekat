$(document).ready(function(){
	var current;
	var search = window.location.search.slice(1).split('&')[0].split('=')[1];
	var title = window.location.search.slice(1).split('&')[1].split('=')[1];
	var user = window.location.search.slice(1).split('&')[2].split('=')[1];
	var comment = window.location.search.slice(1).split('&')[3].split('=')[1];
	
	
	$.get('SearchServlet', {'search': search, 'title': title, 'user': user, 'comment': comment}, function(data){
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
			if(data.loggedInUser == null || (data.loggedInUser.role != 'ADMIN')){
				if(data.videos[c].visibility == 'PUBLIC' && data.videos[v].deleted == false && data.videos[v].owner.blocked == false && data.videos[v].owner.deleted == false){
					$('#searched').append('<div id="videoHome">'+
							'<div id="zaThumb">'+
							'<div class="thumbnailWrapper">'+
								'<a href="video.html?id='+data.videos[c].id+'"><img src="'+data.videos[c].videoImg+'" id="thumbnail"></a>'+
							'</div>'+
						'</div>'+
						'<a href="video.html?id='+data.videos[c].id+'" id="naslovRecomm"><b>'+data.videos[c].name+'</b></a><br>'+
						'<a href="profile.html?id='+data.videos[c].owner.username+'" id="userRecomm">'+data.videos[c].owner.username+'</a>'+
						'<span id="viewsRecomm">'+data.videos[c].views+' views</span>'+
						'<span id="dateRecomm">06.02.2018.</span>'+
					'</div>');
				}
			}else if(data.loggedInUser.role == 'ADMIN'){
				$('#searched').append('<div id="videoHome">'+
						'<div id="zaThumb">'+
						'<div class="thumbnailWrapper">'+
							'<a href="video.html?id='+data.videos[c].id+'"><img src="'+data.videos[c].videoImg+'" id="thumbnail"></a>'+
						'</div>'+
					'</div>'+
					'<a href="video.html?id='+data.videos[c].id+'" id="naslovRecomm"><b>'+data.videos[c].name+'</b></a><br>'+
					'<a href="profile.html?id='+data.videos[c].owner.username+'" id="userRecomm">'+data.videos[c].owner.username+'</a>'+
					'<span id="viewsRecomm">'+data.videos[c].views+' views</span>'+
					'<span id="dateRecomm">06.02.2018.</span>'+
				'</div>');
			}
			
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