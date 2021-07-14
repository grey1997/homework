/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microprofile.config.source.servlet;

import com.microprofile.config.source.MapBasedConfigSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * The {@link ConfigSource} implementation based on {@link FilterConfig}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 * Date : 2021-04-28
 */
public class ServletRequestConfigSource extends MapBasedConfigSource {

    private final ServletRequest servletRequest;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public ServletRequestConfigSource(ServletRequest servletRequest) {
        super("Servlet Request Parameters", 600);
        this.servletRequest = servletRequest;
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = servletRequest.getParameterValues(parameterName);
            if(parameterValues == null || parameterValues.length == 0) {
                return;
            }
//            Arrays.stream(parameterValues).iterator().
            configData.put(parameterName, parameterValues);
            logger.log(Level.INFO, format("Servlet Request Parameter [ %s : %s ]", parameterName, parameterValues.toString()));
        }
    }
}
