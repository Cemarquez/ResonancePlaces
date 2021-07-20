function crearMapa (lugares) {
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tuNHpkYzRpMDB3YjJ1bXlla3R4aDk1dSJ9.hD4UgfbMsz3ezhv9fDPO6Q';
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
    for (let l of lugares){
        new mapboxgl.Marker().setLngLat([l.longitud, l.latitud]).setPopup(new mapboxgl.Popup().setHTML("<strong> " + l.nombre  + "</strong> " + "<br/> <a href='http://localhost:8080/detalleLugar.xhtml?lugar="+ l.id +"'> Ir a detalle </a>" )).addTo(map);
        bounds.extend([l.longitud, l.latitud]);
    }

    map.fitBounds(bounds, {padding: 100})

}