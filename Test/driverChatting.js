// [ passenger채팅 기능 ]

// 서버 사이드 : nodejs에서 돌아가는 express같은 프레임워크
// Modlue dependencies

var express = require('express');
//var routes = require('./routes');
var http = require('http');
var path = require('path');
var pIdx;
var dIdx;

// 1. Express 사용
var app = express();

// 뷰 페이지에서 '채팅하기 버튼' -> localhost:3000/chatting/{pIdx}/{dIdx}
// app.get('/chatting/:pIdx/:dIdx', (req, res) => {

//     pIdx = req.params.pIdx;
//     dIdx = req.params.dIdx;
//     console.log("접속한 탑승자의 pIdx :: " + pIdx);
//     console.log("접속한 탑승자의 dIdx :: " + dIdx);

//     res.sendFile(path.join(__dirname, 'passengerChatting.html'));

// });

app.get('/chatting1/:dIdx', (req, res) => {

    dIdx = req.params.dIdx;
    console.log("접속한 탑승자의 dIdx :: " + dIdx);

    res.sendFile(path.join(__dirname, 'driverChatting.html'));

});

// 2. httpServer 생성
var httpServer = http.createServer(app).listen(3001, function (req, res) {
    console.log('Socket IO server 실행!');
});

// 3. httpServer -> socketIO 지원하는 서버로 업그레이드
var io = require('socket.io').listen(httpServer);

// 4. 클라이언트가 socket.io 채널로 접속이 되었을때에 대한 이벤트를 정의
io.sockets.on('connection', function (socket) {

    console.log('한명의 사용자가 접속했습니다');

    //pIdx ---> 소켓 ID로 설정
    // chatting(pIdx);

    // 5. 연결된 클라이언트에게 메세지 보냄
    // toclient : 이벤트 명
    // msg : 키, value = 'welcome'
    socket.emit('toclient', { msg: 'welcome' });
    //console.log('소켓 연결 후에도 잘 들어오나 pIdx :: ' + pIdx);
    console.log('소켓 연결 후에도 잘 들어오나 dIdx :: ' + dIdx);


    //로그인아이디 매핑(로그인 ID-> 소켓 ID)
    var login_ids = {};

    //chatting(pIdx);
    chatting1(dIdx);


    // --- chatting 
    function chatting(pIdx) {

        console.log('chatting()실행 pidx :: ' + pIdx);
        //기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
        console.log('접속한 소켓의 ID : ' + socket.id); // 자동으로 부여되는 소켓 ID값
        login_ids[pIdx] = socket.id; // 전달받은 idx값을 속성이름으로 추가, 그 속성의 값은 소켓의 ID값으로 할당

        // 임의의 dIdx = 13
        //var dIdx = 13;
        //login_ids[dIdx] = 'MAvQ2I3wCoE-6_JLAAAA'; // 임의의 driver socket.id

        socket.pidx = pIdx; // idx확인할 수 있도록 소켓 객체에 idx속성 추가

        console.log('접속한 클라이언트 ID의 개수 : %d', Object.keys(login_ids).length);

        console.log('----------------------------------');

        //응답메시지전송
        sendResponse(socket, 'Passenger-chatting', '200', '소켓 연결 ok');
    }

    function chatting1(dIdx) {

        console.log('chatting()실행 pidx :: ' + dIdx);
        //기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
        console.log('접속한 소켓의 ID : ' + socket.id); // 자동으로 부여되는 소켓 ID값
        login_ids[dIdx] = socket.id; // 전달받은 idx값을 속성이름으로 추가, 그 속성의 값은 소켓의 ID값으로 할당

        // 임의의 dIdx = 13
        //var dIdx = 13;
        //login_ids[dIdx] = 'MAvQ2I3wCoE-6_JLAAAA'; // 임의의 driver socket.id

        socket.dIdx = dIdx; // idx확인할 수 있도록 소켓 객체에 idx속성 추가

        console.log('접속한 클라이언트 ID의 개수 : %d', Object.keys(login_ids).length);

        console.log('----------------------------------');

        //응답메시지전송
        sendResponse(socket, 'Passenger-chatting', '200', '소켓 연결 ok');
    }

    // --- passenger : chooseDriver 이벤트를 받았을 때 처리
    socket.on('chooseD', function (dIdx) {
        console.log("선택한 dIdx ::" + dIdx.dIdx);
        //기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
        // console.log('드라이버의 접속한 소켓의 ID : ' + socket.id);
        // login_ids[dIdx.dIdx] = socket.id;
        // socket.dIdx = dIdx.dIdx;
        // dIdx = dIdx.dIdx;

        //console.log('접속한 클라이언트 ID의 개수 : %d', Object.keys(login_ids).length);

        //응답메시지전송
        sendResponse(socket, 'login', '200', '로그인되었습니다.');
    });

    socket.on('fromclient1', function (data) { // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
        // data = 들어오는 데이터 : {msg : "문자열"} 형식
        console.log('메세지 받음');

        // 7. 메세지 보내기 : 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
        io.socekts.connected[login_ids[data.pIdx]].emit('toclient', data.msg);
        //응답메시지전송
        sendResponse(socket, 'message', '200', '메시지를전송했습니다.');
        //socket.broadcast.emit('toclient', data); // 자신을 제외하고 다른 클라이언트에게 보냄
        socket.emit('toclient1', data.msg); // 자신의 클라이언트에게만(웹) 보냄
        console.log('Message from client : ' + data.msg);
    });

    // --- driver : choosePassenger 이벤트를 받았을 때 처리
    socket.on('chooseP', function (pIdx) {
        console.log("선택한 pIdx ::" + pIdx.pIdx);
        //기존 클라이언트 ID가 없으면 클라이언트 ID를 맵에 추가
        // console.log('드라이버의 접속한 소켓의 ID : ' + socket.id);
        // login_ids[pIdx.pIdx] = socket.id;
        // socket.pIdx = pIdx.pIdx;
        // pIdx = pIdx.pIdx;

        //console.log('접속한 클라이언트 ID의 개수 : %d', Object.keys(login_ids).length);

        //응답메시지전송
        sendResponse(socket, 'login', '200', '로그인되었습니다.');
    });

    socket.on('fromclient', function (data) { // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
        // data = 들어오는 데이터 : {msg : "문자열"} 형식
        console.log('메세지 받음');

        // 7. 메세지 보내기 : 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
        io.socekts.connected[login_ids[data.dIdx]].emit('toclient1', data.msg);
        //응답메시지전송
        sendResponse(socket, 'message', '200', '메시지를전송했습니다.');
        //socket.broadcast.emit('toclient', data); // 자신을 제외하고 다른 클라이언트에게 보냄
        socket.emit('toclient', data); // 자신의 클라이언트에게만(웹) 보냄
        console.log('Message from client : ' + data.msg);
    });

    // -- 응답 메세지 전송 메소드
    function sendResponse(socket, command, code, message) {
        var statusObj = { command: command, code: code, message: message };
        socket.emit('response', statusObj);
    }

    // 6. 메세지 받기 : 클라이언트로부터 오는 메세지 처리

    // // --- passenger
    // socket.on('fromclient1', function (data) { // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
    //     // data = 들어오는 데이터 : {msg : "문자열"} 형식
    //     console.log('메세지 받음');

    //     // 7. 메세지 보내기 : 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
    //     io.socekts.connected[login_ids[dIdx]].emit('toclient', data);
    //     //응답메시지전송
    //     sendResponse(socket, 'message', '200', '메시지를전송했습니다.');
    //     //socket.broadcast.emit('toclient', data); // 자신을 제외하고 다른 클라이언트에게 보냄
    //     socket.emit('toclient', data); // 자신의 클라이언트에게만(웹) 보냄
    //     console.log('Message from client : ' + data.msg);
    // });

    // // --- driver
    // socket.on('fromclient', function (data) { // 채팅장에서 글쓰면 -> 서버로 'fromclient'라는 이벤트를 보냄
    //     // data = 들어오는 데이터 : {msg : "문자열"} 형식
    //     console.log('메세지 받음');

    //     // 7. 메세지 보내기 : 채팅에서 data를 다른 클라이언트 & 자신에게 보냄
    //     io.socekts.connected[login_ids[pIdx]].emit('toclient', data);
    //     //응답메시지전송
    //     sendResponse(socket, 'message', '200', '메시지를전송했습니다.');
    //     //socket.broadcast.emit('toclient', data); // 자신을 제외하고 다른 클라이언트에게 보냄
    //     socket.emit('toclient1', data); // 자신의 클라이언트에게만(웹) 보냄
    //     console.log('Message from client : ' + data.msg);
    // });

    socket.on('disconnect', function () {
        console.log(pIdx + '사용자가 접속해제를 했습니다');
    });

});