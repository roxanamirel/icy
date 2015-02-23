$(document).ready(function() {

	/*
	 * Only for test, erase all notes
	 */
	/*var index = $.jStorage.index();
	for(var k=0; index.length; k++) {
		console.log(index[k]);
		$.jStorage.deleteKey(index[k]);
	}*/
	

    // Find the biggest z-index value of the notes
    var zIndex = 0;
    var tmpZindex;
    $('.note').each(function() {
        tmpZindex = $(this).css('z-index');
        if (tmpZindex > zIndex) zIndex = tmpZindex;
    })

	// Restore all notes from DB
	restoreNotes();

    // Convert all note elements to jQueryUI draggables
    makeDraggable($('.note'));

    // Configure the fancybox plugin for the "New note" button.
    $("#newNoteButton").fancybox({
        'zoomSpeedIn': 600,
        'zoomSpeedOut': 500,
        'easingIn': 'easeOutBack',
        'easingOut': 'easeInBack',
        'hideOnContentClick': false,
        'padding': 15
    });

    $("#newNoteButon").live('click',
    function() {
        $('.pr-body').val('');
        $('.pr-tags').val('');
        $('#previewNote').removeClass('yellow green blue');
        $('#previewNote').addClass('yellow');
    });

    // Listen for keyup events on fields of the "New note" form
	var newNoteBodyValue = '';
    $('.pr-body').live('keyup',
    function(e) {
        newNoteBodyValue = $(this).val().replace(/<[^>]+>/ig, '');
        $('#previewNote .body').html(newNoteBodyValue);
    });

	var newNoteTagsValue = '';
    $('.pr-tags').live('keyup',
    function(e) {
        $('#previewNote .tags .tag').remove();
        // Remove all existent tags
        newNoteTagsValue = $(this).val().replace(/<[^>]+>/ig, '');
        var tags = newNoteTagsValue.split(",");
        for (var i = 0; i < tags.length; i++) {
            var tag = "<span class='tag'>" + tags[i] + "</span>";
            $('#previewNote .tags').append(tag);
        }
    });

    // Change the color of the preview note
    $('.color').live('click',
    function() {
        var colorClass = $(this).attr('class').replace('color', '');
        $('#previewNote').removeClass('yellow');
        $('#previewNote').removeClass('yellow green blue');
        $('#previewNote').addClass(colorClass);
    });
    var options = {
    	    weekday: "long", year: "numeric", month: "short",
    	    day: "numeric", hour: "2-digit", minute: "2-digit"
    	};
    /* The submit button: */
    $('#note-submit').live('click',
	    function(e) {
	
	        if (newNoteBodyValue.length < 4)
	        {
	            alert("The note text is too short!")
	            return false;
	        }
	
	        if (newNoteTagsValue.length < 1)
	        {
	            alert("You haven't entered a tag!")
	            return false;
	        }
	
	        $(this).replaceWith('<img src="img/ajax_load.gif" style="margin:30px auto;display:block" />');
	
	        var noteid = '"'+new Date().getTime()+'"';
	        var noteJson = "{" +
	        '"id": ' + noteid + ',' +
	        '"zindex": ' + (++zIndex) + ',' +
	        '"body": "' + newNoteBodyValue + '",' +
	        '"date": "' + new Date().toLocaleTimeString("en-us", options) + '",' +
	        '"tags": "' + newNoteTagsValue + '",' +
	        '"left": 150,' +
	        '"top": 150,' +
	        '"color": "' + $.trim($('#previewNote').attr('class').replace('note', '').replace('ui-draggable', '')) + '"' +
	        "}";
	
	        var note = $.evalJSON(noteJson);
	        createNote(note);	// Create the note
	        
	        $(this).fancybox.close();
	    }
    );

	/*
	 * Restore all stored notes
	 */
	function restoreNotes() {
		var index = $.jStorage.index();
		console.log(index);
		
		for(var i=0; i< index.length; i++) {
			var noteJson = $.jStorage.get(index[i]);
			var note = $.evalJSON(noteJson);
			
			console.log(note);
			
			createNote(note);
		}
	};

	/*
	 * Creates a new DOM element note.
	 */
    function createNote(note) {
        var taskboard = $('#taskboard');
        var noteDiv = "<div id='" + note.id + "' class='note " + note.color + "' style='left:" + note.left + "px;top:" + note.top + "px;z-index:" + note.zindex + "'>" +
        note.body	 +
        "<div class='date'>" + note.date + "</div>" +
        "<div class='tags'>";

		if(note.tags) {
	        var tags = note.tags.split(",");
	        for (var i = 0; i < tags.length; i++) {
	            noteDiv += "<span class='tag'>" + tags[i] + "</span>";
	        }
        }
        noteDiv += "</div>";
        noteDiv += "</div>";
        taskboard.append(noteDiv);
        
        console.log($("#"+note.id));
        
        $("#"+note.id).css('position', 'absolute');
        $("#"+note.id).css('top', note.top+'px');
        $("#"+note.id).css('left', note.left+'px');
        
        var noteJson = $.toJSON(note);
        $.jStorage.set(note.id, noteJson);	// Store note info
        makeDraggable($("#"+note.id));	// Make it draggable
        
    };

	/*
	 * Makes the note DOM element draggable and controls note movements.
	 */
    function makeDraggable(element) {
        element.draggable({
            containment: 'parent',
            start: function(e, ui) {
                ui.helper.css('z-index', ++zIndex);
            },
            stop: function(e, ui) {
                // Store new z-index and position of the note.
                var noteid = $(e.target).attr('id');
                var zindex = $(e.target).css('z-index');
                var top = ui.position.top;
                var left = ui.position.left;
                
                var noteJson = $.jStorage.get(noteid);
                var note = $.evalJSON(noteJson);
                note.top = top;
                note.left = left;
                note.zIndex = zindex;
                
                noteJson = $.toJSON(note);
                $.jStorage.set(noteid, noteJson);
            }
        });
    };

});

