// [ 귓속말 기능 ]

// 대화방에 입장하는 손님들은 자동으로 대화명 부여받음
// 사용자는 대화명 바꿀 수 있음
// 사용자는 대화 수신자의 대화명을 선택하여 특정 사용자에게 귓속말을 보낼 수 있음

var express = require('express');
//var routes = require('./routes');
var http = require('http');
var path = require('path');

var app = express();
app.get('/', (req, res) => { res.sendFile(path.join(__dirname, 'oneToOneChattingClient.html')); });

var httpServer = http.createServer(app).listen(3001, function (res, req) {
    console.log('Socket IO server 실행!');
});

var io = require('socket.io').listen(httpServer);

// 클라이언트에게만 메세지 보내려면 : io.socket.socket(socket_id).emit 메소드 사용!
// 대화명(nickname)을 통해서 특정 사용자에게 메세지 보내기
// nickname -> socket_id로 맵핑 테이블 필요

var socket_ids = []; // nickname to socket.id에 대한 매핑 정보 저장
var count = 0;

// socket_id와 nickname테이블을 셋업
// 2. 새로운 사용자가 추가되었음을 알리고, 현재 사용자 리스트를 보냄
function registerUser(socket, nickname) {
    //socket.get('nickname', function (err, pre_nick) { // 현재 클라이언트 소켓의 nickname을 pre_nick으로 읽어옴
    //if (pre_nick != undefined) delete socket_ids[pre_nick]; // 대화명이 바뀔 경우 기존의 socket.id 삭제
    if (socket.nickname != undefined) delete socket_ids[socket.nickname];
    socket_ids[nickname] = socket.id; // 삭제 후 nickname을 키값으로 하여 socket.id저장
    socket.nickname = nickname;
    io.sockets.emit('userlist', { users: Object.keys(socket_ids) });

    //socket.set('nickname', nickname, function () { // nickname -> socket의 set()으로 저장
    //    io.socket.emit('userlist', { users: Object.keys(socket_ids) }); // userlist -> 현재 접속된 사용자의 nickname들을 보냄
    //});
    //});
}

// 클라이언트 접속
// 1. new이벤트 -> nickname생성해서 보냄 (GUEST - 숫자)
io.sockets.on('connection', function (socket) {
    socket.emit('new', {
        nickname: 'GUEST-' + count,
        msg: '<span style=color"Blue"> GUEST-' + count + '</span>님이 입장하셨습니다.'
    });
    registerUser(socket, 'GUEST-' + count);
    count++;

    // 대화명 변경되었을 때 : 
    socket.on('changename', function (data) {
        registerUser(socket, data.nickname); // 전체 사용자 리스트 업데이트
        socket.emit('broadcast_msg', { msg: socket.nickname + '님이 ' + data.nickname + '으로 대화명을 변경하셨습니다.' });
    });

    // 브라우져 닫았을 때 :
    socket.on('disconnect', function (data) {
        if (socket.nickname != undefined) {
            delete socket_ids[socket.nickname];
            io.sockets.emit('userlist', {
                users: Object.keys(socket_ids)
            });
        }
        //socket.get('nickname', function (err, nickname) { // 현재 socket의 nickname 읽기
        //    if (nickname != undefined) {
        //        delete socket_ids[nickname]; // socket_ids에서 해당 nickname에 해당하는 데이터 삭제
        //        io.sockets.emit('userlist', { users: Object.keys(socket_ids) }); // 사용자 목록 업데이트
        //   } // if
        //});
    });

    // 클라이언트로부터 대화 메세지를 다른 사용자에게 보내기 : 
    socket.on('send_msg', function (data) {
        data.msg = socket.nickname + ' :' + data.msg;
        //socket.get('nickname', function (err, nickname) {
        //data.msg = nickname + " : " + data.msg; // 현재 클라이언트의 nickname + 대화내용
        // ** data.to == ALL = 전체 메세지
        if (data.to == 'ALL') socket.broadcast.emit('broadcast_msg', data); // boradcast하기
        // ** data.to == 특정 nickname = 귓속말
        else {
            socket_id = socket_ids[data.to]; // nickname으로 socket_ids에서 socket.id 가져오기
            if (socket_id != undefined) {
                //data.istarget = true;
                io.sockets.connected[socket_id].emit('broadcast_msg', data);
                //io.to(socket_id).emit('broadcast_msg', data);
                // ver01부터 안씀 :  io.sockets.socket(socket_id).emit('broadcast_msg', data); // 해당 socket.id로 메세지 보내기 
            } // if
        }
        socket.emit('broadcast_msg', data);
        //});
    });
});