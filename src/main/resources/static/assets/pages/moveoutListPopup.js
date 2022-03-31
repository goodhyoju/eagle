$( document ).ready(function() {
    $("#datetimepicker1") .datetimepicker({
        locale: "ko",
        format: "YYYY-MM",
        defaultDate: moment(),
        inline: true
    }).on('dp.change', function(e){
        createDashboard();
    });

    createDashboard();
});

function createDashboard(){
    $(".card .card-header span").css('display','inline-block');
    $("#dashboardIframe").attr('height',screen.height-150)

    var getDate = $("#datetimepicker1").data("DateTimePicker").date().toDate();
    var begin = moment(getDate).format('YYYY-MM-01 00:00:00');
    var end = moment(getDate).format("YYYY-MM-") + moment(getDate).daysInMonth()+' 23:59:59';

    var unixBegin = moment(begin).unix()*1000;
    var unixEnd = moment(end).unix()*1000;

    var createUrl = "http://49.50.174.83:3000/d/vwlz7tynz/ceongsomaenia-isa-katog-tonggye?orgId=1&refresh=1m&kiosk&from="+unixBegin+"&to="+unixEnd
    console.log(createUrl);

    $("#dashboardIframe").attr('src',createUrl);
}