$(document).ready(function(){
	
	
	$.get('AdminServlet', function(data){
		console.log(data);
		
		var loggedInUser = data.loggedInUser;
		var users = data.users;
		
		if(loggedInUser == null || loggedInUser.role != 'ADMIN'){
			$('#users').empty();
            $('#users').html("<p>Ne mozete pristupiti ovoj stranici</p>");
		} else {
			for(u in users){
//				$('#users').append('<tr><td><div id="user"> <button id="delete" onclick="potvrda()"><i class="material-icons">delete</i></button>'+
//						'<a href="#">'+users[u].username +'</a><p><span>'+data.users[u].name +'</span> <span>'+data.users[u].lastname +'</span></p>'+
//						' <button id="edit"><i class="material-icons">mode_edit</i></button>'+
//						'<p>'+data.users[u].email +'</p>'+
//						'<p>'+data.users[u].role +'</p>'+
//						'</div></td></tr>');
				
//				$('.container').append('<div id="user"> <button id="delete" onclick="potvrda()"><i class="material-icons">delete</i></button>'+
//						+'<a href="#">'+data.users[u].username +'</a>'+
//						+'<p><span>'+data.users[u].firstname +'</span> <span>'+data.users[u].lastname +'</span></p> <button id="edit"><i class="material-icons">mode_edit</i></button>'+
//						+'<p>'+data.users[u].email +'</p>'+
//						+'<p>'+data.users[u].role +'</p>'+
//						+'</div>');
				if(users[u].deleted === true){
					$('#users').append('<tr><td><div id="user"> <button id="delete" style="background-color:red;" onclick="potvrda()"><i class="material-icons">delete</i></button>'+
							'<a href="#">'+users[u].username +'</a><p><span>'+data.users[u].name +'</span> <span>'+data.users[u].lastname +'</span></p>'+
							' <button id="edit"><i class="material-icons">mode_edit</i></button>'+
							'<p>'+data.users[u].email +'</p>'+
							'<p>Blocked: '+data.users[u].blocked +'</p>'+
							'<p>Deleted: '+data.users[u].deleted +'</p>'+
							'<p>'+data.users[u].role +'</p>'+
							'</div></td></tr>');
				}else{
					$('#users').append('<tr><td><div id="user"> <button id="delete" style="background-color:green;""><i class="material-icons">delete</i></button>'+
							'<a href="profile.html?id='+users[u].username+'">'+users[u].username +'</a><p><span>'+data.users[u].name +'</span> <span>'+data.users[u].lastname +'</span></p>'+
							' <button id="edit"><i class="material-icons">mode_edit</i></button>'+
							'<p>'+data.users[u].email +'</p>'+
							'<p>Blocked: '+data.users[u].blocked +'</p>'+
							'<p>Deleted: '+data.users[u].deleted +'</p>'+
							'<p>'+data.users[u].role +'</p>'+
							'</div></td></tr>');
				}
				
			}
			
			$('#delete').on('click', function(){
				var d = confirm('Da li ste sigurni da zelite da obrisete korisnika?');
				if(d == true){
					console.log('obrisan jee');
				}
			});
//			function potvrda(){
//				confirm('Da li ste sigurni da zelite da obrisete korisnika?');
//			}
			
			
//			$.each(users, function (index, user) {
//                $('#users').append('<tr><td><div id="user"> <button id="delete" onclick="potvrda()"><i class="material-icons">delete</i></button>'+
//						+'<a href="#">'+users.username +'</a>'+
//						+'<p><span>'+users.firstname +'</span> <span>'+users.lastname +'</span></p> <button id="edit"><i class="material-icons">mode_edit</i></button>'+
//						+'<p>'+data.users.email +'</p>'+
//						+'<p>'+data.users.role +'</p>'+
//						+'</div></td></tr>'
//                );
//            });
		}
		
//		for(v in data.videos){
//			$('.recommended').append('<div id="videoHome">'+
//					'<div class="thumbnailWrapper">'+
//						'<a href="video.html"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
//					'</div>'+
//					'<a href="video.html?id='+ data.videos[v].id +'" id="naslov">' + data.videos[v].name + '</a>'+
//					'<a href="profile.html" id="user">pero</a>'+
//					'<span id="views">'+ data.videos[v].views +'</span>'+
//					'<span id="date">06.02.2018.</span>'+
//				'</div>')
//		}
//		
//		for(u in data.topFive){
//			$('#top').append('<div id="osoba"><div id="korisnickoIme">'+
//					'<a href="profile.html">' + data.topFive[u].username + '</a></div>'+
//					'<div id="foloveri">'+ data.topFive[u].subsNumber +' followers</div><button id="zafoluj">Follow</button></div>');
//		}
		
		
		
	});
	

});