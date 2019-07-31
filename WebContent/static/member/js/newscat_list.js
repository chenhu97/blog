$(function() {
//	$("#checkall").change(function() {
//				$(":checkbox:not('#checkall')").prop("checked",
//						$("#checkall").prop("checked"));
//	});

	$(".insertBtn").click(function() {
		location.href = "Member_insert.html";
	});
});

$(function(){
	$("#checkall").change(function() {
		var $otherCheckList = $("input:checkbox:not('#checkall')");
		var checkAllState = $("#checkall").prop("checked");
		$otherCheckList.prop("checked",checkAllState);
	});
});

function datadel() {
	if (confirm("真的要删除吗？")) {
		var num = 0;// 记录删除成功的行数
		var total = 0;// 记录要删除的行数
		var obj = null;// 记录当前对象
		var $otherCheckedList = $("input:checkbox:checked:not('#checkall')");
		// 迭代所有已选中框
		$otherCheckedList.each(function() {
			obj = this;
			var id = $(this).val();
			if (id != null && id != "" && id != "0") {
				total++;
				$.ajax({
					type : 'POST',
					url : "newscat_delete_ajax.do",
					async : false,// 要使用同步
					data : {
						"id" : id
					},
					success : function(data) {
						if (data == "ok") {
							num++;
						} else {
							alert("删除失败");
						}
					},
					error : function(data) {
						alert("Error");
					},
				});
			}
		});
		alert('要删除'+total+'行记录，成功删除'+num+'行记录');
		location.reload();
	}
}