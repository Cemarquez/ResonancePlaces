window.onload = function (){

   let enable=true;

    mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tuNHpkYzRpMDB3YjJ1bXlla3R4aDk1dSJ9.hD4UgfbMsz3ezhv9fDPO6Q';
    var map = new mapboxgl.Map({
        container: 'mapa',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-72.309, 4.473],
        zoom: 4.5

    });

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));

    map.addControl(new mapboxgl.NavigationControl());
    map.addControl(new mapboxgl.FullscreenControl());
    map.on("load", function (e){
        if("geolocation" in navigator)
        {
            navigator.geolocation.getCurrentPosition(position => {
                map.flyTo({
                    center: [position.coords.longitude ,position.coords.latitude],
                    zoom: 14
                })
            });
        }
    });

    map.on("click", function (e){
        if(enable) {
            setLngLat(e.lngLat.lng, e.lngLat.lat)
            enable = false;
            let marker = new mapboxgl.Marker({
                draggable: true
            }).setLngLat([e.lngLat.lng, e.lngLat.lat]).addTo(map);
            marker.on("dragend", function () {
                var lngLat = marker.getLngLat();
                setLngLat(lngLat.lng, lngLat.lat)
            });
        }
    });


}

function setLngLat(lng, lat){
    document.getElementById("crear-lugar:lat-lugar").value = lat
    document.getElementById("crear-lugar:lng-lugar").value = lng
}



