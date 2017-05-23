<div id="iconDiv" style="background: #eeeeee">

</div>
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
