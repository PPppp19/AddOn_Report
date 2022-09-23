<%-- 
    Document   : ep01r002
    Created on : Jul 11, 2019, 9:01:56 AM
    Author     : ACHARD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>

    .wrap-login100 {
        width: 390px;
        background: #fff;
        border-radius: 10px;
        overflow: hidden;
        padding: 30px 55px 33px 55px;
        box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -moz-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -o-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -ms-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
    }

    .container {
        /*width: 970px;*/
        text-align: -webkit-center;
    }

</style>

<div class="container">
    <div class="wrap-login100" >

        <form id="detailsForm" class="login100-form validate-form" target="_blank" action="Report" method="GET">

            <h3>Summary MO Detail Production Report</h3>
            <br>
            <div class="details-form-field">
                <label>Facility :</label>
                <!--<input id="vFacility" name="E1CODE" type="number" />-->
                <select class="form-control form-control-user" name="facility" id="vFacility">
                    <option value="" selected="selected">Select Facility</option>
                </select>
            </div>
            <div class="details-form-field">
                <label>Warehouse :</label>
                <!--<input id="vFacility" name="E1CODE" type="number" />-->
                <select class="form-control form-control-user" name="warehouse" id="vWarehouse">
                    <option value="" selected="selected">Select Warehouse</option>
                </select>
            </div>
            <div class="details-form-field">
                <label>From Date :</label>
                <input class="form-control form-control-user" id="vFromDate" name="fromdate" placeholder="Select Date" />
            </div>
             <div class="details-form-field">
                <label>To Date :</label>
                <input class="form-control form-control-user" id="vToDate" name="todate" placeholder="Select Date" />
            </div>

        </form>

        <form align="center">
            <hr class="my-4">
            <!--<button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="report" value="Sum_MO_Report" type="submit">PDF</button>-->
            <button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="report" value="Sum_MODetail_Report_Excel"type="submit">Excel</button>
        </form> 


    </div>
</div>

<script>

    $(function () {
        $("#vFromDate").datepicker({
            clearBtn: true,
            dateFormat: 'yymmdd'
        });
        $("#vToDate").datepicker({
            clearBtn: true,
            dateFormat: 'yymmdd'
        });
    });

    $("#vFacility").change(function () {
        console.log(this.value);
        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
        $.ajax({
            url: './Sync',
            type: 'GET',
            dataType: 'json',
            data: {
                page: "Warehouse",
                cono: cono,
                divi: divi,
                fac: this.value,
            },
            async: false
        }).done(function (response) {
            console.log(response);
            facility = response;

            $.each(response, function (i, obj) {// MWWHLO,MWWHNM
                var div_data = "<option value=" + obj.MWWHLO + ">" + obj.WAREHOUSE + "</option>";
//                console.log(div_data);
                $(div_data).appendTo('#vWarehouse');
            });

        });
    });

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

        $.each(response, function (i, obj) {// CFFACI,CFFACN
            var div_data = "<option value=" + obj.CFFACI + ">" + obj.FACILITY + "</option>";
//                console.log(div_data);
            $(div_data).appendTo('#vFacility');
        });

    });


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
//            period = response;

        $.each(response, function (i, obj) {
            var div_data = "<option value=" + obj.PECODE + ">" + obj.PECODE + "</option>";
//                console.log(div_data);
            $(div_data).appendTo('#vPeriod');
        });


    });

</script>


<!--</div>-->
<!--</div>-->

<!--<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">สัดส่วนการใช้ ไฟฟ้า, น้ำ, แก็ส</h5>

                    <form id="detailsForm" class="form-signin" target="_blank" action="Report" method="GET">
                        <div class="form-label-group">
                            <label>วันที่ เริ่ม</label>
                            <input type="text" name="stdate" id="stdate" class="form-control" autocomplete="off" required>

                        </div>

                        <div class="form-label-group">
                            <label>วันที่ สิ้นสุด</label>
                            <input type="txt" name="endate" id="endate" class="form-control" autocomplete="off" required>

                        </div>

                    </form>

                    <form align="center">
                        <hr class="my-4">
                        <button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="page" value="Utility" type="submit">PDF</button>
                        <button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="page" value="UtilityExcel"type="submit">Excel</button>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
</div>-->