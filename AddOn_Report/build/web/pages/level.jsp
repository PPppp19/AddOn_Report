<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session.getAttribute("cono") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<style>

    td.limitext{
        white-space: nowrap; 
        width: 100px; 
        overflow: hidden;
        text-overflow:ellipsis;
        /*text-overflow: ellipsis !important;*/
    }

</style>

<section class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div id="jsGrid"></div>
            <div id="detailsDialog" class="hidden-ui-defult">
                <div id="jsGridDetail"></div>
            </div>
        </div>
    </div>
</section>

<script>

    $(function () {

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
            console.log(response);
            facility = response;
        });

        $("#jsGrid").jsGrid({
            width: "100%",
            height: "auto",
            inserting: true,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            filtering: true,
            pageSize: 20,
            pageButtonCount: 5,
            deleteConfirm: "Do you really want to delete the client?",
            rowClick: function (args) {
                console.log(args);
                showDetailsDialog("Edit", args.item);
//                $("#detailsDialog").dialog("option", "title", " Client")
//                        .dialog("open");
//            console.log(client.A2CODE, client.A2STID, client.A2SLVL);
//                getDetail(args.item.A2CODE, args.item.A2STID, args.item.A2SLVL);
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
                            page: "Level",
                            cono: cono,
                            divi: divi
                        },
                        async: false
                    }).done(function (response) {
                        console.log(response);
                        response = $.grep(response, function (item) {
                            return (!filter.A2CODE || (item.A2CODE.indexOf(filter.A2CODE) > -1))
                                    && (!filter.A2STID || (item.A2STID.indexOf(filter.A2STID) > -1))
                                    && (!filter.A2SLVL || (item.A2SLVL.indexOf(filter.A2SLVL) > -1));
                        });

//                        response = $.grep(response, function (item) {
//                            return (!filter.A2CODE || item.A2CODE === filter.A2CODE)
//                                    && (!filter.A2STID || item.A2STID === filter.A2STID)
//                                    && (!filter.A2SLVL || item.A2SLVL === filter.A2SLVL)
//                                    && (!filter.A2DESC || item.A2DESC === filter.A2DESC);
//                        });
                        data.resolve(response);
                    });
                    return data.promise();
                },
                insertItem: function (item) {
                    console.log(item);
                    item.A2CONO = <%out.print(session.getAttribute("cono"));%>
                    item.A2DIVI = <%out.print(session.getAttribute("divi"));%>
                    item.page = "InsertLevel";
                    $.ajax({
                        url: './Sync',
                        type: 'POST',
                        dataType: 'json',
                        data: item,
                    async: false
                    });
//                $("#jsGrid").jsGrid("loadData");
                },
                updateItem: function (item) {
                    console.log(item);
                    item.page = "UpdateLevel";
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
                    item.page = "DeleteLevel";
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
                {type: "control"},
//                {title: "Alloc. Code", name: "A2CODE", type: "text", editing: false, align: "left", valueType: "number|string", validate: {validator: "rangeLength", param: [0, 3]}, width: 100},
                {title: "Alloc. Code", name: "A2CODE", type: "select", editing: false, align: "left", items: facility, valueField: "A1CODE", textField: "ALLOCATION", validate: "required", width: 100},
                {title: "Struct. ID", name: "A2STID", type: "text", editing: false, align: "left", validate: "required", width: 100},
                {title: "Struct. Level", name: "A2SLVL", type: "number", align: "center", validate: {validator: "range", param: [1, 99]}, width: 100},
                {title: "Description", name: "A2DESC", css: "limitext", type: "text", align: "left", validate: "required", width: 200},
                {title: "Refer1", name: "A2REF1", type: "text", align: "left", width: 100},
                {title: "Refer2", name: "A2REF2", type: "text", align: "left", width: 100}
            ]
        });

        function getDetail(A2CODE, A2STID, A2SLVL) {
//            console.log(A2CODE, A2STID, A2SLVL);
            $("#jsGridDetail").jsGrid({
                width: 1115,
                height: 490,
                inserting: true,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
//                filtering: true,
                pageSize: 20,
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
                                page: "LevelDetail",
                                cono: cono,
                                divi: divi,
                                code: A2CODE,
                                struct: A2STID,
                                level: A2SLVL
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            data.resolve(response);
                        });
                        return data.promise();
                    },

                    insertItem: function (item) {
                        console.log(item);
                        item.A3CONO = <%out.print(session.getAttribute("cono"));%>
                        item.A3DIVI = <%out.print(session.getAttribute("divi"));%>
                        item.A3CODE = A2CODE;
//                        item.page = "InsertWorkCenter";
                        item.page = "UpdateLevelWorkCenter";
                        $.ajax({
                            url: './Sync',
                            type: 'POST',
                            dataType: 'json',
                            data: item,
                            async: false
                        });
                        $("#jsGridDetail").jsGrid("loadData");
                    },
                    updateItem: function (item) {
                        console.log(item);
                        item.page = "UpdateWorkCenter";
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
//                        item.page = "DeleteWorkCenter";
                        item.page = "DeleteLevelWorkCenter";
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
                    {type: "control", width: 70},
//            {title: "Alloc. Code", name: "A3CODE", type: "text", editing: false, align: "left", valueType: "number|string", validate: {validator: "rangeLength", param: [0, 3]}, width: 100},
                    {title: "Struct. ID", name: "A3STID", type: "text", editing: false, align: "left", validate: "", width: 100, insertTemplate: function () {
                            var $result = jsGrid.fields.text.prototype.insertTemplate.call(this); // original input
                            $result.val(A2STID);
                            return $result;
                        }},
                    {title: "Struct. Level", name: "A3SLVL", type: "number", editing: false, align: "center", validate: {validator: "range", param: [1, 99]}, width: 100, insertTemplate: function () {
                            var $result = jsGrid.fields.text.prototype.insertTemplate.call(this); // original input
                            $result.val(A2SLVL);
                            return $result;
                        }},
                    {title: "Costc. ID", name: "A3AITM", type: "text", align: "left", validate: "", width: 100},
                    {title: "Shared", name: "A3ASTR", type: "select", align: "center", validate: "", width: 70, items: [{Name: "No", Id: "0"}, {Name: "Yes", Id: "1"}], valueField: "Id", textField: "Name"},
                    {title: "Description", name: "A3ADES", css: "limitext", type: "text", align: "left", validate: "", width: 200},
                    {title: "Meter", name: "A3METY", type: "select", align: "center", validate: "", width: 70, items: [{Name: "No", Id: "0"}, {Name: "Yes", Id: "1"}], valueField: "Id", textField: "Name"},
                    {title: "Rate per Hr.", name: "A3UEHR", type: "text", align: "right", validate: "", width: 100},
                    {title: "Last Meter", name: "A3MELA", type: "text", align: "right", validate: "", width: 100},
                    {title: "Percent%", name: "A3PERS", type: "text", align: "right", validate: "", width: 100},
                    {title: "A3ACCT", name: "A3ACCT", type: "text", align: "left", width: 70},
                    {title: "A3AAC1", name: "A3AAC1", type: "text", align: "left", width: 70},
                    {title: "A3AAC2", name: "A3AAC2", type: "text", align: "left", width: 70},
                    {title: "A3ABOI", name: "A3ABOI", type: "text", align: "left", width: 70},
                    {title: "A3ARE1", name: "A3ARE1", type: "text", align: "left", width: 70},
                    {title: "A3ARE2", name: "A3ARE2", type: "text", align: "left", width: 70},
                    {title: "A3ARE3", name: "A3ARE3", type: "text", align: "left", width: 70}
                ]

//                onCellSelect: function (rowid) {
//                    var rowData = $(this).jqGrid("jsGridDetail", rowid);
//                    consloe.log(rowData);
//                    // now you can use rowData.name2, rowData.name3, rowData.name4 ,...
//                }

            });
        }

        $("#detailsDialog").dialog({
            autoOpen: false,
            width: 1150,
            height: 550,
            close: function () {
//                $("#detailsDialog").validate().resetForm();
//                $("#detailsDialog").find(".error").removeClass("error");
            }
        });

        var showDetailsDialog = function (dialogType, client) {
            $("#detailsDialog").dialog("option", "title", dialogType + " Client")
                    .dialog("open");
//            console.log(client.A2CODE, client.A2STID, client.A2SLVL);
            getDetail(client.A2CODE, client.A2STID, client.A2SLVL);
        };

    });

    $(document).keypress(function (event) {

        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            $("#jsGrid").jsGrid("updateItem");
        }
    });

</script>  