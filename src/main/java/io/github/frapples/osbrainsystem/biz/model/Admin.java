package io.github.frapples.osbrainsystem.biz.model;

import com.google.common.base.Strings;
import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.AdminDO;
import io.github.frapples.osbrainsystem.dal.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Converter<Admin, AdminDO> {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String passwordHash;


    @Override
    public AdminDO convertTo(AdminDO adminDO) {
        Converter.defaultConvert(this, adminDO);
        if (!Strings.isNullOrEmpty(this.getPassword())) {
            adminDO.setPasswordHash(SecurityUtils.sha256(this.getPassword()));
        }
        return adminDO;
    }
}
