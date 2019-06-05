$(document).ready(function(){
    showSidebar();
    function showSidebar(){
        var level = sessionStorage.getItem("level");
        var name = sessionStorage.getItem("username");
        $("p.title").html(name);
        if(level==0){
            $("#sidebar").append('<li role="presentation" class="active"><a href="#"><i class="icon-credit-card"></i> 会员策略</a></li>');
            $("#sidebar").append('<li role="presentation"><a href="/admin/usermanage/manage"><i class="icon-user"></i> 员工管理</a></li>');
        }else if(level==1){
            $("#sidebar").append('<li role="presentation" class="active"><a href="#"><i class="icon-credit-card"></i> 会员策略</a></li>');
        }
    }
    function getVIPvard() {
    
    }
});