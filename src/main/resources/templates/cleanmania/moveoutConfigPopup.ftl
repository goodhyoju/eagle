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
</head>
<body>
    <div class="container" style="max-width: 1000px;margin-top: 5px;">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-header-left">
                            <h5 class="text-primary"><i class="ti-info-alt"></i> 카카오톡 전송 메시지 관리페이지 입니다. 체크된 전송방식으로 적용/저장됩니다.</h5>
                        </div>
                        <div class="card-header-right">
                            <button class="btn btn-sm btn-round btn-grd-info" style="padding: 3px 14px;font-size: 15px;" id="configSaveBtn"><i class="ti-save" style="color: white; font-weight: bold;"></i> 설정 저장</button>
                        </div>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-5">
                                <div class="card" style="margin-bottom: 5px;padding: 5px;">
                                    <div class="card-header" style="padding: 5px;">
                                        <h5><i class="ti-pencil-alt"></i> 상담원 수수료</h5>
                                    </div>
                                    <div class="card-block form-group green-border-focus" style="padding: 1px;">
                                        <div class="form-group row">
                                            <div class="col-sm-10" style="padding-right: 0px;">
                                                <input type="number"  id="commission" class="form-control form-control-round" style="padding-right: 10px;text-align: right;" placeholder="4,000">
                                            </div>
                                            <label class="col-sm-2 col-form-label">원</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="card" style="margin-bottom: 5px;padding: 5px;">
                                    <div class="card-header" style="padding: 5px;">
                                        <h5><i class="ti-pencil-alt"></i> 전송 메시지 설정</h5>
                                    </div>
                                    <div class="card-block form-group green-border-focus" style="padding: 1px;">
                                                                        <textarea class="form-control" id="template" rows="5">청소매니아 입니다.
아래 고객으로부터 이사 요청이 있습니다.</textarea>
                                    </div>
                                </div>
                                <div class="card" style="margin-bottom: 5px;padding: 5px;">
                                    <div class="card-header" style="padding: 5px;">
                                        <h5><i class="ti-printer"></i> 전송 메시지 확인</h5>
                                    </div>
                                    <div class="card-block form-group green-border-focus" style="padding: 1px;">
                                        <div class="col-12 f-12 border-info p-10">
                                            <p class="f-12" id="template_label">청소매니아 입니다.
                                                <br/>아래 고객으로부터 이사 요청이 있습니다.</p>

                                            <br/>■신청자명 :  홍길동
                                            <br/>■연락처 : 010-999-0000
                                            <br/>■이사날짜 : 2022.3.3
                                            <br/>■출발지 : 인천 서구
                                            <br/>■도착지 : 서울 동작
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-7">
                                <div class="card">
                                    <div class="panel-group" id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading ui-accordion-header">
                                                <h6 class="panel-title" style="margin: 5px;">
                                                    <label for='r11' style="margin-bottom: 0px;">
                                                        <input type='radio' id='r11' name='mtype' value='sigle' required /> 1개 연락처에 발송
                                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"></a>
                                                    </label>
                                                </h6>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse in">
                                                <div class="panel-body m-l-5 m-r-5">
                                                    <div class="form-group row m-10">
                                                        <label class="col-sm-3 col-form-label" style="padding-right: 1px;"><i class="ti-money"></i>단가</label>
                                                        <div class="col-sm-7" style="padding-left: 1px;">
                                                            <input type="number"  id="sigle_price" class="form-control form-control-round" style="padding-right: 10px;text-align: right;" placeholder="20,000">
                                                        </div>
                                                        <label class="col-sm-1 col-form-label" style="padding-left: 1px;">원</label>

                                                        <label class="col-sm-3 col-form-label" style="padding-right: 1px;"><i class="ti-id-badge"></i>이름</label>
                                                        <div class="col-sm-9" style="padding-left: 1px;">
                                                            <input type="text" id="sigle_name" name="sigle_name" class="form-control form-control-round" placeholder="수신자 이름">
                                                        </div>
                                                        <label class="col-sm-3 col-form-label"><i class="ti-mobile" style="padding-right: 1px;"></i>연락처</label>
                                                        <div class="col-sm-9" style="padding-left: 1px;">
                                                            <input type="text" id="sigle_phone" name="sigle_phone" class="form-control form-control-round" placeholder="000-0000-0000">
                                                        </div>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>


                                        <div class="panel panel-default">
                                            <div class="panel-heading ui-accordion-header">
                                                <h6 class="panel-title"  style="margin: 5px;">
                                                    <label for='r14' style="margin-bottom: 0px;">
                                                        <input type='radio' id='r14' name='mtype' value='select' required /> 연락처 선택 발송(등록화면 전송용)
                                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"></a>
                                                    </label>
                                                </h6>
                                            </div>
                                            <div id="collapseFour" class="panel-collapse collapse">
                                                <div class="panel-body m-l-5 m-r-5" id="select_card">
                                                    <div class="form-group row m-10">
                                                        <div class="col-sm-12" style="padding: 1px;margin-bottom: 5px;">
                                                            <button class="btn btn-round btn-grd-success btn-mini btn-block" id="select_plus_btn"><i class="icofont icofont-plus"></i></button>
                                                        </div>
                                                        <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-money"></i>단가</label>
                                                        <div class="col-sm-6" style="padding-left: 1px;">
                                                            <input type="number"  id="select_price" class="form-control form-control-round" style="padding-right: 10px;text-align: right;" placeholder="20,000">
                                                        </div>
                                                        <label class="col-sm-1 col-form-label" style="padding-left: 1px;">원</label>
                                                    </div>

                                                    <div class="card" style="margin-bottom: 10px;">
                                                        <div class="card-header" style="padding: 10px 24px;">
                                                            <div class="card-header-right" style="top: 0px;padding: 0px;right: 0px;">
                                                                <a class="font-weight-bold card-remove text-danger" data-toggle="confirmation"><i class="ti-close"></i></a>
                                                            </div>
                                                        </div>
                                                        <div class="card-block text-center form-group row" style="padding: 7px 9px;margin-bottom: 0.25em;">
                                                            <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-id-badge"></i>이름</label>
                                                            <div class="col-sm-8" style="padding-left: 1px;">
                                                                <input type="text" name="select_name" class="form-control form-control-round" placeholder="수신자 이름">
                                                            </div>
                                                            <label class="col-sm-4 col-form-label" style="padding-right: 1px"><i class="ti-mobile"></i>연락처</label>
                                                            <div class="col-sm-8" style="padding-left: 1px;">
                                                                <input type="text" name="select_phone" class="form-control form-control-round" placeholder="000-0000-0000">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>




                                        <div class="panel panel-default">
                                            <div class="panel-heading ui-accordion-header">
                                                <h6 class="panel-title"  style="margin: 5px;">
                                                    <label for='r12' style="margin-bottom: 0px;">
                                                        <input type='radio' id='r12' name='mtype' value='all' required /> 2개이상의 연락처에 일괄 발송
                                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"></a>
                                                    </label>
                                                </h6>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse">
                                                <div class="panel-body m-l-5 m-r-5" id="all_card">
                                                    <div class="form-group row m-10">
                                                        <div class="col-sm-12" style="padding: 1px;margin-bottom: 5px;">
                                                            <button class="btn btn-round btn-grd-success btn-mini btn-block" id="all_plus_btn"><i class="icofont icofont-plus"></i></button>
                                                        </div>
                                                        <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-money"></i>단가</label>
                                                        <div class="col-sm-6" style="padding-left: 1px;">
                                                            <input type="number"  id="all_price" class="form-control form-control-round" style="padding-right: 10px;text-align: right;" placeholder="20,000">
                                                        </div>
                                                        <label class="col-sm-1 col-form-label" style="padding-left: 1px;">원</label>
                                                    </div>

                                                    <div class="card" style="margin-bottom: 10px;">
                                                        <div class="card-header" style="padding: 10px 24px;">
                                                            <div class="card-header-right" style="top: 0px;padding: 0px;right: 0px;">
                                                                <a class="font-weight-bold card-remove text-danger" data-toggle="confirmation"><i class="ti-close"></i></a>
                                                            </div>
                                                        </div>
                                                        <div class="card-block text-center form-group row" style="padding: 7px 9px;margin-bottom: 0.25em;">
                                                            <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-id-badge"></i>이름</label>
                                                            <div class="col-sm-8" style="padding-left: 1px;">
                                                                <input type="text" name="all_name" class="form-control form-control-round" placeholder="수신자 이름">
                                                            </div>
                                                            <label class="col-sm-4 col-form-label" style="padding-right: 1px"><i class="ti-mobile"></i>연락처</label>
                                                            <div class="col-sm-8" style="padding-left: 1px;">
                                                                <input type="text" name="all_phone" class="form-control form-control-round" placeholder="000-0000-0000">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading ui-accordion-header">
                                                <h6 class=panel-title"  style="margin: 5px;">
                                                    <label for='r13' style="margin-bottom: 0px;">
                                                        <input type='radio' id='r13' name='mtype' value='random' required /> 연락처에서 랜덤 발송
                                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"></a>
                                                    </label>
                                                </h6>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse">
                                                <div class="panel-body m-l-5 m-r-5" id="random_card">
                                                    <div class="form-group row m-10">
                                                        <div class="col-sm-12" style="padding: 1px;margin-bottom: 5px;">
                                                            <button class="btn btn-round btn-grd-success btn-mini btn-block" id="random_plus_btn"><i class="icofont icofont-plus"></i></button>
                                                        </div>
                                                        <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-money"></i>단가</label>
                                                        <div class="col-sm-6" style="padding-left: 1px;">
                                                            <input type="number" id="random_price" class="form-control form-control-round" style="padding-right: 10px;text-align: right;" placeholder="20,000">
                                                        </div>
                                                        <label class="col-sm-1 col-form-label" style="padding-left: 1px;">원</label>
                                                        <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-reload"></i>랜덤갯수</label>
                                                        <div class="col-sm-6" style="padding-left: 1px;">
                                                            <input type="number" id="rdcount" class="form-control form-control-round" style="padding-right: 10px;text-align: right;" placeholder="1">
                                                        </div>
                                                        <label class="col-sm-1 col-form-label" style="padding-left: 1px;">개</label>
                                                    </div>
                                                    <div class="card" style="margin-bottom: 10px;">
                                                        <div class="card-header" style="padding: 10px 24px;">
                                                            <div class="card-header-right" style="top: 0px;padding: 0px;right: 0px;">
                                                                <a class="font-weight-bold card-remove text-danger" data-toggle="confirmation"><i class="ti-close"></i></a>
                                                            </div>
                                                        </div>
                                                        <div class="card-block text-center form-group row" style="padding: 7px 9px;margin-bottom: 0.25em;">
                                                            <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-id-badge"></i>이름</label>
                                                            <div class="col-sm-8" style="padding-left: 1px;">
                                                                <input type="text" name="random_name" class="form-control form-control-round" placeholder="수신자 이름">
                                                            </div>
                                                            <label class="col-sm-4 col-form-label" style="padding-right: 1px"><i class="ti-mobile"></i>연락처</label>
                                                            <div class="col-sm-8" style="padding-left: 1px;">
                                                                <input type="text" name="random_phone" class="form-control form-control-round" placeholder="000-0000-0000">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap-confirmation.js"></script>
<script type="text/javascript" src="assets/pages/accordion/accordion.js"></script>
<!-- notification js -->
<script type="text/javascript" src="assets/js/jquery.bootstrap-growl.js"></script>

<script type="text/javascript" src="assets/pages/moveoutConfigPopup.js"></script>

</html>