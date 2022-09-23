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
    <div id="jsGrid"></div>
    <div id="detailsDialog">
        <form id="detailsForm">
            <div class="details-form-field">
                <label for="E2CODE">Code :</label>
                <!--<input id="vCode" name="E1CODE" type="number" />-->
                <select class="form-control form-control-user" name="E2CODE" id="vCode">
                    <option value="" selected="selected">Select Code</option>
                </select>
            </div>
            <div class="details-form-field">
                <label for="E2PECO">Period :</label>
                <!--<input id="vPeriod" name="E1PECO" type="number" />-->
                <select class="form-control form-control-user" name="E2PECO" id="vPeriod">
                    <option value="" selected="selected">Select Period</option>
                </select>
            </div>
        </form>
        <br>
        <div id="detailsForm2" align="center">
            <button class="btn btn-primary" type="submit" form="detailsForm" id="vSave">Create</button> 
        </div>
    </div>
    <div id="detailsDialogDetail" class="hidden-ui-defult">
        <div class="wrap-login100" style="width: 915px;margin-bottom: 0px;">
            <label for="E2CODE">Percent% :</label>
            <input id="vPercent" style="width: 100px" name="E1PECO" type="text" readonly="true"/>
            <label for="E2CODE">Unit Qty. :</label>
            <input id="vQty" style="width: 100px" name="E1PECO" type="text" readonly="true"/>
            <label for="E2CODE">Amt. Qty. :</label>
            <input id="vAmtQty" style="width: 100px" name="E1PECO" type="text" readonly="true"/>
            <label for="E2CODE">Total Amt. :</label>
            <input id="vTotalAmt" style="width: 100px" name="E1PECO" type="text" readonly="true"/>
            <button class="btn btn-success" type="submit" id="vAllocated">Allocated</button>
        </div>
        <div id="jsGridDetail"></div>
    </div>
</section>

<script>

    document.getElementById("detailsForm").style.display = "none";
    document.getElementById("detailsForm2").style.display = "none";
    document.getElementById("detailsDialogDetail").style.display = "none";
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
            editing: true,
            sorting: true,
            paging: true,
//            autoload: true,
            filtering: true,
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
                            page: "Allocation",
                            cono: cono,
                            divi: divi,
                            code: filter.E2CODE,
                            period: filter.E2PECO,
                            fromstatus: "00",
                            tostatus: "10",
                            type: 0
                        },
                        async: false
                    }).done(function (response) {
                        console.log(response);
                        response = $.grep(response, function (item) {
                            return (!filter.E2CODE || (item.E2CODE.indexOf(filter.E2CODE) > -1))
                                    && (!filter.E2STID || (item.E2STID.indexOf(filter.E2STID) > -1))
                                    && (!filter.E2SLVL || (item.E2SLVL.indexOf(filter.E2SLVL) > -1))
                                    && (!filter.E2AITM || (item.E2AITM.indexOf(filter.E2AITM) > -1))
                                    && (!filter.E2PECO || (item.E2PECO.indexOf(filter.E2PECO) > -1));
                        });
                        data.resolve(response);
                    });
                    return data.promise();
                },

                insertItem: function (item) {
                    console.log(item);
                    item.A3CONO = <%out.print(session.getAttribute("cono"));%>
                    item.A3DIVI = <%out.print(session.getAttribute("divi"));%>
                    item.E2TYPE = 0;
                    item.page = "InsertAllocation";
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
                    item.E2TYPE = 0;
                    item.page = "UpdateAllocation";
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
                    item.E2TYPE = 0;
                    item.page = "DeleteAllocation";
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

            rowClick: function (args) {
                showDetailsDialogDetail("Edit", args.item);
            },
            fields: [
                {type: "control", width: 50,
                    modeSwitchButton: false,
                    editButton: false,
                    headerTemplate: function () {
                        return $("<button>").attr("type", "button").text("Add")
                                .on("click", function () {
                                    document.getElementById("vCode").disabled = false;
                                    document.getElementById("vPeriod").disabled = false;
                                    document.getElementById("detailsForm").style.display = "";
                                    document.getElementById("detailsForm2").style.display = "";
                                    document.getElementById("detailsDialogDetail").style.display = "";
                                    showDetailsDialog("Add", {});
                                });
                    }
                },
                {title: "Period", name: "E2PECO", type: "select", align: "center", items: period, valueField: "PECODE", textField: "PECODE", validate: "required", width: 100},
                {title: "Alloc. Code", name: "E2CODE", css: "limitext", type: "select", editing: false, align: "left", items: facility, valueField: "A1CODE", textField: "ALLOCATION", width: 100},
                {title: "Struct. Level", name: "E2SLVL", type: "number", align: "center", validate: {validator: "range", param: [1, 99]}, width: 100},
                {title: "Struct. ID", name: "E2STID", type: "text", align: "center", validate: "required", width: 100},
                {title: "Description", name: "A2DESC", css: "limitext", type: "text", align: "left", validate: "required", width: 500},
                {title: "Status", name: "E2STAT", css: "limitext", type: "text", align: "center", validate: "required", width: 50}
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
                E2CODE: "required",
                E2PECO: "required"
            },
            messages: {
                E2CODE: "Please enter value.",
                E2PECO: "Please enter value."
            },
            submitHandler: function () {
                formSubmitHandler();
            }
        });
        var formSubmitHandler = $.noop;
        var showDetailsDialog = function (dialogType, client) {
            console.log(client);
            $("#vCode").val(client.E1CODE);
            $("#vPeriod").val(client.E1PECO);
            formSubmitHandler = function () {
                saveClient(client, dialogType === "Add");
            };
            $("#detailsDialog").dialog("option", "title", dialogType + " Client").dialog("open");
        };
        var saveClient = function (client, isNew) {
            $.extend(client, {
                E2CODE: $("#vCode").val(),
                E2PECO: $("#vPeriod").val()
            });
            $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", client);
            $("#detailsDialog").dialog("close");
        };
        function getDetail(E2PECO, E2CODE, E2STID, E2SLVL) {
//            console.log(E2CODE, E2STID, E2SLVL

            $("#jsGridDetail").jsGrid({
                width: 915,
                height: 485,
//                inserting: true,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
//                filtering: true,
                pageSize: 17,
                pageButtonCount: 5,
                deleteConfirm: "Do you really want to delete the client?",
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
                                page: "AllocationDetail",
                                cono: cono,
                                divi: divi,
                                code: E2CODE,
                                struct: E2STID,
                                level: E2SLVL,
                                period: E2PECO,
                                type: 0
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        console.log(item);
                        item.E2TYPE = 0;
                        item.page = "UpdateAllocation";
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
//                onItemEditing: function (args) {
//                    if (args.item.E2SLVL == '1') {
//                        console.log("true");
//                        args.cancel = true;
//                    } else {
//                        console.log("false");
//                        args.cancel = true;
//                    }
//                },

                onDataLoaded: function (args) {
                    //console.log(args.grid.data);
                    var rows = args.grid.data;
                    var i, percent = 0, qty = 0, amtqty = 0, totalamt = 0;
                    for (i = 0; i < rows.length; i++) {
                        console.log(rows[i].E2PERS + " : " + parseFloat(rows[i].E2EPQT) + " : " + parseFloat(rows[i].E2EAQT) + " : " + parseFloat(rows[i].E2EAAT));
                        percent += parseFloat(rows[i].E2PERS);
                        qty += parseFloat(rows[i].E2EPQT);
                        amtqty += parseFloat(rows[i].E2EAQT);
                        totalamt += parseFloat(rows[i].E2EAAT);
                    }
                    console.log("Total = " + percent.toFixed(2) + " : " + qty.toFixed(2) + " : " + amtqty.toFixed(2) + " : " + totalamt.toFixed(2));
                    $("#vPercent").val(percent.toFixed(2));
                    $("#vQty").val(qty.toFixed(2));
                    $("#vAmtQty").val(amtqty.toFixed(2));
                    $("#vTotalAmt").val(totalamt.toFixed(2));
                },

                onItemUpdated: function (args) {
                    var rows = args.grid.data;
                    var i, percent = 0, qty = 0, amtqty = 0, totalamt = 0;
                    for (i = 0; i < rows.length; i++) {
                        console.log(rows[i].E2PERS + " : " + parseFloat(rows[i].E2EPQT) + " : " + parseFloat(rows[i].E2EAQT) + " : " + parseFloat(rows[i].E2EAAT));
                        percent += parseFloat(rows[i].E2PERS);
                        qty += parseFloat(rows[i].E2EPQT);
                        amtqty += parseFloat(rows[i].E2EAQT);
                        totalamt += parseFloat(rows[i].E2EAAT);
                    }
                    console.log("Total = " + percent.toFixed(2) + " : " + qty.toFixed(2) + " : " + amtqty.toFixed(2) + " : " + totalamt.toFixed(2));
                    $("#vPercent").val(percent.toFixed(2));
                    $("#vQty").val(qty.toFixed(2));
                    $("#vAmtQty").val(amtqty.toFixed(2));
                    $("#vTotalAmt").val(totalamt.toFixed(2));
                },

                rowClick: function (args) {
                    //console.log(args);
                    var $row = $(args.event.target).closest("tr");

                    if (this._editingRow) {
                        this.updateItem().done($.proxy(function () {
                            this.editing && this.editItem($row);
                        }, this));
                        return;
                    }

                    this.editing && this.editItem($row);
                },
                fields: [
                    {type: "control", deleteButton: false, width: 45},
//                    {title: "Struct. ID", name: "E2CODE", type: "text", editing: false, align: "left", validate: "required", width: 100, insertTemplate: function () {
//                            var $result = jsGrid.fields.text.prototype.insertTemplate.call(this); // original input
//                            $result.val(E2CODE);
//                            return $result;
//                        }},
//                    {title: "Struct. Code", name: "E2STID", type: "number", editing: false, align: "center", validate: {validator: "range", param: [1, 99]}, width: 100, insertTemplate: function () {
//                            var $result = jsGrid.fields.text.prototype.insertTemplate.call(this); // original input
//                            $result.val(E2STID);
//                            return $result;
//                        }},
                    {title: "Period", name: "E2PECO", type: "text", align: "center", editing: false, validate: "required", width: 70},
                    {title: "Level", name: "E2SLVL", type: "text", align: "center", editing: false, validate: "required", width: 50},
                    {title: "Code", name: "E2AITM", type: "text", align: "left", editing: false, validate: "required", width: 70},
                    {title: "Description", name: "A3ADES", css: "limitext", type: "text", align: "left", editing: false, validate: "required", width: 100},
                    {title: "Percent%", name: "E2PERS", type: "decimal", align: "right", width: 85
                        , editTemplate: function (value, item) {
                            var $result = jsGrid.fields.text.prototype.editTemplate.apply(this, arguments);
//                            console.log(item.E2SLVL);
                            if (item.E2SLVL == '1') {
//                                console.log("true");
                                $result.prop("readOnly", true);
//                                $result.prop("editing", false);
                            } else {
//                                console.log("false");
                                $result.prop("readOnly", false);
//                                $result.prop("editing", false);
                            }

                            return $result;
                        }
                    },
                    {title: "Unit Qty.", name: "E2EPQT", type: "decimal", align: "right", width: 85
                        , editTemplate: function (value, item) {
                            var $result = jsGrid.fields.text.prototype.editTemplate.apply(this, arguments);
//                            console.log(item.E2SLVL);
                            if (item.E2SLVL == '2') {
//                                console.log("true");
                                $result.prop("readOnly", true);
//                                $result.prop("editing", false);
                            } else {
//                                console.log("false");
                                $result.prop("readOnly", false);
//                                $result.prop("editing", false);
                            }

                            return $result;
                        }, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }
                    },
                    {title: "Amt Qty.", name: "E2EAQT", type: "decimal", editing: false, align: "right", css: "lvgb", width: 85, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }},
                    {title: "Total Amt.", name: "E2EAAT", type: "decimal", editing: false, align: "right", css: "lvgb", width: 85, itemTemplate: function (value) {
                        return  value.toString().replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }}
                ]
            });
        }

        $("#detailsDialogDetail").dialog({
            autoOpen: false,
            width: 950,
            height: 600,
            close: function () {
//                $("#detailsDialog").validate().resetForm();
//                $("#detailsDialog").find(".error").removeClass("error");
            }
        });
        var showDetailsDialogDetail = function (dialogType, client) {
            $("#detailsDialogDetail").dialog("option", "title", dialogType + " Client").dialog("open");
//            console.log(client.E2PECO, client.E2CODE, client.E2STID, client.E2SLVL);
            getDetail(client.E2PECO, client.E2CODE, client.E2STID, client.E2SLVL);
        };
    });

    $("#vAllocated").click(function () {

        var data = $("#jsGridDetail").jsGrid("option", "data");
        console.log(data[0].E2CODE + " : " + data[0].E2PECO + " : " + data[0].E2STID + " : " + data[0].E2SLVL);

        var r = confirm("Confirm to Allocated ?");
        if (r === true) {

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>

            if (data[0].E2SLVL == '1') {
                var qty = 0;
                $.ajax({
                    url: './Sync',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        page: "BillsDetail",
                        cono: cono,
                        divi: divi,
                        code: data[0].E2CODE,
                        period: data[0].E2PECO
                    },
                    async: false,
                    success: function (data, textStatus, xhr) {
                        console.log(data[0].E1EPQT);
                        qty = data[0].E1EPQT;
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log('Error in Operation');
                    }
                });

                if (parseFloat(qty) != parseFloat($("#vQty").val())) {
                    alert("Unit Qty " + data[0].E1EPQT + " not math");
                    return;
                }

            } else {

                if ($("#vPercent").val() != 100) {
                    alert("Percent not equals 100%");
                    return;
                }

            }

            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "InsertBillsAllocated",
                    cono: cono,
                    divi: divi,
                    code: data[0].E2CODE,
                    period: data[0].E2PECO,
                    struct: data[0].E2STID,
                    level: data[0].E2SLVL,
                    type: 0
                },
                async: false
            });

            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "UpdateStatusAllocatedLevel",
                    cono: cono,
                    divi: divi,
                    code: data[0].E2CODE,
                    period: data[0].E2PECO,
                    struct: data[0].E2STID,
                    level: data[0].E2SLVL,
                    oldstatus: "00",
                    newstatus: "10",
                    type: 0
                },
                async: false
            });

            alert("Allocated Complete.");
            $("#detailsDialogDetail").dialog("close");
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