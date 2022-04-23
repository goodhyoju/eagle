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
                    <div class="card-header">
                        <div class="row">
                            <div class="col-md-12"><h5><i class="ti-hand-point-up"></i>원하는 년월을 입력하면 대시보드가 출력됩니다. 그래프는 1분간격으로 자동 갱신 됩니다.</h5></div>
                            <div class="col-md-12">
                                <div id="datetimepicker1" class="form-bg-primary" style="border-radius: 30px;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <iframe id="dashboardIframe" src="" width="100%" height="700px" frameborder="0"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="pageType" value="${type}" />
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

<script type="text/javascript" src="assets/pages/moveoutListPopup.js"></script>
</html>