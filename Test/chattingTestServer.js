// [ 채팅 기능 ]

// 서버 사이드 : nodejs에서 돌아가는 express같은 프레임워크
// Modlue dependencies

var express = require('express');
//var routes = require('./routes');
var http = require('http');
var path = require('path');

// 1. Express 사용
var app = express();
//app.use(express.static(path.join(__dirname, '/chattingTestClient.html')));
app.get('/', (req, res) => { res.sendFile(path.join(__dirname, 'chattingTestClient.html')); });

// 2. httpServer 생성
var httpServer = http.createServer(app).listen(3000, function (req, res) {
    console.log('Socket IO server 실행!');
});

// 3. httpServer -> socketIO 지원하는 서버로 업그레이드
var io = require('socket.io').listen(httpServer);

// 4. 클라이언트가 socket.io 채널로 접속이 되었을때에 대한 이벤트를 정의
io.sockets.on('connection', function (socket) { // 연결된 클라이언트의 socket객체를 넘겨받음

    // 5. 연결된 클라이언트에게 메세지 보냄
    // toclient : 이벤트 명
    // msg : 키, value = 'welcome'
    socket.emit('toclient', { msg: 'welcome' });

    // 6. 클라이언트로부터 오는 메세지 처리
    socket.on('fromclient', function (data) { // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
        // data = 들어오는 데이터 : {msg : "문자열"} 형식

        // 7. 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
        socket.broadcast.emit('toclient', data); // 자신을 제외하고 다른 클라이언트에게 보냄
        socket.emit('toclient', data); // 자신의 클라이언트에게만(웹) 보냄
        console.log('Message from client : ' + data.msg);
    })
});