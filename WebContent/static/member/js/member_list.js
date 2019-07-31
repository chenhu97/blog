
$(function() {

	$("#checkall").change(function() {

		// [其它框列表]，排除全选框
		var $otherCheckList = $("input:checkbox:not('#checkall')");
		// 取得全选框当前的选中状态
		var checkAllState = $("#checkall").prop("checked");
		// 其它框状态 = 全选框状态
		$otherCheckList.prop("checked", checkAllState);

	});


});


function datadel() {
	if (confirm("真的要删除吗？")) {

		var num = 0; // 记录,删除成功的行数
		var total = 0;// 记录,要删除的行数
		var obj = null;// 记录当前对象
		// [其它已选中框列表]，排除全选框
		var $otherCheckedList = $("input:checkbox:checked:not('#checkall')");

		// 迭代所有已选中框
		$otherCheckedList.each(function() {
			obj = this;
			var id = $(this).val();
			//alert(id);
			if (id != null && id != "" && id != "0") {
				total++;// 总记录
				$.ajax({
					type : 'POST',
					url : "member_delete_ajax.do",
					async : false,// 要使用同步
					data : {
						"id" : id
					},
					success : function(data) {
						if (data == "ok") {
							num++;// layer.msg('已删除!',{icon:1,time:2000});
						} else {
							alert('删除失败!');
						}
					},
					error : function(data) {
						alert('Error!');

					},
				});
			}
		});

		alert('要删除' + total + '行记录, 成功删除' + num + '行记录。');
		location.reload();
	}
}




