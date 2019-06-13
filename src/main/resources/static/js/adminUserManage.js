$(document).ready(function(){
    getManagerList();
    getStaffList();
    showSidebar();
    $("#useradd-form-btn").click(function () {
        var formData = getAddUserForm();
        if(!validateUserForm(formData)) {
            return;
        }
        postRequest(
            '/usermanage/add',
            formData,
            function (res) {
                getManagerList();
                getStaffList();
                $("#userAdd").modal('hide');
            },
             function (error) {
                alert(error);
            });
    });
    $("#usermodify-form-btn").click(function () {
        var formData = getModifyUserForm();
        if(!validateUserForm(formData)) {
            alert("invalidate!");
            return;
        }
        postRequest(
            '/usermanage/modify',
            formData,
            function (res) {
                getManagerList();
                getStaffList();
                $("#userModify").modal('hide');
            },
             function (error) {
                alert(error);
            });
    });
    function showSidebar(){
        var level = sessionStorage.getItem("level");
        var name = sessionStorage.getItem("username");
        $("p.title").text(htmlspecialchars1(name));
        if(level==0){
            $("#sidebar").append('<li role="presentation"><a href="/admin/vip/manage"><i class="icon-credit-card"></i> 会员策略</a></li>');
            $("#sidebar").append('<li role="presentation" class="active"><a href="#"><i class="icon-user"></i> 员工管理</a></li>');
        }else if(level==1){
            $("#sidebar").append('<li role="presentation"><a href="/admin/vip/manage"><i class="icon-credit-card"></i> 会员策略</a></li>');
        }
    }
});

function modifyUser(data){
    var username = getThisName(data);
    $("#userModifyModalLabel").text("当前员工:"+ htmlspecialchars1(username));
}
function deleteUser(data){
    var username = getThisName(data);
    // alert(username);
    var tmpStr = "您确定要删除用户"+username+"?";
    confirm(tmpStr) && postRequest(
        '/usermanage/delete',
        username,
        function (res) {
            getManagerList();
            getStaffList();
        },
         function (error) {
            alert(error);
        });
}
function getAddUserForm(){
    //TODO
    return {
        "username":$("#user-name-input").val(),
        "password":$("#user-password-input").val(),
        "level":$("#level-input").val(),
        "pass_again":$("#password-again-input").val()
    };
}
function getModifyUserForm(){
    //TODO
    console.log(($("#userModifyModalLabel").val()));
    return {
        "username":$("#userModifyModalLabel").text().split(":")[1],
        "password":$("#modify-password-input").val(),
        "level":$("#modify-level-input").val(),
        "pass_again":$("#modify-again-input").val()
    };
}
function validateUserForm(data){
    var isValidate = true;
        if (!data.username || data.username.length < 4 || data.username.length > 10) {
            isValidate = false;
            alert("用户名长度为4-10位！");
        }
        else if (!data.password || data.password.length < 6 || data.password.length > 12) {
            isValidate = false;
            alert("密码长度为6-12位！");
        }else if (!data.pass_again) {
            isValidate = false;
            alert("第二次输入密码不能为空！");
        } else if (data.pass_again != data.password) {
            isValidate = false;
            alert("两次输入密码不一致！");
        }

        return isValidate;
}
function getManagerList(){
    getRequest(
        '/usermanage/managers',
        function(res){
            renderManagerList(res.content);
        },
        function(error){
            alert(error);
        }
    );
}
function getStaffList(){
    getRequest(
        '/usermanage/staff',
        function(res){
            renderStaffList(res.content);
        },
        function(error){
            alert(error);
        }
    );
}
function renderManagerList(list){
    $('#manager').empty();
    var mlDomStr = "";
    list.forEach(element => {
        mlDomStr+=
        '<li class="um-card">'+
            '<div class="um-card-info">'+
                '<span class="um-username">用户名：'+ htmlspecialchars1(element.username)+'</span> <span class="um-password">密码：'+ htmlspecialchars1(element.password)+'</span>'+
            '</div>'+
            '<div class="button-div">'+
            '<button type="button" class="user-button" onclick="modifyUser(this)" data-backdrop="static" data-toggle="modal" data-target="#userModify"">修改</button>'+
            '<button type="button" class="user-button" onclick="deleteUser(this)">删除</button>'+
            '</div>'+
        '</li>'
    });
    $('#manager').append(mlDomStr);
}
function renderStaffList(list){
    $('#staff').empty();
    var slDomStr = "";
    list.forEach(element => {
        slDomStr+=
        '<li class="um-card">'+
            '<div class="um-card-info">'+
            '<span class="um-username">用户名：'+ htmlspecialchars1(element.username)+'</span> <span class="um-password">密码：'+ htmlspecialchars1(element.password)+'</span>'+
            '</div>'+
            '<div class="button-div">'+
            '<button type="button" class="user-button" onclick="modifyUser(this)" data-backdrop="static" data-toggle="modal" data-target="#userModify">修改</button>'+
            '<button type="button" class="user-button" onclick="deleteUser(this)">删除</button>'+
            '</div>'+
        '</li>'
    });
    $('#staff').append(slDomStr);
}
function getThisName(data){
    return (data.parentNode.parentNode.children[0].children[0].innerHTML.split("：")[1]);
}
function htmlspecialchars1(str){            
    str = str.replace(/&/g, '&amp;');  
    str = str.replace(/</g, '&lt;');  
    str = str.replace(/>/g, '&gt;');  
    str = str.replace(/"/g, '&quot;');  
    str = str.replace(/'/g, '&#039;');  
    return str;  
}  
  
//这个版本多转换了一些内容  
function htmlspecialchars2(str) {        
        var s = "";  
        if (str.length == 0) return "";  
        for   (var i=0; i<str.length; i++)  
        {  
            switch (str.substr(i,1))  
            {  
                case "<": s += "&lt;"; break;  
                case ">": s += "&gt;"; break;  
                case "&": s += "&amp;"; break;  
                case " ":  
                    if(str.substr(i + 1, 1) == " "){  
                        s += " &nbsp;";  
                        i++;  
                    } else s += " ";  
                    break;  
                case "\"": s += "&quot;"; break;  
                case "\n": s += "<br>"; break;  
                default: s += str.substr(i,1); break;  
            }  
        }  
        return s;  
    } 