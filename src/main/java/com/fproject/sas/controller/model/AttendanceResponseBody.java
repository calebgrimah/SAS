package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Attendance;
import com.fproject.sas.domain.AttendanceRecordForADay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
import java.util.List;

@Data
@AllArgsConstructor
public class AttendanceResponseBody {

    private List<AttendanceRecordForADay> attendances;
}
