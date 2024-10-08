package com.doyouknow.project.controller;

import com.doyouknow.project.dto.DeptDTO;
import com.doyouknow.project.service.MapService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("headerDeptInfoList")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String mapPage(@RequestParam(value = "locDetail", required = false) String locDetail, Model model) {
        System.out.println("확인용 locDetail: " + locDetail);

        List<DeptDTO> deptInfo = mapService.selectAllDept();
        model.addAttribute("headerDeptInfoList", deptInfo);

        System.out.println("DeptInfo: " + deptInfo);

        return "map/map";
    }

    @GetMapping("/mapData")
    @ResponseBody
    public List<DeptDTO> getMapData(@RequestParam(value = "locDetail", required = false) String locDetail) {
            return mapService.selectAllDept();
    }
}
