package com.rgb.grw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

	private String att_no; // 근태번호
	private String emp_no; // 사원번호
	private String emp_name; // 사원이름
	private Date att_intime; // 출근시간
	private Date att_outtime; // 퇴근시간
	private int att_worktime; // 근무시간 (분 단위)
	private int att_basetime; // 고정 근무시간 (분 단위)
	private String att_status; // 출근 상태 (출근, 퇴근, 지각, 조퇴 등)
	private int att_late; // 지각 시간 (분 단위)

}
