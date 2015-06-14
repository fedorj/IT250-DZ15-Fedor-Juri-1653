/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.dao.SobaDao;
import com.mycompany.methotels.entities.Soba;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

/**
 *
 * @author Fedor
 */
public class InPlaceEditorX {

    @Property
    private Soba soba;
    @Property
    private List<Soba> sobe;
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
    @Inject
    private SobaDao sobaDao;
    @Inject
    private ComponentResources componentResources;

    void onActivate() {
        sobe = sobaDao.getListaSvihSoba();
    }

    @CommitAfter
    @OnEvent(component = "ime", value = InPlaceEditor.SAVE_EVENT)
    void setImeSobe(Long id, String value) {
        Soba soba = (Soba) sobaDao.getSobaById(id.intValue());
        soba.setIme(value);
        sobaDao.dodajIliUpdatujSobu(soba);
    }
}
