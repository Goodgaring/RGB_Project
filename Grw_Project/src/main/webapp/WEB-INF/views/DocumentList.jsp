<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RGB Groupware Project</title>
</head>
<%@include file="./header.jsp"%>
<body>
	<div style="padding-left:80px; margin-top:10px;" id="kt_app_content" class="app-content flex-column-fluid">
		<div style="padding-left:80px;" id="kt_app_content_container" class="app-container container-xxl">
			<div style="width:100%; height:auto; margin-top:10px;" class="card">
				<div class="card-header border-0 pt-5">
					<h3 class="card-title align-items-start flex-column">
						<span class="card-label fw-bold fs-3 mb-1">진행중인 문서 현황</span>
					</h3>
<!-- 					<div class="card-toolbar"> -->
<!-- 						<a href="#" class="btn btn-sm btn-primary"> <img style="width: 30px; padding-right: 5px;" src="./assets/images/document.svg"> 문서함 이동 -->
<!-- 						</a> -->
<!-- 					</div> -->
				</div>
				<div class="card-body pt-3 pb-1">
					<div class="table-responsive">
						<table class="table align-middle gs-0 gy-4">
							<thead>
								<tr class="border-0">
									<th class="p-0 min-w-100px"></th>
									<th class="p-0 min-w-100px"></th>
									<th class="p-0 min-w-100px"></th>
									<th class="p-0 min-w-100px"></th>
									<th class="p-0 min-w-100px text-end"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div class="d-flex align-items-center">
											<div class="symbol symbol-45px me-5">
												<img src="./assets/images/whiteimg.png">
											</div>
											<div class="d-flex justify-content-start flex-column">
												<p style="text-align:center; margin:0px;"><b>문서번호</b></p>
											</div>
										</div>
									</td>
									<td class="text-end">
										<p style="text-align:center; margin:0px;"><b>문서명</b></p>
									</td>
									<td class="text-end">
										<p style="text-align:center; margin:0px;"><b>기안자</b></p>
									</td>
									<td class="text-end">
										<p style="pointer-events: none; text-align:center;" class="text-gray-800 fw-bold text-hover-primary d-block mb-1 fs-6">
										마감일자
										</p>
									</td>
									<td class="text-end">
									</td>
								</tr>
								<tr>
									<td>
										<div class="d-flex align-items-center">
											<div class="symbol symbol-45px me-5">
												<img src="assets/media/avatars/300-9.jpg" class="" alt="">
											</div>
											<div class="d-flex justify-content-start flex-column">
												<p class="text-gray-800 fw-bold text-hover-primary mb-1 fs-6">문서번호</p>
											</div>
										</div>
									</td>
									<td class="text-end">
										<a href="#" class="text-gray-800 fw-bold text-hover-primary d-block mb-1 fs-6" style="text-align:center;">문서명</a>
										<span class="text-gray-500 fw-semibold d-block fs-7" style="text-align:center;">기안날짜</span>
									</td>
									<td class="text-end">
										<p style="text-align:center;" class="text-gray-800 fw-bold text-hover-primary d-block mb-1 fs-6">기안자</p>
									</td>
									<td class="text-end">
									<p style="text-align:center;" class="text-gray-800 fw-bold text-hover-primary d-block mb-1 fs-6">
										마감일자
									</p>
									</td>
									<td class="text-end">
										<div class="d-flex flex-column w-100 me-2">
											<div class="d-flex flex-stack text-gray-500 fs-7 fw-semibold mb-2">
												<span class="me-2">79%</span> <span>Progress</span>
											</div>
											<div class="progress h-6px w-100 bg-light-danger">
												<div class="progress-bar bg-danger" role="progressbar" style="width: 79%" aria-valuenow="79" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

<%@include file="./footer.jsp"%>
</body>
</html>