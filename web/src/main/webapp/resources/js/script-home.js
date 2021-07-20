function crearMapa(lugares) {
    console.log(gfdgdf);
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tuNHpkYzRpMDB3YjJ1bXlla3R4aDk1dSJ9.hD4UgfbMsz3ezhv9fDPO6Q';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11'
    });

    map.on("load", function (e){
    });
}

function ubicarLugares(lugares, map){
    for (let l of lugares){
        new mapboxgl.Marker().setLngLat([l.longitud, l.latitud]).addTo(map);
    }
}