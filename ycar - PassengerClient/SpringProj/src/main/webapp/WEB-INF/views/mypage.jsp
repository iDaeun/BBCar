<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <style>
        table {
            border: 3px solid;
        }
        #myInfoEdit {
            display: none;
        }
    </style>
</head>

<body>
	<div id="myInfo">
    <h2>내 정보</h2>
    <table>
        <tr>
            <td>${login.name}</td>
            <td>(${login.id})</td>
        </tr>
        <tr>
            <td>${login.email}</td>
        </tr>
        <tr>
            <td>오늘의 닉네임 :</td>
            <td>${login.nickname}</td>
        </tr>
        <tr>
            <td colspan="2"><button onclick="editForm()">내 정보 수정</button></td>
        </tr>
    </table>
    </div>

    <div id="myInfoEdit">
        <table>
            <tr>
                <td>이메일 주소</td>
            </tr>
            <tr>
                <td><input type="hidden" value="${login.id}" name="id" id="id"><input type="email" id="email" value="${login.email}"></td>
            </tr>
            <tr>
                <td>기존 비밀번호</td>
            </tr>
            <tr>
                <td><input type="password" id="pw1" name="pw1" required></td>
            </tr>
            <tr>
                <td>새로운 비밀번호</td>
            </tr>
            <tr>
                <td><input type="password" id="pw2" name="pw2" required></td>
            </tr>
            <tr>
                <td>비밀번호 다시입력</td>
            </tr>
            <tr>
                <td><input type="password" id="pw3" required><span id="pwSpan"></span><input type="checkbox" id="pwCheck"></td>
            </tr>
            <tr>
                <td><input type="button" id="edit" value="수정하기"></td>
            </tr>
            <tr>
                <td><button onclick="backBtn()">취소</button></td>
            </tr>
        </table>
    </div>

    <h2>즐겨찾는 장소</h2>

    <h2>선호 탑승 환경</h2>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	
    <script>
        $(document).ready(function() {
        	
        	// 임시비밀번호 발급 회원 로그인 --> 마이페이지에서 비밀번호 수정페이지 띄우기
        	getParams();
        	
        	$('#edit').click(function(){
        	
            // 공백인 상태에서 수정버튼 눌렀을 시 못가게 막음
            var pw1 = $('#pw1').val();
            var pw2 = $('#pw2').val();
            var pw3 = $('#pw3').val();
            	
            if(pw1.length<1 || pw2.length<1 || pw3.length<1){
            	alert('비밀번호를 입력해주세요!');
            	return false;
            }
        	
        	if (!$('#pwCheck').prop('checked')) {
					alert('[비말번호 불일치] 다시 확인해주세요!');
					return false;
			}
        	            	
            	$.ajax({
            		url: 'http://localhost:9090/passenger/members/mypage',
            		type: 'PUT',
            		data: JSON.stringify({
            			id:$('#id').val(),
            			pw1:$('#pw1').val(),
            			pw2:$('#pw2').val()
            		}),
            		contentType: 'application/json; charset=utf-8',
            		success: function(data){
            			if(data==0){
            				alert('잘못된 비밀번호');
            			}
            			if(data==1){
            				alert('내 정보 수정 성공!');
            				backBtn();
            			}
            			if(data==2){
            				alert('*수정오류');
            			}
            		},
            	});
            	return false;
        	});
        	            
        	// 새로운 비밀번호 일치하는지 확인
			$('#pw3, #pw2').focusout(function() {
				if ($('#pw2').val() == $('#pw3').val() || $('#pw3').val() == $('#pw2').val()) {
					$('#pwSpan').html('비밀번호 일치');
					$('#pwSpan').css('color', 'green');
					$('#pwCheck').prop('checked', true);
				} else {
					$('#pwSpan').html('비밀번호 불일치');
					$('#pwSpan').css('color', 'red');
					$('#pwCheck').prop('checked', false);
				}
			});
        });
        function editForm() {
            $('#myInfoEdit').css('display', 'block');
        }
        function backBtn() {
            $('#pw1').val('');
            $('#pw2').val('');
            $('#pw3').val('');
            $('#myInfoEdit').css('display', 'none');
            $('#myInfo').css('display','block');
        }
        
        function getParams(){
        	// 현재 페이지의 url
			var url = decodeURIComponent(location.href);
			// url이 encodeURIComponent로 인코딩 되었을 때 다시 디코딩
			url = decodeURIComponent(url);

			var params;
			// url에서 '?' 문자 이후의 파라미터 문자열까지 자르기
			params = url.substring(url.indexOf('?') + 1, url.length);
			// 파라미터 구분자("&")로 분리
			params = params.split("&");
			//alert('params: ' + params);
			// ("=")로 분리해서 값 받아오기
			var user = params[0].split("=")[1];

			alert('user: ' + user);
			
			if(user == 'temp'){
				// 정보수정 폼 띄우기
				alert('비밀번호를 수정해주세요!');
				$('#myInfo').css('display','none');
				editForm();
			}
        }
    </script>
</body>

</html>