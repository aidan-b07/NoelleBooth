function initMap() {
    var mapProp= {
        center:new google.maps.LatLng(51.508742,-0.120850),
        zoom:5,
    };
    var map = new google.maps.Map(document.getElementById("map"), mapProp);
}

document.addEventListener("DOMContentLoaded", () => {
    initMap();
})