package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LocationStats;
import com.example.demo.service.ResidentLocService;

@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private ResidentLocService resLocService;

	@GetMapping("/overall")
	public LocationStats getOverallStats() {
		return resLocService.getOverAllStats();
	}

	@GetMapping("/muni/{muniCode}")
	public LocationStats getStatsByMuni(@PathVariable("muniCode") String muniCode) {
		return resLocService.findVbTrueResByMuni(muniCode);
	}

	@GetMapping("/{brgyCode}")
	public LocationStats getStatsByBrgy(@PathVariable("brgyCode") String brgyCode) {
		return resLocService.findVbTrueResByBrgy(brgyCode);
	}

	@GetMapping("/percent-per-muni")
	public Object percentPerMuniData() {
		return resLocService.percLocation();
	}

}
