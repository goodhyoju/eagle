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

                                                    test
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
    Kakao.init('699817b6a0cbbae4cc3ed7e9d1208db7');
    Kakao.isInitialized();
    Kakao.Auth.authorize({
        redirectUri: 'http://localhost:8080'
    });
    displayToken()
    function displayToken() {
        const token = getCookie('authorize-access-token')
        if(token) {
            Kakao.Auth.setAccessToken(token)
            Kakao.Auth.getStatusInfo(({ status }) => {
                if(status === 'connected') {
                    document.getElementById('token-result').innerText = 'login success. token: ' + Kakao.Auth.getAccessToken()
                } else {
                    Kakao.Auth.setAccessToken(null)
                }
            })
        }
    }
    function getCookie(name) {
        const value = "; " + document.cookie;
        const parts = value.split("; " + name + "=");
        if (parts.length === 2) return parts.pop().split(";").shift();
    }
</script>
</html>
