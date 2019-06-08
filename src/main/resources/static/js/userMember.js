$(document).ready(function () {
    getVIP();
    getCoupon();
    getVIPCards();
});

var isBuyState = true;
var vipCardId;
var cardId;
var targetAmount;
var discountAmount;

function getVIPCards() {
    getRequest(
        '/vipactivity/getValid',
        function (res) {
            renderVIPCards(res.content);
        }
    );
}
function renderVIPCards(list) {
    $("#user-vip-list").empty();
    var innerHTML="";
    list.forEach(function (card) {
        innerHTML+="<div class='info'>"+
            "            <div class='card-id'>" +card.cardName+
            "<span class=\"label label-warning\">"+"<span>ID:</span>"+"<span>"+card.id+"</span>"+"</span>"+
            "            </div>"+
            "            <div class=\"price\"><b id=\"member-buy-price\">"+card.cardPrice+"</b>元/张</div>" +
            "            <div id=\"member-buy-description\" class=\"description\">"+"充值优惠:满"+card.targetAmount+"送"+card.discountAmount+"</div>" +
            "            <div class='description'> 购票打"+card.discount*10+"折</div> "+
            "            <button onclick=\"buyClick(this)\">立即购买</button>"+"</div>"
    });
    $("#user-vip-list").append(innerHTML);
}
function getVIP() {
    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                // 是会员
                $("#member-card").css("visibility", "visible");
                $("#member-card").css("display", "");
                $("#nonmember-card").css("display", "none");
                $("#member-id").text(res.content.id);
                $("#member-joinDate").text(res.content.joinDate.substring(0,10));
                $("#member-balance").text(res.content.balance.toFixed(2));
                cardId=res.content.cardId;
                vipCardId = res.content.id;
                getUserVIPCard(res.content.cardId);
            } else {
                // 非会员
                $("#member-card").css("display", "none");
                $("#nonmember-card").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });
    function getUserVIPCard(cardId) {
        getRequest(
            '/vipactivity/getbyid?cardId='+cardId,
            function (res) {
                if(res.success){
                    $("#member-name").text(res.content.cardName);
                    $("#member-description").text("充值满"+res.content.targetAmount+"送"+res.content.discountAmount);
                    $("#member-discount").text("购票享"+res.content.discount*10+"折优惠");
                    targetAmount=res.content.targetAmount;
                    discountAmount=res.content.discountAmount;
                }
            }
        )
    }
    /**
    getRequest(
        '/vip/getVIPInfo',
        function (res) {
            if (res.success) {
                $("#member-buy-price").text(res.content.price);
                $("#member-buy-description").text("充值优惠：" + res.content.description + "。永久有效");
                $("#member-description").text(res.content.description);
            } else {
                alert(res.content);
            }

        },
        function (error) {
            alert(error);
        });*/
}

function buyClick(obj) {
    clearForm();
    $('#buyModal').modal('show');
    $("#userMember-amount-group").css("display", "none");
    $("#vip-card-id").text($(obj).parent().children().eq(0).children().children().eq(1).text());
    isBuyState = true;
}

function chargeClick() {
    clearForm();
    $('#buyModal').modal('show');
    $("#userMember-amount-group").css("display", "");
    $("#vip-card-id").text($("#member-id").text());
    isBuyState = false;
}

function upCard() {
    alert("测试");
}

function clearForm() {
    $('#userMember-form input').val("");
    $('#userMember-form .form-group').removeClass("has-error");
    $('#userMember-form p').css("visibility", "hidden");
}

function confirmCommit() {
    if (validateForm()) {
        if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {
            if (isBuyState) {
                getRequest(
                    '/vip/add?userId=' + sessionStorage.getItem('id')+"&&cardId="+$("#vip-card-id").text(),
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("购买会员卡成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            } else {
                postRequest(
                    '/vip/charge',
                    {vipId: vipCardId, cardId:cardId ,targetAmount:targetAmount,discountAmount:discountAmount,amount: parseInt($('#userMember-amount').val())},
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("充值成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            }
        } else {
            alert("银行卡号或密码错误");
        }
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userMember-cardNum').val()) {
        isValidate = false;
        $('#userMember-cardNum').parent('.form-group').addClass('has-error');
        $('#userMember-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userMember-cardPwd').val()) {
        isValidate = false;
        $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
        $('#userMember-cardPwd-error').css("visibility", "visible");
    }
    if (!isBuyState && (!$('#userMember-amount').val() || parseInt($('#userMember-amount').val()) <= 0)) {
        isValidate = false;
        $('#userMember-amount').parent('.form-group').addClass('has-error');
        $('#userMember-amount-error').css("visibility", "visible");
    }
    return isValidate;
}

function getCoupon() {
    getRequest(
        '/coupon/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                var couponList = res.content;
                var couponListContent = '';
                for (let coupon of couponList) {
                    couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                        '<div class="col-md-8 left">' +
                        '<div class="name">' +
                        coupon.name +
                        '</div>' +
                        '<div class="description">' +
                        coupon.description +
                        '</div>' +
                        '<div class="price">' +
                        '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4 right">' +
                        '<div>有效日期：</div>' +
                        '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                        '</div></div></div></div>'
                }
                $('#coupon-list').html(couponListContent);

            }
        },
        function (error) {
            alert(error);
        });
}

function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}