package org.liu.mongo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@Document
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
	
	private BigDecimal original_value;//
	
	private BigDecimal value;//

	private int dcs_value_type;//

	private boolean dcs_value;

	private String dcs_value_str;//
	
	private String bind_code;//网关编号

	private String serialNum;//数据通道号

	private Long deptId;

	private String deptName;

	private Integer prodLineId;

	private String productLine;

	private Long systemId;

	private String systemName;

	public Long getValue_id() {
		return value_id;
	}

	public void setValue_id(Long value_id) {
		this.value_id = value_id;
	}

	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}

	public Long getEqp_id() {
		return eqp_id;
	}

	public void setEqp_id(Long eqp_id) {
		this.eqp_id = eqp_id;
	}

	public Long getEv_id() {
		return ev_id;
	}

	public void setEv_id(Long ev_id) {
		this.ev_id = ev_id;
	}

	public String getEqp_name() {
		return eqp_name;
	}

	public void setEqp_name(String eqp_name) {
		this.eqp_name = eqp_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public BigDecimal getOriginal_value() {
		return original_value;
	}

	public void setOriginal_value(BigDecimal original_value) {
		this.original_value = original_value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getBind_code() {
		return bind_code;
	}

	public void setBind_code(String bind_code) {
		this.bind_code = bind_code;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getValue_group() {
		return value_group;
	}

	public void setValue_group(String value_group) {
		this.value_group = value_group;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getData_source() {
		return data_source;
	}

	public void setData_source(Integer data_source) {
		this.data_source = data_source;
	}

	public String getEv_name() {
		return ev_name;
	}

	public void setEv_name(String ev_name) {
		this.ev_name = ev_name;
	}

	public String getEv_code() {
		return ev_code;
	}

	public void setEv_code(String ev_code) {
		this.ev_code = ev_code;
	}

	public Integer getWarn_level() {
		return warn_level;
	}

	public void setWarn_level(Integer warn_level) {
		this.warn_level = warn_level;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getProdLineId() {
		return prodLineId;
	}

	public void setProdLineId(Integer prodLineId) {
		this.prodLineId = prodLineId;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

    public String getValue_unit() {
        return value_unit;
    }

    public void setValue_unit(String value_unit) {
        this.value_unit = value_unit;
    }

	public int getDcs_value_type() {
		return dcs_value_type;
	}

	public void setDcs_value_type(int dcs_value_type) {
		this.dcs_value_type = dcs_value_type;
	}

	public boolean isDcs_value() {
		return dcs_value;
	}

	public void setDcs_value(boolean dcs_value) {
		this.dcs_value = dcs_value;
	}

	public String getDcs_value_str() {
		return dcs_value_str;
	}

	public void setDcs_value_str(String dcs_value_str) {
		this.dcs_value_str = dcs_value_str;
	}
}
