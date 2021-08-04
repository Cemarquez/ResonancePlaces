function crearMapa (lugares) {
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tyNDJxY2c4MnJzajJvbW5obmR5ZzJzeiJ9.u5GTJHs4XDj1XMlg976kKg';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11'
    });

    map.on("load", function (e){
        ubicarLugares(lugares, map);
    });

}

function ubicarLugares(lugares, map){
    let bounds = new mapboxgl.LngLatBounds();

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            var start = [position.coords.longitude, position.coords.latitude];
            for (let l of lugares) {

                let marker = new mapboxgl.Marker().setLngLat([l.longitud, l.latitud]).setPopup(new mapboxgl.Popup().setHTML("<strong> " + l.nombre  + "</strong> " + "<br/> <a href='http://localhost:8080/detalleLugar.xhtml?lugar="+ l.id +"'> Ir a detalle </a>" )).addTo(map);
                let posFinal = marker.getLngLat();
                let markerInicial = new mapboxgl.Marker().setLngLat(start).setPopup(new mapboxgl.Popup().setHTML());
                let posInicial = markerInicial.getLngLat();
                var distance = (posInicial.distanceTo(posFinal)).toFixed(0);

                setDistance(distance, l.contadorId);

                bounds.extend([l.longitud, l.latitud]);

            }
            map.fitBounds(bounds, {padding: 100})
        });

    }



}

function setDistance(disancia, id){
    document.getElementById("form:lista:" + id + ":distancia").value = disancia;
}


