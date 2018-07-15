$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/event/all"
    }).then(function(data) {
       $('.event-id').append(data.id);
       $('.event-content').append(data.name);
    });
});