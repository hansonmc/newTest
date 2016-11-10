package com.jlc.api.dto;
/**
 * 交易类型
 * @date Mar 18, 2014
 */
public enum TradeType {

    // 如果有其他需要,请自行添加
    ONLINE_INCOME(11, "在线充值"), OFF_INCOME(12, "线下充值"), OUTLAY_CASH(2, "提现"), // 申请解冻,审核解冻
    ORDER(3, "投资"), OUT_CASH(4, "放款"),
    //ORDER_LCPLAN(5, "金宝宝投资"),// 划入,划出
    //二次上线增加优化功能
	DEPOSIT(8, "利息保证金"), INTEREST(9, "利息"), CAPITAL(10, "本金"), LOAN(13, "借款"), 
	SERVFEE(14, "服务费"), SUPEFEE(15, "监管费"), HANDLINGFEE(16, "提现手续费"), TRANSFER(17, "转账"),REWARD(20, "奖金"),
	J_TRANSFER_IN(21, "金宝宝活期转入"),J_TRANSFER_OUT(22, "金宝宝活期转出"),J_INTEREST(23, "金宝宝活期利息"),ZCREDITASSIGN(25,"债权转让"),
	ZCREDITASSIGN_POUNDAGE(26,"债转手续费"),NEGINTEREST(27,"债权转让负值利息垫付"),
	//LC_TRANSFER_FEE(28,"联储计划退出手续费"),LC_TRANSFER(29,"联储计划退出"),LC_INTEREST(30, "联储计划利息"),
	COUPON_RECYCLING(31,"奖券回收"),
	VIP(36,"VIP会员费"),APP_INTEREST(37,"APP投资奖励"),RedPacket_RECYCLING(38,"红包回收"),JZINTEREST(39,"金钻特权奖励")
	,ORDER_JINBAOBAO_MONTH(40,"月宝宝投资"),ORDER_JINBAOBAO_SEASON(41,"季宝宝投资"),ORDER_JINBAOBAO_YEAR(42,"年宝宝投资"),
	TRANSFER_JINBAOBAO_MONTH(43,"月宝宝转出"),TRANSFER_JINBAOBAO_SEASON(44,"季宝宝转出"),TRANSFER_JINBAOBAO_YEAR(45,"年宝宝转出"),
	INTEREST_JINBAOBAO_MONTH(46,"月宝宝利息"),INTEREST_JINBAOBAO_SEASON(47,"季宝宝利息"),INTEREST_JINBAOBAO_YEAR(48,"年宝宝利息"),
	TRANSFER_FEE_JINBAOBAO_MONTH(49,"月宝宝转出手续费"),TRANSFER_FEE_JINBAOBAO_SEASON(50,"季宝宝转出手续费"),TRANSFER_FEE_JINBAOBAO_YEAR(51,"年宝宝转出手续费"),
	;
    
    private int id;
    private String name;

    private TradeType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
