function crearRuta (lugar) {
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tuNHpkYzRpMDB3YjJ1bXlla3R4aDk1dSJ9.hD4UgfbMsz3ezhv9fDPO6Q';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [lugar.longitud, lugar.latitud],
        zoom: 13,
    });


    map.on("load", function (e){
        let directions =new MapboxDirections({
            accessToken: mapboxgl.accessToken
        });

        if("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(position => {
                map.flyTo({
                    center: [position.coords.longitude ,position.coords.latitude],
                    zoom: 14
                })

                var start = [position.coords.longitude, position.coords.latitude];
                var end = [lugar.longitud, lugar.latitud];
                directions.setOrigin(start);
                directions.setDestination(end)

                var bounds = [
                    start, // southwest coordinates
                    end // northeast coordinates
                ];

                map.fitBounds(bounds, {padding: (100)})
                let bnds = map.getBounds();
                map.setMaxBounds(bnds);
            });
        }

        map.addControl(
            directions,
            'top-left'
        );

        directions.interactive(false);
    });



}

function onClick(e){

}