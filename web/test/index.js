$( document ).ready(function() {
    isRunning();
    $.get( "https://jsonschema.duckdns.org:8080/getResults", function( data ) {
        console.log( data );
        var str = "";
        json = JSON.parse(data);
        for(var i = 0; i < json.tests.length; i++){
            str += "<tr>";  

            str += "<td>"+ json.tests[i].idRun +"</td>";
            str += "<td>"+ json.tests[i].nTestSuperati + "/" + json.tests[i].nTestTotali +"</td>";
            str += "<td>"+ json.tests[i].datetime +"</td>";
            str += '<td><a href="details/?id='+ json.tests[i].idRun +'">Details</a></td>';

            str += "</tr>";

        }

        console.log(str);

        $("#tbody").html(str);
      });
});

$("#execute").click(function(){
    $("#execute").prop('disabled', true);
    $("#execute").html('Running');

    $.get( "https://jsonschema.duckdns.org:8080/test", function( data ) {
        location.reload();
    });
});

function isRunning(){
    $.get( "https://jsonschema.duckdns.org:8080/isRunning", function( data ) {
        if(data == 'true'){
            $("#execute").prop('disabled', true);
            $("#execute").html('Running');
        }else{
            $("#execute").prop('disabled', false);
            $("#execute").html('Execute Tests');
        }

        setInterval(() => {
            isRunning();
        }, 3000);
    });
    
}
