package com.example.wigellsushi.logging;

import com.example.wigellsushi.WigellSushiApplication;
import org.apache.logging.log4j.*;


import org.apache.log4j.Logger;

public class Log4j {

    public static final Logger logger = Logger.getLogger(WigellSushiApplication.class);

    private Log4j() {
    }

}
