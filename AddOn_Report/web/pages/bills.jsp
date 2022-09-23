<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session.getAttribute("cono") == null) {
        response.sendRedirect("login.jsp");
    }
%>

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

    .hide{
        display:none;
    }

</style>

<section class="container">
    <div id="jsGrid"></div>
    <div id="detailsDialog">
        <form id="detailsForm">
            <div class="details-form-field">
                <label for="E1CODE">Code :</label>
                <!--<input id="vCode" name="E1CODE" type="number" />-->
                <select class="form-control form-control-user" name="E1CODE" id="vCode">
                    <option value="" selected="selected">Select Code</option>
                </select>
            </div>
            <div class="details-form-field">
                <label for="E1PECO">Period :</label>
                <!--<input id="vPeriod" name="E1PECO" type="number" />-->
                <select class="form-control form-control-user" name="E1PECO" id="vPeriod">
                    <option value="" selected="selected">Select Period</option>
                </select>
            </div>
            <div class="details-form-field">
                <label for="E1METS">Meter Start :</label>
                <input id="vMeterStart" name="E1METS" type="number" value="0" onkeyup="CalQty()" disabled="true"/>
                <!--<input name="E1METS" type="number" value="0">-->
            </div>
            <div class="details-form-field">
                <label for="E1METE">Meter End :</label>
                <input id="vMeterEnd" name="E1METE" type="number" value="0" onkeyup="CalQty()" disabled="true"/>
            </div>
            <div class="details-form-field">
                <label for="E1EPAT">Unit Qty. :</label>
                <input id="vUnitQty" name="E1EPAT" type="number"/>
            </div>
            <div class="details-form-field">
                <label for="E1EPQT">Amount :</label>
                <input id="vAmount" name="E1EPQT" type="number" onkeyup="CalVat()"/>
            </div>
            <div class="details-form-field">
                <label for="E1EVAT">Vat 7% :</label>
                <input id="vVat" name="E1EVAT" type="number" disabled="true"/>
            </div>
            <div class="details-form-field">
                <label for="E1ETAT">Total :</label>
                <input id="vTotal" name="E1ETAT" type="number" disabled="true"/>
            </div>
            <div class="details-form-field">
                <label for="E1RAAT">Total Amt. :</label>
                <input id="vTotalAmt" name="E1RAAT" type="number" disabled="true"/>
            </div>
        </form><br>
        <div id="detailsForm2" align="center">
            <button class="btn btn-success" type="submit" form="detailsForm" id="vSave">Save</button> 
            <button class="btn btn-primary" type="submit" id="vAllocated">Allocated</button>
        </div>
    </div>
</section>

<script>

    document.getElementById("detailsForm").style.display = "none";
    document.getElementById("detailsForm2").style.display = "none";

    $(function () {

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
            editing: true,
            sorting: true,
            paging: true,
//            autoload: true,
            filtering: true,

            rowClick: function (args) {
                document.getElementById("vCode").disabled = true;
                document.getElementById("vPeriod").disabled = true;
                document.getElementById("detailsForm").style.display = "";
                document.getElementById("detailsForm2").style.display = "";
                showDetailsDialog("Edit", args.item);
            },

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
                            page: "Bills",
                            cono: cono,
                            divi: divi,
                            period: filter.E1PECO,
                            type: 0
                        },
                        async: false
                    }).done(function (response) {
                        console.log(response);

                        var i;
                        for (i = 0; i < response.length; i++) {
                            response[i].E1PECO = filter.E1PECO;
                        }

                        data.resolve(response);
                    });
                    return data.promise();
                },

                insertItem: function (item) {
                    console.log(item);
                    item.E1CONO = <%out.print(session.getAttribute("cono"));%>
                    item.E1DIVI = <%out.print(session.getAttribute("divi"));%>
                    item.E1TYPE = 0;
                    item.page = "InsertBills";
                    $.ajax({
                        url: './Sync',
                        type: 'POST',
                        dataType: 'json',
                        data: item,
                        async: false
                    });
                    $("#jsGrid").jsGrid("loadData");
                },

                updateItem: function (item) {
                    console.log(item);
                    item.E1TYPE = 0;
                    item.page = "UpdateBills";
                    $.ajax({
                        url: './Sync',
                        type: 'POST',
                        dataType: 'json',
                        data: item,
                        async: false
                    });
//                $("#jsGrid").jsGrid("loadData");
                },

                deleteItem: function (item) {
                    console.log(item);
                    item.E1TYPE = 0;
                    item.page = "DeleteBills";
                    $.ajax({
                        url: './Sync',
                        type: 'POST',
                        dataType: 'json',
                        data: item,
                        async: false
                    });
//                $("#jsGrid").jsGrid("loadData");
                }

            },

            fields: [
                {type: "control", width: 50,
                    modeSwitchButton: false,
//                    editButton: false,
                    headerTemplate: function () {
                        return $("<button>").attr("type", "button").text("Add")
                                .on("click", function () {
                                    document.getElementById("vCode").disabled = false;
                                    document.getElementById("vPeriod").disabled = false;
                                    document.getElementById("detailsForm").style.display = "";
                                    document.getElementById("detailsForm2").style.display = "";
                                    showDetailsDialog("Add", {});
                                });
                    }},
                {title: "Period", name: "E1PECO", type: "select", editing: false, align: "center", items: period, valueField: "PECODE", textField: "PECODE", validate: "required", width: 80},
                {title: "Code", name: "DESC", css: "limitext", type: "text", editing: false, align: "left", items: facility, valueField: "A1CODE", textField: "ALLOCATION", width: 120},
                {title: "Meter Start", name: "E1METS", type: "decimal", editing: false, align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Meter End", name: "E1METE", type: "decimal", editing: false, align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Unit Qty.", name: "E1EPQT", type: "decimal", align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Amount", name: "E1EPAT", type: "decimal", align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Vat", name: "E1EVAT", type: "decimal", editing: false, align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Total", name: "E1ETAT", type: "decimal", editing: false, align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                {title: "Total Amt.", name: "E1RAAT", type: "decimal", editing: false, align: "right", width: 100, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }}

            ]
        });

        $("#detailsDialog").dialog({
            autoOpen: false,
            width: 400,
            close: function () {
                $("#detailsForm").validate().resetForm();
                $("#detailsForm").find(".error").removeClass("error");
            }
        });

        $("#detailsForm").validate({
            rules: {
                E1CODE: "required",
                E1PECO: "required",
                E1METS: "required", //{required: true, range: [18, 150]},
                E1METE: "required", //{required: true, minlength: 10},
                E1EPQT: "required",
                E1EPAT: "required",
                E1EVAT: "required",
                E1ETAT: "required",
                E1RAAT: "required"
            },
            messages: {
                E1CODE: "Please enter value.",
                E1PECO: "Please enter value.",
                E1METS: "Please enter value.",
                E1METE: "Please enter value.",
                E1EPQT: "Please enter value.",
                E1EPAT: "Please enter value.",
                E1EVAT: "Please enter value.",
                E1ETAT: "Please enter value.",
                E1RAAT: "Please enter value."
            },
            submitHandler: function () {
                formSubmitHandler();
            }
        });

        var formSubmitHandler = $.noop;

        var showDetailsDialog = function (dialogType, client) {
            console.log(client);

//            if (dialogType === "Add") {
            document.getElementById("vAllocated").style.display = "none";
//            } else {
//                document.getElementById("vAllocated").style.display = "";
//            }

            $("#vCode").val(client.E1CODE);
            $("#vPeriod").val(client.E1PECO);
            $("#vMeterStart").val(0);
            $("#vMeterEnd").val(0);
            $("#vUnitQty").val(client.E1EPQT);
            $("#vAmount").val(client.E1EPAT);
            $("#vVat").val(client.E1EVAT);
            $("#vTotal").val(client.E1ETAT);
            $("#vTotalAmt").val(client.E1RAAT);
            formSubmitHandler = function () {
                saveClient(client, dialogType === "Add");
            };
            $("#detailsDialog").dialog("option", "title", dialogType + " Client")
                    .dialog("open");
        };

        var saveClient = function (client, isNew) {
            $.extend(client, {
                E1CODE: $("#vCode").val(),
                E1PECO: $("#vPeriod").val(),
                E1METS: $("#vMeterStart").val(),
                E1METE: $("#vMeterEnd").val(),
                E1EPQT: $("#vUnitQty").val(),
                E1EPAT: $("#vAmount").val(),
                E1EVAT: $("#vVat").val(),
                E1ETAT: $("#vTotal").val(),
                E1RAAT: $("#vTotalAmt").val()
            });
            $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", client);
            $("#detailsDialog").dialog("close");
        };

    });

    function CalQty() {
        var meterstart = document.getElementById("vMeterStart").value;
        var meterend = document.getElementById("vMeterEnd").value;
        var total = parseFloat(meterend) - parseFloat(meterstart);
        document.getElementById("vUnitQty").value = total.toFixed(2);
    }

    function CalVat() {

        if ($("#vCode").val() === "201") {
            console.log("true");
            var amt = document.getElementById("vAmount").value;
            var vat = parseFloat(amt);
            var total = parseFloat(amt);
            var totalamt = parseFloat(amt);
            document.getElementById("vVat").value = vat.toFixed(2);
            document.getElementById("vTotal").value = total.toFixed(2);
            document.getElementById("vTotalAmt").value = totalamt.toFixed(2);

        } else {
            console.log("false");
            var amt = document.getElementById("vAmount").value;
            var vat = ((parseFloat(amt) * 107) / 100) - parseFloat(amt);
            var total = parseFloat(amt) + parseFloat(vat);
            //var totalamt = parseFloat(amt) + (parseFloat(vat) * (81.67 / 100)); // Year 2019
            var totalamt = parseFloat(amt) + (parseFloat(vat) * (85.25 / 100)); // Year 2020
            document.getElementById("vVat").value = vat.toFixed(2);
            document.getElementById("vTotal").value = total.toFixed(2);
            document.getElementById("vTotalAmt").value = totalamt.toFixed(2);

        }

    }

    $("#vAllocated").click(function () {

        console.log($("#vCode").val() + " : " + $("#vPeriod").val());
        var r = confirm("Confirm to Allocated ?");
        if (r === true) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var code = $("#vCode").val();
            var period = $("#vPeriod").val();
            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "InsertBillsAllocated",
                    cono: cono,
                    divi: divi,
                    code: code,
                    period: period,
                    level: 2,
                    type: 0
                },
                async: false
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
                    oldstatus: "00",
                    newstatus: "10",
                    type: 0
                },
                async: false
            });

            alert("Allocated Complete.");
            $("#detailsDialog").dialog("close");
        }

    });

    $(document).keypress(function (event) {

        var keycode = (event.keyCode ? event.keyCode : event.which);
        console.log(keycode);
        if (keycode == '13') {
            $("#jsGrid").jsGrid("updateItem");
        }
    });

</script>  