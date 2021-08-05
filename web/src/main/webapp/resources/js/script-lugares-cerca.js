var marcadores=[];
mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tyNDJxY2c4MnJzajJvbW5obmR5ZzJzeiJ9.u5GTJHs4XDj1XMlg976kKg';
var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11'
});

function crearMapa (lugares) {



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
                marcadores.push(marker);
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

function ubicarLugaresActualizados(lugares) {
    if (marcadores !== null) {
        for (var i = marcadores.length - 1; i >= 0; i--) {
            marcadores[i].remove();
        }
    }
    let bounds = new mapboxgl.LngLatBounds()
    for (let l of lugares) {
        let marker = new mapboxgl.Marker().setLngLat([l.longitud, l.latitud]).setPopup(new mapboxgl.Popup().setHTML("<strong> " + l.nombre + "</strong> " + "<br/> <a href='http://localhost:8080/detalleLugar.xhtml?lugar=" + l.id + "'> Ir a detalle </a>")).addTo(map);
        marcadores.push(marker);
        bounds.extend([l.longitud, l.latitud]);
    }
    console.log(marcadores.length);

}
function setDistance(disancia, id){
    document.getElementById("form:lista:" + id + ":distancia").value = disancia;
}


