// [ 채팅 기능 ]

var express = require('express');
var http = require('http');
var path = require('path');
var cors = require('cors');
var Idx;
var anotherIdx;

var login_ids = {}; // 로그인아이디 매핑(로그인 ID-> 소켓 ID)
var connected = []; // 현재 connected된 아이디 저장

// 1. Express 사용
var app = express();

app.use(cors());

// P 뷰 페이지에서 '채팅하기 버튼' -> localhost:3000/chatting?pIdx=ㅁ&pNick=ㅁ&dIdx=ㅁ&dNick=ㅁ
app.get('/chatting', (req, res) => {

    Idx = req.query.pIdx;
    anotherIdx = req.query.dIdx;
    //console.log("접속한 탑승자의 pIdx :: " + Idx);

    res.sendFile(path.join(__dirname, 'passengerChatting.html'));

});

// D 뷰 페이지에서 '채팅하기 버튼' -> localhost:3000/chatting1?dIdx=ㅁ&dNick=ㅁ&pIdx=ㅁ&pNick=ㅁ
app.get('/chatting1', (req, res) => {

    Idx = req.query.dIdx;
    anotherIdx = req.query.pIdx;
    //console.log("접속한 탑승자의 dIdx :: " + Idx);

    res.sendFile(path.join(__dirname, 'driverChatting.html'));

});

// 2. httpServer 생성
var httpServer = http.createServer(app).listen(3000, function (req, res) {
    console.log('Socket IO server 실행!');
});

// 3. httpServer -> socketIO 지원하는 서버로 업그레이드
var io = require('socket.io').listen(httpServer);

// 4. 클라이언트가 socket.io 채널로 접속이 되었을때에 대한 이벤트를 정의
io.sockets.on('connection', function (socket) {

    console.log('@@한명의 사용자가 접속했습니다');

    connected.push(Idx);
    console.log(connected);

    //pIdx ---> 소켓 ID로 설정
    chatting(socket, Idx);


    // 6. 메세지 받기 : 클라이언트로부터 오는 메세지 처리

    // --- passenger 
    socket.on('pOnline', function (data) {

        for (var i = 0; i < connected.length; i++) {
            if (connected[i] == data.dIdx) {
                let client = io.sockets.connected[login_ids[data.dIdx]];
                client.emit('pOnline', { dIdx: data.dIdx });
            }
        }
    });

    socket.on('fromclient', function (data) {
        // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
        // data = 들어오는 데이터 : {msg : "문자열"} 형식
        console.log('메세지 받음');

        // 7. 메세지 보내기 : 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
        let client = io.sockets.connected[login_ids[data.dIdx]];
        console.log(data.dIdx + '에게 보내기');
        client.emit('toclient1', data.msg);

        //응답메시지전송
        sendResponse(socket, 'message', '200', '메시지를전송했습니다.');
        console.log('Message from passenger : ' + data.msg);
    });

    // --- driver : choosePassenger 이벤트를 받았을 때 처리
    socket.on('dOnline', function (data) {

        for (var i = 0; i < connected.length; i++) {
            if (connected[i] == data.pIdx) {
                let client = io.sockets.connected[login_ids[data.pIdx]];
                client.emit('pOnline', { pIdx: data.pIdx });
            }
        }
    });

    socket.on('fromclient1', function (data) {
        // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
        // data = 들어오는 데이터 : {msg : "문자열"} 형식
        console.log('메세지 받음');

        // 7. 메세지 보내기 : 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
        let client = io.sockets.connected[login_ids[data.pIdx]];
        console.log(data.pIdx + '에게 보내기');
        client.emit('toclient', data.msg);
        //응답메시지전송
        sendResponse(socket, 'message', '200', '메시지를전송했습니다.');
        console.log('Message from driver : ' + data.msg);
    });

    socket.on('disconnect', function () {
        console.log('----------------------------------');
        console.log(Idx + '사용자가 접속해제를 했습니다');
        console.log('----------------------------------');

        for (var i = 0; i < connected.length; i++) {
            if (connected[i] == Idx) {
                connected.splice(connected.indexOf(Idx), 1);
                let client = io.sockets.connected[login_ids[anotherIdx]];
                client.emit('offline', Idx);
            }
        }

        console.log(connected);
    });

});

// -- 응답 메세지 전송 메소드
function sendResponse(socket, command, code, message) {
    var statusObj = { command: command, code: code, message: message };
    socket.emit('response', statusObj);
}

// --- chatting 
function chatting(socket, Idx) {

    //console.log('chatting()실행 idx :: ' + Idx);
    //기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
    console.log(Idx + '가 접속한 소켓의 ID : ' + socket.id); // 자동으로 부여되는 소켓 ID값
    login_ids[Idx] = socket.id; // 전달받은 idx값을 속성이름으로 추가, 그 속성의 값은 소켓의 ID값으로 할당

    console.log('접속한 클라이언트 ID의 개수 : %d', Object.keys(login_ids).length);

    console.log('----------------------------------');

    //응답메시지전송
    sendResponse(socket, 'Passenger-chatting', '200', '소켓 연결 ok');
}