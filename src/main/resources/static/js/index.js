$(document).ready(function(){
    $('#search').click(function(e){
        e.preventDefault();
        $('html, body').animate({
            scrollTop: $("#ordering").offset().top -60
            }, 1000);
    });

    $('#gallery').click(function(e){
        e.preventDefault();
        $('html, body').animate({
            scrollTop: $(".subtitle").offset().top
            }, 1000);
    });
});


