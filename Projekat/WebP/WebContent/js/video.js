$(document).ready(function(){
	var current;
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