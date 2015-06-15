/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.dao.SobaDao;
import com.mycompany.methotels.entities.Soba;
import com.mycompany.methotels.services.ProtectedPage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author Fedor
 */
@RequiresRoles("Admin")
public class DodavanjeSobe {

    @Persist
    @Property
    private Soba soba;
    @Inject
    private SobaDao sobaDao;
    @Property
    private Soba onesoba;
    @Property
    private List<Soba> sobe;

    @InjectComponent
    private Zone zoneSoba;
    @InjectComponent
    private Zone formZone;
    @Inject
    private Request request;
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void onActivate() {
        sobe = sobaDao.getListaSvihSoba();
    }

    @CommitAfter
    Object onSuccess() {
        sobaDao.dodajIliUpdatujSobu(soba);
        sobe = sobaDao.getListaSvihSoba();
        soba = new Soba();
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(zoneSoba).addRender(formZone);
        }
        return request.isXHR() ? zoneSoba.getBody() : null;
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        sobaDao.obrisiSobu(id);
        sobe = sobaDao.getListaSvihSoba();
        return request.isXHR() ? zoneSoba.getBody() : null;
    }

    @CommitAfter
    Object onActionFromEdit(Soba sobe) {
        soba = sobe;
        return request.isXHR() ? zoneSoba.getBody() : null;
    }

}
