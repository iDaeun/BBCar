<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

</head>
<body>
	<h1>로그인</h1>
	<form id="form" class="form-inline" role="form">
		<table id="table">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="id" name="id" required></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="pw" name="pw" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" style="float: right"
					value="LOGIN"> <span id="span"></span></td>
			</tr>
			<tr>
				<td colspan="2"><a id="custom-login-btn" href="javascript:loginWithKakao()">
				<img style="float: right" src="images/kakao_account_login_btn_medium_narrow.png" /></a></td>
			</tr>
		</table>
	</form>
	
	<script>
	
		// KAKAO :: 사용할 앱의 JavaScript 키를 설정해 주세요.
	    Kakao.init('b4d22862e49da7bced7dc7592288a369');
		
		$(document).ready(function(){
			
			$('#form').submit(function(){
				
				var id = $('#id').val();
				
				$.ajax({
					url: 'http://localhost:9090/passenger/members/login',
					type: 'POST',
					data: $('#form').serialize(),
					success: function(data){
						if(data=='1'){
							alert('존재하지 않는 회원입니다, 다시 로그인해주세요.');
						} 
						if(data=='2'){
							// 정상 로그인
							// session에 아이디 저장
							alert(id);
							sessionStorage.setItem("login",id);
							alert('정상적으로 로그인되었습니다.');
							alert('session: '+sessionStorage.getItem("login"));
						} 
						if(data=='3'){
							alert('비밀번호 불일치, 다시 로그인해주세요.');
						} 
					}
				});
				return false;
			});
		});
		
		// 카카오 로그인
        function loginWithKakao() {
            // 로그인 창을 띄웁니다.
            Kakao.Auth.login({
                success: function(authObj) {
                   
                	alert('Auth.login >>>>> '+JSON.stringify(authObj));

                    // 정보 확인 -> id(email) session에 저장
                        Kakao.API.request({
                            url: '/v2/user/me',
                            success: function(res) {
                                alert('API.request >>>>> '+JSON.stringify(res));
                                var id = res.id;
                               	
                                sessionStorage.setItem("login",id);
                                alert('정상적으로 로그인되었습니다.');
    							alert('session: '+sessionStorage.getItem("login"));
                                
                            },
                            fail: function(error) {
                                alert(JSON.stringify(error));
                            }
                        });
                },
                fail: function(err) {
                    alert(JSON.stringify(err));
                    alert('[카카오]로그인 실패');
                }
            });
        };
	
	</script>
	
</body>
</html>