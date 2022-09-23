<%-- 
    Document   : WHS021
    Created on : Sep 23, 2022, 1:52:04 PM
    Author     : Phongsathon
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


<html>
    <div class="container">
        <div class="wrap-login100" >

            <form id="detailsForm" class="login100-form validate-form" target="_blank" action="Report" method="GET">

                <h3>WHS021</h3>
                <br>
                
                
                 <div class="details-form-field">
                    <label>Facility :</label>
                     <select class="form-control form-control-user" name="fac" id="vFacility">
                    <option value="" selected="selected">Select Facility</option>
                </select>
                    
                <div class="details-form-field">
                    <label>WareHouse :</label>
                    <select class="form-control form-control-user" name="whs" id="vWarehouse">
                    <option value="" selected="selected">Select Warehouse</option>
                        </select>
                    </div>
                </div>
                <div class="details-form-field">
                    <label>Lot. No.:</label>
                    <input class="form-control form-control-user" id="vLotN" name="vLotN" type="text" placeholder="Input Lot. NO." />
                </div>
            </form>

            <form align="center">
                <hr class="my-4">
                <button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="report" value="MPM024" type="submit">PDF</button>
                <button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="report" value="MPM024_Excel"type="submit">Excel</button>
            </form> 


        </div>
    </div>
</html>
<script>
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

</script>
