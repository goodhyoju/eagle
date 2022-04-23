<!DOCTYPE html>
<html lang="utf-8">
<head>
    <title>청소매니아 </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Favicon icon -->
    <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">

    <!-- Google font--><link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600" rel="stylesheet">
    <!-- Required Fremwork -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css">
    <!-- themify-icons line icon -->
    <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css">
    <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css">

    <!-- ico font -->
    <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">

    <!-- bootstrap datetimepicker -->
    <link  href="assets/css/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">

</head>
<body>
    <div class="container" style="max-width: 100%">
        <div class="row">
            <div class="col-12">
                <div class="card" >
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-12 form-group">
                                <label for="name">name</label>
                                <input class="form-control" id="name" value="개똥이"/>
                                <label for="phone">phone</label>
                                <input class="form-control" id="phone" value="010-0000-0000"/>
                                <label for="movedate">movedate</label>
                                <input class="form-control" id="movedate" value="9999-99-99"/>

                                <label for="start">start</label>
                                <input class="form-control" id="start" value="경기도 목성"/>
                                <label for="end">end</label>
                                <input class="form-control" id="end" value="경기도 수성"/>

                                <label for="sender">sender</label>
                                <input class="form-control" id="sender"  value="홍길동"/>
                                <label for="sendernum">sendernum</label>
                                <input class="form-control" id="sendernum" value="010-4177-1954"/>

                                <button class="btn btn-danger" id="testCallBtn">메시지 보내기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>

<!-- notification js -->
<script type="text/javascript" src="assets/js/jquery.bootstrap-growl.js"></script>

<!-- bootstrap datetimepicker -->
<script src="assets/js/datetimepicker/moment.min.js" ></script>
<script src="assets/js/datetimepicker/moment-with-locales.min.js"></script>
<script src="assets/js/datetimepicker/ko.js"></script>
<script src="assets/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
    $("#testCallBtn").click( function() {
        var json={
            type: "config",
            name: $("#name").val(),
            phone: $("#phone").val(),
            movedate: $("#movedate").val(),
            start: $("#start").val(),
            end: $("#end").val(),
            sender: $("#sender").val(),
            sendernum: $("#sendernum").val()
        };

        $.ajax({
            url: "/cleanmania/sendMoveOutTalk",
            type: "POST",
            data: JSON.stringify(json),
            dataType: 'json',
            contentType: "application/json; UTF-8;"
        });

        /*var tjson={
            name: "구마적",
            phone: "010-0000-000",
        };

        $.ajax({
            url: "/cleanmania/talkSelectListCheck",
            type: "POST",
            data: JSON.stringify(tjson),
            dataType: 'json',
            contentType: "application/json; UTF-8;",
            success: function(data){
                console.log('data: '+data);
            },error: function (request, status, error){
                console.log('data null');
            }
        });*/
    });
</script>
</html>