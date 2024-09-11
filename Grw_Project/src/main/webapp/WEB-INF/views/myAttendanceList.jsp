<%@page import="com.rgb.grw.dto.AttendanceDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근태 페이지</title>

<%@include file="./header.jsp"%>
</head>

<body>
	<div id="kt_app_content_container" class="app-container container-xxl"
		style="padding-top: 30px;">
		<div class="card">
			<div class="card-header borde r-0 pt-6">
				<div class="card-title d-flex justify-content-between align-items-center">
    <div class="d-flex align-items-center position-relative my-1">
        <i class="ki-duotone ki-magnifier fs-3 position-absolute ms-5">
            <span class="path1"></span> <span class="path2"></span>
        </i>
        <input type="text" data-kt-user-table-filter="search" class="form-control form-control-solid w-250px ps-13" placeholder="검색">
    </div>
    <div class="d-flex align-items-center">
        <form action="empAttendanceList.do" method="get" class="d-flex align-items-center">
            <label for="month" class="me-2 mb-0"></label>
            <input type="month" id="month" name="month" class="form-control form-control-solid me-2" placeholder="월을 선택하세요">
            <button type="submit" class="btn btn-primary" style="width: 100px;">조회</button>
        </form>
    </div>
</div>

				</div>
				<div class="card-body py-4">
					<div id="kt_table_users_wrapper"
						class="dataTables_wrapper dt-bootstrap4 no-footer">
						<div class="table-responsive">
							<table
								class="table align-middle table-row-dashed fs-6 gy-5 dataTable no-footer"
								id="kt_table_users">
								<thead>
									<tr
										class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
										<th class="w-10px pe-2 sorting_disabled" rowspan="1"
											colspan="1" aria-label="" style="width: 29.8906px;">
											<div
												class="form-check form-check-sm form-check-custom form-check-solid me-3">
												<input class="form-check-input" type="checkbox"
													data-kt-check="true"
													data-kt-check-target="#kt_table_users .form-check-input"
													value="1">
											</div>
										</th>
										<th class="min-w-125px sorting" tabindex="0"
											aria-controls="kt_table_users" rowspan="1" colspan="1"
											aria-label="User: activate to sort column ascending"
											style="width: 90px;">사원번호</th>
										<th class="min-w-125px sorting" tabindex="0"
											aria-controls="kt_table_users" rowspan="1" colspan="1"
											aria-label="User: activate to sort column ascending"
											style="width: 90px;">사원이름</th>
										<th class="min-w-125px sorting" tabindex="0"
											aria-controls="kt_table_users" rowspan="1" colspan="1"
											aria-label="Role: activate to sort column ascending"
											style="width: 200.172px;">출근시간</th>
										<th class="min-w-125px sorting" tabindex="0"
											aria-controls="kt_table_users" rowspan="1" colspan="1"
											aria-label="Last login: activate to sort column ascending"
											style="width: 200.172px;">퇴근시간</th>
										<th class="min-w-125px sorting" tabindex="0"
											aria-controls="kt_table_users" rowspan="1" colspan="1"
											aria-label="Two-step: activate to sort column ascending"
											style="width: 200.172px;">근무시간</th>
										<th class="min-w-125px sorting" tabindex="0"
											aria-controls="kt_table_users" rowspan="1" colspan="1"
											aria-label="Two-step: activate to sort column ascending"
											style="width: 200.172px;">근무상태</th>
									</tr>
								</thead>
								<tbody class="text-gray-600 fw-semibold">
									<c:forEach var="attendance" items="${monthlyAttendance}">
										<tr>
											<td>
												<div
													class="form-check form-check-sm form-check-custom form-check-solid me-3">
													<input class="form-check-input" type="checkbox" value="1">
												</div>
											</td>
											<td>${attendance.emp_no}</td>
											<td>${attendance.emp_name}</td>
											<td>
											    <c:choose>
											        <c:when test="${attendance.att_intime != null}">
											            <fmt:formatDate value="${attendance.att_intime}" pattern="MM월 dd일 HH시 mm분" />
											        </c:when>
											        <c:otherwise>-</c:otherwise>
											    </c:choose>
											</td>
											<td>
											    <c:choose>
											        <c:when test="${attendance.att_outtime != null}">
											            <fmt:formatDate value="${attendance.att_outtime}" pattern="MM월 dd일 HH시 mm분" />
											        </c:when>
											        <c:otherwise>-</c:otherwise>
											    </c:choose>
											</td>
											<td>
											    <c:choose>
											        <c:when test="${attendance.att_worktime != null}">
											            <c:set var="hours" value="${attendance.att_worktime / 60}" />
											            <c:set var="minutes" value="${attendance.att_worktime % 60}" />
											            ${hours}시간 ${minutes}분
											        </c:when>
											        <c:otherwise>-</c:otherwise>
											    </c:choose>
											</td>
											<td>
											    <c:choose>
											        <c:when test="${attendance.att_intime == null && attendance.att_outtime == null}">
											            결근
											        </c:when>
											        <c:when test="${attendance.att_outtime == null}">
											            비정상
											        </c:when>
											        <c:otherwise>${attendance.att_status}</c:otherwise>
											    </c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script src="./assets/js/widgets.bundle.js"></script>
<script src="./assets/js/custom/apps/chat/chat.js"></script>
<script src="./assets/js/custom/utilities/modals/create-campaign.js"></script>
<script src="./assets/js/custom/utilities/modals/users-search.js"></script>
<script src="./js/att_list.js"></script>
<%@include file="./footer.jsp"%>
</html>
