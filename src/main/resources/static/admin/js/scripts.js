/*!
    * Start Bootstrap - SB Admin v6.0.0 (https://startbootstrap.com/templates/sb-admin)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-sb-admin/blob/master/LICENSE)
    */
(function ($) {
    "use strict";

    // Add active state to sidbar nav links
    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
    $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function () {
        if (this.href === path) {
            $(this).addClass("active");
        }
    });

    // Toggle the side navigation
    $("#sidebarToggle").on("click", function (e) {
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });
})(jQuery);
$(document).ready(function() {
    $('#productForm').validate({
        rules : {
            productName : {
                required : true,
                minlength: 12,
                maxLength:255
            },
            quantity : {
                required : true,
                digits : true
            },
            price : {
                required: true,
                digits: true
            },
            description : {
                required: true,
                maxLength: 300
            }
        },
        messages : {
        }
    });
    $('#adminChangePassword').validate({
        rules : {
            oldPassword : {
                required : true,
            },
            newPassword : {
                required : true,
                minlength: 8
            },
            confirm : {
                required : true,
                equalTo : '#newPassword'
            },
        },
        messages : {
        }
    });
    $('#mess').modal('show');
});


