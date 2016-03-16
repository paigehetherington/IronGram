function getPhotos(photosData) {
    for (var i in photosData) {
        var elem = $("<img>");
        elem.attr("src", photosData[i].fileName);
        $("#photos").append(elem);
    }
}

//function getPhotosAjax() {
//     $.get("/photos", getPhotos);
//}


function getUser(userData) {
    if (userData.length == 0) {
        $("#login").show();
    }
    else {
        $("#logout").show();
        $("#upload").show();
        $.get("/photos", getPhotos);
//        getPhotosAjax();
//        setInterval(getPhotosAjax, 3000);
    }
}

$.get("/user", getUser);
