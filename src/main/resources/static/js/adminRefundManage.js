$(document).ready(function(){
    showSidebar();
    showRefundPolicy();
    $("#refund-form-btn").click(function () {
        var formData = getRefundForm();
        if(!validateRefundForm(formData)) {
            alert("请输入正确的退款比例！");
            return;
        }
        postRequest(
            '/refund/manage/update',
            formData,
            function (res) {
                showRefundPolicy(res.content);
                $("#refundModal").modal('hide');
            },
             function (error) {
                alert(error);
            });
    });
    function showRefundPolicy(data){
        getRequest(
            '/refund/manage/refundpolicy',
            function(res){
                renderRefundPage(res.content);
            },
            function(error){
                alert(error);
            }
        );
    }
    function renderRefundPage(data){
        // alert(data.refund_day);
        $("#r-day").html(data.refund_day);
        $("#r-hour").html(data.refund_hour);
    }
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

    function validateRefundForm(formData){
        var refund_day = formData.refund_day;
        var refund_hour = formData.refund_hour;
        console.log(refund_day);
        console.log(refund_hour);
        return !(isNaN(refund_day) || isNaN(refund_hour) || refund_day>1 || refund_day<0 ||refund_hour>1 ||refund_hour<0);
    }
    function getRefundForm(){
        return {
            "refund_day":parseFloat($("#refund-day").val()),
            "refund_hour":parseFloat($("#refund-hour").val())
        }
    }
    

});