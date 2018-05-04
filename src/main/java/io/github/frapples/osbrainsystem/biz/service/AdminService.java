package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.Admin;
import io.github.frapples.osbrainsystem.dal.repository.AdminRepository;
import io.github.frapples.osbrainsystem.dal.utils.SecurityUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public ResponseDTO<Object> addAdmin(Admin admin) {
        boolean success = adminRepository.addAdmin(admin);
        return ResponseDTO.ofSuccess(success);
    }


    public ResponseDTO<Admin> getAdmin(Integer id) {
        if (id == null) {
            return ResponseDTO.ofFailed();
        }

        return adminRepository.getAdmin(id).map(ResponseDTO::ofSuccess).orElse(ResponseDTO.ofFailed());
    }

    public ResponseDTO<Boolean> check(String name, String password) {
        Optional<Admin> admin = adminRepository.getAdminByName(name);
        boolean pass = admin.map((ad) ->
            SecurityUtils.sha256(password).equals(ad.getPasswordHash()))
            .orElse(false);
        return ResponseDTO.ofSuccess(pass);
    }
}
