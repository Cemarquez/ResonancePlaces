function crearRuta (lugar) {
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2VtYXJxdWV6MjkiLCJhIjoiY2tuNHpkYzRpMDB3YjJ1bXlla3R4aDk1dSJ9.hD4UgfbMsz3ezhv9fDPO6Q';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        zoom: 10
    });


    map.on("load", function (e){
        let directions =new MapboxDirections({
            accessToken: mapboxgl.accessToken
        });

        if("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(position => {
                map.flyTo({
                    center: [position.coords.longitude ,position.coords.latitude],
                })

                var start = [position.coords.longitude, position.coords.latitude];
                var end = [lugar.longitud, lugar.latitud];
                directions.setOrigin(start);
                directions.setDestination(end);
                let center = [(start[0] + end[0])/2, (start[1] + end[1])/2  ];
                var bounds = [
                    start, // southwest coordinates
                    end
                ];
                map.fitBounds(bounds, {padding: 100});
                map.setCenter(center);

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