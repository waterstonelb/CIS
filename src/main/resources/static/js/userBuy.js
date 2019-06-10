var ticketList;
var cancelTickets=[];
$(document).ready(function () {
    $("#user-name").prepend(sessionStorage.getItem('username'));
    getMovieList();
    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                ticketList=res.content;
                renderTicketList(res.content);
                ticketList.forEach(function (ticket) {
                    if(ticket.state=="已完成"||ticket.state=="已出票")
                        $('#movie-cancel-input').append("<option value="+ ticket.id +">"+ticket.schedule.movieName+(ticket.rowIndex+1)+"排"+(ticket.columnIndex+1)+"座"+"</option>");
                })
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    function renderTicketList(list) {
        var strhtml='';
        for(let scheduleItemwithSeats of list) {
            strhtml+= "<tr>"+"<td>" + scheduleItemwithSeats.schedule.movieName + "</td>" +
                "<td>" + scheduleItemwithSeats.schedule.hallName + "</td>" +
                "<td>" + (scheduleItemwithSeats.rowIndex+1)+"排"+(scheduleItemwithSeats.columnIndex+1)+"座" + "</td>" +
                "<td>" + scheduleItemwithSeats.schedule.startTime.substr(0,19).replace("T", " ") + "</td>" +
                "<td>" + scheduleItemwithSeats.schedule.endTime.substr(0,19).replace("T", " ") + "</td>";
            if(scheduleItemwithSeats.state=="已完成")
                strhtml+="<td><button id='"+scheduleItemwithSeats.id+"'  onclick='issueTicket(this)'>出票</button></td>"+"</tr>";
            else
                strhtml+="<td>" + scheduleItemwithSeats.state + "</td>"+"</tr>";
        }
        $('#schedule-info').append(strhtml)
    }

    $('#movie-cancel-input').change(function () {
        var ticketId = $('#movie-cancel-input').val();
        var inList = false;
        cancelTickets.forEach(element => {
            // console.log("ele:"+element);
            // console.log("ticket"+ticketId);
            if(element==ticketId){
                inList = true;
            }
        });
        if(!inList){
            cancelTickets.push(ticketId);
            renderSelectedMovies(ticketId);
        }
    });

    //渲染选择的退票的电影
    function renderSelectedMovies(ticketId) {
        var strhtml='';
        for(let scheduleItemwithSeats of ticketList) {
            if (scheduleItemwithSeats.id == ticketId) {
                strhtml +="<tr id='"+scheduleItemwithSeats.id+"'>"+"<td>" + scheduleItemwithSeats.schedule.movieName + "</td>" +
                    "<td>" + scheduleItemwithSeats.schedule.hallName + "</td>" +
                    "<td>" + (scheduleItemwithSeats.rowIndex+ 1) + "排" + (scheduleItemwithSeats.columnIndex + 1) + "座" + "</td>" +
                    "<td>" + scheduleItemwithSeats.schedule.startTime.substr(0, 19).replace("T", " ") + "</td>" +
                    "<td>" + scheduleItemwithSeats.schedule.endTime.substr(0, 19).replace("T", " ") + "</td>" +
                    "<td>" + scheduleItemwithSeats.state + "</td>"+ "</tr>";
            }
        }
        $('#cancel-info').append(strhtml);
    }
    $("#cancel-btn-form").click(function () {
        if (cancelTickets.length<1)
            alert("您没有选择要退的电影票");
        else {
            postRequest(
                "/ticket/cancel",
                cancelTickets,
                function (res) {
                    if(res.content!="普通用户")
                        sessionStorage.setItem("balance",res.content);
                    alert("退票成功");
                    location.reload();
                },
                function (error) {
                    alert("退票失败")
                }
            )
        }
    });
    $("#back-btn").click(function () {
        $("#cancel-info").empty();
        cancelTickets=[];
    });

});

function issueTicket(data){
    alert("出票了！"+data.getAttribute("id"));
    postRequest(
        "/ticket/issue",
        data.getAttribute("id"),
        function (res) {
            alert("出票成功");
            location.reload();
        },
        function (error) {
            alert("出票失败")
        }
    )
}