$(document).ready(function() {

    var canSeeDate = 0;

    getCanSeeDayNum();
    getCinemaHalls();
    showSidebar();

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                halls = res.content;
                renderHall(halls);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderHall(halls){
        $('#hall-card').empty();
        $('.selector').empty();
        var hallDomStr = "";
        halls.forEach(function (hall) {
            $('.selector').append("<option value="+ hall.id +">"+hall.name+"</option>");
            var seat = "";
            for(var i =0;i<hall.row;i++){
                var temp = ""
                for(var j =0;j<hall.column;j++){
                    temp+="<div class='cinema-hall-seat'></div>";
                }
                seat+= "<div>"+temp+"</div>";
            }
            var hallDom =
                "<div class='cinema-hall'>" +
                "<div>" +
                "<span class='cinema-hall-name'>"+ hall.name +"</span>" +
                "<span class='cinema-hall-size'>"+ hall.column +'*'+ hall.row +"</span>" +
                "</div>" +
                "<div class='cinema-seat'>" + seat +
                "</div>" +
                "</div>";
            hallDomStr+=hallDom;
        });
        $('#hall-card').append(hallDomStr);
    }

    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    $('#canview-modify-btn').click(function () {
       $("#canview-modify-btn").hide();
       $("#canview-set-input").val(canSeeDate);
       $("#canview-set-input").show();
       $("#canview-confirm-btn").show();
    });

    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        if(isNaN(dayNum)){
            alert("请输入一个数字！")
            return
        }
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })

    function showSidebar(){
        var level = sessionStorage.getItem("level");
        var name = sessionStorage.getItem("username");
        $("p.title").html(name);
        if(level==0){
            $("#sidebar").append('<li role="presentation"><a href="/admin/vip/manage"><i class="icon-credit-card"></i> 会员策略</a></li>');
            $("#sidebar").append('<li role="presentation"><a href="/admin/usermanage/manage"><i class="icon-user"></i> 员工管理</a></li>');
        }else if(level==1){
            $("#sidebar").append('<li role="presentation"><a href="/admin/vip/manage"><i class="icon-credit-card"></i> 会员策略</a></li>');
        }
    }
    $('#edit-hall-btn').click(function () {
        var form = {
            id: $("#select-hall-id").children('option:selected').val(),
            column : $("#edit-column").val(),
            row: $("#edit-row").val(),
            name: $("#edit-name").val()
        };

        postRequest(
            '/hall/update',
            form,
            function (res) {
                if(res.success){
                    getCinemaHalls();
                    $("#editModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
    $('#add-hall-btn').click(function () {
        var form = {
            column : $("#add-column").val(),
            row: $("#add-row").val(),
            name: $("#add-name").val()
        };


        postRequest(
            '/hall/add',
            form,
            function (res) {
                if(res.success){
                    getCinemaHalls();
                    $("#addModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
    $('#del-hall-btn').click(function () {
        var form = {
            id: $("#del-hall-id").children('option:selected').val()
        };

        postRequest(
            '/hall/del',
            form,
            function (res) {
                if(res.success){
                    getCinemaHalls();
                    $("#delModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
});
