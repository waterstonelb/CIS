$(document).ready(function(){
    showSidebar();
    getVIPvard();
    getVIPCoupon();
    getVIPinfo();
    function getVIPinfo() {
        getRequest(
            '/vip/' + sessionStorage.getItem('id') + '/get',
            function (res) {
                if (res.success) {
                    // 是会员
                    sessionStorage.setItem('isVIP', '1');
                    sessionStorage.setItem('cardId', res.content.cardId);
                    sessionStorage.setItem('vipId', res.content.id);
                    sessionStorage.setItem('joinDate',res.content.joinDate);
                    sessionStorage.setItem('balance',res.content.balance);
                    getUserVIPCard(res.content.cardId);
                } else {
                    // 非会员
                    sessionStorage.setItem('isVIP', '0');
                    sessionStorage.setItem('discount','1');
                }
            },
            function (error) {
                alert(error);
            });

        function getUserVIPCard(cardId) {
            getRequest(
                '/vipactivity/getbyid?cardId=' + cardId,
                function (res) {
                    if (res.success) {
                        sessionStorage.setItem('targetAmount', res.content.targetAmount);
                        sessionStorage.setItem('discountAmount',res.content.discountAmount);
                        sessionStorage.setItem('discount',res.content.discount);
                        sessionStorage.setItem('cardName',res.content.cardName);
                    }
                }
            )
        }
    }
    function getVIPCoupon() {
        getRequest(
            '../../activity/get',
            function (res) {
                activities=res.content;
                activities.forEach(function (activity) {
                    if(activity.coupon.level==1){
                        $("#coupon-list").append("<option value="+ activity.coupon.id +">"+activity.coupon.name+"</option>")
                    }
                })
            }
        )
    }

    $("#date-submit").click(function () {
        getRequest(
            '../../statistics/amount?startDate='+$("#start-date").val()+"&&endDate="+$("#end-date").val(),
            function (res) {
                renderAmount(res.content);
            },
            function (error) {
                alert(error)
            }
        )
    })
    function renderAmount(list) {
        $("#amount-history").empty();
        var innerHTML="";
        if(list.length>0){
            list.forEach(function (every) {
                innerHTML+="<tr>"+
                    "<td>"+every.userId+"</td>"+
                    "<td>"+every.cardId+"</td>"+
                    "<td>"+every.realPrice.toFixed(2)+"</td>"+
                    "<td>"+"<button class='btn btn-primary' onclick='sendCoupon(this)'>赠送</button>"+"</td>"+
                    "</tr>"
            })
        }
        $("#amount-history").append(innerHTML);
    }
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
                    "    <div class='activity-card'>" +
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
        if($("#vip-discount-mod-input").val()>1){
            alert("打折优惠不能超过十折");
            return;
        }
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
        if($("#vip-discount-input").val()>1){
            alert("打折优惠不能超过十折");
            return;
        }
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
function sendCoupon(obj) {
    var userId=$(obj).parent().parent().children().eq(0).text();
    var couponId=$("#coupon-list").val();
    postRequest(
        '/vip/coupon',
        {
            userId:userId,
            couponId:couponId
        },
        function (res) {
            if(res.success)
                alert("赠送成功");
            else
                alert("赠送失败");
        },
        function (error) {
            alert(JSON.stringify(error));
        }
    )
}