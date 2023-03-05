package UTCC.project.bus.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UTCC.framework.utils.ConvertDateUtils;
import UTCC.framework.utils.UserLoginUtil;
import UTCC.project.bus.model.BusModel;
import UTCC.project.bus.repository.BusRepository;
import UTCC.project.bus.vo.BusReq;
import UTCC.project.bus.vo.BusRes;

@Service
public class BusService {

	@Autowired
	private BusRepository busRepository;
	
	
	public void saveBus(BusReq req) {
		BusModel busModel = new BusModel();
		busModel.setBusNo(req.getBusNo());
		busModel.setBusType(req.getBusType());
		busModel.setBusPlate(req.getBusPlate());
		busModel.setBusProvince(req.getBusProvince());
		busModel.setFare(req.getFare());
		busModel.setDiscountFare(req.getDiscountFare());
		busModel.setCreateDate(new Date());
		busModel.setCreateBy(UserLoginUtil.getUsername());
		busRepository.save(busModel);
	}
	
	
	public List<BusRes> getAllList() {
		List<BusRes> dataRes = new ArrayList<BusRes>();
		BusRes dataSet;
		List<BusModel> fwBus = (List<BusModel>) busRepository.findAll();
		for (BusModel fwBusSet : fwBus) {
			dataSet = new BusRes();
			dataSet.setBusNo(fwBusSet.getBusNo());
			dataSet.setId(fwBusSet.getId());	
			dataSet.setBusType(fwBusSet.getBusType());
			dataSet.setBusPlate(fwBusSet.getBusPlate());
			dataSet.setBusProvince(fwBusSet.getBusProvince());
			dataSet.setFare(fwBusSet.getFare().setScale(2, BigDecimal.ROUND_HALF_UP));
			dataSet.setDiscountFare(fwBusSet.getDiscountFare().setScale(2, BigDecimal.ROUND_HALF_UP));
			dataSet.setCreateDate(ConvertDateUtils.formatDateToString(fwBusSet.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
			dataRes.add(dataSet);
	
		}
		return dataRes;
	}

}
