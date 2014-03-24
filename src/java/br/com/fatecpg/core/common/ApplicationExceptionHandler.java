/*
 * Copyright (C) 2014 Vitor Hugo Salgado <vsalgadopb@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.fatecpg.core.common;

import br.com.fatecpg.core.entities.Log;
import br.com.fatecpg.core.repositories.LogRepository;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
@Service
public class ApplicationExceptionHandler implements HandlerExceptionResolver {

    private LogRepository logRepository;

    @Autowired
    public void setLogRepository(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) {

        Log log = new Log();

        String message = excptn.getMessage();

        if (message == null || message.isEmpty()) {
            message = "Erro de aplicação.";
        }

        log.setCreatedDate(new Date());
        log.setDetails(excptn.toString());
        log.setIpAddress(hsr.getLocalAddr());
        log.setMessage(message);
        log.setUrl(hsr.getRequestURI());
        log.setUsername("");
        //log.setUsername(hsr.getUserPrincipal().getName());

        logRepository.add(log);

        return new ModelAndView("error", "log", log);
    }

}
