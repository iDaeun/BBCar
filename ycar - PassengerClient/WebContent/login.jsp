<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
			<!--
            <tr>
                <td colspan="2"><a id="custom-login-btn" href="javascript:loginWithKakao()">
                        <img style="float: right" src="images/kakao_account_login_btn_medium_narrow.png" /></a></td>
            </tr>
-->
		</table>
	</form>
	
	<script>
		
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
							// 미인증 회원
							// 인증 이메일 요청
							alert('미인증 회원입니다, 인증해주세요.');
						} 
						if(data=='4'){
							alert('비밀번호 불일치, 다시 로그인해주세요.');
						}
					}
				});
				return false;
			});
		});
	
	</script>
	
</body>
</html>