$(document).ready(function(){
	
	var usernameInput = $('#usernameInput');
	var passInput = $('#passInput');
	
	$('#registerbtn').on('click', function(event){
		var username = usernameInput.val();
		var password = passInput.val();
//		alert(username);
		console.log("username jee: " + username);
		
		if(username == "" || password == ""){
			message.text("Morate popuniti username i password");
			return false;
		}
		
		$.post('RegistrationServlet', {'username': username, 'password': password}, function(data){
			
			if(data.status == 'success'){
				window.location.replace('home.html');
			}else{
				alert("Username je zauzet");
			}
			
		});
		
		event.preventDefault();
		return false;
		
	});
	
	

});