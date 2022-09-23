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
                        page: "Type",
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
                item.ATCONO = <%out.print(session.getAttribute("cono"));%>
                item.ATDIVI = <%out.print(session.getAttribute("divi"));%>
                item.page = "InsertType";
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
                item.page = "UpdateType";
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
                item.page = "DeleteType";
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
            {title: "Type Code", name: "ATTYPE", type: "text", editing: false, align: "center", validate: {validator: "rangeLength", param: [0, 2]}, width: 100},
            {title: "Description", name: "ATNAME", type: "text", align: "left", validate: "required", width: 200},
            {title: "Ref", name: "ATREF1", type: "text", align: "left", width: 500}
        ]
    });

</script>  