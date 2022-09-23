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
        </div>
    </div>
</section>

<script>

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

    var type;
    $.ajax({
        url: './Sync',
        type: 'GET',
        dataType: 'json',
        data: {
            page: "Type",
            cono: "10",
            divi: "101"
        },
        async: false
    }).done(function (response) {
//        console.log(response);
        type = response;
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
                        page: "WorkCenter",
                        cono: cono,
                        divi: divi
                    },
                    async: false
                }).done(function (response) {
                    console.log(response);
                    response = $.grep(response, function (item) {
                        return (!filter.A3CODE || item.A3CODE === filter.A3CODE)
                                && (!filter.A3AITM || (item.A3AITM.indexOf(filter.A3AITM) > -1))
                                && (!filter.A3SLVL || (item.A3SLVL.indexOf(filter.A3SLVL) > -1));
                    });
                    data.resolve(response);
                });
                return data.promise();
            },
            insertItem: function (item) {
                console.log(item);
                item.A3CONO = <%out.print(session.getAttribute("cono"));%>
                item.A3DIVI = <%out.print(session.getAttribute("divi"));%>
                item.page = "InsertWorkCenter";
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
                item.page = "DeleteWorkCenter";
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
            console.log(args);
            var $row = $(args.event.target).closest("tr");
            this.editing && this.editItem($row);
        },

        fields: [
            {type: "control", width: 70},
            {title: "Alloc. Code", name: "A3CODE", css: "limitext", type: "select", editing: false, align: "left", items: facility, valueField: "A1CODE", textField: "ALLOCATION", validate: "required", width: 100},
//            {title: "Alloc. Code", name: "A3CODE", type: "text", editing: false, align: "left", valueType: "number|string", validate: {validator: "rangeLength", param: [0, 3]}, width: 100},
//            {title: "Struct. ID", name: "A3STID", type: "text", editing: false, align: "left", validate: "required", width: 100},
            {title: "Costc. ID", name: "A3AITM", type: "text", editing: false, align: "left", validate: "required", width: 100},
            {title: "Struct. Level", name: "A3SLVL", type: "number", editing: false, align: "center", validate: {validator: "range", param: [1, 99]}, width: 100},
            {title: "Shared", name: "A3ASTR", type: "select", align: "center", validate: "required", width: 70, items: [{Name: "No", Id: "0"}, {Name: "Yes", Id: "1"}], valueField: "Id", textField: "Name"},
            {title: "Description", name: "A3ADES", css: "limitext", type: "text", align: "left", validate: "required", width: 200},
            {title: "Meter", name: "A3METY", type: "select", align: "center", validate: "required", width: 70, items: [{Name: "No", Id: "0"}, {Name: "Yes", Id: "1"}], valueField: "Id", textField: "Name"},
            {title: "Rate per Hr.", name: "A3UEHR", type: "text", align: "right", validate: "required", width: 100},
            {title: "Last Meter", name: "A3MELA", type: "text", align: "right", validate: "required", width: 100},
            {title: "Percent%", name: "A3PERS", type: "text", align: "right", validate: "required", width: 100},
            {title: "A3ACCT", name: "A3ACCT", type: "text", align: "right", width: 70},
            {title: "A3AAC1", name: "A3AAC1", type: "text", align: "right", width: 70},
            {title: "A3AAC2", name: "A3AAC2", type: "text", align: "right", width: 70},
            {title: "A3ABOI", name: "A3ABOI", type: "text", align: "right", width: 70},
            {title: "A3ARE1", name: "A3ARE1", type: "text", align: "right", width: 70},
            {title: "A3ARE2", name: "A3ARE2", type: "text", align: "right", width: 70},
            {title: "A3ARE3", name: "A3ARE3", type: "text", align: "right", width: 70}
        ]
    });

    $(document).keypress(function (event) {

        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            $("#jsGrid").jsGrid("updateItem");
        }
    });

</script>  