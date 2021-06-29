<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>netty实现websocket测试</title>
    <script>
        let socket;
        function enter(){
            socket = new WebSocket("ws://localhost:8888");
            socket.onopen = function (event) {
                socket.send(document.form.uid.value + "已上线");
            }
            socket.onmessage = function (event) {
                document.form.responseText.value += event.data + "\r\n";
            }
            socket.onclose = function (event) {
                document.form.responseText.value += document.form.uid.value + "已下线" + "\r\n";
            }
        }
        function send(){
            console.log(111111)
            if(socket.readyState == WebSocket.OPEN){
                socket.send(document.form.uid.value+":"+document.form.message.value);
            }else{
                alert("Websocket服务器未连接");
            }
        }
    </script>
</head>
<body>
<form name="form" onsubmit="return false;">
    <h1>客户端发送消息</h1>
    <div>
        <label>名字：</label><input type="text" name="uid" value=""/><input type="button" value="上线" onclick="enter()"/>
    </div>
    <div>
        <label>内容：</label><input type="text" name="message" value=""/><input type="button" value="发消息" onclick="send()"/>
    </div>
    <h1>服务端返回消息</h1>
    <textarea name="responseText" style="width:900px;height: 300px;"></textarea>
</form>
</body>
</html>