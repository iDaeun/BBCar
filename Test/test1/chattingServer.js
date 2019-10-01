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
app.get('/chatting/:pIdx', (req, res) => {

    pIdx = req.params.pIdx;
    //dIdx = req.params.dIdx;
    console.log("접속한 탑승자의 pIdx :: " + pIdx);
    //console.log("접속한 탑승자의 dIdx :: " + dIdx);

    res.sendFile(path.join(__dirname, 'passenger.html'));

});

app.get('/chatting1/:dIdx', (req, res) => {

    dIdx = req.params.dIdx;
    console.log("접속한 탑승자의 dIdx :: " + dIdx);

    res.sendFile(path.join(__dirname, 'driver.html'));

});

// 2. httpServer 생성
var httpServer = http.createServer(app).listen(3000, function (req, res) {
    console.log('Socket IO server 실행!');
});

// 3. httpServer -> socketIO 지원하는 서버로 업그레이드
var io = require('socket.io').listen(httpServer);

//var io = require('socket.io').listen(3000);
var clients = [];

io.sockets.on('connection', function (socket) {
    socket.on('login', function (data) {
        var clientInfo = new Object();
        clientInfo.uid = data.uid;
        clientInfo.id = socket.id;
        clients.push(clientInfo);
    });

    socket.on('message special user', function (data) {
        // 클라이언트 소켓 아이디를 통해서 그 소켓을 가진 클라이언트에만 메세지를 전송
        for (var i = 0; i < clients.length; i++) {
            var client = clients[i];
            console.log('client.uid = ' + client.uid);
            if (client.uid == data.uid) {
                io.sockets.socket(client.id).send(data.msg);
                break;
            }
        }
    });

    socket.on('disconnect', function () {
        for (var i = 0; i < clients.length; i++) {
            var client = clients[i];
            if (client.id == socket.id) {
                clients.splice(i, 1);
                break;
            }
        }
        console.log('user disconnected');
    });
});