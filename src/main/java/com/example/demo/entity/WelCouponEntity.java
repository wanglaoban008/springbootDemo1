package com.example.demo.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户券包
 * 
 * @author gaoshuang
 * @email gaoshuang@163.com
 * @date 2018-01-08 19:53:25
 */
public class WelCouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键Id
	private Long id;
	//用户ID
	@NotBlank(message="用户id不能为空")
	private Long userId;
	//业务流水号
	@NotBlank(message="业务流水号不能为空")
	private String bizNum;
	//券金额（分）
	@Min(value=100, message="初始化金额格式不正确")
	private Double money;
	//加息率 10% -10
	@Min(value=0, message="初始化加息率格式不正确")
	private Double rate;
	//投资最低金额
	@Min(value=100, message="初始化投资最低金额格式不正确")
	private Double investAtleastMoney;
	//类型(1开头是理财券，2开头是红包，3开头是加息券)：10:常规理财券; 11:新手理财券;20：常规红包;21：新手红包;30：加息券 
	@NotBlank(message="券类型格式不正确")
	private String type;
	//状态：；0:未使用;1:已使用;2：已过期;
	private String status;
	//使用范围：产品类型
	@NotBlank(message="券使用规则格式不正确")
	private String useRange;
	//使用范围中文描述：产品类型
	private String useRangeName;
	//有效天数 单位：天 
	@Min(value=1, message="有效期限格式不正确")
	private Integer validDay;
	//使用时间
	private Date useTime;
	//活动id
	private Long actActivityId;
	//活动类型
	private String actActivityType;
	//插入时间
	private Date insertTime;
	//添加用户ID
	private Long insertUser;
	//修改时间
	private Date updateTime;
	//过期时间
	private Date overTime;
	//修改者
	private Long updateUser;
	//版本号;用于更新时对比操作;
	private Long versions;
	//是否逻辑删除;0:不删除;1:逻辑删除;所有查询sql都要带上del=0这个条件
	private String del;
	//备注
	private String remark;

	/**
	 * 设置：主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键Id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：业务流水号
	 */
	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}
	/**
	 * 获取：业务流水号
	 */
	public String getBizNum() {
		return bizNum;
	}
	/**
	 * 设置：券金额（分）
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	/**
	 * 获取：券金额（分）
	 */
	public Double getMoney() {
		return money;
	}
	/**
	 * 设置：加息率 10% -10
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
	/**
	 * 获取：加息率 10% -10
	 */
	public Double getRate() {
		return rate;
	}
	/**
	 * 设置：投资最低金额
	 */
	public void setInvestAtleastMoney(Double investAtleastMoney) {
		this.investAtleastMoney = investAtleastMoney;
	}
	/**
	 * 获取：投资最低金额
	 */
	public Double getInvestAtleastMoney() {
		return investAtleastMoney;
	}
	/**
	 * 设置：类型(1开头是理财券，2开头是红包，3开头是加息券)：10:常规理财券; 11:新手理财券;20：常规红包;21：新手红包;30：加息券 
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型(1开头是理财券，2开头是红包，3开头是加息券)：10:常规理财券; 11:新手理财券;20：常规红包;21：新手红包;30：加息券 
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：状态：；0:未使用;1:已使用;2：已过期
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态：；0:未使用;1:已使用;2：已过期
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：使用范围：产品类型
	 */
	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	/**
	 * 获取：使用范围：产品类型
	 */
	public String getUseRange() {
		return useRange;
	}
	/**
	 * 设置：使用范围中文描述：产品类型
	 */
	public void setUseRangeName(String useRangeName) {
		this.useRangeName = useRangeName;
	}
	/**
	 * 获取：使用范围中文描述：产品类型
	 */
	public String getUseRangeName() {
		return useRangeName;
	}
	/**
	 * 设置：有效天数 单位：天 
	 */
	public void setValidDay(Integer validDay) {
		this.validDay = validDay;
	}
	/**
	 * 获取：有效天数 单位：天 
	 */
	public Integer getValidDay() {
		return validDay;
	}
	/**
	 * 设置：活动id
	 */
	public void setActActivityId(Long actActivityId) {
		this.actActivityId = actActivityId;
	}
	/**
	 * 获取：活动id
	 */
	public Long getActActivityId() {
		return actActivityId;
	}
	/**
	 * 设置：活动类型
	 */
	public void setActActivityType(String actActivityType) {
		this.actActivityType = actActivityType;
	}
	/**
	 * 获取：活动类型
	 */
	public String getActActivityType() {
		return actActivityType;
	}
	/**
	 * 设置：插入时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：插入时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：添加用户ID
	 */
	public void setInsertUser(Long insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：添加用户ID
	 */
	public Long getInsertUser() {
		return insertUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：过期时间
	 */
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getOverTime() {
		return overTime;
	}
	/**
	 * 设置：修改者
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：修改者
	 */
	public Long getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：版本号;用于更新时对比操作;
	 */
	public void setVersions(Long versions) {
		this.versions = versions;
	}
	/**
	 * 获取：版本号;用于更新时对比操作;
	 */
	public Long getVersions() {
		return versions;
	}
	/**
	 * 设置：是否逻辑删除;0:不删除;1:逻辑删除;所有查询sql都要带上del=0这个条件
	 */
	public void setDel(String del) {
		this.del = del;
	}
	/**
	 * 获取：是否逻辑删除;0:不删除;1:逻辑删除;所有查询sql都要带上del=0这个条件
	 */
	public String getDel() {
		return del;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
}
