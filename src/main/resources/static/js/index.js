$(document).ready(function () {
    sessionStorage.clear();
    $("#login-btn").click(function () {
        var formData = getLoginForm();
        if (!validateLoginForm(formData)) {
            return;
        }

        postRequest(
            '/login',
            formData,
            function (res) {
                if (res.success) {
//                	alert(res);
                    sessionStorage.setItem('username', formData.username);
                    sessionStorage.setItem('id', res.content.id);
                    sessionStorage.setItem('level',res.content.level);
                    //sessionStorage.setItem('level',0);
                    if (sessionStorage.getItem('level') == 0) {
                        sessionStorage.setItem('role', 'admin');
                        window.location.href = "/admin/movie/manage"
                    }else if(sessionStorage.getItem('level') == 1){
                        sessionStorage.setItem('role', 'manager');
                        window.location.href = "/admin/movie/manage"
                    }else if(sessionStorage.getItem('level') == 2){
                        sessionStorage.setItem('role', 'staff');
                        window.location.href = "/admin/movie/manage"
                    } else {
                        sessionStorage.setItem('role', 'user');
                        window.location.href = "/user/home"
                    }
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            });
    });

    function getLoginForm() {
        return {
            username: $('#index-name').val(),
            password: $('#index-password').val()
        };
    }

    function validateLoginForm(data) {
        var isValidate = true;
        if (!data.username) {
            isValidate = false;
            $('#index-name').parent('.input-group').addClass('has-error');
            $('#index-name-error').css("visibility", "visible");
        }
        if (!data.password) {
            isValidate = false;
            $('#index-password').parent('.input-group').addClass('has-error');
            $('#index-password-error').css("visibility", "visible");
        }
        return isValidate;
    }
});