$( document ).ready(function() {
    confirmation_init();

    $("#all_plus_btn").click( function() {
        addCard('all','','');
        confirmation_init();
    });
    $("#select_plus_btn").click( function() {
        addCard('select','','');
        confirmation_init();
    });
    $("#random_plus_btn").click( function() {
        addCard('random','','');
        confirmation_init();
    });
    $("#template").on("keydown", function(e){
        $("#template_label").html($(this).val().replace(/(?:\r\n|\r|\n)/g, '<br />'));
    });
    $('#r11').on('click', function(){
        $(this).parent().find('a').trigger('click')
    });

    $('#r12').on('click', function(){
        $(this).parent().find('a').trigger('click')
    });
    $('#r13').on('click', function(){
        $(this).parent().find('a').trigger('click')
    });
    $('#r14').on('click', function(){
        $(this).parent().find('a').trigger('click')
    });

    phoneCheck('sigle');
    phoneCheck('all');
    phoneCheck('random');

    loadingConfig();
});

function loadingConfig(){
    $.ajax({
        url: "/cleanmania/talkconfig",
        type: "GET",
        dataType: 'json',
        contentType: "application/json; UTF-8;",
        success: function(data){
            var mtype = data.mtype;
            var price = data.price;
            var names = data.names;
            var phone = data.phones;
            var template = data.template;
            var rdcount = data.rdcount;
            var commission = data.commission;

            $("#template").val(template);
            $("#template_label").html(template.replace(/(?:\r\n|\r|\n)/g, '<br />'));

            $("input[value='"+mtype+"']").click();
            $("#"+mtype+"_price").val(price);
            $("#rdcount").val(rdcount);

            $("#commission").val(commission);

            if(mtype=='sigle'){
                $("#sigle_name").val(names);
                $("#sigle_phone").val(phone);
            }else{
                var namesArr = names.split(',');
                var phoneArr = phone.split(',');
                if(namesArr.length == phoneArr.length){
                    $("#"+mtype+"_card").children('.card').remove();
                    $.each(phoneArr, function (index, item) {
                        var temp_name=namesArr[index];
                        var temp_phone=item;
                        addCard(mtype,temp_name,temp_phone);
                    });
                    confirmation_init();
                }
            }


        },
        error: function (request, status, error){
            notifycation("정보를 불러올 수 없습니다.","danger",'right')
        }

    });
}
function confirmation_init(){
    $('[data-toggle="confirmation"]').confirmation({
        placement: 'top',
        title: '삭제하시겠습니까?',
        btnOkLabel: '삭제',
        btnOkIconClass: 'ti-check',
        btnOkClass: 'btn btn-success',
        btnCancelLabel: '취소',
        btnCancelIconClass: 'ti-close',
        btnCancelClass: 'btn btn-danger',
        onConfirm: function (event, element) {
            $(this).parent().parent().parent().remove();
        },
        onCancel: function (event, element) {

        }
    });
}

function addCard(id,name,phone){
    var html = '<div class="card" style="margin-bottom: 10px;">'+
        '    <div class="card-header" style="padding: 10px 24px;">'+
        '        <div class="card-header-right" style="top: 0px;padding: 0px;right: 0px;">'+
        '            <a class="font-weight-bold card-remove text-danger" data-toggle="confirmation"><i class="ti-close"></i></a>'+
        '        </div>'+
        '    </div>'+
        '    <div class="card-block text-center form-group row" style="padding: 7px 9px;margin-bottom: 0.25em;">'+
        '        <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-id-badge"></i>이름</label>'+
        '        <div class="col-sm-8" style="padding-left: 1px;">'+
        '            <input type="text" name="'+id+'_name" class="form-control form-control-round" placeholder="수신자 이름" value="'+name+'">'+
        '        </div>'+
        '        <label class="col-sm-4 col-form-label" style="padding-right: 1px;"><i class="ti-mobile"></i>연락처</label>'+
        '        <div class="col-sm-8" style="padding-left: 1px;">'+
        '            <input type="text" name="'+id+'_phone" class="form-control form-control-round" placeholder="000-0000-0000" value="'+phone+'">'+
        '        </div>'+
        '    </div>'+
        '</div>';
    $("#"+id+"_card").append(html);
    phoneCheck(id);
}
function phoneCheck(id) {
    $("input[name='"+id+"_phone']").on("input", function () {
        var temp =destroyMask($(this).val());
        $(this).val(createMask(temp));
    })
}
function destroyMask(string) {
    return string.replace(/\D/g, '').substring(0,11);
}
function createMask(string) {
    return string.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3");
}

function notifycation(message,type,align){
    $.bootstrapGrowl(message, {
        ele: 'body', // which element to append to
        type: type, // (null, 'info', 'error', 'success')
        offset: {from: 'top', amount: 20}, // 'top', or 'bottom'
        align: align, // ('left', 'right', or 'center')
        width: 'auto'
    });
}

$('#configSaveBtn').confirmation({
    placement: 'left',
    title: '설정을 저장하시겠습니까??',
    btnOkLabel: '저장',
    btnOkIconClass: 'ti-check',
    btnOkClass: 'btn btn-success',
    btnCancelLabel: '취소',
    btnCancelIconClass: 'ti-close',
    btnCancelClass: 'btn btn-danger',
    onConfirm: function (event, element) {
        var mtypeValue = $("input[name='mtype']:checked").val();

        // check length to names,phones
        var checkFlag = false;
        $("input[name='"+mtypeValue+"_name']").map(function(){
            var name =  this.value;
            if(name == null || name.length < 1){
                checkFlag = true;
            }
        });
        $("input[name='"+mtypeValue+"_phone']").map(function(){
             var phone =  this.value;
            if(phone == null || phone.length < 1){
                checkFlag = true;
            }
        });

        if(checkFlag){
            notifycation('이름과 연락처는 모두 입력해야 합니다.','danger','center');
            return false;
        }
        console.log("GOGOG~~!");

        var json = {
            division: "cleanmania",
            mtype: mtypeValue,
            price: Number($("#"+mtypeValue+"_price").val()),
            used: 1,
            names: $("input[name='"+mtypeValue+"_name']").map(function(){ return this.value.replace(/,/gi, "/") }).get().join(),
            phones: $("input[name='"+mtypeValue+"_phone']").map(function(){ return this.value }).get().join(),
            rdcount: Number($("#rdcount").val()),
            template: $("#template").val(),
            commission: Number($("#commission").val())
        };

        $.ajax({
            url: "/cleanmania/talkConfigSave",
            type: "POST",
            data: JSON.stringify(json),
            dataType: 'json',
            contentType: "application/json; UTF-8;",
            success: function(data){
                notifycation("저장을 성공 하였습니다.","primary",'right');
            },
            error: function (request, status, error){
                notifycation("저장을 실패 하였습니다.","danger",'right')
            }

        });
    },
    onCancel: function (event, element) {

    }
});