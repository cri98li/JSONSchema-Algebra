function sendRequest(){
    var action = $("#SelectAction").val();
    var inputTextarea = $("#inputTextarea").val();

    $.post("http://aow75q8kv6.execute-api.eu-west-1.amazonaws.com/JSONSchema_To_Algebra?action=normalize",
        JSON.stringify(inputTextarea),
    ).done(function(data){
            console.log("Data: " + data );
          }
    );

    var data = JSON.stringify({"$id":"pageTimings.json#","$schema":"http://json-schema.org/draft-06/schema#","type":"object","properties":{"onContentLoad":{"type":"number","min":-1},"onLoad":{"type":"number","min":-1},"comment":{"type":"string"}}});

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
    if(this.readyState === 4) {
        console.log(this.responseText);
    }
    });

    xhr.open("POST", "http://aow75q8kv6.execute-api.eu-west-1.amazonaws.com/JSONSchema_To_Algebra?action=normalize");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);
}