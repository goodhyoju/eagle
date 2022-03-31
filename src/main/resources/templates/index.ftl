<!DOCTYPE html>
<html lang="utf-8">

<#include "/common/common_head.ftl">

<body>
<div class="fixed-button">
    <a href="https://codedthemes.com/item/gradient-able-admin-template" target="_blank" class="btn btn-md btn-primary">
        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Upgrade To Pro
    </a>
</div>
<!-- Pre-loader start -->
<div class="theme-loader">
    <div class="loader-track">
        <div class="loader-bar"></div>
    </div>
</div>
<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <#include "/common/common_top.ftl">

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">
                <#include "/common/common_left.ftl">
                <div class="pcoded-content">
                    <div class="pcoded-inner-content">

                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>Kakaotalk API Test</h5>
                                                    <span>이사 여부 체크후 전송하면 자동으로 카톡 대상자에게 카톡을 보낸다.</span>
                                                </div>
                                                <div class="card-block">

                                                    <button class="btn btn-success" id="configBtn">이사톡 환경설정</button>
                                                    <button class="btn btn-info" id="dashboardBtn">이사톡 대시보드</button>
                                                    <button class="btn btn-dark" id="testBtn">이사톡 TEST 하기</button>
                                                    <p id="token-result"></p>



                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="styleSelector">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/common/common_script.ftl">
</body>

<script type="text/javascript">
    $( document ).ready(function() {
        $("#configBtn").click( function() {
            PopupCenter('moveoutConfigPopup','moveout',800,600);
        });
        $("#dashboardBtn").click( function() {
            PopupCenter('moveoutListPopup','moveout',screen.width,screen.height );
        });
        $("#testBtn").click( function() {
            PopupCenter('moveoutTestPopup','moveout',800,600);
        });


    });
    function PopupCenter(url, title, w, h) {
        // Fixes dual-screen position                         Most browsers      Firefox
        var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
        var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;
        var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
        var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
        var left = ((width / 2) - (w / 2)) + dualScreenLeft;
        var top = ((height / 2) - (h / 2)) + dualScreenTop;
        var newWindow = window.open(url, "_blank", 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
        // Puts focus on the newWindow
        if (window.focus) {
            newWindow.focus();
        }
    }
</script>
</html>
