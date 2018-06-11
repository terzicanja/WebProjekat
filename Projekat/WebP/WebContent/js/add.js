$(document).ready(function(){
	var doing = window.location.search.slice(1).split('&')[0].split('=')[1];
	var id = window.location.search.slice(1).split('&')[1].split('=')[1];
	var urlInput = $('#urlInput');
	var imgInput = $('#imgInput');
	var nameInput = $('#nameInput');
	var descriptionInput = $('#descriptionInput');
//	var v = $('input[name=visi]:checked');
//	var c = $('input[name=comments]:checked');
//	var r = $('input[name=rating]:checked');
//	var b = $('input[name=blocked]:checked');
//	var d = $('input[name=deleted]:checked');
	console.log('id videa za editovanje' + id);
	console.log('doingggg '+ doing);
	
	if(doing == 'add'){
		console.log('ovo je dodavanje');
		$('.admin').css('display', 'none');
		$('#addbtn').on('click', function(event){
			var url = urlInput.val();
			var img = imgInput.val();
			var name = nameInput.val();
			var description = descriptionInput.val();
			var visibility = v.val();
			var comments = c.val();
			var rating = r.val();
			var blocked = b.val();
			var deleted = d.val();
			console.log('submitttttt');
			
			$.post('VideoServlet', {'id':id, 'doing':doing, 'url':url, 'img':img, 'name':name, 'description':description, 'visibility':visibility, 'comments':comments, 'rating':rating, 'blocked':blocked,'deleted':deleted}, function(data){
//				if(data.loggedInUser == null){
//					alert('u cant do this');
//				}
			});
			
		});
	}else if(doing == 'edit'){
		$.get('VideoServlet', {'id': id}, function(data){
			if(data.loggedInUser.role == 'USER'){
				$('.admin').css('display', 'none');
			}
			urlInput.val(data.video.videoURL);
			urlInput.prop('disabled', true);
			imgInput.val(data.video.videoImg);
			nameInput.val(data.video.name);
			descriptionInput.val(data.video.description);
			
			if(data.video.visibility == 'PUBLIC'){
				$('#public').prop('checked', true);
			}else if(data.video.visibility == 'UNLISTED'){
				$('#unlisted').prop('checked', true);
			}else if(data.video.visibility == 'PRIVATE'){
				$('#private').prop('checked', true);
			}
			
			if(data.video.commentsAllowed == true){
				$('#cyes').prop('checked', true);
			}else if(data.video.commentsAllowed == false){
				$('#cno').prop('checked', true);
			}
			
			if(data.video.ratingAllowed == true){
				$('#ryes').prop('checked', true);
			}else if(data.video.ratingAllowed == false){
				$('#rno').prop('checked', true);
			}
			
			if(data.video.blocked == true){
				$('#byes').prop('checked', true);
			}else if(data.video.blocked == false){
				$('#bno').prop('checked', true);
			}
			
			if(data.video.deleted == true){
				$('#dyes').prop('checked', true);
			}else if(data.video.deleted == false){
				$('#dno').prop('checked', true);
			}
//			v.val(data.video.visibility);
			
			$('#addbtn').on('click', function(event){
				var v = $('input[name=visi]:checked');
				var c = $('input[name=comments]:checked');
				var r = $('input[name=rating]:checked');
				var b = $('input[name=blocked]:checked');
				var d = $('input[name=deleted]:checked');
				console.log('editttt');
				var url = urlInput.val();
				var img = imgInput.val();
				var name = nameInput.val();
				var description = descriptionInput.val();
				var visibility = v.val();
				var comments = c.val();
				var rating = r.val();
				var blocked = b.val();
				var deleted = d.val();
				
				$.post('VideoServlet', {'id':id, 'doing':doing, 'url':url, 'img': img, 'name':name, 'description':description, 'visibility':visibility, 'comments':comments, 'rating':rating, 'blocked':blocked,'deleted':deleted}, function(data){
					window.location.replace('home.html');
				});
				
			});
		});
	}
	
//	$.post('VideoServlet', {'id': id, 'doing': }, function(data){
//		
//	});
	
});