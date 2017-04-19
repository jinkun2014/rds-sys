<div id="iconDiv">

</div>
<%--<input type='button' class='icon-01' title='icon-01' style='margin:2px;'/>
<input type='button' class='icon-1012333' title='icon-1012333' style='margin:2px;'/>
<input type='button' class='icon-2012080111634' title='icon-2012080111634' style='margin:2px;'/>
</br>--%>
<script type="text/javascript">
    $(function(){
        for (var i=0;i<iconData.length;i++){
            $("#iconDiv").append("<input type='button' class='"+iconData[i]['text']+"' title='"+iconData[i]['text']+"' style='margin:5px;padding:5px;width: 25px;height: 25px;'/>");
        }
        $("input[type='button']").each(function(){
            $(this).click(function(){
                SysResource.input.setIcon($(this).attr("class"));
            });
        });
    });
</script>
