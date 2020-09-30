/*
 * jQuery autoResize (textarea auto-resizer)
 * @copyright James Padolsey http://james.padolsey.com
 * @version 1.04
 */

(function($){
    
    $.fn.autoResize = function(options) {
        
        // Just some abstracted details,
        // to make plugin users happy:
        var settings = $.extend({
            onResize : function(){},
            animate : true,
            animateDuration : 150,
            animateCallback : function(){},
            extraSpace : 20
        }, options);
        
        // Only textarea's auto-resize:
        this.filter('textarea').each(function(){
            
                // Get rid of scrollbars and disable WebKit resizing:
            var textarea = $(this).css({/*resize:'none','overflow-y':'hidden'*/}),
            
                // Cache original height, for use later:
                origHeight = textarea.height(),
                
                // Need clone of textarea, hidden off screen:
                clone = (function(){
                    
                    // Properties which may effect space taken up by chracters:
                    var props = ['height','width','lineHeight','textDecoration','letterSpacing'],
                        propOb = {};
                        
                    // Create object of styles to apply:
                    $.each(props, function(i, prop){
                        propOb[prop] = textarea.css(prop);
                    });
                    
                    // Clone the actual textarea removing unique properties
                    // and insert before original textarea:
                    return textarea.clone().removeAttr('id').removeAttr('name').css({
                        position: 'absolute',
                        top: 0,
                        left: -9999
                    }).css(propOb).attr('tabIndex','-1').insertBefore(textarea);
					
                })(),
                lastScrollTop = null,
                updateSize = function() {
					
                    // Prepare the clone:
                    clone.height(0).val($(this).val()).scrollTop(10000);
					
                    // Find the height of text:
                    var scrollTop = Math.max(clone.scrollTop(), origHeight) + settings.extraSpace,
                        toChange = $(this).add(clone);
						
                    // Don't do anything if scrollTip hasen't changed:
                    if (lastScrollTop === scrollTop) { return; }
                    lastScrollTop = scrollTop;
                    
                    // Fire off callback:
                    settings.onResize.call(this);
					
                    // Either animate or directly apply height:
                    settings.animate && textarea.css('display') === 'block' ?
                        toChange.stop().animate({height:scrollTop}, settings.animateDuration, settings.animateCallback)
                        : toChange.height(scrollTop);
                };
            
            // Bind namespaced handlers to appropriate events:
            textarea
                .unbind('.dynSiz')
                .bind('keyup.dynSiz', updateSize)
                .bind('keydown.dynSiz', updateSize)
                .bind('change.dynSiz', updateSize);
            
        });
        
        // Chain:
        return this;
        
    };
    
    
    
})(jQuery);




function sendRequest(){
    var action = $("#SelectAction").val();
    var inputTextarea = $("#inputTextarea").val();
    $('#alert').hide();

    if(action == null){
        showAlert('Error', "You must select an action!");
        return;
    }
    
    if(inputTextarea == ""){
        showAlert('Error', "You must enter some input!");
        return;
    }

    $("#translate").prop('disabled', true);
    $("#translate").html('Loading...');
    //$('#loadingGif').show();

    $.ajax({ 
        type : "POST", 
        url : "https://jsonschema.duckdns.org:8080/execute?op="+action, 
        data : inputTextarea,
        beforeSend: function(xhr){xhr.setRequestHeader('Content-Type', 'text/plain');},
        success : function(data) { 
            console.log("Data: " + data );
            console.log(data);
            if(action != "toGrammarString" 
                    && action != "notEliminationFull" 
                    && action != "andMerging"
                    && action != "notEliminationWitness" 
                    && action != "Canonicalization"
                    && action != "variableNormalization"
                    && action != "DNF"
                    && action != "objPrepare"
                    && action != "arrPrepare"
                    && action != "objArrPrepare")
                $("#outputTextarea").val(JSON.stringify(JSON.parse(data), null, '\t'));
            else
                $("#outputTextarea").val(data);

                $("#translate").prop('disabled', false);
                $("#translate").html('Translate');
                //$('#loadingGif').hide();

                $("#outputTextarea").trigger('change.dynSiz');
                $("#inputTextarea").trigger('change.dynSiz');
        }, 
        error : function(data) { 
            console.log(data);
            showAlert('Error: '+ data.status, data.responseText);
        
            $("#translate").prop('disabled', false);
            $("#translate").html('Translate');
            //$('#loadingGif').hide();
        } 
      });

    function showAlert(title, body){
        $("#alert-title").html("");
        if(body == null || body == "")
            $("#alert-body").html("Request Error");
        else
            $("#alert-body").html(body);
        $('#alert').show();
    }

}

$( document ).ready(function() {
    $.get( "https://jsonschema.duckdns.org:8080/version", function( data ) {
        $( "#version" ).html( data );
    });
});

//LISTENER
$('#inputFileButton').click( function(){
    $('#inputFile').click();
});

$('#inputFile').change(function(event){
    var file = event.target;
    var fr = new FileReader();
    fr.onload = function(event){
        $("#inputTextarea").val(event.target.result);
        $("#inputTextarea").trigger('change.dynSiz');
    };
    fr.readAsText(file.files[0]);
});

$('#downloadFileButton').click(function() {
    var content = $("#outputTextarea").val();
    var fileName = "output.txt";
    var contentType = 'text/plain';
    var a = document.createElement("a");
    var file = new Blob([content], {type: contentType});
    a.href = URL.createObjectURL(file);
    a.download = fileName;
    a.click();
});

$('textarea').autoResize();
$("#outputTextarea").trigger('change.dynSiz');
$("#inputTextarea").trigger('change.dynSiz');
