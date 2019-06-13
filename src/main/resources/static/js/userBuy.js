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
                    if(ticket.state=="已完成")
                        $('#movie-cancel-input').append("<option value="+ ticket.id +">"+
                        ticket.schedule.movieName+(ticket.rowIndex+1)+"排"+(ticket.columnIndex+1)+"座 "+
                        ticket.schedule.startTime.substr(0,16).replace("T", " ")+"</option>");
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
                strhtml+="<td><button class='btn btn-primary' id='"+scheduleItemwithSeats.id+"' data-toggle='modal' data-target='#myModal' onclick='issueTicket(this)'>出票</button></td>"+"</tr>";
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
                    var ret = res.content;
                    console.log(ret);
                    console.log(sessionStorage.getItem("balance"));
                    if(ret!="普通用户")
                        sessionStorage.setItem("balance",ret+parseFloat(sessionStorage.getItem("balance")));
                    alert("退票成功!找您 "+ret.toFixed(2)+" 元。");
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
    // alert("出票了！"+data.getAttribute("id"));
    postRequest(
        "/ticket/issue",
        data.getAttribute("id"),
        function (res) {
            $("#ticket-movie-name").text(res.content.schedule.movieName);
            $("#ticket-user-name").text(sessionStorage.getItem("username"));
            $("#ticket-seat1").text((res.content.rowIndex+1)+"-"+(res.content.columnIndex+1));
            $("#ticket-seat2").text((res.content.rowIndex+1)+"-"+(res.content.columnIndex+1));
            $("#ticket-timestart").text(res.content.schedule.startTime.substr(11,5));
            $("#ticket-timeend").text(res.content.schedule.endTime.substr(11,5));
        },
        function (error) {
            alert("出票失败")
        }
    )
}