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
            innerValidHTML+=
                "<div class='activity-container'>" +
                "    <div class='activity-card card'>" +
                "       <div class='activity-line'>" +
                "           <span class=\"label label-success\">VIP</span>"+
                "           <span class='title'>"+vipCard.cardName+"</span>" +
                "       </div>" +
                "       <div class='activity-line'>" +
                "           <span class='title'>"+vipCard.cardPrice+"元"+"</span>"+
                "       </div>" +
                "    </div>" +
                "    <div class='activity-coupon primary-bg'>" +
                "        <span class='title'>充值"+vipCard.targetAmount+"送"+vipCard.discountAmount+"</span>" +
                "        <span class='title'>购票打"+vipCard.discount*10+"折</span>" +
                "    </div>" +
                "</div>";
        });
        $(".content-vipcard").append(innerValidHTML);
    }
});