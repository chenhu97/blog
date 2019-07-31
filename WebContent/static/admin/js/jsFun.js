$(function() {
	$("#checkall").change(function() {
		var $otherCheckList = $("input:checkbox:not('#checkall')");
		var checkAllState = $("#checkall").prop("checked");
		$otherCheckList.prop("checked", checkAllState);
	});
});
