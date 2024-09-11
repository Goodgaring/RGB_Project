function Add() {
	document.forms['kt_modal_add_user_form'].submit();
}


document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.querySelector('input[data-kt-user-table-filter="search"]');
	const table = document.querySelector('#kt_table_users tbody');

	searchInput.addEventListener('input', function() {
		const searchText = searchInput.value.toLowerCase();
		const rows = table.querySelectorAll('tr');

		rows.forEach(row => {
			const cells = row.querySelectorAll('td');
			let match = false;

			cells.forEach(cell => {
				if (cell.textContent.toLowerCase().includes(searchText)) {
					match = true;
				}
			});

			row.style.display = match ? '' : 'none';
		});
	});
});

$(document).ready(function() {
	var table = $('#kt_table_users').DataTable({
		"pageLength": 10 // 기본 페이지당 항목 수
	});

	$('#pageLength').on('change', function() {
		var value = $(this).val();
		table.page.len(value).draw();
	});
});
