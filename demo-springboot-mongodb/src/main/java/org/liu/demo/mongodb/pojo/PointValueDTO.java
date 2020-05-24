package org.liu.demo.mongodb.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PointValueDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long ev_id;//监控点id

	private String ev_name;//监控点名称

	private String ev_code;//监控点编码

	private Long value_id;//监控项id

	private String value_name;//监控项名称

	private String value_unit;//单位

	private String value_group;//监控项分组
	
	private Long eqp_id;//设备id
	
	private String eqp_name;//设备名称
	
	private Date create_time;

	private Date update_time;//更新时间

	private Integer status;//状态

	private Integer warn_level;//预警级别

	private Integer data_source;//数据来源
	
	private Long time;

	private BigDecimal original_value;//原始值
	
	private BigDecimal value;//目标值

	private Integer dataType;//数据类型 1=整数 2=浮点数 3=字符 4=开关量

	private Boolean switcher;//开关量

	private String stringValue;//字符串值

	private String serialNum;//网关序列号

	private Integer alleywayType;//通道类型

	private Integer alleywayNum;//通道号

	private Long deptId;

	private String deptName;

	private Integer prodLineId;

	private String productLine;

	private Long systemId;

	private String systemName;
}
