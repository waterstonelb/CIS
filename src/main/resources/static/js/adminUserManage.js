$(document).ready(function(){
    getManagerList();
    getStaffList();
    showSidebar();
    function showSidebar(){
        var level = sessionStorage.getItem("level");
        var name = sessionStorage.getItem("username");
        $("p.title").html(name);
        if(level==0){
            $("#sidebar").append('<li role="presentation"><a href="/admin/vip/manage"><i class="icon-credit-card"></i> 会员策略</a></li>');
            $("#sidebar").append('<li role="presentation" class="active"><a href="#"><i class="icon-user"></i> 员工管理</a></li>');
        }else if(level==1){
            $("#sidebar").append('<li role="presentation"><a href="/admin/vip/manage"><i class="icon-credit-card"></i> 会员策略</a></li>');
        }
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
            '<li class="card">'+
                '<div class="um-card-info">'+
                    '用户名：<span>'+element.username+'</span> 密码：<span>'+element.password+'</span>'+
                '</div>'+
                '<button type="button">修改</button>'+
                '<button type="button">删除</button>'+
            '</li>'
        });
        $('#manager').append(mlDomStr);
    }
    function renderStaffList(list){
        $('#staff').empty();
        var slDomStr = "";
        list.forEach(element => {
            slDomStr+=
            '<li class="card">'+
                '<div class="um-card-info">'+
                    '用户名：<span>'+element.username+'</span> 密码：<span>'+element.password+'</span>'+
                '</div>'+
                '<button type="button">修改</button>'+
                '<button type="button">删除</button>'+
            '</li>'
        });
        $('#staff').append(slDomStr);
    }

});