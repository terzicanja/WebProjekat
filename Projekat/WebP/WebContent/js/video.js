$(document).ready(function(){
	var current;
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	$('#sort').on('click', function(){
		console.log('izgleda da ne ulazi ovde uopste');
		$("#myDropdown").toggle("show");
	});

	window.onclick = function(event) {
		if (!event.target.matches('.dropbtn')) {

			var dropdowns = document
					.getElementsByClassName("dropdown-content");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show')) {
					openDropdown.classList.remove('show');
				}
			}
		}
	}
	
	$.get('VideoServlet', {'id': id, 'sort': 'none'}, function(data){
//		console.log(data.video);
		if(data.videoStatus == 'cantSeeVideo'){
			console.log('ne mozes videti video ajde radi pliz');
			$('.container').empty();
			$('.container').html("<p>This video has been blocked or deleted</p>");
		}else if(data.videoStatus == 'privateVideo'){
			$('.container').empty();
			$('.container').html("<p>This video is private</p>");
		}
		
		if(data.ratingStatus == 'neMozee'){
			console.log('ne mozes da vidis rating');
			$('#rejtovanje').remove();
		}
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
			$('#comm').append('<div class="komentar" id="'+data.comments[c].id+'">'+
					'<a href="profile.html?id='+data.comments[c].author.username+'" id="user'+data.comments[c].id+'"><b>'+data.comments[c].author.username+'</b></a>'+
					'<div id="dislikeRating">'+data.comments[c].dislikesNumber+'</div>'+
					'<a href="#" id="dislikeComment" class="'+data.comments[c].id+'"><i class="material-icons">thumb_down</i></a>'+
					'<a href="#" id="likeComment" class="'+data.comments[c].id+'"><i class="material-icons">thumb_up</i></a>'+
					'<div id="likeRating">'+data.comments[c].likesNumber+'</div><br>'+
					'<div class="comment" id="content'+data.comments[c].id+'">'+data.comments[c].content+'</div>'+
					'<!--<button id="like"><i class="material-icons">thumb_up</i></button>-->'+
					'<span id="dateComm'+data.comments[c].id+'">'+data.comments[c].date+'</span>'+
					'<input type="button" id="editComment" name="'+data.comments[c].id+'" value="Edit">'+
					'<input type="button" id="deleteComment" name="'+data.comments[c].id+'" value="Delete">'+
					'<!--<button type="button" id="deleteComment" name="'+data.comments[c].id+'">delete</button>-->'+
				'</div>');
		}
		
		
		$('input[type=button]#deleteComment').on('click', function(event){
			console.log('kliknut je delete');
			var commentId = $(this).attr('name');
			var f = '#user' + commentId;
			var user = $(f).text();
			
			if(data.loggedInUser != null && (data.loggedInUser.username == user || data.loggedInUser.role == 'ADMIN')){
				$.post('CommentServlet', {'status': 'delete', 'commentId': commentId, 'id': id}, function(data){
					location.reload();
				});
			}else{
				alert('u cant delete this comment');
			}
			
			event.preventDefault();
			return false;
		});
		
		
		$('input[type=button]#editComment').on('click', function(event){
			console.log('kliknut edit comment');
			var commentId = $(this).attr('name');
			console.log('id komentara je ovaj: '+commentId);
			var find = '#content' + commentId;
			var f = '#user' + commentId;
			var addTextarea = '#dateComm' + commentId;
			var old = $(find).text();
			var user = $(f).text();
			console.log(old);
			
			console.log('loggedinuser za komentare je: '+data.loggedInUser);
			
			if(data.loggedInUser != null && (data.loggedInUser.username == user || data.loggedInUser.role == 'ADMIN')){
				$(find).hide();
				$(addTextarea).before('<textarea id="editContent'+commentId+'" maxlength="100">'+old+'</textarea>');
				var editContent = '#editContent' + commentId;
				$(editContent).after('<button type="button" class="cancelEdit" id="cancelEdit'+commentId+'" name="'+commentId+'">cancel</button>');
				$(editContent).after('<button type="button" class="confirmEdit" id="confirmEdit'+commentId+'" name="'+commentId+'">confirm</button>');
				
				$('.confirmEdit').on('click', function(event){
					console.log('potvrdjujem edit');
					var newId = $(this).attr('name');
					var newContent = '#editContent' + newId;
					var newComment = $(newContent).val();
					$.post('CommentServlet',{'id': id, 'newId':newId,'status':"edit",'newComment':newComment},function(data){
//						var oldContent=$(select).text(content);
//						var oldDate=$(dateSelect).text(data.newDate);
						$(newContent).fadeOut();
						$(find).fadeIn();
						$(find).text(newComment);
						var brisi = '#cancelEdit'+newId;
						var brisi2 = '#confirmEdit'+newId;
						$(brisi).fadeOut();
						$(brisi2).fadeOut();
					});
					
					event.preventDefault();
					return false;
				});
				
				$('.cancelEdit').on('click', function(event){
					console.log('cancel edit');
					var contentId = $(this).attr('name');
					var editContent = '#editContent' + contentId;
					var cancelEdit = '#cancelEdit' + contentId;
					var confirmEdit = '#confirmEdit' + contentId;
					var findOld = '#content' + contentId;
					
					$(editContent).remove();
					$(cancelEdit).remove();
					$(confirmEdit).remove();
					
					$(findOld).show();
					
					event.preventDefault();
					return false;
				});
			}else{
				alert('u cant edit comment');
			}
			
			
//			if(data.loggedInUser == null || data.loggedInUser.username != user || data.loggedInUser.role != 'ADMIN'){
//				alert('u cant edit comment');
//			}else{
//				alert('udji ovde jebem ti mateeeer');
//			}
			
			
			
			event.preventDefault();
			return false;
		});
		
		
		$('#addVideo').on('click', function(event){
			if(data.loggedInUser == null){
				alert('morate se ulogovati');
			}else{
				window.location.replace('add.html?doing=add&id=0');
			}
		});
		
		
		$('#editVideo').on('click', function(event){
			if(data.loggedInUser == null){
				alert('ne mozete menjati ovaj video');
			}else if(data.loggedInUser.role == 'ADMIN' || data.loggedInUser.username == data.video.owner.username){
				window.location.replace('add.html?doing=edit&id='+id);
			}else{
				alert('ne mozete menjati ovaj video');
			}
			
		});
		
		$('#deleteVideo').on('click', function(event){
//			window.location.replace('add.html?doing=edit&id='+id);
			
			if(data.loggedInUser == null){
				alert('ne mozete brisati ovaj video');
			}else if(data.loggedInUser.role == 'ADMIN' || data.loggedInUser.username == data.video.owner.username){
				$.post('VideoServlet', {'id':id, 'doing':'delete', 'url':'url', 'name':'name', 'description':'description', 'visibility':'visibility', 'comments':'comments', 'rating':'rating'}, function(data){
					location.reload();
				});
			}else{
				alert('ne mozete brisati ovaj video');
			}
			
			
		});
		
		
		$('#likeComment').on('click', function(event){
			var commentId = $(this).attr('class');
			console.log('lajkovanje komentara ' + commentId);
			$.get('RatingServlet', {'id': id, 'commentId': commentId, 'what': 'comment'}, function(data){
				$("#likeRating").text(data.commentLikes);
				$("#dislikeRating").text(data.commentDislikes);
//				console.log(status);
				
				if(data.commentStatus == "cannotLike"){
					alert("u cant rate this comment");
					return false;
				}
				
				if(data.commentStatus == "commentLiked"){
					console.log("ulazi ovde lajkovi komentaraaa");
					$("#likeComment").css('color', 'green');
					$("#dislikeComment").css('color', '#D3D3D3');
				} else if (data.commentStatus == "unrated"){
					$("#dislikeComment").css('color', '#D3D3D3');
					$("#likeComment").css('color', '#D3D3D3');
				}
			});
			event.preventDefault();
			return false;
		});
		
		$('#dislikeComment').on('click', function(event){
			var commentId = $(this).attr('class');
			$.post('RatingServlet', {'id': id, 'commentId': commentId, 'what': 'comment'}, function(data){
				$("#dislikeRating").text(data.commentDislikes);
				$("#likeRating").text(data.commentLikes);
//				console.log(status);
				
				if(data.commentStatus == "cannotLike"){
					alert("u cant rate this comment");
					return false;
				}
				
				if(data.commentStatus == "commentDisliked"){
					console.log("ulazi ovde dissss");
					$("#dislikeComment").css('color', 'red');
					$("#likeComment").css('color', '#D3D3D3');
				} else if (data.status == "unrated"){
					$("#dislikeComment").css('color', '#D3D3D3');
					$("#likeComment").css('color', '#D3D3D3');
				}
			});
			event.preventDefault();
			return false;
		});
	});
	
	
	
	
	$('#like').on('click', function(event){
		$.get('RatingServlet', {'id': id, 'commentId': '0', 'what': 'video'}, function(data){
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
				$("#like").css('color', '#D3D3D3');
			}
		});
		event.preventDefault();
		return false;
	});
	
	$('#dislike').on('click', function(event){
		$.post('RatingServlet', {'id': id, 'commentId': '0', 'what': 'video'}, function(data){
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
				$("#like").css('color', '#D3D3D3');
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
			if(data.commentsAllowed == 'no'){
				$('#addContent').val('');
				alert('ne mozete postaviti komentar');
			}else{
				$('#addContent').val('');
				console.log('komentar je postavljen');
//				location.reload();
			}
//			$('#addContent').val('');
			
//			$('#komPoStr').after('<div class="komentar" id="'+data.comments[c].id+'">'+
//					'<a href="profile.html?id='+data.comments[c].author.username+'" id="user"><b>'+data.comments[c].author.username+'</b></a>'+
//					'<div id="dislikeRating">'+data.comments[c].dislikesNumber+'</div>'+
//					'<a href="#" id="dislikeComment" class="'+data.comments[c].id+'"><i class="material-icons">thumb_down</i></a>'+
//					'<a href="#" id="likeComment" class="'+data.comments[c].id+'"><i class="material-icons">thumb_up</i></a>'+
//					'<div id="likeRating">'+data.comments[c].likesNumber+'</div><br>'+
//					'<div class="comment" id="content'+data.comments[c].id+'">'+data.comments[c].content+'</div>'+
//					'<!--<button id="like"><i class="material-icons">thumb_up</i></button>-->'+
//					'<span id="dateComm'+data.comments[c].id+'">10.02.2016.</span>'+
//					'<input type="button" id="editComment" name="'+data.comments[c].id+'" value="Edit">'+
//					'<input type="button" id="deleteComment" name="'+data.comments[c].id+'" value="Delete">'+
//					'<!--<button type="button" id="deleteComment" name="'+data.comments[c].id+'">delete</button>-->'+
//				'</div>');
			
		});
		event.preventDefault();
		return false;
	});
	
	
	
	$('#mostPopular, #leastPopular, #newest, #oldest').on('click', function(event){
		var sort = $(this).attr('id');
		console.log('sortiraj po: '+ sort);
		
		$('#comm').empty();
		console.log('tu bi trebalo da obrise sve prethodne');
		
		$.get('VideoServlet', {'id': id, 'sort': sort}, function(data){
			
			for(c in data.comments){
				$('#comm').append('<div class="komentar" id="'+data.comments[c].id+'">'+
						'<a href="profile.html?id='+data.comments[c].author.username+'" id="user'+data.comments[c].id+'"><b>'+data.comments[c].author.username+'</b></a>'+
						'<div id="dislikeRating">'+data.comments[c].dislikesNumber+'</div>'+
						'<a href="#" id="dislikeComment" class="'+data.comments[c].id+'"><i class="material-icons">thumb_down</i></a>'+
						'<a href="#" id="likeComment" class="'+data.comments[c].id+'"><i class="material-icons">thumb_up</i></a>'+
						'<div id="likeRating">'+data.comments[c].likesNumber+'</div><br>'+
						'<div class="comment" id="content'+data.comments[c].id+'">'+data.comments[c].content+'</div>'+
						'<!--<button id="like"><i class="material-icons">thumb_up</i></button>-->'+
						'<span id="dateComm'+data.comments[c].id+'">'+data.comments[c].date+'</span>'+
						'<input type="button" id="editComment" name="'+data.comments[c].id+'" value="Edit">'+
						'<input type="button" id="deleteComment" name="'+data.comments[c].id+'" value="Delete">'+
						'<!--<button type="button" id="deleteComment" name="'+data.comments[c].id+'">delete</button>-->'+
					'</div>');
			}
		});
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