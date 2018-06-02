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
		$("#brojLajkova").text(data.videoLikes);
		$("#brojDislajkova").text(data.videoDislikes);
		
		if(data.status == "liked"){
			console.log("ulazi ovde lajkovi videoservlet");
			$("#like").css('color', 'green');
			$("#dislike").css('color', '#D3D3D3');
		} else if (data.status == "unrated"){
			$("#dislike").css('color', '#D3D3D3');
			$("#like").css('color', '#D3D3D3');
		} else if (data.status == "disliked"){
			$("#like").css('color', '#D3D3D3');
			$("#dislike").css('color', 'red');
		}
		
		
		for(c in data.comments){
			$('#komPoStr').after('<div class="komentar" id="'+data.comments[c].id+'">'+
					'<a href="profile.html?id='+data.comments[c].author.username+'" id="user"><b>'+data.comments[c].author.username+'</b></a>'+
					'<div id="dislikeRating">8</div>'+
					'<a href="#" id="dislike"><i class="material-icons">thumb_down</i></a>'+
					'<a href="#" id="like"><i class="material-icons">thumb_up</i></a>'+
					'<div id="likeRating">23</div><br>'+
					'<div class="comment" id="content'+data.comments[c].id+'">'+data.comments[c].content+'</div>'+
					'<!--<button id="like"><i class="material-icons">thumb_up</i></button>-->'+
					'<span id="dateComm'+data.comments[c].id+'">10.02.2016.</span>'+
					'<input type="button" id="editComment" name="'+data.comments[c].id+'" value="Edit">'+
					'<input type="button" id="deleteComment" name="'+data.comments[c].id+'" value="Delete">'+
					'<!--<button type="button" id="deleteComment" name="'+data.comments[c].id+'">delete</button>-->'+
				'</div>');
		}
		
		
		$('input[type=button]#deleteComment').on('click', function(event){
			console.log('kliknut je delete');
			var commentId = $(this).attr('name');
			$.post('CommentServlet', {'status': 'delete', 'commentId': commentId}, function(data){
				
			});
			event.preventDefault();
			return false;
		});
		
		
		$('input[type=button]#editComment').on('click', function(event){
			console.log('kliknut edit comment');
			var commentId = $(this).attr('name');
			console.log('id komentara je ovaj: '+commentId);
			var find = '#content' + commentId;
			var addTextarea = '#dateComm' + commentId;
			var old = $(find).text();
			console.log(old);
			$(find).hide();
			$(addTextarea).before('<textarea id="editContent'+commentId+'" maxlength="100">'+old+'</textarea>');
			var editContent = '#editContent' + commentId;
			$(editContent).after('<button type="button" id="cancelEdit" name="'+commentId+'">cancel</button>');
			$(editContent).after('<button type="button" id="confirmEdit" name="'+commentId+'">confirm</button>');
			
			$('#confirmEdit').on('click', function(event){
				console.log('potvrdjujem edit');
				var newId = $(this).attr('name');
				var newContent = '#editContent' + newId;
				var newComment = $(newContent).val();
				$.post('CommentServlet',{'id': id, 'newId':newId,'status':"edit",'newComment':newComment},function(data){
//					var oldContent=$(select).text(content);
//					var oldDate=$(dateSelect).text(data.newDate);
					$(newContent).fadeOut();
					$(find).text(newComment);
				});
				
				event.preventDefault();
				return false;
			});
			
			$('#cancelEdit').on('click', function(event){
				console.log('cancel edit');
				var contentId = $(this).attr('name');
				var editContent = '#editContent' + contentId;
				var cancelEdit = '#cancelEdit' + contentId;
				var confirmEdit = '#confirmEdit' + contentId;
				var findOld = '#content' + contentId;
				
				$(editContent).remove();
				$('#cancelEdit').remove();
				$('#confirmEdit').remove();
				
				$(findOld).show();
				
				event.preventDefault();
				return false;
			});
			
			event.preventDefault();
			return false;
		});
		
		
		$('#addVideo').on('click', function(event){
			window.location.replace('add.html?doing=add&id=0');
		});
		
		
		$('#editVideo').on('click', function(event){
			window.location.replace('add.html?doing=edit&id='+id);
		});
		
		
	});
	
	
	
	
	$('#like').on('click', function(event){
		$.get('RatingServlet', {'id': id}, function(data){
			$("#brojLajkova").text(data.numberOfLikes);
			$("#brojDislajkova").text(data.numberOfDislikes);
			console.log(status);
			
			if(data.status == "cannotLike"){
				alert("u cant rate this video");
				return false;
			}
			
			if(data.status == "liked"){
				console.log("ulazi ovde lajkovi");
				$("#like").css('color', 'green');
				$("#dislike").css('color', '#D3D3D3');
			} else if (data.status == "unrated"){
				$("#dislike").css('color', '#D3D3D3');
			}
		});
		event.preventDefault();
		return false;
	});
	
	$('#dislike').on('click', function(event){
		$.post('RatingServlet', {'id': id}, function(data){
			$("#brojDislajkova").text(data.numberOfDislikes);
			$("#brojLajkova").text(data.numberOfLikes);
			console.log(status);
			
			if(data.status == "cannotLike"){
				alert("u cant rate this video");
				return false;
			}
			
			if(data.status == "disliked"){
				console.log("ulazi ovde dissss");
				$("#dislike").css('color', 'red');
				$("#like").css('color', '#D3D3D3');
			} else if (data.status == "unrated"){
				$("#dislike").css('color', '#D3D3D3');
			}
		});
		event.preventDefault();
		return false;
	});
	
	
	$('#postComment').on('click', function(event){
		console.log('kliknut post comment');
		var contentInput = $('#addContent');
		var content = contentInput.val();
		$.post('CommentServlet', {'content': content, 'status': 'add', 'id': id}, function(data){
			
		});
		event.preventDefault();
		return false;
	});
	
	
	
	
//	$('input[type=button]#deleteComment').on('click', function(event){
//		console.log('kliknut je delete');
//		var commentId = $(this).attr('name');
//		$.post('CommentServlet', {'status': 'delete', 'commentId': commentId}, function(data){
//			$('#addContent').clear();
//		});
//		event.preventDefault();
//		return false;
//	});
	
//	$('#editComment').on('click', function(event){
//		console.log('kliknut edit comment');
//		
//		event.preventDefault();
//		return false;
//	});
	
	
	
	
	
	
	$.get('LoggedInServlet', function(data) {
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