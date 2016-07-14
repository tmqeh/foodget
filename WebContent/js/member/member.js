	var emailck = false;
	var nameck = false;
	var pwck = false;
	var root = root;
	
	function join() {
		emailcheck();
		namecheck();
		pwcheck();
		
		if(emailck == false) {
			return;
		} else if(nameck == false) {
			return;
		} else if(pwck == false) {
			return;
		} else {
		document.joinform.action = root+"/member/member.html";
		document.joinform.submit();
		}
	}
	
	function emailcheck() {
		var checkemail = $("#email").val();
		var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		
		if(checkemail.length==0) {
			$("#emailEmpty").show();
			$("#email").addClass('joincheck-fail');
			$("#email").removeClass('joincheck-ok');
			$("#emailFormCheck").hide();
		} else {			 
			$("#emailEmpty").hide();
			if(regex.test(checkemail) === false) {
				$("#emailFormCheck").show();			
			} else {
				$("#emailFormCheck").hide();
				$("#email").removeClass('joincheck-fail');
				$("#email").addClass('joincheck-ok');
				$.ajax ({
					type:"GET",
					url:root+"/member/idcheck.html",
					dataType:"json",
					data: {"email": checkemail},
					success:function(data) {
						if(data.cnt == 0) {
							$("#emailCheck").hide();
							$("#email").addClass('joincheck-ok');
							$("#email").removeClass('joincheck-fail');
							emailck=true;
						} else {
							$("#emailCheck").show();
							$("#email").addClass('joincheck-fail');
							$("#email").removeClass('joincheck-ok');
						}				
					}
				});
			}			
		}		
	}

	function namecheck() {
		var checkname = $("#name").val();
		if(checkname.length==0) {
			$("#nameEmpty").show();
			$("#name").addClass('joincheck-fail');
			$("#name").removeClass('joincheck-ok');
		} else {
			$("#nameEmpty").hide();
			$("#name").removeClass('joincheck-fail');
			$("#name").addClass('joincheck-ok');
			nameck=true;
		}
	}
	
	function pwcheck() {
		var checkpw = $("#password").val();
		if(checkpw.length==0) {
			$("#pwEmpty").show();
			$("#password").addClass('joincheck-fail');
			$("#password").removeClass('joincheck-ok');
		} else {
			$("#pwEmpty").hide();
			$("#password").removeClass('joincheck-fail');
			$("#password").addClass('joincheck-ok');			
			 
			if(checkpw.length < 8) {
				$("#pwCheck").show();
				$("#password").addClass('joincheck-fail');
				$("#password").removeClass('joincheck-ok');
			} else {
				$("#pwCheck").hide();
				$("#password").removeClass('joincheck-fail');
				$("#password").addClass('joincheck-ok');
				pwck=true;
			}	
		}
	}
	
	function login() {			
		document.loginform.action = root+"/member/login.html";
		document.loginform.submit();
	}
   
    var loginFail = loginFail;
	$(document).ready(function(){
		if(loginFail == "loginFail") {
			$("#loginModal").modal("show");
			$("#loginfail").show();
			$("#password_login").addClass('joincheck-fail');
			$("#email_login").addClass('joincheck-fail');
		}
	});