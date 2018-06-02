$(document).ready(function(){
	var doing = window.location.search.slice(1).split('&')[0].split('=')[1];
	var id = window.location.search.slice(1).split('&')[1].split('=')[1];
	var urlInput = $('#urlInput');
	var nameInput = $('#nameInput');
	var descriptionInput = $('#descriptionInput');
	var v = $('input[name=visi]:checked');
	var c = $('input[name=comments]:checked');
	var r = $('input[name=rating]:checked');
	console.log('id videa za editovanje' + id);
	console.log('doingggg '+ doing);
	
	if(doing == 'add'){
		console.log('ovo je dodavanje');
		$('#addbtn').on('click', function(event){
			var url = urlInput.val();
			var name = nameInput.val();
			var description = descriptionInput.val();
			var visibility = v.val();
			var comments = c.val();
			var rating = r.val();
			console.log('submitttttt');
			
			$.post('VideoServlet', {'id':id, 'doing':doing, 'url':url, 'name':name, 'description':description, 'visibility':visibility, 'comments':comments, 'rating':rating}, function(data){
				
			});
			
		});
	}else if(doing == 'edit'){
		$.get('VideoServlet', {'id': id}, function(data){
			urlInput.val(data.video.videoURL);
			nameInput.val(data.video.name);
			descriptionInput.val(data.video.description);
//			v.val(data.video.visibility);
			
			$('#addbtn').on('click', function(event){
				console.log('editttt');
				var url = urlInput.val();
				var name = nameInput.val();
				var description = descriptionInput.val();
				var visibility = v.val();
				var comments = c.val();
				var rating = r.val();
				
				$.post('VideoServlet', {'id':id, 'doing':doing, 'url':url, 'name':name, 'description':description, 'visibility':visibility, 'comments':comments, 'rating':rating}, function(data){
					
				});
				
			});
		});
	}
	
//	$.post('VideoServlet', {'id': id, 'doing': }, function(data){
//		
//	});
	
});