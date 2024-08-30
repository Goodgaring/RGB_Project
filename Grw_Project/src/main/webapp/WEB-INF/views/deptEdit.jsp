<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="./header.jsp"%>
</head>
<body>


<div id="kt_app_content" class="app-content flex-column-fluid">
								<!--begin::Content container-->
								<div id="kt_app_content_container" class="app-container container-xxl">
									<!--begin::Card-->
									<div class="card card-flush pb-0 bgi-position-y-center bgi-no-repeat mb-10" style="background-size: auto calc(100% + 10rem); background-position-x: 100%; background-image: url('assets/media/illustrations/sketchy-1/4.png')">
										<!--begin::Card header-->
										<div class="card-header pt-10">
											<div class="d-flex align-items-center">
												<!--begin::Icon-->
												<div class="symbol symbol-circle me-5">
													<div class="symbol-label bg-transparent text-primary border border-secondary border-dashed">
														<i class="ki-duotone ki-abstract-47 fs-2x text-primary">
															<span class="path1"></span>
															<span class="path2"></span>
														</i>
													</div>
												</div>
												<!--end::Icon-->
												<!--begin::Title-->
												<div class="d-flex flex-column">
													<h2 class="mb-1">Settings</h2>
													<div class="text-muted fw-bold">
													<a href="#">Keenthemes</a>
													<span class="mx-3">|</span>
													<a href="#">File Manager</a>
													<span class="mx-3">|</span>2.6 GB 
													<span class="mx-3">|</span>758 items</div>
												</div>
												<!--end::Title-->
											</div>
										</div>
										<!--end::Card header-->
										<!--begin::Card body-->
										<div class="card-body pb-0">
											<!--begin::Navs-->
											<div class="d-flex overflow-auto h-55px">
												<ul class="nav nav-stretch nav-line-tabs nav-line-tabs-2x border-transparent fs-5 fw-semibold flex-nowrap">
													<!--begin::Nav item-->
													<li class="nav-item">
														<a class="nav-link text-active-primary me-6" href="apps/file-manager/folders.html">Files</a>
													</li>
													<!--end::Nav item-->
													<!--begin::Nav item-->
													<li class="nav-item">
														<a class="nav-link text-active-primary me-6 active" href="apps/file-manager/settings.html">Settings</a>
													</li>
													<!--end::Nav item-->
												</ul>
											</div>
											<!--begin::Navs-->
										</div>
										<!--end::Card body-->
									</div>
									<!--end::Card-->
									<!--begin::Card-->
									<div class="card card-flush">
										<!--begin::Card header-->
										<div class="card-header pt-8">
											<!--begin::Card title-->
											<div class="card-title">
												<h2>부서관리 수정</h2>
											</div>
											<!--end::Card title-->
										</div>
										<!--end::Card header-->
										<!--begin::Card body-->
										<div class="card-body">
											<!--begin::Form-->
											<form class="form" id="kt_file_manager_settings">
												<!--begin::Input group-->
												<div class="fv-row row mb-15">
													<!--begin::Col-->
													<div class="col-md-3 d-flex align-items-center">
														<!--begin::Label-->
														<label class="fs-6 fw-semibold">부서명</label>
														<!--end::Label-->
													</div>
													<!--end::Col-->
													<!--begin::Col-->
													<div class="col-md-9">
														<!--begin::Input-->
														<select name="language" aria-label="Select a Language" data-control="select2" data-placeholder="Select a Language..." class="form-select mb-2 select2-hidden-accessible" data-select2-id="select2-data-7-wsdn" tabindex="-1" aria-hidden="true" data-kt-initialized="1">
															<option data-select2-id="select2-data-9-6g3a"></option>
															<option value="id">Bahasa Indonesia - Indonesian</option>
															<option value="msa">Bahasa Melayu - Malay</option>
															<option value="ca">Català - Catalan</option>
															<option value="zh-cn">简体中文 - Simplified Chinese</option>
															<option value="zh-tw">繁體中文 - Traditional Chinese</option>
														</select><span class="select2 select2-container select2-container--bootstrap5" dir="ltr" data-select2-id="select2-data-8-f00s" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single form-select mb-2" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-disabled="false" aria-labelledby="select2-language-0u-container" aria-controls="select2-language-0u-container"><span class="select2-selection__rendered" id="select2-language-0u-container" role="textbox" aria-readonly="true" title="Select a Language..."><span class="select2-selection__placeholder">Select a Language...</span></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
														<!--end::Input-->
													</div>
													<!--end::Col-->
												</div>
												<!--end::Input group-->
												<!--begin::Input group-->
												<div class="fv-row row mb-15">
													<!--begin::Col-->
													<div class="col-md-3">
														<!--begin::Label-->
														<label class="fs-6 fw-semibold mt-2">부서번호</label>
<!-- 														<div class="text-muted fs-7">For more info on moment() date formats, please  -->
<!-- 														<a href="https://momentjs.com/docs/#/displaying/format/" target="_blank">click here</a>. -->
<!-- 														</div> -->
														<!--end::Label-->
													</div>
													<!--end::Col-->
													<!--begin::Col-->
													<div class="col-md-9">
														<!--begin::Input-->
														<select name="dateformat" aria-label="Select a date format" data-hide-search="true" data-control="select2" data-placeholder="Select a date format..." class="form-select mb-2 select2-hidden-accessible" data-select2-id="select2-data-10-ued0" tabindex="-1" aria-hidden="true" data-kt-initialized="1">
															<option data-select2-id="select2-data-12-gupf"></option>
															<option value="1">10 Sep 2021, 10:15 AM</option>
															<option value="2">10/09/2021, 10:15 AM</option>
															<option value="3">09-10-2021</option>
															<option value="4">Sunday, September 10th 2010</option>
														</select><span class="select2 select2-container select2-container--bootstrap5" dir="ltr" data-select2-id="select2-data-11-vq1z" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single form-select mb-2" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-disabled="false" aria-labelledby="select2-dateformat-a7-container" aria-controls="select2-dateformat-a7-container"><span class="select2-selection__rendered" id="select2-dateformat-a7-container" role="textbox" aria-readonly="true" title="Select a date format..."><span class="select2-selection__placeholder">Select a date format...</span></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
														<!--end::Input-->
													</div>
													<!--end::Col-->
												</div>
												<!--end::Input group-->
												<!--begin::Input group-->
<!-- 												<div class="fv-row row mb-15"> -->
<!-- 													<div class="col-md-3 d-flex align-items-center"> -->
<!-- 														<label class="fs-6 fw-semibold">Automatic time zone</label> -->
<!-- 													</div> -->
<!-- 													<div class="col-md-9"> -->
<!-- 														<div class="form-check form-switch form-check-custom form-check-solid me-10"> -->
<!-- 															<input class="form-check-input h-30px w-50px" name="autotimezone" type="checkbox" value="" id="autotimezone"> -->
<!-- 															<label class="form-check-label" for="autotimezone">GMT +10:00</label> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
												<!--end::Input group-->
												<!--begin::Input group-->
												<div class="fv-row row mb-15">
													<!--begin::Col-->
													<div class="col-md-3">
														<!--begin::Label-->
														<label class="fs-6 fw-semibold mt-2">부서관리자</label>
<!-- 														<div class="text-muted fs-7">Default file compression type when downloading folders</div> -->
														<!--end::Label-->
													</div>
													<!--end::Col-->
													<!--begin::Col-->
													<div class="col-md-9">
														<!--begin::Input-->
														<select name="compression" aria-label="Select a Compressions type" data-control="select2" data-hide-search="true" data-placeholder="Select a Compressions type..." class="form-select mb-2 select2-hidden-accessible" data-select2-id="select2-data-13-s94b" tabindex="-1" aria-hidden="true" data-kt-initialized="1">
															<option data-select2-id="select2-data-15-1pdt"></option>
															<option value="1">*.zip</option>
															<option value="2">*.7z</option>
															<option value="3">*.rar</option>
															<option value="4">*.tar.gz</option>
														</select><span class="select2 select2-container select2-container--bootstrap5" dir="ltr" data-select2-id="select2-data-14-eckf" style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single form-select mb-2" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-disabled="false" aria-labelledby="select2-compression-fi-container" aria-controls="select2-compression-fi-container"><span class="select2-selection__rendered" id="select2-compression-fi-container" role="textbox" aria-readonly="true" title="Select a Compressions type..."><span class="select2-selection__placeholder">Select a Compressions type...</span></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
														<!--end::Input-->
													</div>
													<!--end::Col-->
												</div>
												<!--end::Input group-->
												<!--begin::Input group-->
												
												<!--end::Input group-->
												<!--begin::Action buttons-->
												<div class="row mt-12">
													<div class="col-md-9 offset-md-3">
														<!--begin::Cancel-->
														<button type="button" class="btn btn-light me-3">취소</button>
														<!--end::Cancel-->
														<!--begin::Button-->
														<button type="button" class="btn btn-primary" id="kt_file_manager_settings_submit">
															<span class="indicator-label">저장</span>
															<span class="indicator-progress">저장 중 입니다...
															<span class="spinner-border spinner-border-sm align-middle ms-2"></span></span>
														</button>
														<!--end::Button-->
													</div>
												</div>
												<!--begin::Action buttons-->
											</form>
											<!--end::Form-->
										</div>
										<!--end::Card body-->
									</div>
									<!--end::Card-->
								</div>
								<!--end::Content container-->
							</div>
							
							
</body>


<%@include file="./footer.jsp"%>
</html>