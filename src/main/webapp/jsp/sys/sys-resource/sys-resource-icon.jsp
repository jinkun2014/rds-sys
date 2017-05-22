<div id="iconDiv" style="background: #eeeeee">

</div>
<%--<input type='button' class='icon-01' title='icon-01' style='margin:2px;'/>
<input type='button' class='icon-1012333' title='icon-1012333' style='margin:2px;'/>
<input type='button' class='icon-2012080111634' title='icon-2012080111634' style='margin:2px;'/>
</br>--%>
<script type="text/javascript">
    function clickIcon(url) {
        SysResource.input.setIcon(url);
    }

    $(function () {
        $.ajax({
            type: "GET",
            url: "/icons",
            success: function (data) {
                $("#iconDiv").empty();
                for (var i = 0; i < data.length; i++) {
                    $('#iconDiv').append('<img onclick="clickIcon(\'' + data[i] + '\')"  style="margin: 5px;" src="' + data[i] + '"/>');
                }
            }
        });
    });
</script>
