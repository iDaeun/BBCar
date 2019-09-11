<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>YCAR카풀</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">

    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
        #form {
            display: none;
        }

        #findIdForm {
            display: none;
        }

        #findPwForm {
            display: none;
        }

        .ion-ios-arrow-back {
            color: darkgray;
        }
    </style>
</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light site-navbar-target" id="ftco-navbar">
        <div class="container">
            <a class="navbar-brand" href="index.html">Ecoland</a>
            <button class="navbar-toggler js-fh5co-nav-toggle fh5co-nav-toggle" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="oi oi-menu"></span> Menu
            </button>

            <div class="collapse navbar-collapse" id="ftco-nav">
                <ul class="navbar-nav nav ml-auto">
                    <li class="nav-item"><a href="index.html#home-section" class="nav-link"><span>Home</span></a></li>
                    <li class="nav-item"><a href="index.html#services-section" class="nav-link"><span>Services</span></a></li>
                    <li class="nav-item"><a href="index.html#about-section" class="nav-link"><span>About</span></a></li>
                    <li class="nav-item"><a href="index.html#destination-section" class="nav-link"><span>Destination</span></a></li>
                    <li class="nav-item"><a href="index.html#hotel-section" class="nav-link"><span>Hotel</span></a></li>
                    <li class="nav-item"><a href="index.html#restaurant-section" class="nav-link"><span>Restaurant</span></a></li>
                    <li class="nav-item"><a href="index.html#blog-section" class="nav-link"><span>Blog</span></a></li>
                    <li class="nav-item"><a href="index.html#contact-section" class="nav-link"><span>Contact</span></a></li>
                    <li class="nav-item"><a href="index.html#contact-section" class="nav-link"><span>Contact</span></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <section class="hero-wrap" style="background-image: url('images/bg_4.jpg');" data-stellar-background-ratio="0.5">
        <div class="container">
            <div class="row no-gutters slider-text align-items-center justify-content-start">

                <div class="comment-form-wrap pt-5">
                    <!--<h3 class="mb-3 bread">Login</h3>-->

                    <!--로그인 폼-->
                    <form id="form" class="p-5 bg-light">
                        <div class="form-group">
                            <label for="name">ID</label>
                            <input class="form-control" type="text" id="id" name="id" required>
                        </div>
                        <div class="form-group">
                            <label for="email">PW</label>
                            <input class="form-control" type="password" id="pw" name="pw" required>
                        </div>
                        <div class="form-group">
                            <input style="width: 100%" type="submit" value="LOGIN" class="btn py-3 px-4 btn-primary">
                        </div>
                        <div class="form-group">
                            <a id="custom-login-btn" href="javascript:loginWithKakao()">
                                <img style="width: 100%" src="images/kakao_account_login_btn_medium_narrow.png" /></a>
                        </div>
                        <div style="overflow: hidden" class="form-group">
                            <a style="float: left" href="javascript:findId()">아이디 찾기</a>
                            <a style="float: right" href="javascript:findPw()">비밀번호 찾기</a>
                        </div>
                        <div style="text-align: center" class="form-group">
                            <a href="#">아직 회원이 아니신가요?</a>
                        </div>
                    </form>

                    <!--아이디찾기 폼-->
                    <form id="findIdForm" class="p-5 bg-light">
                        <label for="name">ID 찾기</label>
                        <div class="form-group">
                            <input class="form-control" type="text" id="name" name="name" placeholder="이름(실명)" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="email" id="email" name="email" placeholder="이메일" required>
                        </div>
                        <div class="form-group">
                            <input style="width: 100%" type="submit" value="입력 완료" class="btn py-3 px-4 btn-primary">
                        </div>
                        <div style="text-align: center" class="form-group">
                            <a href="javascript:findPw()">비밀번호 찾기</a>
                        </div>
                        <div style="text-align: center" class="form-group">
                            <a href="#">아직 회원이 아니신가요?</a>
                        </div>
                        <span><a class="ion-ios-arrow-back" href="javascript:backBtn()"> BACK</a></span>
                    </form>

                    <!--비밀번호찾기 폼-->
                    <form id="findPwForm" class="p-5 bg-light">
                        <label for="name">PW 찾기</label>
                        <div class="form-group">
                            <input class="form-control" type="text" id="name" name="name" placeholder="이름(실명)" required>
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="email" id="email" name="email" placeholder="이메일" required>
                        </div>
                        <div class="form-group">
                            <input style="width: 100%" type="submit" value="입력 완료" class="btn py-3 px-4 btn-primary">
                        </div>
                        <div style="text-align: center" class="form-group">
                            <a href="javascript:findId()">아이디 찾기</a>
                        </div>
                        <div style="text-align: center" class="form-group">
                            <a href="#">아직 회원이 아니신가요?</a>
                        </div>
                        <span><a class="ion-ios-arrow-back" href="javascript:backBtn()"> BACK</a></span>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <!-- loader -->
    <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
            <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg></div>


    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-migrate-3.0.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.waypoints.min.js"></script>
    <script src="js/jquery.stellar.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/aos.js"></script>
    <script src="js/jquery.animateNumber.min.js"></script>
    <script src="js/scrollax.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
    <script src="js/google-map.js"></script>

    <script src="js/main.js"></script>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

    <script>
        // KAKAO :: 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('b4d22862e49da7bced7dc7592288a369');

        $(document).ready(function() {

            if (sessionStorage.getItem("login") == null) {

                $('#form').css('display', 'block');

                $('#form').submit(function() {

                    var id = $('#id').val();

                    $.ajax({
                        url: 'http://localhost:9090/pClient/login',
                        type: 'POST',
                        data: $('#form').serialize(),
                        success: function(data) {
                            if (data == '1') {
                                alert('존재하지 않는 회원입니다, 다시 로그인해주세요.');
                            }
                            if (data == '2') {
                                // 정상 로그인
                                // session에 아이디 저장 : object 형식 (idx,id,nickname)
                                //sessionStorage.setItem("login", JSON.stringify(data.login));
                                alert('정상적으로 로그인되었습니다.');
                                $('#form').css('display', 'none');
                            }
                            if (data == '3') {
                                alert('비밀번호 불일치, 다시 로그인해주세요.');
                            }
                        }
                    });
                    return false;
                });
            }
        });

        // 카카오 로그인
        function loginWithKakao() {
            // 로그인 창을 띄웁니다.
            Kakao.Auth.login({
                success: function(authObj) {

                    // 정보 확인 -> session에 저장
                    Kakao.API.request({
                        url: '/v2/user/me',
                        success: function(res) {
                            var id = res.id;
                            $.ajax({
                                url: 'http://localhost:9090/pClient/login/' + id,
                                type: 'POST',
                                success: function(data) {
                                    //sessionStorage.setItem("login", JSON.stringify(data.login));
                                    if(data=='success'){
                                    alert('정상적으로 로그인되었습니다.');
                                    $('#form').css('display', 'none');}                                   
                                }
                            });
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

        // ID찾기
        function findId() {
        	$('#id, #pw').val('');
            $('#form').css('display', 'none');
            $('#findPwForm').css('display', 'none');
            $('#findIdForm').css('display', 'block');
        };

        $('#findIdForm').submit(function() {
            $.ajax({
                url: 'http://localhost:9090/passenger/members/login/findId',
                type: 'GET',
                data: $('#findIdForm').serialize(),
                success: function(data) {
                    if (data == 1) {
                        alert('해당 이메일 주소로 아이디 정보를 발송하였습니다');
                        $('#name, #email').val('');
                        $('#findIdForm').css('display', 'none');
                        $('#form').css('display', 'block');
                    }
                    if (data == 2) {
                        alert('없는 정보이거나 일치하지 않는 정보입니다, 다시 입력해주세요');
                    }
                }
            });
            return false;
        });


        // PW찾기
        function findPw() {
        	$('#id, #pw').val('');
            $('#form').css('display', 'none');
            $('#findIdForm').css('display', 'none');
            $('#findPwForm').css('display', 'block');
        };

        $('#findPwForm').submit(function() {
            $.ajax({
                url: 'http://localhost:9090/passenger/members/login/findPw',
                type: 'GET',
                data: $('#findPwForm').serialize(),
                success: function(data) {
                    if (data == 1) {
                        alert('해당 이메일 주소로 임시 비밀번호를 발송하였습니다');
                        $('#name, #email').val('');
                        $('#findPwForm').css('display', 'none');
                        $('#form').css('display', 'block');
                    }
                    if (data == 2) {
                        alert('없는 정보이거나 일치하지 않는 정보입니다, 다시 입력해주세요');
                    }
                }
            });
            return false;
        });

        // back 버튼 누르면 로그인 폼으로 돌아감
        function backBtn() {
            $('#name, #email').val('');
            $('form').css('display', 'none');
            $('#form').css('display', 'block');
        }
    </script>

</body></html>
	