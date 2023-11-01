package com.hummersoft.eira.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hummersoft.eira.dto.EquipmentListDTO;
import com.hummersoft.eira.dto.EquipmentDTO;
import com.hummersoft.eira.model.DataSource;



@Component
public class SiteViewUtility {
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	
	public List<EquipmentListDTO> inverterEquipmentList(int siteId,int todayFalg,List<DataSource> lstDataSource,List<EquipmentDTO> lstEquipmentAll) {
		
		List<EquipmentListDTO> lstEquipmentList = new ArrayList<EquipmentListDTO>();
		Double Efficiency = 0.0;
		
		System.out.println("lstEquipmentAll.size() "+lstEquipmentAll.size());
		for (int i=0; i<lstEquipmentAll.size(); i++) {
			
			EquipmentListDTO equipDTO = new EquipmentListDTO();
			Integer equipid = lstEquipmentAll.get(i).getEquipmentId();
			//System.out.println("equipid  "+equipid);
			
			equipDTO.setSiteID(lstEquipmentAll.get(i).getSiteId());
			equipDTO.setEquipmentID(equipid);
			
			if (lstEquipmentAll.get(i).getEquipmentCode() != null) 
				equipDTO.setEquipmentCode(lstEquipmentAll.get(i).getEquipmentCode());
			
			if (lstEquipmentAll.get(i).getCutomerRef()!= null) 
				equipDTO.setEquipmentReference(lstEquipmentAll.get(i).getCutomerRef());
				
			if (lstEquipmentAll.get(i).getdisplayname() != null) 
				equipDTO.setEquipmentName(lstEquipmentAll.get(i).getdisplayname());
			
			if (lstEquipmentAll.get(i).getPrimarySerialNo()!= null) 
				equipDTO.setSerialNo(lstEquipmentAll.get(i).getPrimarySerialNo());
			

			if (lstEquipmentAll.get(i).getCustomerNaming() != null) 
				equipDTO.setCustomerNaming(lstEquipmentAll.get(i).getCustomerNaming());
			
			//lstEquipmentAll.get(i).get
			List<DataSource>  myLstDataSource = lstDataSource.stream().filter(p -> p.getEquipmentID().equals(equipid) )
					.collect(Collectors.toList());
			
				if (myLstDataSource.size() != 0) {
					DataSource myObjDataSource = myLstDataSource.get(0);

					Double dbYesterdayTotalEnergy = 0.0;
					Double dbTotalEnergy = 0.0;
					Double dbTodayEnergy = 0.0;
					Double dbTodayHoursOn = 0.0;

					Date LastUpdatedDate = null;

					if (myObjDataSource.getTotalEnergy() != null) 
						dbTotalEnergy += myObjDataSource.getTotalEnergy();
					
					if (myObjDataSource.getTodayEnergy() != null) 
						dbTodayEnergy +=myObjDataSource.getTodayEnergy();
					
					//System.out.println("dbTotalEnergy  "+dbTotalEnergy);

					if (myObjDataSource.getYesterdayTotalEnergy() != null) 
						dbYesterdayTotalEnergy += myObjDataSource.getYesterdayTotalEnergy();

					if (myObjDataSource.getTodayHoursOn() != null) 
						dbTodayHoursOn += myObjDataSource.getTodayHoursOn();

					if (myObjDataSource.getLastDataReceived() != null) 
						LastUpdatedDate = myObjDataSource.getLastDataReceived();
					
					if (myObjDataSource.getStatus() != null) 
						equipDTO.setNetworkStatus(myObjDataSource.getStatus().toString());
					
				
					if (myObjDataSource.getActivePower() != null && myObjDataSource.getInputPower_01() != null) {
						Efficiency = myObjDataSource.getActivePower() / myObjDataSource.getInputPower_01() * 100;
						if (Efficiency >= 0 && Efficiency <= 100) {
							equipDTO.setInverterEfficiency(String.format("%.2f", Efficiency));
						}else {
							equipDTO.setInverterEfficiency("-");
						}
					} else if (myObjDataSource.getApparentPower() != null && myObjDataSource.getInputPower_01() != null) {
						Efficiency = myObjDataSource.getApparentPower() / myObjDataSource.getInputPower_01() * 100;
						if (Efficiency >= 0 && Efficiency <= 100) {
							equipDTO.setInverterEfficiency(String.format("%.2f", Efficiency));
						}else {
							equipDTO.setInverterEfficiency("-");
						}
					}
					else {
						equipDTO.setInverterEfficiency("--");
					}
					if (equipDTO.getInverterEfficiency().equals("Infinity")) {
						equipDTO.setInverterEfficiency("String Compare");
					}
					//System.out.println("*** dbTodayEnergy "+dbTodayEnergy);
					if(todayFalg ==0 && dbTotalEnergy > 0) {
						dbTodayEnergy = dbTotalEnergy - dbYesterdayTotalEnergy;
					}/*else {
						if (myObjDataSource.getTodayEnergy() != null) {
							dbTodayEnergy = equipmentService.getTodayEnergyByEquipmentId(objEquipmentListBean.getEquipmentID());
						}
					}*/

					dbTotalEnergy = dbTotalEnergy / 1000;

					equipDTO.setTodayEnergy(String.format("%.2f", dbTodayEnergy));
					equipDTO.setTotalEnergy(String.format("%.2f", dbTotalEnergy));
					
					if (LastUpdatedDate == null) 
						equipDTO.setLastUpdate("-");
					 else 
						 LastUpdatedDate = GetZoneTimeOffsetByMins(LastUpdatedDate, "330");
						 equipDTO.setLastUpdate(sdf.format(LastUpdatedDate));
					
					
					if (lstEquipmentAll.get(i).getCapacity() != null) {
						if ((dbTodayEnergy) / lstEquipmentAll.get(i).getCapacity() >= 7.0) {
							equipDTO.setPerformanceRatio("-");
						} else {
							equipDTO.setPerformanceRatio(
									String.format("%.2f", ((dbTodayEnergy) / lstEquipmentAll.get(i).getCapacity())));

						}

					} else {
						equipDTO.setPerformanceRatio("-");
					}
			
					//equipDTO.setTodayEnergy(dbTodayEnergy);
					//equipDTO.setTotalEnergy(dbTotalEnergy);
					//equipDTO.setLastUpdate(LastUpdatedDate.toString());
			
			
			//if (lstEquipmentAll.get(i).getDismandalFlag().equals(0)) 
				
			
			}
				lstEquipmentList.add(equipDTO);
		}
		
		return lstEquipmentList;
		
	}
	
	
	
	public Date GetZoneTimeOffsetByMins(Date AppServerDate, String TimezoneOffset) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(AppServerDate);
		Integer OffsetInMins = Integer.valueOf(TimezoneOffset);
		cal.add(Calendar.MINUTE, OffsetInMins);
		return cal.getTime();
	}

}

