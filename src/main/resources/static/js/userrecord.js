var ticketList;
var cancelTickets=[];
$(document).ready(function () {
    getUserBuy();
    function getUserBuy() {
        getRequest(
            '/userstatistics/BuyRecord',
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
                "<td>" + scheduleItemwithSeats.realPrice + "</td>"+"</tr>";
        }
        $('#schedule-info').append(strhtml)
    }


    getUserCharge();
    function getUserCharge() {
        getRequest(
            '/userstatistics/ChargeRecord',
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