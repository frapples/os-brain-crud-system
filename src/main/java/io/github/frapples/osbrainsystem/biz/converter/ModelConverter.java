package io.github.frapples.osbrainsystem.biz.converter;


import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.github.frapples.osbrainsystem.biz.model.Admin;
import io.github.frapples.osbrainsystem.dal.dao.AdminDO;
import io.github.frapples.osbrainsystem.dal.utils.SecurityUtils;

public class ModelConverter extends DefaultConverter {

    public static AdminDO convert(Admin admin, Class<AdminDO> clazz) {
        AdminDO adminDO = convert((Object)admin, clazz);

        Preconditions.checkNotNull(adminDO);

        if (!Strings.isNullOrEmpty(admin.getPassword())) {
            adminDO.setPasswordHash(SecurityUtils.sha256(admin.getPassword()));
        }
        return adminDO;
    }
}
