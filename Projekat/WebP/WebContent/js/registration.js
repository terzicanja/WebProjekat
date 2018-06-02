$(document).ready(function(){
	var doing = window.location.search.slice(1).split('&')[0].split('=')[1];
	var id = window.location.search.slice(1).split('&')[1].split('=')[1];
	
	var usernameInput = $('#usernameInput');
	var passInput = $('#passInput');
	var emailInput = $('#emailInput');
	var nameInput = $('#nameInput');
	var lastnameInput = $('#lastnameInput');
	var descriptionInput = $('#descriptionInput');
	
	$('#registerbtn').on('click', function(event){
		var username = usernameInput.val();
		var password = passInput.val();
		var email = emailInput.val();
		var name = nameInput.val();
		var lastname = lastnameInput.val();
		var description = descriptionInput.val();
//		alert(username);
		console.log("username jee: " + username);
		
		if(username == "" || password == ""){
			message.text("Morate popuniti username i password");
			return false;
		}
		
		$.post('RegistrationServlet', {'doing': doing, 'id': id, 'username': username, 'password': password, 'email': email, 'name':name, 'lastname':lastname, 'description':description}, function(data){
			
			if(data.status == 'success'){
				window.location.replace('home.html');
			}else if(data.status == 'existing' && doing == 'add'){
				alert("Username je zauzet");
			}
			
		});
		
		event.preventDefault();
		return false;
		
	});
	
	

});