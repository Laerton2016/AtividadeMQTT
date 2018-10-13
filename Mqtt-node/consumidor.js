var mqtt = require('mqtt');
var client = mqtt.connect('ws://iot.eclipse.org:80/ws');
//var client = mqtt.connect('10.0.75.1:9001');
client.on('connect', function(){
    client.subscribe('sensor/temperatura');
    console.log('conectado')
});
client.on('message', function(topic, message){
    console.log("Recebendo: ", message.toString());
});