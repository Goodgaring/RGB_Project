//자산 전체 리스트
document.addEventListener('DOMContentLoaded', function() {

	fetch('./fclist/facility.do')
	.then(response => {
		if(!response.ok) {
			throw new Error('response error')
		}
		return response.json();
	})
	.then(data => {
		let fcList = document.getElementById('facility_container');
		if(fcList){
			data.forEach(item => {
				let div = document.createElement('div');
//				console.log(fcList)
				div.textContent = item.fc_name;
//				fcList.append(div);

				div.classList.add('d-flex', 'align-items-center', 'py-2');
				fcList.appendChild(div);
			});
			
		}
		
	})
	.catch(error => {
		console.log('Fetch Error');
	})
});


let dayarry =[]; //날짜 저장
let dayname=[]; //요일명 저장
let currentPage=1;
let sessionAuth=null;
let sessionEmp = null;

//당일~12일후 까지 13일간의 기간 테이블
document.addEventListener('DOMContentLoaded', function() {
	
	//조회 날짜 범위 계산하기 오늘을 기점으로 앞으로 13일간의 기간

	//현재날짜
	let today = new Date() 
		
	//9일후의 날짜
	let endday = new Date();
	endday.setDate(today.getDate()+12);
	
	//요일 변환 함수
	function getDayName(day) {	
		const days = ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa']
		return days[day.getDay()];
		} 
	
	for(let d=today; d<=endday; d.setDate(d.getDate()+1)) {
		dayarry.push(d.getDate());
		dayname.push(getDayName(d));
		}
//	console.log(dayarry);
//	console.log(dayname);

	//daycontainer
	let daylist = document.getElementById('reservation_day_container');
	
	for(i=0; i<dayarry.length; i++) {
		let alink = document.createElement('a');
		let parentli = document.createElement('li')
		
		// 요일 span
        let namespan = document.createElement('span');
        namespan.classList.add('opacity-50', 'fs-7', 'fw-semibold');
        namespan.textContent = dayname[i];
        
        // 날짜 span
        let dayspan = document.createElement('span');
        dayspan.classList.add('fs-6', 'fw-bolder');
        dayspan.textContent = dayarry[i];
        
        // a 태그 설정
        alink.classList.add('nav-link', 'btn', 'd-flex', 'flex-column', 'flex-center', 'rounded-pill', 'min-w-40px', 'me-2', 'py-4', 'btn-active-primary');
        alink.setAttribute('data-bs-toggle', 'tab');
        alink.setAttribute('href', `#kt_schedule_day_${i}`);
        alink.setAttribute('aria-selected', 'false');
        alink.setAttribute('tabindex', '-1');
        alink.setAttribute('role', 'tab');
        
        // a 태그에 span 추가
        alink.append(namespan);
        alink.append(dayspan);
        
        // li 태그 설정
        parentli.classList.add('nav-item', 'me-1');
        parentli.append(alink);
        
        // daylist에 li 추가
        daylist.append(parentli);
        
	}
	
	
//	document.getElementById('kt_schedule_day_0').classList.add('active');
//	document.querySelector('li .nav-link').classList.add('active');

	
})

//데이터 테이블스 적용하기
var table;
$(document).ready(function() {
	table = $("#table_list").DataTable({
        "info": false,
        "paging": true, // 페이징 활성화
        "ordering": true, // 정렬 활성화
        "order": [[3, "desc"]], // 4번째 열 대신 3번째 열(신청일 기준)로 변경
        "ajax":{
			"url" :"./booklist/facility.do",
			"type" :"GET",
			"dataSrc" : ""
		},
		"columns":[
			//번호
			{"data" : "bk_no"},
			
			//신청자산이름
			{"data" : "bk_title"},
			
			{ // 사용기간
                "data": null,
                "render": function (data) {
					
					let startDate = data.bk_stday.split(' ')[0]; // 2024-09-09
					let startTime = data.bk_stday.split(' ')[1].slice(0, -3); // 19:00
					
					let endDate = data.bk_edday.split(' ')[0]; // 2024-09-09
					let endTime = data.bk_edday.split(' ')[1].slice(0, -3); // 20:00
					
					startDate = startDate.slice(5); // 09-09
					endDate = endDate.slice(5); // 09-09
					
					return startDate +' ' + startTime + ' ~ ' + endDate +' ' + endTime;
                }
            },
            
            //신청자
             {"data" : null,
             	"render": function(data){
//             		return "<span data-bs-toggle='modal' data-bs-target='#kt_modal_add_schedule'>"+data.bk_name+"</span>";
						return data.bk_name
             		}
             },
             
			// 진행상태
             { "data": null, 
                "render": function (data, type, row) {
                    let state;
                    if (row.bk_state === 'S') {
                        state = '신청대기';
                    } else if (row.bk_state === 'C') {
                        state = '신청취소';
                    } else if (row.bk_state === 'Y') {
                        state = '신청수락';
                    } else {
                        state = '반려';
                    }
//                    return "<span data-bs-toggle='modal' data-bs-target='#kt_modal_add_schedule'>"+state+"</span>";
					return state;
                }
            },
			
			//신청일	
			{"data": null,
                "render": function (data) {
					
					let date = data.bk_regdate.split(' ')[0]; // 2024-09-09
					let time = data.bk_regdate.split(' ')[1].slice(0, -3); // 19:00
					
					date = date.slice(5); // 09-09
					time = time.slice(5); // 09-09
					
					let regdate = date +' ' + time;
//					let regdate=data.bk_stday.split(' ')[0]+' '+data.bk_stday.split(' ')[1].slice(0,-3);
                    return regdate;
                }
            },
            
            //신청사유
			{"data" :"bk_content"},
            
		],
		
		
        "columnDefs":[
            {"orderable": false, "targets": 0}, // 첫 번째 열은 정렬 불가
            {"targets" : 0, "visible":false , "searchable": false}
        ],
        
    	"responsive": {	
        	"details": {
            	"display": DataTable.Responsive.display.modal({
                header: function (row) {
                    var data = row.data();
                    return '예약신청내역';
                }
            }),
            renderer: DataTable.Responsive.renderer.tableAll({
                tableClass: 'table'
            })
        	}
    	},
    	
    });
		table.on('click', 'tbody tr', (e) => {
	    let classList = e.currentTarget.classList;
	 
	    if (classList.contains('selected')) {
	        classList.remove('selected');
	    }
	    else {
	        table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
	        classList.add('selected');
	    }
	});

		//취소버튼
	    let cancelbtn = document.getElementById('cancelbtn');
	    if(cancelbtn) {
		
		    document.querySelector('#cancelbtn').addEventListener('click', function () {
		    	let rowData = table.row('.selected').data();
		    	console.log(rowData.bk_no);
		    	table.row('.selected').remove().draw(false);
		        cancel(rowData.bk_no);
		    });
		    
		}
	    
	    // 관리자 승인 버튼 클릭 시 선택된 행 삭제
	    let appbtn = document.getElementById('approvebtn');
	    if(appbtn) {
		
		    document.querySelector('#approvebtn').addEventListener('click', function () {
		    	let rowData = table.row('.selected').data();
		    	console.log(rowData.bk_no);
		    	table.row('.selected').remove().draw(false);
		        approve(rowData.bk_no);
		    });
		    
		}
		
		let denybtn = document.getElementById('denybtn');
		// 관리자 반려 버튼 클릭 시 선택된 행 삭제
		if(denybtn){
		    document.querySelector('#denybtn').addEventListener('click', function () {
		    	let rowData = table.row('.selected').data();
		    	console.log(rowData.bk_no);
		    	table.row('.selected').remove().draw(false);
		        deny(rowData.bk_no);
		    });
		}
	    
  });

 
  
//    table.on('buttons-action', function (e, buttonApi, dataTable, node, config) {
//    console.log('Button ' + buttonApi.text() + ' was activated');
//});
//    $("#preVideo").on("shown.bs.modal", function () {
//  	$('#tblList3>tbody').empty();
//  });
//  $("#preVideo").on("hidden.bs.modal", function () {
// 	$('#tblList3>tbody').empty();
//});
//    $('#table_list').on('click', 'td', function() {
//        var rowData = table.row(this).data();
//       	console.log("ssss",rowData)
//       	$('#kt_facility_location').val(rowData.fc_no).change();
//       	$('#kt_modal_add_schedule_datepicker_start').val(rowData.bk_stday).attr('disabled', true);
//       	$('#kt_modal_add_schedule_datepicker_end').val(rowData.bk_edday).attr('disabled', true);
//       	$('#facility_use').val(rowData.bk_content)
//       	$('#modal_cancel_button').text('예약취소').on('click', function() {
//				cancel(rowData.bk_no)
//			});
//       	$("#kt_modal_add_schedule").show()
//       	
//		bk_no = rowData.bk_no;
//		console.log(rowData.bk_no);
//    });
//});


//전체 일정 조회
document.addEventListener('DOMContentLoaded', function() {
	
	//현재날짜
	let today = new Date() 
		
	//12일후의 날짜
	let endday = new Date();
	endday.setDate(today.getDate()+12);

	//필요한것 날짜(YYYY-MM-DD), 날짜의 요일이므로 형태 변환
	let startDate = today.toISOString().split('T')[0];
	let endDate = endday.toISOString().split('T')[0];

//	console.log(startDate, endDate);
	fetch('./bookalllist/facility.do?'+ new URLSearchParams({
		startDate: startDate,
		endDate:endDate
	}))
	.then(response=> {
		if (!response.ok) {
        throw new Error('Network response was not ok');
    	}
    	return response.json(); 
	})
	.then(data=>{
		
		data.forEach(item => {
			
			createContents(item);
//			console.log('item',item);
			
		});
		
	})
	.catch(error=> {
		console.error('Error', error);
	})
});

function updateCreateContent() {
	 // 현재 활성화된 탭을 찾습니다.
    let activeTab = document.querySelector('.tab-pane.active');
    let activeTabId = activeTab ? activeTab.id : null;  // 활성화된 탭의 ID 저장

    // 현재 날짜
    let today = new Date();
    
    // 12일 후의 날짜
    let endday = new Date();
    endday.setDate(today.getDate() + 12);

    // 필요한 날짜(YYYY-MM-DD) 변환
    let startDate = today.toISOString().split('T')[0];
    let endDate = endday.toISOString().split('T')[0];

    fetch('./bookalllist/facility.do?' + new URLSearchParams({
        startDate: startDate,
        endDate: endDate
    }))
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); 
    })
    .then(data => {
        let contentContainer = document.getElementById('reservation_content-container');
        contentContainer.innerHTML = '';  // 기존 내용을 초기화
        
        data.forEach(item => {
            createContents(item);  // 데이터에 따라 새로운 콘텐츠 생성
        });

        // 기존 활성화된 탭을 다시 활성화
        if (activeTabId) {
            let newlyActiveTab = document.getElementById(activeTabId);
            if (newlyActiveTab) {
                // 기존 탭을 다시 활성화
                newlyActiveTab.classList.add('active', 'show');
            }
        }
    })
    .catch(error => {
        console.error('Error', error);
    });
}


//자산예약현황 날짜별 일정 콘텐츠 영역
function createContents(item) {
//	 console.log('Creating content for item:', item);
    for (let i = 0; i < dayarry.length; i++) {
        let contentContainer = document.getElementById('reservation_content-container');
        let parentdiv = document.getElementById(`kt_schedule_day_${i}`);
        
        // parentdiv가 없다면 생성
        if (!parentdiv) {
            parentdiv = document.createElement('div');
            parentdiv.classList.add('tab-pane', 'fade');
            parentdiv.setAttribute('id', `kt_schedule_day_${i}`);
            contentContainer.appendChild(parentdiv);
        }

        // 시간 형식 변환
        let startDay = item.bk_stday.slice(5, 10).replace('-', '/');
        let startTime = item.bk_stday.split('T')[1].slice(0, 5);
        let endDay = item.bk_edday.slice(5, 10).replace('-', '/');
        let endTime = item.bk_edday.split('T')[1].slice(0, 5);

        // 예약 시작일이나 종료일이 해당 날짜와 일치하는지 확인
        if (new Date(item.bk_stday).getDate() == dayarry[i] || new Date(item.bk_edday).getDate() == dayarry[i]) {
            let bookcontainer = document.createElement('div');
            let sidebardiv = document.createElement('div');
            let infodiv = document.createElement('div');
            let timediv = document.createElement('div');
            let titlelink = document.createElement('a');
            let divname = document.createElement('div');
            let aview = document.createElement('a');
            let statediv = document.createElement('div');

            // 시간 표시
            timediv.classList.add('fs-7', 'mb-1');
            timediv.innerHTML = `${startDay} ${startTime} - ${endDay} ${endTime}`;

            // 제목 링크
            titlelink.href = "#";
            titlelink.classList.add('fs-5', 'fw-bold', 'text-gray-900', 'text-hover-primary', 'mb-2');
            titlelink.textContent = item.bk_title;

            // 예약자 정보
            divname.classList.add('fs-7', 'text-muted');
            divname.innerHTML = `예약자 ${item.bk_name}</a>`;

            // 사이드바
            sidebardiv.classList.add('position-absolute', 'h-100', 'w-4px', 'bg-secondary', 'rounded', 'top-0', 'start-0');

            // 보기 버튼
            aview.setAttribute('onclick', `openModal(${item.bk_no})`);
            aview.classList.add('btn', 'btn-light', 'bnt-active-light-primary', 'btn-sm');
            aview.setAttribute('data-bs-target','#kt_modal_add_event');
//            alink.setAttribute('data-bk-no', `${item.bk_no}`)
            aview.textContent = 'View';
            
            
            //상태 뱃지
//            <div class="badge badge-light-success">Completed</div>
			if(item.bk_state == 'Y') {
				statediv.classList.add('badge','badge-light-success');
				statediv.textContent = '승인';
			} else if (item.bk_state == 'S') {
				statediv.classList.add('badge','badge-light-primary');
				statediv.textContent = '대기';
			} else if(item.bk_state == 'C') { 
				statediv.classList.add('badge','badge-light-warning');
				statediv.textContent = '취소';
			}
			

            // 일정 정보 박스
            infodiv.classList.add('fw-semibold', 'ms-5');
            infodiv.appendChild(timediv);
            infodiv.appendChild(titlelink);
            infodiv.appendChild(divname);

            // 일정 컨테이너
            bookcontainer.classList.add('d-flex', 'flex-stack', 'position-relative', 'mt-6');
            bookcontainer.appendChild(sidebardiv);
            bookcontainer.appendChild(infodiv);
            bookcontainer.appendChild(statediv);
//            bookcontainer.appendChild(aview);
            
            // 부모 div에 추가
            parentdiv.appendChild(bookcontainer);
        }
    }
}


//timepicker 시작일 초기화설정
document.addEventListener('DOMContentLoaded', function() {
  flatpickr("#kt_modal_add_schedule_datepicker_start", {
    enableTime: true,
    enable: [
		function(date) {
	        let today = new Date();
	        let endday = new Date(); 
	        endday.setDate(today.getDate() + 12);
	
	        // date가 today와 endday 사이에 있는지 확인
	        return (date >= today && date <= endday);
	        }
	],
    noCalendar: false,
    dateFormat: "Y-m-d H:i", // 시간만 표시
    time_24hr: true,   // 24시간 형식
    onChange: function(selectedDates, dateStr, instance) {
      // 사용자가 시간만 선택하면, 분을 00으로 설정
      if (selectedDates.length > 0) {
        let selectedDate = selectedDates[0];
        instance.setDate(selectedDate.setMinutes(0));
      }
    }
  });
});
//timepicker 종료일 초기화설정
document.addEventListener('DOMContentLoaded', function() {
  flatpickr("#kt_modal_add_schedule_datepicker_end", {
    enableTime: true,
    enable: [
		function(date) {
	        let today = new Date();
	        let endday = new Date(); 
	        endday.setDate(today.getDate() + 12);
	
	        // date가 today와 endday 사이에 있는지 확인
	        return (date >= today && date <= endday);
	        }
	],
    noCalendar: false,
    dateFormat: "Y-m-d H:i", // 시간만 표시
    time_24hr: true,   // 24시간 형식
    onChange: function(selectedDates, dateStr, instance) {
      // 사용자가 시간만 선택하면, 분을 00으로 설정
      if (selectedDates.length > 0) {
        let selectedDate = selectedDates[0];
        instance.setDate(selectedDate.setMinutes(0));
      }
    }
  });
});

//예약신청
function insertReservation() {
    // 셀렉트(자산선택) 값
    let selectElement = document.getElementById('kt_facility_location');
    let selectFacility = selectElement.options[selectElement.selectedIndex];
    let fc_no = selectFacility.value;
    let bk_title = selectFacility.textContent;

    // 사용일
    let bk_stday = document.getElementById('kt_modal_add_schedule_datepicker_start').value;
    let bk_edday = document.getElementById('kt_modal_add_schedule_datepicker_end').value;
    bk_stday = bk_stday + ':00';
    bk_edday = bk_edday + ':00';
    let startDateTime = new Date(bk_stday);
    let endDateTiem = new Date(bk_edday);

    // 시간 설정이 안되어있을때
    if (startDateTime == '' || endDateTiem == '') {
        alert('시간을 설정해주세요');
        return;
    }
	console.log('startDateTime',startDateTime)
	console.log('endDateTiem',endDateTiem)
    // 사용종료시간이 사용시작시간보다 같거나 빠른경우
    if (startDateTime >= endDateTiem) {
        alert('시간설정이 잘못되었습니다.');
        return;
    }

    // 자산선택이 되어있지 않은 경우
    if (fc_no == '') {
        alert('자산을 선택해주세요');
        return;
    }

    // 사용목적
    let bk_content = document.getElementById('facility_use').value;
    if(bk_content == '') {
	alert('내용을 적어주세요');
	return;
	}

    let data = {
        fc_no: fc_no,
        bk_title: bk_title,
        bk_stday: bk_stday,
        bk_edday: bk_edday,
        bk_content: bk_content
    };

//    console.log('data:', data);

    fetch('./addreservation/facility.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
			
			//응답오류
			if(!response.ok){
				return response.text()
				 
			}
			return response.json();
				
		})	
    .then(data => {
        
		table.clear();
		updateCreateContent();
		  data.forEach(item => {
       
	        table.row.add({
				"bk_no" : item.bk_no,
	            "bk_title": item.bk_title,       // 제목
	            "bk_stday": item.bk_stday,       // 시작일
	            "bk_edday": item.bk_edday,       // 종료일
	            "bk_name" : item.bk_name,	 	 //작성자
	            "bk_state": item.bk_state,       // 상태
	            "bk_content": item.bk_content,   // 내용
	            "bk_regdate": item.bk_regdate    // 등록일
	        }).draw();
    	 });
    })
    .catch(error => {
        console.error('insertfetch error', error);
    });
}


//모달 닫힐때 리셋
document.addEventListener('DOMContentLoaded', function () { 
    // 모달 요소 선택
    var addmodalElement = document.getElementById('kt_modal_add_schedule');

    // 모달이 닫힐 때 호출될 함수
    function resetModalContent() {
        // 모든 input 요소들을 찾고 초기화
        document.querySelectorAll('#kt_modal_add_schedule input').forEach(function(input) {
           	if(input.type=='checkbox') {
				input.checked=false;	//체크박스는 안되서 if문으로 걸어줌
			} else {
	            input.value = ''; // 각 input의 값을 빈 문자열로 설정
			}
            
        });
        //셀렉트요소도 있으니까
        document.querySelectorAll('#kt_modal_add_schedule select').forEach(function(select) {
			select.selectedIndex=0;
		})
		
    }

    // 모달이 닫힐 때 resetModalContent 함수를 호출합니다.
    addmodalElement.addEventListener('hidden.bs.modal', function () {
        resetModalContent();
    });

});

//승인하기
function approve(bk_no){
	
	console.log(bk_no)
	fetch('./approve/facility.do?'+ new URLSearchParams({
		bk_no :bk_no,
		bk_state :'Y'
	}))
	.then(response=> {
		if (!response.ok) {
        throw new Error('Network response was not ok');
    	}
    	return response.json(); 
	})
	.then(data=>{
		
		console.log('1111 값넘어옴',data);
		table.clear();
		updateCreateContent();
		  data.forEach(item => {
       
	        table.row.add({
				"bk_no" : item.bk_no,
	            "bk_title": item.bk_title,       // 제목
	            "bk_stday": item.bk_stday,       // 시작일
	            "bk_edday": item.bk_edday,       // 종료일
	            "bk_name" : item.bk_name,	 	 //작성자
	            "bk_state": item.bk_state,       // 상태
	            "bk_content": item.bk_content,   // 내용
	            "bk_regdate": item.bk_regdate    // 등록일
	        }).draw();
	        
    	 });
//		$('#kt_modal_add_schedule').modal('hide');
	})
	.catch(error => {
		console.log('error 승인')
	})
	
	
}

//반려하기
function deny(bk_no) {
	fetch('./approve/facility.do?'+ new URLSearchParams({
		bk_no :bk_no,
		bk_state :'N'
	}))
	.then(response=> {
		if (!response.ok) {
        throw new Error('Network response was not ok');
    	}
    	return response.json(); 
	})
	.then(data=>{
		console.log('1111 값넘어옴',data);
		
		table.clear();
		updateCreateContent();
		  data.forEach(item => {
       
	        table.row.add({
				"bk_no" : item.bk_no,
	            "bk_title": item.bk_title,       // 제목
	            "bk_stday": item.bk_stday,       // 시작일
	            "bk_edday": item.bk_edday,       // 종료일
	            "bk_name" : item.bk_name,	 	 //작성자
	            "bk_state": item.bk_state,       // 상태
	            "bk_content": item.bk_content,   // 내용
	            "bk_regdate": item.bk_regdate    // 등록일
	        }).draw();
    	 });
    	 
	})
	.catch(error => {
		console.log('error 반려')
	})
}

//취소하기
function cancel(bk_no) {
	
	fetch('./cancel/facility.do?'+ new URLSearchParams({
		bk_no :bk_no
	}))
	.then(response=> {
		if (!response.ok) {
        throw new Error('Network response was not ok');
    	}
    	return response.json(); 
	})
	.then(data=>{
		
//		console.log('1111 값넘어옴',data);
		
		table.clear()
		updateCreateContent();
		data.forEach(item => {
       		console.log('2222 성공실행')
	        table.row.add({
				"bk_no" : item.bk_no,
	            "bk_title": item.bk_title,       // 제목
	            "bk_stday": item.bk_stday,       // 시작일
	            "bk_edday": item.bk_edday,       // 종료일
	            "bk_name" : item.bk_name,	 	 //작성자
	            "bk_state": item.bk_state,       // 상태
	            "bk_content": item.bk_content,   // 내용
	            "bk_regdate": item.bk_regdate    // 등록일
	        }).draw();
    	 });
    })
    .catch(error => {
        console.error('error 취소망함', error);
    });
       		
};

