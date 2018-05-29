$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	$.get('UserServlet', {'id': id}, function(data){
		console.log(data);
		
		$('#korisnik').text(data.user.username);
		$('#profileDate').text(data.user.registrationDate);
		//$('#brFollowers').text(data.user.username);
		$('#korisnik').text(data.user.username);
		$('#korisnik').text(data.user.username);
		
		
		for(v in data.videos){
			$('.recommended').append('<div id="videoHome">'+
					'<div class="thumbnailWrapper">'+
						'<a href="video.html?id='+data.videos[v].id+'"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
					'</div>'+
					'<a href="video.html?id='+data.videos[v].id+'" id="naslov">'+data.videos[v].name+'</a>'+
					'<a href="profile.html" id="user">'+data.videos[v].owner.username+'</a>'+
					'<span id="views">'+data.videos[v].views+' views</span>'+
					'<span id="date">06.02.2018.</span>'+
				'</div>');
		}
		
		
		if(data.loggedInUser == null || data.loggedInUser.role != 'ADMIN'){
			$('#blockbtn').css('display', 'none');
			$('#deletebtn').css('display', 'none');
		}
		
		if (data.loggedInUser.username == data.user.username){
			$('#followbtn').css('visibility', 'hidden');
			
			for(v in data.videos){
				$('.recommended').append('<div id="videoHome">'+
						'<div class="thumbnailWrapper">'+
							'<a href="video.html?id='+data.videos[v].id+'"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
						'</div>'+
						'<a href="video.html?id='+data.videos[v].id+'" id="naslov">'+data.videos[v].name+'</a>'+
						'<a href="profile.html" id="user">'+data.videos[v].owner.username+'</a>'+
						'<span id="views">'+data.videos[v].views+' views</span>'+
						'<span id="date">06.02.2018.</span>'+
					'</div>');
			}
			
		} else {
			for(v in data.videos){
				if(data.videos[v].visibility == 'PUBLIC'){
					$('.recommended').append('<div id="videoHome">'+
							'<div class="thumbnailWrapper">'+
								'<a href="video.html?id='+data.videos[v].id+'"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
							'</div>'+
							'<a href="video.html?id='+data.videos[v].id+'" id="naslov">'+data.videos[v].name+'</a>'+
							'<a href="profile.html" id="user">'+data.videos[v].owner.username+'</a>'+
							'<span id="views">'+data.videos[v].views+' views</span>'+
							'<span id="date">06.02.2018.</span>'+
						'</div>');
				}
			}
		}
		
		
	});
	

});