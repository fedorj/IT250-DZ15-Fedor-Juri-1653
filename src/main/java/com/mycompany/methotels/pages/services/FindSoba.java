/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages.services;

import com.mycompany.methotels.dao.SobaDao;
import com.mycompany.methotels.entities.Soba;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Fedor
 */
public class FindSoba {

    @Inject
    private Request request;
    @Property
    private List<Soba> sobe;
    @Property
    private Soba soba;
    @Inject
    private SobaDao sobaDao;

    Object onActivate(@RequestParameter("ime") String ime) {
        if (sobe == null) {
            sobe = new ArrayList<Soba>();
        }
        String response = "<table class=\"navigation\" > <th>\n"
                + " Sobe\n"
                + " </th>\n"
                + " ";
        sobe = sobaDao.getListaSobaPoImenu(ime);
        for (Soba s : sobe) {
            response += (" <tr>\n"
                    + " <td> " + s.getIme() + "</td>\n"
                    + " </tr>");
        }
        response += "</table>";
        return new TextStreamResponse("text/plain", response);
    }
}
