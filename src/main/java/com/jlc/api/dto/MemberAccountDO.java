package com.jlc.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberAccountDO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long memberId;// 用户ID,T_USER(用户基本信息表).ID
    private BigDecimal accountAmount;// 资金总额,默认0.00
    private BigDecimal frozenFunds;// 用户冻结资金,默认0.00
    @SuppressWarnings("unused")
	private BigDecimal accountsubfrozen;// 资金总额-冻结资金

    public MemberAccountDO() {
    }

    public Long getMemberId() {
        return memberId;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getFrozenFunds() {
        return frozenFunds;
    }

    public void setFrozenFunds(BigDecimal frozenFunds) {
        this.frozenFunds = frozenFunds;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

	public BigDecimal getAccountsubfrozen() {
		if(accountAmount==null || frozenFunds==null)
		{
			return new BigDecimal("0");
		}
		return accountAmount.subtract(frozenFunds);
	}

	public void setAccountsubfrozen(BigDecimal accountsubfrozen) {
		this.accountsubfrozen = accountsubfrozen;
	}

}
