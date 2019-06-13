var selectedSeats = [];
var scheduleId;
var order = { ticketId: [], couponId: 0, userId: 0 };
var coupons = [];
var isVIP = false;
var useVIP = true;
var ticket;
var userId;

$(document).ready(function () {
    $("#user-name").prepend(sessionStorage.getItem('username'));
    scheduleId = parseInt(window.location.href.split('?')[1].split('&')[1].split('=')[1]);
    userId = sessionStorage.getItem("id")
    getInfo();

    function getInfo() {
        getRequest(
            '/ticket/get/occupiedSeats?scheduleId=' + scheduleId + '&&id=' + sessionStorage.getItem("id"),
            function (res) {
                if (res.success) {
                    renderSchedule(res.content.scheduleItem, res.content.seats);
                }
                else {
                    alert(res.message);
                    location.reload();
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }
});

function renderSchedule(schedule, seats) {
    $('#schedule-hall-name').text(schedule.hallName);
    $('#order-schedule-hall-name').text(schedule.hallName);
    $('#schedule-fare').text(schedule.fare.toFixed(2));
    $('#order-schedule-fare').text(schedule.fare.toFixed(2));
    $('#schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");
    $('#order-schedule-time').text(schedule.startTime.substring(5, 7) + "月" + schedule.startTime.substring(8, 10) + "日 " + schedule.startTime.substring(11, 16) + "场");

    var hallDomStr = "";
    var seat = "";
    for (var i = 0; i < seats.length; i++) {
        var temp = ""
        for (var j = 0; j < seats[i].length; j++) {
            var id = "seat" + i + j

            if (seats[i][j] == 0) {
                // 未选
                temp += "<button class='cinema-hall-seat-choose' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
            } else if (seats[i][j] == 1) {
                // 已完成
                temp += "<button class='cinema-hall-seat-finish' disabled='true'></button>";
            } else if (seats[i][j] == 2) {
                //锁座
                temp += "<button class='cinema-hall-seat' id='" + id + "' onclick='seatClick(\"" + id + "\"," + i + "," + j + ")'></button>";
                selectedSeats[selectedSeats.length] = [i, j];
                selectedSeats.sort(function (x, y) {
                    var res = x[0] - y[0];
                    return res === 0 ? x[1] - y[1] : res;
                });
            }
        }
        seat += "<div>" + temp + "</div>";
    }
    var hallDom =
        "<div class='cinema-hall'>" +
        "<div>" +
        "<span class='cinema-hall-name'>" + schedule.hallName + "</span>" +
        "<span class='cinema-hall-size'>" + seats.length + '*' + seats[0].length + "</span>" +
        "</div>" +
        "<div class='cinema-seat'>" + seat +
        "</div>" +
        "</div>";
    hallDomStr += hallDom;

    $('#hall-card').html(hallDomStr);
    beforConfirm();
}

function seatClick(id, i, j) {
    let seat = $('#' + id);
    if (seat.hasClass("cinema-hall-seat-choose")) {//未选择：灰色
        seat.removeClass("cinema-hall-seat-choose");
        seat.addClass("cinema-hall-seat");//已选择:绿色

        selectedSeats[selectedSeats.length] = [i, j];
    } else {
        seat.removeClass("cinema-hall-seat");//去掉绿色
        seat.addClass("cinema-hall-seat-choose");//改为灰色

        selectedSeats = selectedSeats.filter(function (value) {//selectedSeats改为0
            return value[0] != i || value[1] != j;
        })
    }

    selectedSeats.sort(function (x, y) {
        var res = x[0] - y[0];
        return res === 0 ? x[1] - y[1] : res;
    });
    beforConfirm();

}
function beforConfirm() {
    let seatDetailStr = "";
    if (selectedSeats.length == 0) {
        seatDetailStr += "还未选择座位";
        $('#order-confirm-btn').attr("disabled", "disabled")
    } else {
        for (let seatLoc of selectedSeats) {
            seatDetailStr += "<span>" + (seatLoc[0] + 1) + "排" + (seatLoc[1] + 1) + "座</span>";
        }
        $('#order-confirm-btn').removeAttr("disabled");
    }
    $('#seat-detail').html(seatDetailStr);
}

function orderConfirmClick() {
    $('#seat-state').css("display", "none");
    $('#order-state').css("display", "");
    id = parseInt(window.location.href.split('?')[1].split('&')[0].split('=')[1]);
    var orderInfo;

    // 传递已选座位表
    function toS() {
        var json = [];
        for (var k = 0; k < selectedSeats.length; k++) {
            var j = {};
            j.rowIndex = selectedSeats[k][0];
            j.columnIndex = selectedSeats[k][1];
            json.push(j)
        }
        return json
    }
    postRequest(
        "/ticket/lockSeat",
        {
            userId: userId,
            scheduleId: scheduleId,
            seats: toS()
        },
        function (res) {
            if (res.success) {
                orderInfo = res.content;
                renderOrder(orderInfo);
                coupons = orderInfo.coupons
            }
            else {
                alert(res.message);
                location.reload();
            }
        },
        function (error) {
            alert(error)
        }
    );
   

    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            isVIP = res.success;
            useVIP = res.success;
            if (isVIP) {
                $('#member-balance').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
            } else {
                $("#member-pay").css("display", "none");
                $("#nonmember-pay").addClass("active");

                $("#modal-body-member").css("display", "none");
                $("#modal-body-nonmember").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });
}

function switchPay(type) {
    useVIP = (type == 0);
    if (type == 0) {
        $("#member-pay").addClass("active");
        $("#nonmember-pay").removeClass("active");

        $("#modal-body-member").css("display", "");
        $("#modal-body-nonmember").css("display", "none");
    } else {
        $("#member-pay").removeClass("active");
        $("#nonmember-pay").addClass("active");

        $("#modal-body-member").css("display", "none");
        $("#modal-body-nonmember").css("display", "");
    }
}

function renderOrder(orderInfo) {
    var ticketStr = "<div>" + selectedSeats.length + "张</div>";
    for (let ticketInfo of orderInfo.ticketVOList) {
        ticketStr += "<div>" + (ticketInfo.rowIndex + 1) + "排" + (ticketInfo.columnIndex + 1) + "座</div>";
        order.ticketId.push(ticketInfo.id);
    }
    $('#order-tickets').html(ticketStr);

    var total = orderInfo.total.toFixed(2);
    $('#order-total').text(total);
    $('#order-footer-total').text("总金额： ¥" + total);
    if (sessionStorage.getItem('isVIP') == '1')
        $('#order-discount-total').text("会员折后价: ￥" + total * sessionStorage.getItem('discount'));

    var couponTicketStr = "";
    if (orderInfo.coupons.length == 0) {
        $('#order-discount').text("优惠金额：无");
        $('#order-actual-total').text(" ¥" + total * sessionStorage.getItem('discount'));
        $('#pay-amount').html("<div><b>金额：</b>" + total * sessionStorage.getItem('discount') + "元</div>");
    } else {
        coupons = orderInfo.coupons;
        for (let coupon of coupons) {
            couponTicketStr += "<option>满" + coupon.targetAmount + "减" + coupon.discountAmount + "</option>"
        }
        $('#order-coupons').html(couponTicketStr);
        changeCoupon(0);
    }
}

function changeCoupon(couponIndex) {
    order.couponId = coupons[couponIndex].id;
    $('#order-discount').text("优惠金额： ¥" + coupons[couponIndex].discountAmount.toFixed(2));
    var actualTotal = sessionStorage['discount'] * (parseFloat($('#order-total').text()) - parseFloat(coupons[couponIndex].discountAmount)).toFixed(2);
    if (sessionStorage.getItem('isVIP') == '1')
        $('#order-discount-total').text("会员折后价: ￥" + actualTotal);
    $('#order-actual-total').text(" ¥" + actualTotal);
    $('#pay-amount').html("<div><b>金额：</b>" + actualTotal + "元</div>");
}

function payConfirmClick() {
    if (useVIP) {
        postPayRequest();
    } else {
        if (validateForm()) {
            if ($('#userBuy-cardNum').val() === "123123123" && $('#userBuy-cardPwd').val() === "123123") {
                order.userId = sessionStorage.getItem('id');
                postPayRequest();
            } else {
                alert("银行卡号或密码错误");
            }
        }
    }
}

// TODO:填空
function postPayRequest() {
    $('#order-state').css("display", "none");
    $('#success-state').css("display", "");
    $('#buyModal').modal('hide');
    if (useVIP) {
        postRequest(
            "/ticket/vip/buy",
            {
                ticketId: order.ticketId,
                couponId: order.couponId,
                userId: sessionStorage.getItem("id")
            },
            function (res) {
                sessionStorage['balance'] = res.content;
            },
            function () {
            }
        )
    } else {
        postRequest(
            "/ticket/buy",
            order,
            function () {
            },
            function () {
            }
        )
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userBuy-cardNum').val()) {
        isValidate = false;
        $('#userBuy-cardNum').parent('.form-group').addClass('has-error');
        $('#userBuy-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userBuy-cardPwd').val()) {
        isValidate = false;
        $('#userBuy-cardPwd').parent('.form-group').addClass('has-error');
        $('#userBuy-cardPwd-error').css("visibility", "visible");
    }
    return isValidate;

}