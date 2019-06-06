$(document).ready(function(){

    showSidebar();
    getVIPvard();
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
        getRequest(
            '/vipactivity/getAll',
            function (res) {
                var vipCards = res.content;
                renderVIPCards(vipCards);
            }
        )
    }
    function renderVIPCards(vipCards) {
        $(".content-vipcard").empty();
        var innerValidHTML="";
        vipCards.forEach(function (vipCard) {
            if(vipCard.status==1) {
                innerValidHTML +=
                    "<div class='activity-container'>" +
                    "    <div class='activity-card card'>" +
                    "       <div class='activity-line'>" +
                    "           <span class=\"label label-success\" id='card-id'>ID:" + vipCard.id + "</span>" +
                    "           <span class='title'>" + vipCard.cardName + "</span>" +
                    "       </div>" +
                    "       <div class='activity-line'>" +
                    "           <span class='title'>" + vipCard.cardPrice + "元" + "</span>" +
                    "       </div>" +
                    "    </div>" +
                    "    <div class='activity-coupon primary-bg'>" +
                    "        <span class='title'>充值" + vipCard.targetAmount + "送" + vipCard.discountAmount + "</span>" +
                    "        <span class='title'>购票打" + vipCard.discount * 10 + "折</span>" +
                    "    </div>" +
                    "</div>";
            }
        });
        $(".content-vipcard").append(innerValidHTML);
    }

    $("#vip-delete-btn").click(function () {
       var id=$("#vip-delete-input").val();
       getRequest(
           '/vipactivity/invalid/'+id,
           function (res) {
               if(res.success) {
                   getVIPvard();
                   $("#vipdeleteModal").modal('hide');
               }else{
                   alert(res.message);
               }
           },
           function (error) {
               alert(JSON.stringify(error));
           }
       );
    });
    $("#vip-mod-btn").click(function () {
        var form={
            id: $("#vip-id-mod-input").val(),
            cardName: $("#vip-name-mod-input").val(),
            cardPrice: $("#vip-price-mod-input").val(),
            targetAmount: $("#vip-target-mod-input").val(),
            discountAmount: $("#vip-discountamount-mod-input").val(),
            discount: $("#vip-discount-mod-input").val()
        };
        postRequest(
            '/vipactivity/updata',
            form,
            function (res) {
                if(res.success){
                    getVIPvard();
                    $("#vipcardmodModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
    $("#vip-form-btn").click(function () {
        var form={
            cardName: $("#vip-name-input").val(),
            cardPrice: $("#vip-price-input").val(),
            targetAmount: $("#vip-target-input").val(),
            discountAmount: $("#vip-discountamount-input").val(),
            discount: $("#vip-discount-input").val()
        };
        postRequest(
            '/vipactivity/addcard',
            form,
            function (res) {
                if(res.success){
                    getVIPvard();
                    $("#vipcardModal").modal('hide');
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