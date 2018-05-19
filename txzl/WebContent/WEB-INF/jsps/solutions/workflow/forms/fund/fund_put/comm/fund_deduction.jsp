<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="id_window_fund_offset" class="mini-window"  closed="true" modal="true" title="坐扣资金" style="display:none;width:1000px;padding-top:20px;">
     <div style="overflow: hidden;height:300">
          <button class="btn btn-primary" onclick="funddue()"  ><span>抵扣</span></button>
     </div>
    <div id="tabDueDeatils" class="mini-tabs" activeIndex="0"  style="width: 100%;" bodyStyle="padding:0;border:0;">
	<div title="资金付款计划" name="put_plan" iconCls="icon-expand">
	     <jsp:include page="fund_guarance_info.jsp" >
	           <jsp:param value="false" name="isView"/>
	     </jsp:include>
	</div>
	<div title="租金回笼计划" name="put_his" iconCls="icon-expand">
	     <jsp:include page="rent_income_plan.jsp" ></jsp:include>
	</div> 
    </div>
</div>	
<script language="javascript">
   function  funddue(){
	   var receiverFund=mini.clone(mini.get("fund_guarance_plan").getSelecteds());
	   var cautiondetail=mini.get("caution_money_refund_detail").getData();
	   if(receiverFund.length==0){
		   mini.alert("请选择坐扣的资金计划");return false;
		 }
	   var payFund={};
       var planmoney=0;
       var pays=[];
       //抵扣资金
       for(var i=0;i<receiverFund.length;i++){
    	   var feetype=receiverFund[i].feetype;
    	   var paymentid=receiverFund[i].paymentid;
    	   for(var y=0;y<cautiondetail.length;y++){
    		   if(feetype==cautiondetail[y].feetype&&paymentid==cautiondetail[y].paymentid){
    			   mini.alert("该笔选中的已添加到坐扣资金明细中,不能重复添加！")
    			   return false;
    		   }
    		   
    	   }
    	   payFund=receiverFund[i];
    	   planmoney=parseFloat(payFund.overmoney);
    	   if(parseFloat(planmoney)>0&&parseFloat(receiverFund[i].overmoney)>0){
               if(parseFloat(planmoney)>=parseFloat(receiverFund[i].overmoney)){
                    planmoney=parseFloat(planmoney)-parseFloat(receiverFund[i].overmoney);
                    planmoney=parseFloat(planmoney).toFixed(2);
                      
               }else{
            	   receiverFund[i].overmoney=planmoney;
            	   planmoney=0;
               }
               receiverFund[i].fpnote="抵扣"+payFund.feetypename+"第"+payFund.paymentid+"笔";
               var pay=mini.clone(payFund);
               pay.pid=receiverFund[i].id;
               pay.overmoney=receiverFund[i].overmoney;
               pay.settlemethod='payfund11';
       		   pay.settlemethodname='坐扣';
       		   pay.fpnote="使用"+receiverFund[i].feetypename+"(收款编号"+receiverFund[i].paymentid+")抵扣";
       		   pays.push(pay);
           }else{
        	   receiverFund[i].overmoney=0;
           }    	   
    	      if(planmoney>0){
    	    	  var pay=mini.clone(payFund);
    	          pay.pid="";
    	          pay.settlemethod='payfund6';
    	  		  pay.settlemethodname='电汇';
    	  		  pay.overmoney=planmoney;
    	  		  pays.push(pay);
    	      } 
      }
       setDeduceFundPutPlan(receiverFund); 
       /*
      //抵扣租金
      if(parseFloat(planmoney)>0 && rentPlan.length>0){
         for(var i=0;i<rentPlan.length;i++){
        	 var rent=rentPlan[i].currentoverage;
        	 var corpus=rentPlan[i].curcorpusoverage;
        	 var interest=rentPlan[i].curinterestoverage;
        	 if(parseFloat(planmoney)>0&&parseFloat(rent)>0){
                 if(parseFloat(planmoney)>=parseFloat(rent)){
                      planmoney=parseFloat(planmoney)-parseFloat(rent);
                      planmoney=parseFloat(planmoney).toFixed(2);
                      rentPlan[i].penaltyoverage=0;  
                 }else{
                    var calculateresult = calculatePlanRent(planmoney, corpus, interest, 0);  
                    rentPlan[i].currentoverage=calculateresult.rent;
                    rentPlan[i].curcorpusoverage=calculateresult.corpus;
                    rentPlan[i].curinterestoverage=calculateresult.interest;
                    planmoney=0 ;
                 }
                 rentPlan[i].memo="抵扣"+payFund.feetypename+"第"+payFund.paymentid+"笔";
                 var pay=mini.clone(payFund);
                 pay.pid=rentPlan[i].id;
                 pay.overmoney=rentPlan[i].rent;
                 pay.settlemethod='payfund11';
         		 pay.settlemethodname='坐扣';
         		 pay.fpnote="使用租金第"+rentPlan[i].rentlist+"抵扣";
         		 pays.push(pay);
             }else{
          	   rentplan[i].currentoverage=0;
             }
         }
       
         generateDeduceRentDetail(rentPlan);
      } */
   //   getFundPutPlan(pays);--此方法在此好像多此一举
      mini.get("id_window_fund_offset").hide();
      mini.alert("操作成功,请到本次投放明细查看!"); 
  }
</script>