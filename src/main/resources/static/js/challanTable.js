function saveUpdateModal()
{
	 
	//alert(agentId)
    
    
	
	var modalqtyAvl=jQuery('input[name="modalqtyAvl"]').val()
	var totQtyDistributed=jQuery('input[name="totQtyDistributed"]').val()
	var agentId=$( "#agentId" ).val()
	var agentDestination=$( "#agentDestination" ).val()
	
	//alert(documnet.getElementById("totQty"))
	//alert(d)
	
	//alert("Selected :"+agentId)
	
	if(totQtyDistributed.length<=0){
		alert("Please Enter Distribute Qty");
		return 10;
		
	}
	if(agentDestination.length<=0){
		alert("Please Select Destination");
		return 10;
	}
	if(parseInt(agentId.length)<=0){
		alert("Please Select Distribute To");
		return 10;
	    
	}
	
	if(totQtyDistributed==0 || modalqtyAvl<totQtyDistributed){
		alert("You Entered More Than Available");
		return 10;
		}
	else{
		//alert("modalqtyAvl:"+modalqtyAvl+" totQtyDistributed:"+totQtyDistributed+" agentId:"+agentId.length+" agentDestination:"+agentDestination.length)
		
		userJson = $("form").serializeArray();
		$.ajax({
	         type: "GET",
	         url: "/saveUpdateModal",
	         data: userJson,
	         success: function (data) {
	        	
	            if(data ==="success"){
	        	 alert("Data is saved successfully");
	             $("#updateDistributionModal").modal('hide');
	             window.location.reload();
	             //callAjaxForUpdateDistribution();
	        	 }
	            else
	            	alert("Error !!")
	         }
	     });
	}
//alert("Called save");	
}

function loadUpdateModal(lotId){
		//alert("Coming from modal");
		//alert(lotId);
		$.ajax({
		    type : "GET",
		    url : "/loadUpdateModal",
		    data : {
		    "id" : lotId
		    },
		    success: function(data){
		    	//alert(data)
		    	//loadjs("/js/dataTable.js");
		    	$('#modalContent').html(data);
		    	
		    	//loadjs("/js/dataTable.js");
		    	//$('#updateTable').DataTable();
		    	
		    }
		});
		}  
$(document).ready(function(){
	
	
	
	//below function required to load js functions after page is dynamically loaded
	function loadjs(file) {
        var script = document.createElement("script");
        script.type = "application/javascript";
        script.src = file;
        document.body.appendChild(script);
    }
	
	function callAjaxForUpdateDistribution()
	{
		$.ajax({
		    type : "GET",
		    url : "/updateDistribution",
		    data : {
		    "id" : 1
		    },
		    success: function(data){
		    	$('#menu2').html(data);
		    	
		    	loadjs("/js/dataTable.js");
		    	$('#updateTable').DataTable();
		    	
		    }
		});
	}
	//Calling Ajax to load the updated distribution page dynamically
	$("#updateTab").on("click", function() {
		
		callAjaxForUpdateDistribution();
		
		//$('#updateTable').DataTable();
		
    });
	//$('#updateTable').DataTable();
   
	$("#openTab2").on("click", function() {
        $("#tab2").load('/Controller2/Index');
    });
    
	 
	 
    //For redirecting same tab when page is refreshed  
	$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
        localStorage.setItem('activeTab', $(e.target).attr('href'));
    });
    var activeTab = localStorage.getItem('activeTab');
    //alert(activeTab !== "#menu2")
    //Since menu2 is dynamic page it should be loaded runtime
    if(activeTab /*&& activeTab !== "#menu2"*/){
        $('#menuTab a[href="' + activeTab + '"]').tab('show');
        callAjaxForUpdateDistribution();
    }
    
    //Calculating WT
	function getTotalWt() {
		
		var sel = document.challanForm.boxId;
		if(sel=="")
			WtPerBox=0;
		else
			var opt = sel.options[sel.selectedIndex];
		var WtPerBox=opt.text.split('-')[1];
		var totalQty=document.challanForm.totalQty.value;
		var result=WtPerBox*totalQty;
		alert("Result"+result)
		//document.challanForm.totalQty.innerHTML = totalQty*WtPerBox;
		document.challanForm.tw.value = result;
	}
	
	var i=1;
     
     $("#add_row").click(function(){
    	 var j;
    	 var trader = "[[${traders}]]";
    	 
    	 // var a=[[${traders}]] "Test";
    	// alert(traderss[i].trader_name);
    	// alert(traderss[i].trader_id);
    	 var traderList="";
    	 var itemList="";
    	 var boxTypesList="";
    	 
    	 for(j=0;j<traders.length;j++){
    		 
    		 
    		 traderList=traderList+"<option value=\""+ traders[j].trader_id +"\">"+traders[j].trader_name+"("+traders[j].trader_mark+")</option>";
 			
       		}
          for(j=0;j<items.length;j++){
    		 
    		 
        	  itemList=itemList+"<option value=\""+ items[j].item_id +"\">"+items[j].item_name+"</option>";
 			
       		}
          for(j=0;j<boxTypes.length;j++){
     		 
     		 
        	  boxTypesList=boxTypesList+"<option value=\""+ boxTypes[j].box_id +"\">"+boxTypes[j].box_name+"-"+boxTypes[j].total_wt+"</option>";
 			
       		}
    	 //alert(list);
      $('#addr'+i).html("<td>"+ (i+1) +"</td>" +
      		"<td><select class='form-control' id='traders' name='traderId' required><option value=''>Select Trader</option>"+
      		traderList
           +"</select> </td>" +
           "<td><select class='form-control' id='items' name='itemId' required ><option value=''>Select Item</option>"+
           itemList
           +"</select> </td>" +
           "<td><select class='form-control' id='boxTypes' name='boxId' required><option value=''>Select Type</option>"+
           boxTypesList
           +"</select> </td>" +
           
    		"<td><input  name='totalQty' type='text' placeholder='Total Quantity'  class='form-control input-md' required onclick='getTotalWt()'></td>"+
    		"<td><input  name='receiver' type='text' placeholder='Receiver'  class='form-control input-md' ></td>"
    		);

      $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
      i++; 
  });
     $("#delete_row").click(function(){
    	 if(i>1){
		 $("#addr"+(i-1)).html('');
		 i--;
		 }
	 });

});