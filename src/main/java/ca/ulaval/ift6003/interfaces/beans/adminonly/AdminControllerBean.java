package ca.ulaval.ift6003.interfaces.beans.adminonly;

import ca.ulaval.ift6003.application.AdminApplication;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

import javax.faces.bean.ManagedProperty;

public abstract class AdminControllerBean extends ControllerBean {

    @ManagedProperty(value = "#{adminApplication}")
    protected AdminApplication adminApplication;

    public void setAdminApplication(AdminApplication adminApplication) {
        this.adminApplication = adminApplication;
    }
}
