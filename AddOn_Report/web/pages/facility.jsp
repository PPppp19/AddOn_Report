<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session.getAttribute("cono") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<section class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div id="jsGrid"></div>
        </div>
    </div>
</section>

<script>

    var alloctype;
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
        alloctype = response;

    });

    $("#jsGrid").jsGrid({
        width: "100%",
        height: "auto",
        inserting: true,
        editing: true,
        sorting: true,
        paging: true,
        autoload: true,
//        filtering: true,
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
                        page: "Facility",
                        cono: cono,
                        divi: divi
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
                item.A1CONO = <%out.print(session.getAttribute("cono"));%>
                item.A1DIVI = <%out.print(session.getAttribute("divi"));%>
                item.page = "InsertFacility";
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
                item.page = "UpdateFacility";
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
                item.page = "DeleteFacility";
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
//            {title: "Alloc. Code", name: "A1CODE", type: "select", editing: true, align: "left", validate: {validator: "rangeLength", param: [0, 3]}, width: 100, items: [{Name: "100 : Electric", Id: "100"}, {Name: "200 : Water", Id: "200"}, {Name: "300 : Gas", Id: "300"}], valueField: "Id", textField: "Name", insertTemplate: function () {
//                    var grid = this._grid;
//                    var $result = jsGrid.fields.select.prototype.insertTemplate.call(this);
//                    $result.on("change", function () {
////                        console.log("change to " + $result.val());
//                        var selectedValue = $result.val();
//                        var anotherFieldIndex = 2;
//                        var desiredValue = "was selected: " + selectedValue;
////                        grid.option("fields")[anotherFieldIndex].insertControl.val(desiredValue);
//                    });
//                    return $result;
//                }},
            {title: "Alloc. Code", name: "A1CODE", type: "text", editing: false, align: "center", validate: {validator: "rangeLength", param: [0, 3]}, width: 100},
            {title: "Description", name: "A1DESC", type: "text", align: "left", validate: "required", width: 100},
            {title: "Name", name: "A1NAME", type: "text", align: "left", validate: "required", width: 200},
            {title: "Type", name: "A1TYPE", type: "select", align: "left", items: alloctype, valueField: "ATTYPE", textField: "TYPE", validate: "required", width: 150},
            {title: "Unit", name: "A1MUUN", type: "text", align: "center", validate: "required", width: 100},
            {title: "Rate", name: "A1RATE", type: "number", align: "right", validate: "required", width: 100},
            {title: "Refer1", name: "A1REF1", type: "text", align: "left", width: 100},
            {title: "Refer2", name: "A1REF2", type: "text", align: "left", width: 100}
        ]
    });

</script>  