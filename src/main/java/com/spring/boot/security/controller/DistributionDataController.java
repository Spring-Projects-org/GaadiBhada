package com.spring.boot.security.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.constant.TableConstant;
import com.spring.boot.security.dto.AgentDestinationData;
import com.spring.boot.security.dto.DistributionUpdateRowMapper;
import com.spring.boot.security.dto.SubLotBookData;
import com.spring.boot.security.dto.TraderAgentDetails;
import com.spring.boot.security.dto.UpdateModalMapper;
import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.entity.SubLotBook;
import com.spring.boot.security.entity.TraderAgent;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.DistributionUpdateVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.forms.data.UpdateModalVO;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.AgentDestinationRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.SubLotBookRepository;
import com.spring.boot.security.repository.TraderAgentRespository;

@Controller
public class DistributionDataController {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(DistributionDataController.class);
	
	@Autowired
	DataSource dataSource;
	@Autowired
	LotBookRepository lotBookRepository;
	@Autowired
	TraderAgentRespository traderAgentRespository;
	@Autowired
	AgentDestinationRepository agentDestinationRepository;
	
	@Autowired
	SubLotBookData subLotBookData;
	
	@RequestMapping(value="/updateDistribution" ,method=RequestMethod.GET)
	@ResponseBody
	public String updateDistribution(@RequestParam Integer id) {
		TableQuery tableQuery=new TableQuery();
		
		String sql=tableQuery.getUpdateDistributionQuery();
		LOGGER.debug(sql);
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		List<DistributionUpdateVO> list =jdbcTemplate.query(sql, new DistributionUpdateRowMapper());
		
StringBuilder htmlHeader=new StringBuilder("<table id=\"updateTable\" class=\"table table-striped table-bordered\" style=\"width:100%\">\r\n" + 
		"        <thead>\r\n" + 
		"            <tr>\r\n" + 
		"                <th>Challan Id</th>\r\n" + 
		"                <th>Date</th>\r\n" + 
		"                <th>Truck No</th>\r\n" + 
		"                <th>From-To-Where</th>\r\n" + 
		"                <th>Sender</th>\r\n" + 
		"                 <th>Item</th>\r\n"+
		"                 <th>Box Type</th>\r\n"+
		"                 <th>Qty</th>\r\n"+
		"                 <th>Total Balance</th>\r\n"+
		"                <th>Action</th>\r\n" + 
		"            </tr>\r\n" + 
		"        </thead>\r\n" + 
		"        <tbody>\r\n" + 
		"            \r\n");
StringBuilder Htmlbody=new StringBuilder();
		for(int i=0;i<list.size();i++) {
			DistributionUpdateVO distributionUpdateVO=list.get(i);
			Htmlbody.append(
		"            <tr>\r\n" + 
		"                <td>"+distributionUpdateVO.getChallan_id()+"</td>\r\n" + 
		"                <td>"+distributionUpdateVO.getChallan_date()+"</td>\r\n" + 
		"                <td>"+distributionUpdateVO.getTruck_no()+"</td>\r\n" + 
		"                <td>"+distributionUpdateVO.getSource_name()+"-"+distributionUpdateVO.getDestination()+"</td>\r\n" + 
		"                <td>"+distributionUpdateVO.getTrader_name()+"("+distributionUpdateVO.getTrader_mark()+")</td>\r\n" +
		"                <td>"+distributionUpdateVO.getItem_name()+"</td>\r\n" +
		"                <td>"+distributionUpdateVO.getBox_name()+"-"+distributionUpdateVO.getTotal_wt()+"(Kg)</td>\r\n" +
		"                <td>"+distributionUpdateVO.getTotal_qty()+"</td>\r\n" + 
		"                <td>"+distributionUpdateVO.getTotBal()+"</td>\r\n" +
		"                <td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#updateDistributionModal\" onclick=\"loadUpdateModal("+distributionUpdateVO.getLot_id()+")\">Update Distribution</button></td>"+
		"            </tr>\r\n" );
		}
		StringBuilder htmlFooter=new StringBuilder(		"        </tbody>\r\n" + 
		"        <tfoot>\r\n" + 
		"            <tr>\r\n" + 
		"                <th>Challan Id</th>\r\n" + 
		"                <th>Date</th>\r\n" + 
		"                <th>Truck No</th>\r\n" + 
		"                <th>From-To-Where</th>\r\n" + 
		"                <th>Sender</th>\r\n" + 
		"                 <th>Item</th>\r\n"+
		"                 <th>Box Type</th>\r\n"+
		"                 <th>Qty</th>\r\n"+
		"                <th>Action</th>\r\n" +
		"            </tr>\r\n" + 
		"        </tfoot>\r\n" + 
		"    </table>");
StringBuilder modalContent=new StringBuilder();
modalContent.append(
		" <div id=\"updateDistributionModal\" class=\"modal fade\" role=\"dialog\">"
		  +"<div class=\"modal-dialog modal-lg\" >"

		    +"<!-- Modal content-->"
		    +"<div class=\"modal-content\">"
		      +"<div class=\"modal-header\">"
		        +"<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>"
		        +"<h4 class=\"modal-title\">Update Distribution</h4>"
		     +" </div>"
		      +"<div class=\"modal-body\" id=\"modalContent\">"
		        +"<p>Some text in the modal.</p>"
		      +"</div>"
		      +"<div class=\"modal-footer\">"
		      +"<button id=\"save\" class=\"btn btn-width bkgrnd-cyan save-details\" type=\"button\" name=\"save-details\" onclick=\"saveUpdateModal()\" data-toggle=\"modal\"  >Save</button>"
		       +" <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>"
		      + " </div> </div> </div> </div>"
		    	  		
		
		);
	    return htmlHeader+Htmlbody.toString()+htmlFooter.toString()+modalContent.toString();
		
	}
	
	
	@RequestMapping(value="/loadUpdateModal" ,method=RequestMethod.GET)
	@ResponseBody
	public String loadUpdateModalData(@RequestParam Integer id) {
		

		List<TraderAgent> traderAgents =traderAgentRespository.findAll();
	
		List<AgentDestination> agentDestinations=agentDestinationRepository.findAll();
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		LOGGER.debug(TableQuery.getUpdateModalQuery());
		UpdateModalVO updateModalVO=jdbcTemplate.queryForObject(TableQuery.getUpdateModalQuery(),new Object[] {id}, new UpdateModalMapper());

		StringBuilder updatemodalForm=new StringBuilder(
				
				"<form class=\"form-inline\"  action=\"#\" method=\"post\" name=\"updateModalForm\" data-toggle=\"validator\">"
						+"<div class=\"row clearfix\">"
						+"<div class=\"col-md-12 column\">"
						+"<table class=\"table table-bordered table-hover\" id=\"updateModalFormTab\">"

						+"<thead>"
						+"<tr>"
						+"<td><label for=\"lotId\">Lot Id</label></td>"
						+"<td><label for=\"totalQty\">Total Qty</label></td>"
						+"<td><label for=\"qtyAvl\">Qty Avl</label></td>"
						+"<td><label for=\"reciever\">Reciever</label></td>"
						+"<td><label for=\"distributeTo\">Distribute To</label></td>"
						+"<td><label for=\"distributeTo\">Destination</label></td>"
						+"<td><label for=\"distributeQty\">Distribute Qty</label></td></tr>"
						+"</thead>"
						
						+"<tbody>"
						+"<tr>"
						+"<td><label for=\"lotIdVal\" name=\"lot\">"+updateModalVO.getLotId()+"</label>"
						+ "<input type=\"hidden\" name=\"modalLotId\" value="+updateModalVO.getLotId()+"></td>"
						+"<td><label for=\"totalQtyVal\">"+updateModalVO.getTot_qty()+"</label>"
						+ "<input type=\"hidden\" name=\"modalTotQty\" value="+updateModalVO.getTot_qty()+"></td>"
						+"<td><label for=\"qtyAvlVal\">"+updateModalVO.getTot_avl()+"</label>"
						+ "<input type=\"hidden\" name=\"modalqtyAvl\" value="+updateModalVO.getTot_avl()+"></td>"
						+"<td><label for=\"recieverVal\">"+FormUtils.nullToEmpty(updateModalVO.getReceiver())+"</label>"
						+ "<input type=\"hidden\" name=\"modalReciever\" value="+FormUtils.nullToEmpty(updateModalVO.getReceiver())+"></td>"
						
						+"<td><select class=\"form-control\" id=\"agentId\" name=\"agentId\" required >"
						  +"<option value=\"\">Select Agent</option>"
						);
						  
						for(TraderAgent traderAgent:traderAgents)
							updatemodalForm.append("<option value=\""+traderAgent.getAgentId()+"\">"+traderAgent.getAgentName()+"("+traderAgent.getAgentMmark()+")</option>");
						
						updatemodalForm.append("</select></td>"
								+"<td><select class=\"form-control\" id=\"agentDestination\" name=\"agentDestination\" required >"
								+"<option value=\"\">Select Destination</option>");
						
						for(AgentDestination agentDestination:agentDestinations)
						updatemodalForm.append("<option value=\""+agentDestination.getAgentDestinationId()+"\">"+agentDestination.getAgentDestinationName()+"</option>");
						updatemodalForm.append( " </select></td> "
						+"<td><input type=\"text\" name=\"totQtyDistributed\" placeholder='Distribute Qty' class=\"form-control\" required></td>"
						+"</tr>"
						
						 +"</tbody>"
						 +"</table>"
						 +"</div>"
						+"</div>"
						+"</form>"
				
				);

		
	
			    return updatemodalForm.toString();	
	}
	
	@RequestMapping(value="/saveUpdateModal" ,method=RequestMethod.GET)
	@ResponseBody
	public String saveUpdateModalData(@RequestParam int modalLotId,@RequestParam int modalTotQty,
			@RequestParam int modalqtyAvl,@RequestParam(required = false) String modalReciever,@RequestParam int agentId
			,@RequestParam int agentDestination,@RequestParam int totQtyDistributed ,HttpServletRequest request ) throws DataBaseException {
		
		HttpSession session=request.getSession(false);
		String sessionId=session.getId();
		String createTimeStamp=FormUtils.getCurrentTimeStamp();
		SubLotBook subLotBook=new SubLotBook();
		subLotBook.setLotId(modalLotId);
		subLotBook.setTotalQty(totQtyDistributed);
		subLotBook.setAgentDestinationId(agentId);
		subLotBook.setAgentId(agentId);
		subLotBook.setCreateTimeStamp(createTimeStamp);
		subLotBook.setSessionId(sessionId);

		SubLotBook saveSubLotBook=subLotBookData.saveSubLotBookData(subLotBook);
		if(saveSubLotBook==null) {
			
			throw new DataBaseException("Exception found while saving data into Challan Book ","management");
		
	}
		else
			return "success";
		/*System.out.println("modalLotId:"+modalLotId);
		System.out.println("modalTotQty:"+modalTotQty);
		System.out.println("modalqtyAvl:"+modalqtyAvl);
		System.out.println("modalReciever:"+modalReciever);
		System.out.println("agentId:"+agentId);	
		System.out.println("agentDestination:"+agentDestination);	
		System.out.println("totQtyDistributed:"+totQtyDistributed);*/
		
		
		
	}
	

}
