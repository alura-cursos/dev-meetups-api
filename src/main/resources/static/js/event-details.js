function deleteEvent(id) {
    fetch(`/api/events/${id}`, {method: 'DELETE'})
    .then(function(response) {
        if(response.status == 200){
            window.location.replace("/events");
        }
    })
}