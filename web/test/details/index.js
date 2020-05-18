$( document ).ready(function() {
    $.get( "http://jsonschema.ddns.net:8080/getTests?"+window.location.search.substr(1), function( data ) {
        console.log( data );
        var str = "";
        json = JSON.parse(data);
        for(var i = 0; i < json.tests.length; i++){

            str += '<tr>';
            var stato = "text-danger";
            if(json.tests[i].esito)
                stato = "text-success";

            str += '<td><p class="'+ stato +'">' + json.tests[i].nomeFileInput+ '</p>' +
                        '<textarea class="form-control" id="inputTextarea" rows="10" placeholder="Input textarea">'
                        + json.tests[i].fileInput +'</textarea></td>';

            str += '<td><p class="'+ stato +'">' + json.tests[i].nomeFileOutput+ '</p>' +
                        '<textarea class="form-control" id="inputTextarea" rows="10" placeholder="Input textarea">'
                        + json.tests[i].fileOutput +'</textarea></td>';

            str += "</tr>";

        }

        console.log(str);

        $("#tbody").html(str);
      });
});