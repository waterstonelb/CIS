var ticketList;
var cancelTickets=[];
$(document).ready(function () {
    $("#user-name").prepend(sessionStorage.getItem('username'));
    getUserBuy();
    function getUserBuy() {
        var id = sessionStorage.getItem('id');
        getRequest(
            '/userstatistics/BuyRecord?id='+id,
            function (res) {
                ticketList=res.content;
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        var strhtml='';
        for(let scheduleItemwithSeats of list) {
            strhtml+= "<tr>"+"<td>" + scheduleItemwithSeats.name + "</td>" +
                "<td>" + scheduleItemwithSeats.time + "</td>" +
                "<td>" + scheduleItemwithSeats.realPrice.toFixed(2) + "</td>"+"</tr>";
        }
        $('#schedule-info').append(strhtml)
    }


    getUserCharge();
    function getUserCharge() {
        var id = sessionStorage.getItem('id');
        getRequest(
            '/userstatistics/ChargeRecord?id='+id,
            function (res) {
                ticketList=res.content;
                renderTicketList2(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList2(list) {
        var strhtml2='';
        for(let scheduleItemwithSeats of list) {
            strhtml2+= "<tr>"+"<td>" + scheduleItemwithSeats.time + "</td>" +
                "<td>" + scheduleItemwithSeats.chargeNum+ "</td>"+"</tr>";
        }
        $('#cancel-info').append(strhtml2)
    }
});