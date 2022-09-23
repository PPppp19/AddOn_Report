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
                        page: "Period",
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
                item.PECONO = <%out.print(session.getAttribute("cono"));%>
                item.PEDIVI = <%out.print(session.getAttribute("divi"));%>
                item.page = "InsertPeriod";
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: item
//                    async: false
                });
//                $("#jsGrid").jsGrid("loadData");
            },
            updateItem: function (item) {
                console.log(item);
                item.page = "UpdatePeriod";
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: item
//                    async: false
                });
//                $("#jsGrid").jsGrid("loadData");
            },
            deleteItem: function (item) {
                console.log(item);
                item.page = "DeletePeriod";
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: item
//                    async: false
                });
//                $("#jsGrid").jsGrid("loadData");
            }

        },
        fields: [
            {type: "control"},
            {title: "Period. Code", name: "PECODE", type: "number", editing: false, align: "center", valueType: "number", validate: {validator: "range", param: [0, 999999]}, width: 100},
            {title: "Year", name: "PEYEA4", type: "text", align: "center", validate: "required", width: 100},
            {title: "Month", name: "PEMONT", type: "number", align: "center", validate: {validator:  "range", param: [1, 99]}, width: 100},
            {title: "Description", name: "PEDESC", type: "text", align: "left", validate: "required", width: 500}
        ]
    });

</script>  