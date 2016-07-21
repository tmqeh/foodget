// 사용할 앱의 JavaScript 키를 설정해 주세요.
 Kakao.init('e5c985a3818ec24af24b34c20d4aa905');
 // 카카오 로그인 버튼을 생성합니다.
 $("#kakao-btn").click(function(){ 		
    Kakao.Auth.login({
    success: function(authObj) {
		// 로그인 성공시, API를 호출합니다.
		Kakao.API.request({
			url: '/v1/user/me',
			success: function(res) {
				$("#kakaoJson").val(JSON.stringify(res));
				$("#kakaoflag").val("kakao");
				document.loginform.action = root+"/member/login.html";
				document.loginform.submit();
			},
			fail: function(error) {
				alert(JSON.stringify(error));
			}
		});
	},
	fail: function(err) {
		alert(JSON.stringify(err));
	}
	});
 });
