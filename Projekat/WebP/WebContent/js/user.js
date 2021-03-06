$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	$.get('UserServlet', {'id': id}, function(data){
		console.log(data);
		
		if(data.userStatus == 'obrisan'){
			$('.content').empty();
            $('.content').html("<p>Korisnik sa ovim user-om vise ne postoji</p>");
		}else if(data.userStatus == 'blokiran'){
			$('.content').empty();
            $('.content').html("<p>Korisnik je trenutno blokiran</p>");
		}else if(data.userStatus == 'ok'){
			
			$('#korisnik').text(data.user.username);
			$('#profileDate').text(data.user.registrationDate);
			$('#brFollowers').text(data.user.subsNumber + ' followers');
			$('#opisKanala').text(data.user.description);
//			$('#korisnik').text(data.user.username);
			
			
			for(v in data.videos){
				$('.recommended').append('<div id="videoHome">'+
						'<div class="thumbnailWrapper">'+
							'<a href="video.html?id='+data.videos[v].id+'"><img src="'+data.videos[v].videoImg+'" id="thumbnail"></a>'+
						'</div>'+
						'<a href="video.html?id='+data.videos[v].id+'" id="naslov">'+data.videos[v].name+'</a>'+
						'<a href="profile.html" id="user">'+data.videos[v].owner.username+'</a>'+
						'<span id="views">'+data.videos[v].views+' views</span>'+
						'<span id="date">06.02.2018.</span>'+
					'</div>');
			}
			
			for(s in data.subscribedTo){
				$('#following').append('<div id="osoba">'+
						'<div id="korisnickoIme">'+data.subscribedTo[s].username+'</div>'+
						'<div id="foloveri">'+data.subscribedTo[s].subsNumber+' followers</div>'+
						'<button id="zafoluj">Follow</button>'+
					'</div>');
			}
			
			
			if(data.loggedInUser == null || data.loggedInUser.role != 'ADMIN'){
				$('#blockbtn').css('display', 'none');
				$('#deletebtn').css('display', 'none');
				$('#editbtn').css('display', 'none');
			}
			
			if(data.subs == 'following'){
				$('#followbtn').text('Following');
			}else{
				$('#followbtn').text('Follow');
			}
			
			if(data.user.blocked === true){
				console.log('user je blokiran');
				$('#blockbtn').text('Blocked');
			}else if(data.user.blocked === false){
				console.log('user nije blokiran');
				$('#blockbtn').text('Block');
			}
			
			if(data.user.deleted === true){
				console.log('user je deleted');
				$('#deletebtn').text('Deleted');
			}else if(data.user.deleted === false){
				console.log('user nije deleted');
				$('#deletebtn').text('Delete');
			}
			
			if (data.loggedInUser.username == data.user.username){
				$('#followbtn').css('visibility', 'hidden');
				$('#editbtn').show();
				
//				for(v in data.videos){
//					$('.recommended').append('<div id="videoHome">'+
//							'<div class="thumbnailWrapper">'+
//								'<a href="video.html?id='+data.videos[v].id+'"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
//							'</div>'+
//							'<a href="video.html?id='+data.videos[v].id+'" id="naslov">'+data.videos[v].name+'</a>'+
//							'<a href="profile.html" id="user">'+data.videos[v].owner.username+'</a>'+
//							'<span id="views">'+data.videos[v].views+' views</span>'+
//							'<span id="date">06.02.2018.</span>'+
//						'</div>');
//				}
				
			} else {
//				for(v in data.videos){
//					if(data.videos[v].visibility == 'PUBLIC'){
//						$('.recommended').append('<div id="videoHome">'+
//								'<div class="thumbnailWrapper">'+
//									'<a href="video.html?id='+data.videos[v].id+'"><img src="images/thumbnail.jpg" id="thumbnail"></a>'+
//								'</div>'+
//								'<a href="video.html?id='+data.videos[v].id+'" id="naslov">'+data.videos[v].name+'</a>'+
//								'<a href="profile.html" id="user">'+data.videos[v].owner.username+'</a>'+
//								'<span id="views">'+data.videos[v].views+' views</span>'+
//								'<span id="date">06.02.2018.</span>'+
//							'</div>');
//					}
//				}
			}
			
			$('#blockbtn').on('click', function(event){
				var blocktxt = $(this).text();
				console.log('blokiras korisnika' + blocktxt);
				$.post('UserServlet', {'id': id, 'status': 'block'}, function(data){
					if(blocktxt == 'Blocked'){
						$('#blockbtn').text('Block');
					}else if(blocktxt == 'Block'){
						$('#blockbtn').text('Blocked');
					}
				});
			});
			
			
			$('#deletebtn').on('click', function(event){
				var deletetxt = $(this).text();
				console.log('brises korisnika');
					$.post('UserServlet', {'id': id, 'status': 'delete'}, function(data){
						if(deletetxt == 'Deleted'){
							$('#deletebtn').text('Delete');
						}else if(deletetxt == 'Delete'){
							$('#deletebtn').text('Deleted');
						}
				});
			});
			
			
			$('#followbtn').on('click', function(event){
				console.log('pratis korisnika');
					$.post('UserServlet', {'id': id, 'status': 'follow'}, function(data){
						if(data.subs == 'cantFollowYourself'){
							alert('Ne mozes folovati sam sebe');
						}else{
							location.reload();
						}
						
				});
			});
			
			
			$('#editbtn').on('click', function(event){
				window.location.replace('register.html?doing=edit&id='+data.user.username+'');
			});
			
			
			
			
			
			
		}
		
		
	});
	

});