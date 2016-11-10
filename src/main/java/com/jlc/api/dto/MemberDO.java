package com.jlc.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class MemberDO implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final int INIT = 0; // 新建标识
    public static final int UPDATE = 1; // 更新标识

    private Long memberId;// 会员ID
    private String name;// 用户名
    private String password;// 登录密码(加密之后的密文)
    private String nickName;// 用户昵称
    private Integer type;// 用户类型(1:投资方,2:借款方)
    private String typeName;// 用户类型名称
    private String email;// 用户邮箱
    private String phone;// 用户手机号
    private Integer sex;// 性别(0:保密,1:男,2:女.默认0)
    private String sexName;// 性别名称(0:保密,1:男,2:女.默认0)
    private String realName;// 用户真实姓名
    private String idNo;// 用户身份证号
    private String headPortrait;// 用户头像
    private String province;// 用户所在地-省名称
    private String city;// 用户所在地-市名称
    private Integer isAuth;// 是否已认证(0:未认证,1:已认证.默认0,3:已驳回)
    private Integer isBoundPhone;// 是否已绑定手机(0:未绑定手机,1:已绑定手机.默认0)
    private Integer isProtected;// 是否已设定密保(0:没设置密保,1:已设置密保,默认0)
    private String boundPhone;// 已绑定手机号
    private Date regTime;// 注册时间
    private Integer status;// 用户状态(1:生效,0:失效,默认1)
    private String statusName;// 用户状态名称(1:生效,0:失效,默认1)
    private Date loginTime;// 登录时间(初次注册,其值为注册时间)
    private Integer isDel;// 是否已删除(0:未删除,1:已删除,默认0)
    private String operator;// 最新修改人(只记录后台操作人员)
    private Date optTime;// 最新修改时间(只记录后台操作人员)
    private String qq;// QQ号码
    private String birthday;// 出生日期
    private String officeTel;// 办公电话
    private String officeAdr;// 办公地址
    private String officeZip;// 办公地址邮编
    private String homeAdr;// 家庭住址
    private String homeZip;// 家庭住址邮编
    private String remark;// 备注
    private Integer registType;//注册类型（1：手机注册 2：邮箱注册）
    private Integer verifyStatus;//是否已验证邮箱（1未验证 2已验证）
    private String authUrlUp;//省份认证url
    private String authUrlDw;//省份认证url
    private String paymentPas;//支付密码
    private String corporateName;//企业名称
    private String organizationCode;//住址机构代码
    private int isPayPasswd;//是否设置资金密码
    private String randNo;//邮箱注册校验随机码
    
    private String regSource;//注册来源
    
    private BigDecimal accountAmount;// 资金总额,默认0.00
    private BigDecimal frozenFunds;// 用户冻结资金,默认0.00
    private BigDecimal txFrozenFunds;// 用户冻结资金,默认0.00
    private BigDecimal jbbFrozenFunds;// 用户冻结资金,默认0.00

	@SuppressWarnings("unused")
	private BigDecimal availebalAmount;//可以金额= 资金总额 - 用户冻结资金
    
    private BigDecimal investAmount;//投资金额
    private BigDecimal incomeAmount;//收益金额
    
    private BigDecimal allIncome;//总收益
    
    private BigDecimal monthIncome;//月收益
    
    private BigDecimal allInvest;//累计投资金额
    
    private BigDecimal notGetIncome;//待收本息
    
    private BigDecimal unrepayInsterest;//待收利息
    
    private BigDecimal unrepayCapital;//待收本金
    
    private int projectAccount;
    
    private String incomeCode;//唯一约束条件
    
    private int lotteryAmount; //奖券数量
    
    private String noAgree;//连连订单号
    
    private BigDecimal subscriptionAllAmount;//债权认购总金额
    
    
    private Integer userClass;
    
    private BigDecimal lcInvestmentAmount;//联储计划投资总额
    private BigDecimal totalAssets;//资产总额
    private String lcsCode;//理财码
    
    private Date regTimeBegin;// 开始时间（注册时间查询条件）
    private Date regTimeEnd;//   结束时间(注册时间查询条件)
    private String investType;//投资类型    	1:三天未投资 	2:一月内投资<=1000
    private String employeeName;//销售人员名
    private String channel;//频道Id
    private Integer isJinQin;//是否是金亲     0：不是，1：是
    private String deviceId;//app设备号
    
    private String inviteurl;//邀请URL
    
    private String jzPage;//金钻特权页面
    
    private BigDecimal jzRate;//用户当前金钻特权收益率
    
    private Long marketerCode;//二维码编号
    private String ip;//注册ip
    private BigDecimal jinbbInvestAmount;//金宝宝在投金额
    private BigDecimal scatteredIvestAmount;//散标项目在投金额
    private BigDecimal zzIvestAmount;//债转在投金额
	private BigDecimal accountTotalAssets;//账户总资产
    private BigDecimal yesterdayIncome;//昨日收益
    private BigDecimal jinbbHQAccount;//金宝宝活期账户
    private Integer zpTimes;//可用转盘活动抽奖次数
    

	public Integer getZpTimes() {
		return zpTimes;
	}

	public void setZpTimes(Integer zpTimes) {
		this.zpTimes = zpTimes;
	}

	public BigDecimal getZzIvestAmount() {
		return zzIvestAmount;
	}

	public void setZzIvestAmount(BigDecimal zzIvestAmount) {
		this.zzIvestAmount = zzIvestAmount;
	}

	public BigDecimal getScatteredIvestAmount() {
		return scatteredIvestAmount;
	}

	public void setScatteredIvestAmount(BigDecimal scatteredIvestAmount) {
		this.scatteredIvestAmount = scatteredIvestAmount;
	}
    
    public BigDecimal getJinbbHQAccount() {
		return jinbbHQAccount;
	}

	public void setJinbbHQAccount(BigDecimal jinbbHQAccount) {
		this.jinbbHQAccount = jinbbHQAccount;
	}

	public BigDecimal getYesterdayIncome() {
		return yesterdayIncome;
	}

	public void setYesterdayIncome(BigDecimal yesterdayIncome) {
		this.yesterdayIncome = yesterdayIncome;
	}

	public BigDecimal getAccountTotalAssets() {
		return accountTotalAssets;
	}

	public void setAccountTotalAssets(BigDecimal accountTotalAssets) {
		this.accountTotalAssets = accountTotalAssets;
	}

	public BigDecimal getJinbbInvestAmount() {
		return jinbbInvestAmount;
	}

	public void setJinbbInvestAmount(BigDecimal jinbbInvestAmount) {
		this.jinbbInvestAmount = jinbbInvestAmount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getMarketerCode() {
		return marketerCode;
	}

	public void setMarketerCode(Long marketerCode) {
		this.marketerCode = marketerCode;
	}

	public BigDecimal getJzRate() {
		return jzRate;
	}

	public void setJzRate(BigDecimal jzRate) {
		this.jzRate = jzRate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getIsJinQin() {
		return isJinQin;
	}

	public void setIsJinQin(Integer isJinQin) {
		this.isJinQin = isJinQin;
	}

	public String getLcsCode() {
		return lcsCode;
	}

	public void setLcsCode(String lcsCode) {
		this.lcsCode = lcsCode;
	}

	public Integer getUserClass() {
		return userClass;
	}

	public void setUserClass(Integer userClass) {
		this.userClass = userClass;
	}

	public BigDecimal getAllIncome() {
		return allIncome;
	}

	public void setAllIncome(BigDecimal allIncome) {
		this.allIncome = allIncome;
	}

	public BigDecimal getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

	public BigDecimal getAllInvest() {
		return allInvest;
	}

	public void setAllInvest(BigDecimal allInvest) {
		this.allInvest = allInvest;
	}

	public BigDecimal getNotGetIncome() {
		return notGetIncome;
	}

	public void setNotGetIncome(BigDecimal notGetIncome) {
		this.notGetIncome = notGetIncome;
	}

	/**
     * 这是后添加的(卡号和开户行)
     */
    private String bankCard; //提现卡号
    private String bankName;//提现卡开户行
    private String bankNameSub;//提现卡开户行支行
	private BigDecimal accountsubfrozen;// 资金总额-冻结资金


    public BigDecimal getAccountsubfrozen() {
		return accountsubfrozen;
	}

	public void setAccountsubfrozen(BigDecimal accountsubfrozen) {
		this.accountsubfrozen = accountsubfrozen;
	}

	/**
     * 初始化会员默认值
     */
    public void initMember() {
        if (StringUtils.isBlank(nickName)) {
            this.nickName = "会员";
        }
        if (null == this.sex) {
            this.sex = 0;
        }
        if (null == isAuth) {
            this.isAuth = 0;
        }
        if (null == isBoundPhone) {
            this.isBoundPhone = 0;
        }
        if (null == isProtected) {
            this.isProtected = 0;
        }
        if (null == regTime) {
            this.regTime = new Date();
        }
        if (null == loginTime) {
            this.loginTime = new Date();
        }
        if (null == isDel) {
            this.isDel = 0;
        }
        if (null == status) {
            this.status = 1;
        }
    }

    // -------------------------- set/get 方法---------------------------------

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
    	
    	if(StringUtils.isEmpty(nickName)) nickName="会员";
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
    	//如果前台传入的身份证号码最后一位为小写的x统一处理为大写X
		if(idNo!=null && idNo.length() > 2 && idNo.endsWith("x")){
			//只为15位或者18位的身份证替换，防止和加密后的密文最后有x冲突，加密后的位数为8的倍数
			if(idNo.length() ==18 || idNo.length() == 15){
				idNo = idNo.substring(0,idNo.length()-1) + "X";
			}
		}
        this.idNo = idNo;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public Integer getIsBoundPhone() {
        return isBoundPhone;
    }

    public void setIsBoundPhone(Integer isBoundPhone) {
        this.isBoundPhone = isBoundPhone;
    }

    public Integer getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(Integer isProtected) {
        this.isProtected = isProtected;
    }

    public String getBoundPhone() {
        return boundPhone;
    }

    public void setBoundPhone(String boundPhone) {
        this.boundPhone = boundPhone;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getTypeName() {
        this.typeName = MemberType.getNameById(this.type);
        return typeName;
    }

    public String getSexName() {
        this.sexName = Sex.getName(this.sex);
        return sexName;
    }

    public String getStatusName() {
        this.statusName = Status.getName(this.status);
        return statusName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeAdr() {
        return officeAdr;
    }

    public void setOfficeAdr(String officeAdr) {
        this.officeAdr = officeAdr;
    }

    public String getOfficeZip() {
        return officeZip;
    }

    public void setOfficeZip(String officeZip) {
        this.officeZip = officeZip;
    }

    public String getHomeAdr() {
        return homeAdr;
    }

    public void setHomeAdr(String homeAdr) {
        this.homeAdr = homeAdr;
    }

    public String getHomeZip() {
        return homeZip;
    }

    public void setHomeZip(String homeZip) {
        this.homeZip = homeZip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

	public Integer getRegistType() {
		return registType;
	}

	public void setRegistType(Integer registType) {
		this.registType = registType;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	
	public String getAuthUrlUp() {
		return authUrlUp;
	}

	public void setAuthUrlUp(String authUrlUp) {
		this.authUrlUp = authUrlUp;
	}

	public String getAuthUrlDw() {
		return authUrlDw;
	}

	public void setAuthUrlDw(String authUrlDw) {
		this.authUrlDw = authUrlDw;
	}

	
	
	public String getPaymentPas() {
		return paymentPas;
	}

	public void setPaymentPas(String paymentPas) {
		this.paymentPas = paymentPas;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getRandNo() {
		return randNo;
	}

	public void setRandNo(String randNo) {
		this.randNo = randNo;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setFrozenFunds(BigDecimal frozenFunds) {
		this.frozenFunds = frozenFunds;
	}

	public BigDecimal getFrozenFunds() {
		return frozenFunds;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIsPayPasswd(int isPayPasswd) {
		this.isPayPasswd = isPayPasswd;
	}

	public int getIsPayPasswd() {
		return isPayPasswd;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	public String getRegSource() {
		return regSource;
	}

	public void setProjectAccount(int projectAccount) {
		this.projectAccount = projectAccount;
	}

	public int getProjectAccount() {
		return projectAccount;
	}

	public void setBankNameSub(String bankNameSub) {
		this.bankNameSub = bankNameSub;
	}

	public String getBankNameSub() {
		return bankNameSub;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setUnrepayInsterest(BigDecimal unrepayInsterest) {
		this.unrepayInsterest = unrepayInsterest;
	}

	public BigDecimal getUnrepayInsterest() {
		return unrepayInsterest;
	}

	public void setUnrepayCapital(BigDecimal unrepayCapital) {
		this.unrepayCapital = unrepayCapital;
	}

	public BigDecimal getUnrepayCapital() {
		return unrepayCapital;
	}

	public void setAvailebalAmount(BigDecimal availebalAmount) {
		this.availebalAmount = availebalAmount;
	}

	public BigDecimal getAvailebalAmount() {
		return accountAmount.subtract(frozenFunds);
	}

	public void setLotteryAmount(int lotteryAmount) {
		this.lotteryAmount = lotteryAmount;
	}

	public int getLotteryAmount() {
		return lotteryAmount;
	}

	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

	public BigDecimal getSubscriptionAllAmount() {
		return subscriptionAllAmount;
	}

	public void setSubscriptionAllAmount(BigDecimal subscriptionAllAmount) {
		this.subscriptionAllAmount = subscriptionAllAmount;
	}

	public BigDecimal getLcInvestmentAmount() {
		return lcInvestmentAmount;
	}

	public void setLcInvestmentAmount(BigDecimal lcInvestmentAmount) {
		this.lcInvestmentAmount = lcInvestmentAmount;
	}

	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Date getRegTimeBegin() {
		return regTimeBegin;
	}

	public void setRegTimeBegin(Date regTimeBegin) {
		this.regTimeBegin = regTimeBegin;
	}

	public Date getRegTimeEnd() {
		return regTimeEnd;
	}

	public void setRegTimeEnd(Date regTimeEnd) {
		this.regTimeEnd = regTimeEnd;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getInviteurl() {
		return inviteurl;
	}

	public void setInviteurl(String inviteurl) {
		this.inviteurl = inviteurl;
	}

	public String getJzPage() {
		return jzPage;
	}

	public void setJzPage(String jzPage) {
		this.jzPage = jzPage;
	}

	public BigDecimal getTxFrozenFunds() {
		return txFrozenFunds;
	}

	public void setTxFrozenFunds(BigDecimal txFrozenFunds) {
		this.txFrozenFunds = txFrozenFunds;
	}

	public BigDecimal getJbbFrozenFunds() {
		return jbbFrozenFunds;
	}

	public void setJbbFrozenFunds(BigDecimal jbbFrozenFunds) {
		if(jbbFrozenFunds != null){
			txFrozenFunds = frozenFunds.subtract(jbbFrozenFunds);
		}
		this.jbbFrozenFunds = jbbFrozenFunds;
	}
	
	
	
}