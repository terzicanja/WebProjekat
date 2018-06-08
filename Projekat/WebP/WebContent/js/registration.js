$(document).ready(function(){
	var doing = window.location.search.slice(1).split('&')[0].split('=')[1];
	var id = window.location.search.slice(1).split('&')[1].split('=')[1];
	
	var usernameInput = $('#usernameInput');
	var passInput = $('#passInput');
	var emailInput = $('#emailInput');
	var nameInput = $('#nameInput');
	var lastnameInput = $('#lastnameInput');
	var descriptionInput = $('#descriptionInput');
	
	
	if(doing == 'edit'){
		$.get('UserServlet', {'id': id}, function(data){
			$('#doing').text('Edit');
			
			if(data.loggedInUser == null || data.loggedInUser == 'undefined'){
				window.location.replace('register.html?doing=add&id=0');
			}else if(data.loggedInUser.role != 'ADMIN' && data.loggedInUser.username != data.user.username){
				$('.container').empty();
				$('.container').text('U cant edit this user');
			}else{
				usernameInput.val(data.user.username);
				passInput.val(data.user.password);
				emailInput.val(data.user.email);
				nameInput.val(data.user.name);
				lastnameInput.val(data.user.lastname);
				descriptionInput.val(data.user.description);
				$("#usernameInput").prop('disabled', true);
				$('#registerbtn').text('Edit');
				if(data.loggedInUser.role == 'ADMIN'){
					$('#reg').before('<tr>'+
	                        '<td>Role:</td>'+
	                        '<td><input type="radio" name="role" id="admin" value="ADMIN" checked> Admin <input type="radio" id="user" name="role" value="USER"> User</td>'+
	                    '</tr><tr>'+
	                        '<td>Blocked:</td>'+
	                        '<td><input type="radio" name="block" id="byes" value="true" checked> Yes <input type="radio" id="bno" name="block" value="false"> No</td>'+
	                    '</tr><tr>'+
	                    '<td>Deleted:</td>'+
	                    '<td><input type="radio" name="del" id="dyes" value="true" checked> Yes <input type="radio" id="dno" name="del" value="false"> No</td>'+
	                '</tr>');
					
					if(data.user.role == 'ADMIN'){
						$('#admin').prop('checked',true);
					}else if(data.user.role == 'USER'){
						$('#user').prop('checked',true);
					}
					
					if(data.user.blocked == true){
						$('#byes').prop('checked',true);
					}else if(data.user.blocked == false){
						$('#bno').prop('checked',true);
					}
					
					if(data.user.deleted == true){
						$('#dyes').prop('checked',true);
					}else if(data.user.deleted == false){
						$('#dno').prop('checked',true);
					}
			}
			
			
			}
		});
	}
	
	$('#registerbtn').on('click', function(event){
		var r = $('input[name=role]:checked');
		var b = $('input[name=block]:checked');
		var d = $('input[name=del]:checked');
		var username = usernameInput.val();
		var password = passInput.val();
		var email = emailInput.val();
		var name = nameInput.val();
		var lastname = lastnameInput.val();
		var description = descriptionInput.val();
		var role = r.val();
		var blocked = b.val();
		var deleted = d.val();
//		alert(username);
		console.log("username jee: " + username + 'i role je: '+ role);
		
		if(username == "" || password == ""){
			message.text("Morate popuniti username i password");
			return false;
		}
		
		if(role == null){
			role = "nista";
		}
		
		console.log('role bez admina je: ' + role);
		
		$.post('RegistrationServlet', {'doing': doing, 'id': id, 'username': username, 'password': password, 'email': email, 'name':name, 'lastname':lastname, 'description':description, 'role':role, 'blocked':blocked, 'deleted':deleted}, function(data){
			
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