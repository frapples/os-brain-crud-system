package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.Admin;
import io.github.frapples.osbrainsystem.dal.dao.AdminDO;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;
import io.github.frapples.osbrainsystem.dal.mapper.AdminMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    @Autowired
    private AdminMapper adminMapper;

    public boolean addAdmin(Admin admin) {
        AdminDO adminDO = ModelConverter.convert(admin, AdminDO.class);
        if (adminDO == null) {
            return false;
        } else {
            return adminMapper.insert(adminDO) >= 1;
        }
    }

    public Optional<Admin> getAdmin(int id) {
        AdminDO adminDO = adminMapper.selectById(id);
        Admin admin = ModelConverter.convert(adminDO, Admin.class);
        return Optional.ofNullable(admin);
    }

    public Optional<Admin> getAdminByName(String name) {
        List<AdminDO> adminDOs = adminMapper.selectList(new EntityWrapper<AdminDO>().eq("name", name));
        List<Admin> admins = ModelConverter.convert(adminDOs, Admin.class);
        if (admins.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(admins.get(0));
        }
    }
}
