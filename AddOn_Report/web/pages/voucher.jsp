<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session.getAttribute("cono") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<script src="./assets/daterangepicker/datetimeui.js"></script>
<style>

    button {
        outline: none !important;
        border: hidden;
        background: springgreen;
    }

    .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
        font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
        font-size: 14px;
        font-weight: 300 !important;
    }

    .details-form-field input,
    .details-form-field select {
        width: 250px;
        float: right;
    }

    .details-form-field {
        margin: 25px 0;
    }

    .details-form-field:first-child {
        margin-top: 10px;
    }

    .details-form-field:last-child {
        margin-bottom: 10px;
    }

    .details-form-field button {
        display: block;
        width: 100px;
        margin: 0 auto;
    }

    input.error, select.error {
        border: 1px solid #ff9999;
        background: #ffeeee;
    }

    label.error {
        float: right;
        margin-left: 100px;
        font-size: .8em;
        color: #ff6666;
    }

    .form-control{
        display:block;
        width:100%;
        height:27px;
        padding:2px 0px;
        font-size:14px;
        line-height:1.42857143;
        color:#555;
        background-color:#fff;
        background-image:none;
        border:1px solid #ccc;
        border-radius:4px;
        -webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s

    }

    .form-control:focus{
        border-color:#66afe9;
        outline:0;
        -webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
        box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
    }

    .form-control::-moz-placeholder{
        color:#999;
        opacity:1
    }

    .form-control:-ms-input-placeholder{
        color:#999
    }

    .form-control::-webkit-input-placeholder{
        color:#999
    }

    .form-control::-ms-expand{
        background-color:transparent;
        border:0
    }

    .form-control[disabled],.form-control[readonly],fieldset[disabled] .form-control{
        background-color:#eee;
        opacity:1
    }

    .form-control[disabled],fieldset[disabled] .form-control{
        cursor:not-allowed
    }

    td.limitext{
        white-space: nowrap; 
        width: 100px; 
        overflow: hidden;
        text-overflow:ellipsis;
        /*text-overflow: ellipsis !important;*/
    }

    .wrap-login100 {
        width: 100%;
        background: #de5f5f;
        border-radius: 2px;
        overflow: hidden;
        padding: 10px 10px 10px 10px;

        box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -moz-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -o-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -ms-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
    }

    td.lvgb{
        background-color: #bdd4ff !important;
    }

</style>

<section class="container">
    <div class="wrap-login100" style="width: 100%;margin-bottom: 0px;">
        <label for="txtCode">Code :</label>
        <input id="vtxtCode" style="width: 50px" name="txtCode"type="text"/>
        <label for="txtPeriod">Period :</label>
        <input id="vtxtPeriod" style="width: 50px" name="Period"type="text"/>
        <label for="txtFunc">Func :</label>
        <input id="vtxtFunc" style="width: 50px" name="txtFunc" value="932" type="text"/>
        <label for="date">Date :</label>
        <input id="vDate" style="width: 80px" name="date" type="text"/>
        <label for="txtVCText">Text :</label>
        <input id="vtxtVCText" style="width: 200px" name="txtVCText" value="" type="text"/>
        <button id="vReStatus" class="btn btn-info" type="submit" form="">Re 10->00</button> 
        <button id="vPostData" class="btn btn-success" type="submit" form="">Post to M3</button> 
        <label id="vVoucherNumber"></label>
    </div>
    <div id="jsGrid"></div>

</section>

<script>

    $(function () {

        $("#vDate").datepicker({dateFormat: 'yy-mm-dd'}).datepicker("setDate", new Date($.now()));

        (function (jsGrid, $) {
            var NumberField = jsGrid.NumberField;

            function DecimalField(config) {
                NumberField.call(this, config);
            }

            DecimalField.prototype = new NumberField({

                step: 0.01,

                filterValue: function () {
                    return this.filterControl.val() ? parseFloat(this.filterControl.val()) : undefined;
                },

                insertValue: function () {
                    return this.insertControl.val() ? parseFloat(this.insertControl.val()) : undefined;
                },

                editValue: function () {
                    return this.editControl.val() ? parseFloat(this.editControl.val()) : undefined;
                },

                _createTextBox: function () {
                    return NumberField.prototype._createTextBox.call(this)
                            .attr("step", this.step);
                }
            });

            jsGrid.fields.decimal = jsGrid.DecimalField = DecimalField;

        }(jsGrid, jQuery));

        function formatNumber(n, currency) {
            return currency + n.toFixed(4).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
        }

        var facility;
        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
        $.ajax({
            url: './Sync',
            type: 'GET',
            dataType: 'json',
            data: {
                page: "Facility",
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
//            console.log(response);
            facility = response;
            $.each(response, function (i, obj) {
                var div_data = "<option value=" + obj.A1CODE + ">" + obj.ALLOCATION + "</option>";
//                console.log(div_data);
                $(div_data).appendTo('#vCode');
            });

        });

        var period;
        $.ajax({
            url: './Sync',
            type: 'GET',
            dataType: 'json',
            data: {
                page: "Period",
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
//            console.log(response);
            period = response;
            $.each(response, function (i, obj) {
                var div_data = "<option value=" + obj.PECODE + ">" + obj.PECODE + "</option>";
//                console.log(div_data);
                $(div_data).appendTo('#vPeriod');
            });

        });

        $("#jsGrid").jsGrid({
            width: "100%",
            height: "auto",
//            inserting: true,
//            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            filtering: true,
            pageSize: 20,
            pageButtonCount: 5,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();
                    var cono = <%out.print(session.getAttribute("cono"));%>
                    var divi = <%out.print(session.getAttribute("divi"));%>
                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {
                            page: "Voucher",
                            cono: cono,
                            divi: divi,
                            code: filter.E2CODE,
                            period: filter.E2PECO,
                            type: 0
                        },
                        async: false
                    }).done(function (response) {
                        console.log(response);
                        $("#vtxtCode").val(filter.E2CODE);
                        $("#vtxtPeriod").val(filter.E2PECO);
                        $("#vtxtVCText").val(filter.E2PECO);

                        var i;
                        for (i = 0; i < response.length; i++) {
                            response[i].E2CODE = filter.E2CODE;
                            response[i].E2PECO = filter.E2PECO;
                        }

                        data.resolve(response);
                    });
                    return data.promise();
                }
            },

            fields: [
//                {type: "control", width: 50, modeSwitchButton: false, editButton: false},
                {title: "Alloc. Code", name: "E2CODE", css: "limitext", type: "select", editing: false, align: "center", items: facility, valueField: "A1CODE", textField: "ALLOCATION", width: 100},
                {title: "Period", name: "E2PECO", type: "select", align: "center", items: period, valueField: "PECODE", textField: "PECODE", validate: "required", width: 80},
                {title: "ACCC", name: "ACCC", type: "text", align: "left", validate: "required", width: 70},
                {title: "COST", name: "COST", type: "text", align: "left", validate: "required", width: 70},
                {title: "BONO", name: "BONO", type: "text", align: "left", validate: "required", width: 60},
                {title: "REF1", name: "REF1", css: "limitext", type: "text", align: "left", validate: "required", width: 100},
                {title: "REF2", name: "REF2", css: "limitext", type: "number", align: "left", validate: {validator: "range", param: [1, 99]}, width: 100},
                {title: "REF3", name: "REF3", css: "limitext", type: "text", align: "left", validate: "required", width: 100},
                {title: "VOUC", name: "VOUC", css: "limitext", type: "text", align: "left", validate: "required", width: 150},
                {title: "AMT", name: "AMT", type: "decimal", align: "right", validate: "required", css: "lvgb", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Status", name: "E2STAT", type: "number", align: "center", validate: "required", width: 60}
            ]
        });

    });

    $("#vReStatus").click(function () {

        var r = confirm("Confirm to Re Status ?");
        if (r === true) {

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var code = $("#vtxtCode").val();
            var period = $("#vtxtPeriod").val();

            if (code == '') {
                alert("Please input code.");
                return;
            }
            if (period == '') {
                alert("Please input period.");
                return;
            }


            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "UpdateStatusAllocated",
                    cono: cono,
                    divi: divi,
                    code: code,
                    period: period,
                    oldstatus: "10",
                    newstatus: "00",
                    type: 0
                },
                async: false
            });

            alert("Re Status Complete.");
            $("#jsGrid").jsGrid("loadData");

        }
    });

    $("#vPostData").click(function () {

        var r = confirm("Confirm to Post Data ?");
        if (r === true) {

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var code = $("#vtxtCode").val();
            var period = $("#vtxtPeriod").val();
            var date = $("#vDate").val();
            var txtFunc = $("#vtxtFunc").val();
            var txtVCText = $("#vtxtVCText").val();

            if (code == '') {
                alert("Please input code.");
                return;
            }
            if (period == '') {
                alert("Please input period.");
                return;
            }
            if (txtFunc == '') {
                alert("Please input period.");
                return;
            }
            if (txtVCText == '') {
                alert("Please input text.");
                return;
            }

            console.log("date : " + date.substring(8, 10) + date.substring(5, 7) + date.substring(2, 4));
            $.ajax({
                url: './Sync',
                type: 'GET',
                dataType: 'json',
                data: {
                    page: "PostToM3Voucher",
                    cono: cono,
                    divi: divi,
                    code: code,
                    period: period,
                    date: date.substring(8, 10) + date.substring(5, 7) + date.substring(2, 4),
                    txtFunc: txtFunc,
                    txtVCText: txtVCText,
                    type: 0
                },
                async: false,
                success: function (data, textStatus, xhr) {
                    console.log(data[0].txtResult);
                    $("#vVoucherNumber").text(data[0].txtResult);
                },
                error: function (xhr, textStatus, errorThrown) {
                    console.log('Error in Operation');
                }
            });

            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "UpdateStatusAllocated",
                    cono: cono,
                    divi: divi,
                    code: code,
                    period: period,
                    oldstatus: "10",
                    newstatus: "90",
                    type: 0
                },
                async: false
            });
            alert("Post Data Complete.");
            $("#jsGrid").jsGrid("loadData");
        }
    });

    $(document).keypress(function (event) {

        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            $("#jsGridDetail").jsGrid("updateItem");
        }
    });

</script>  