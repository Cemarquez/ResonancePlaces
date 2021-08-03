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
    let bounds = new mapboxgl.LngLatBounds()

    let directions =new MapboxDirections({
        accessToken: mapboxgl.accessToken
    });
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
    for (let l of lugares) {

                var start = [position.coords.longitude, position.coords.latitude];
                var end = [lugar.longitud, lugar.latitud];
                directions.setOrigin(start);
                directions.setDestination(end)

                directions.on("route", e => {
                    let routes = e.route

                    let distance = routes.map(r => r.distance);
                    console.log(distance);
                    setDistance(distance, l.contadorId);
                });

                new mapboxgl.Marker().setLngLat([l.longitud, l.latitud]).setPopup(new mapboxgl.Popup().setHTML("<strong> " + l.nombre + "</strong> " + "<br/> <a href='http://localhost:8080/detalleLugar.xhtml?lugar=" + l.id + "'> Ir a detalle </a>")).addTo(map);
                bounds.extend([l.longitud, l.latitud]);





        }
        });
    }





    map.fitBounds(bounds, {padding: 100})


}

function setDistance(disancia, id){
    document.getElementById("lista:" + id + ":distancia").value = disancia;
}


