package com.spring.boot.security.controller;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.constant.TableConstant;
import com.spring.boot.security.dto.DistributionUpdateRowMapper;
import com.spring.boot.security.forms.data.DistributionUpdateVO;
import com.spring.boot.security.forms.data.TableQuery;

@Controller
public class DistributionDataController {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(DistributionDataController.class);
	
	@Autowired
	DataSource dataSource;
	
	
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
		"                <td><a href=\"\">Update</a></td>\r\n" + 
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

	    return htmlHeader+Htmlbody.toString()+htmlFooter.toString();
		
	}
	

}
