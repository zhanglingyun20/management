package com.management.controller.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Halo on 2016/8/20.
 */
@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {

    @RequestMapping(value = "/map")
    public String map()
    {
        return "monitor/map";
    }

}
