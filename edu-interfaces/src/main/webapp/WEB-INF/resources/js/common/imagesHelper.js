function replaceNotLoadedImage(el) {
    if( el.naturalWidth == "undefined" || el.naturalWidth == 0 ) {
        if (el.src == "undefined" || el.src.indexOf(".jxplus.cn") > 0) {
            $(el).attr( "src",  "/resources/images/no_image.jpg");
        }
    }
};

$().ready(function () {
    $("img").each(function(){
        replaceNotLoadedImage(this);
    });

});