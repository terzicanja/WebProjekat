$(document).ready(function(){
	
	$.get('HomeServlet', {'sort': 'none'}, function(data){
		console.log(data);
//		console.log('ulogovani jeee '+loggedInUser);
		for(v in data.videos){
			if(data.loggedInUser == null || (data.loggedInUser.role != 'ADMIN')){
				if(data.videos[v].visibility == 'PUBLIC' && data.videos[v].deleted == false && data.videos[v].owner.blocked == false && data.videos[v].owner.deleted == false){
					$('.recommended').append('<div id="videoHome">'+
							'<div class="thumbnailWrapper">'+
								'<a href="video.html?id='+ data.videos[v].id +'"><img src="'+data.videos[v].videoImg+'" id="thumbnail"></a>'+
							'</div>'+
							'<a href="video.html?id='+ data.videos[v].id +'" id="naslov">' + data.videos[v].name + '</a>'+
							'<a href="profile.html?id='+ data.videos[v].owner.username +'" id="user">'+ data.videos[v].owner.username +'</a>'+
							'<span id="views">'+ data.videos[v].views +' views</span>'+
							'<span id="date">'+ data.videos[v].date +'</span>'+
						'</div>')
				}
			}else if(data.loggedInUser.role == 'ADMIN'){
				$('.recommended').append('<div id="videoHome">'+
						'<div class="thumbnailWrapper">'+
							'<a href="video.html?id='+ data.videos[v].id +'"><img src="'+data.videos[v].videoImg+'" id="thumbnail"></a>'+
						'</div>'+
						'<a href="video.html?id='+ data.videos[v].id +'" id="naslov">' + data.videos[v].name + '</a>'+
						'<a href="profile.html?id='+ data.videos[v].owner.username +'" id="user">'+ data.videos[v].owner.username +'</a>'+
						'<span id="views">'+ data.videos[v].views +' views</span>'+
						'<span id="date">'+ data.videos[v].date +'</span>'+
					'</div>')
			}
			
		}
		
		for(u in data.topFive){
			$('#top').append('<div id="osoba"><div id="korisnickoIme">'+
					'<a href="profile.html?id=' + data.topFive[u].username + '">' + data.topFive[u].username + '</a></div>'+
					'<div id="foloveri">'+ data.topFive[u].subsNumber +' followers</div><button id="zafoluj">Follow</button></div>');
		}
	});
	
	$('#mostPopular, #leastPopular, #newest, #oldest, #alphabeticAuthorReverse, #alphabeticAuthor, #alphabeticReverse, #alphabetic').on('click', function(event){
		var sort = $(this).attr('id');
		console.log('sortiraj po: '+ sort);
		
		$('.recommended').empty();
		
		$.get('HomeServlet', {'sort': sort}, function(data){
			console.log(data);
			for(v in data.videos){
				$('.recommended').append('<div id="videoHome">'+
						'<div class="thumbnailWrapper">'+
							'<a href="video.html?id='+ data.videos[v].id +'"><img src="'+data.videos[v].videoImg+'" id="thumbnail"></a>'+
						'</div>'+
						'<a href="video.html?id='+ data.videos[v].id +'" id="naslov">' + data.videos[v].name + '</a>'+
						'<a href="profile.html?id='+ data.videos[v].owner.username +'" id="user">'+ data.videos[v].owner.username +'</a>'+
						'<span id="views">'+ data.videos[v].views +' views</span>'+
						'<span id="date">'+ data.videos[v].date +'</span>'+
					'</div>')
			}
			
//			for(u in data.topFive){
//				$('#top').append('<div id="osoba"><div id="korisnickoIme">'+
//						'<a href="profile.html?id=' + data.topFive[u].username + '">' + data.topFive[u].username + '</a></div>'+
//						'<div id="foloveri">'+ data.topFive[u].subsNumber +' followers</div><button id="zafoluj">Follow</button></div>');
//			}
		});
		
	});
	
	
	$('#searchbtn').on('click', function(event){
		var srchinput = $('.srchinput');
		var search = srchinput.val();
		var title = false;
		var user = false;
		var comment = false;
		if($("#cbComment").is(':checked')){
			comment = "true";
		}
		if($("#cbTitle").is(':checked')){
			title = "true";
		}
		if($("#cbUser").is(':checked')){
			user = "true";
		}
		console.log('searchujem po: ' + title + user + comment);
		
		window.location.replace('search.html?search='+search+'&title='+title+'&user='+user+'&comment='+comment);
		
	});
	
	
	
	
	
	
//	$.ajax({
//		url: 'https://jsonplaceholder.typicode.com/users',
//		type: 'GET',
//		dataType: 'json',
//		success: function(response){
//			//var dict = [];
//			//console.log(response);
//			//var followers = response.address;
//			//var f = followers.zipcode.substring(0,4);
//			osobe.sort(function(a, b){
//				return a.zipcode < b.zipcode;
//			});
//			osobe.sort();
//			//console.log(response);
//
//			console.log(osobe);
//
//
//
//			for (var i = 0; i < 5; i++) {
//				var osoba = osobe[i];
//				var user = osoba.username;
//				var followers = osoba.zipcode.substring(0,4);
//
//				//dict = [{
//				//	ime: user,
//				//	folov: followers
//				//}];
//
//				//$('#top').data('osoba', {korisnickoIme: user, broj: followers});
//				//$('#top').data('brfolovera', followers);
//
//				var newDiv = $('<div id="osoba"><div id="korisnickoIme"><a href="profile.html">'+user+'</a></div><div id="foloveri">'+followers +' followers</div><button id="zafoluj">Follow</button></div>');
//				$('#top').append(newDiv);
//			}
			//console.log(dict);

			//var novo = dict.sort(function(a, b){
			//	return a.folov - b.folov
			//});
			//console.log("novo ajde"+novo);
			//for(var s = 0; i<5; s++){
			//	var newDiv = $('<div id="osoba"><div id="korisnickoIme">'+novo.ime+'</div><div id="foloveri">'+novo.folov +'followers</div><button id="zafoluj">Follow</button></div>');
			//	$('#top').prepend(newDiv);
			//}

			//var keyValues = [];
			//for(var key in dict){
			//	keyValues.push([key, dict[key]]);
			//}

			//keyValues.sort(function compare(a, b){
			//	return a[1] - b[1]
			//});
			//keyValues.sort();
			//for(var ia in keyValues){
			//	console.log("proba"+ia.ime);
			//}
			


			//var so = dict.sort(function (a, b){
			//	return a.folov - b.folov;
			//});



			//var sorted = [];
			//for(var key in osobe){
			//	sorted[sorted.length] = key;
			//}
			//sorted.sort();

			//var sortirani = osobe.sort($('#top').data('osoba').broj);
			//var sortirani = sorted.sort(function(a, b){
			//	return a.brojf.localeCompare(b.brojf);
			//	return a.osobe.broj > b.osobe.broj;
			//});
			//for(var s = 0; i<10; s++){
			//	var newDiv = $('<div id="osoba"><div id="korisnickoIme">'+so.ime+'</div><div id="foloveri">'+followers +'followers</div><button id="zafoluj">Follow</button></div>');
			//	$('#top').prepend(newDiv);
			//}
//		}
//	});


//var osobe = [
//  {
//    "id": 1,
//    "name": "Leanne Graham",
//    "username": "Bret",
//    "email": "Sincere@april.biz",
//    "zipcode": "9299"
//  },
//  {
//    "id": 2,
//    "name": "Ervin Howell",
//    "username": "Antonette",
//    "email": "Shanna@melissa.tv",
//    "zipcode": "90566"
//  },
//  {
//    "id": 3,
//    "name": "Clementine Bauch",
//    "username": "Samantha",
//    "email": "Nathan@yesenia.net",
//    "zipcode": "59590"
//  },
//  {
//    "id": 4,
//    "name": "Patricia Lebsack",
//    "username": "Karianne",
//    "email": "Julianne.OConner@kory.org",
//    "zipcode": "53919"
//  },
//  {
//    "id": 5,
//    "name": "Chelsey Dietrich",
//    "username": "Kamren",
//    "email": "Lucio_Hettinger@annie.ca",
//    "zipcode": "33263"
//  },
//  {
//    "id": 6,
//    "name": "Mrs. Dennis Schulist",
//    "username": "Leopoldo_Corkery",
//    "email": "Karley_Dach@jasper.info",
//    "zipcode": "23505"
//  },
//  {
//    "id": 7,
//    "name": "Kurtis Weissnat",
//    "username": "Elwyn.Skiles",
//    "email": "Telly.Hoeger@billy.biz",
//    "zipcode": "58804"
//  },
//  {
//    "id": 8,
//    "name": "Nicholas Runolfsdottir V",
//    "username": "Maxime_Nienow",
//    "email": "Sherwood@rosamond.me",
//    "zipcode": "45169"
//  },
//  {
//    "id": 9,
//    "name": "Glenna Reichert",
//    "username": "Delphine",
//    "email": "Chaim_McDermott@dana.io",
//    "zipcode": "76495"
//  },
//  {
//    "id": 10,
//    "name": "Clementina DuBuque",
//    "username": "Moriah.Stanton",
//    "email": "Rey.Padberg@karina.biz",
//    "zipcode": "31428"
//  }
//]
//
//
//$("#pagination a").click(function(){
//		$("#pagination a").removeClass("active");
//		$(this).addClass("active");
//	});
//
//
//
});