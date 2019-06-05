$(document).ready(function(){//TODO
    var level = sessionStorage.getItem("level");
    if(level==0){//admin
        showAdmin();
    }else if(level==1){//manager
        showManager();
    }else if(level==2){//staff
        showStaff();
    }

    function showAdmin(){
        //人员管理
        $("#sidebar").append('<li role="presentation"><a href="/admin/usermanage/manage"><i class="icon-user"></i> 人员管理</a></li>');
    }
    function showManager(){
        //会员策略
    }
    function showStaff(){

    }
});