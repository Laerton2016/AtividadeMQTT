var mqtt = require('mqtt');
var client = mqtt.connect('ws://iot.eclipse.org:80/ws');
var flagEnviar = true;
var tempMax =100;
//var client = mqtt.connect('iot.eclipse.org:1883');
//var client = mqtt.connect('10.0.75.1:9001');
var iv = setInterval(
    function(){
        if (flagEnviar){
            var randInt = Math.floor(Math.random() * tempMax);
            client.publish('sensor1/temperatura', ''+randInt);
            console.log('msg:', randInt);        
            console.log('temp max:', tempMax);
        }
        
    },2000
);

client.on ('connect', function(){
    client.subscribe('atuador1/temperatura')
});

client.on('message', function(topic, message){
    if (topic == 'atuador1/temperatura'){
        flagEnviar = "0" == message.toString();
    }
});

client.on ('connect', function(){
    client.subscribe('ajuste/temperatura')
});

client.on('message', function(topic, message){
    if (topic == 'ajuste/temperatura'){
        tempMax = parseInt(message.toString());
    }
    
    
    

});