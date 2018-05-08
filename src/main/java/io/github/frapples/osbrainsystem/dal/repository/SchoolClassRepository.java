package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.SchoolClass;
import io.github.frapples.osbrainsystem.dal.dao.SchoolClassDO;
import io.github.frapples.osbrainsystem.dal.mapper.SchoolClassMapper;
import io.github.frapples.osbrainsystem.dal.mapper.UserMapper;
import io.github.frapples.osbrainsystem.dal.utils.CrudRepositoryUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolClassRepository {

    @Autowired
    private SchoolClassMapper schoolClassMapper;

    @Autowired
    private UserMapper userMapper;

    public boolean addClass(SchoolClass schoolClass) {
        return CrudRepositoryUtils.insert(schoolClassMapper, schoolClass, SchoolClassDO.class);
    }


    public List<SchoolClass> getClasses() {
        List<SchoolClassDO> dos = schoolClassMapper.selectList(new EntityWrapper<>());
        dos.forEach((classDO) -> {
            int count = userMapper.selectCount(Condition.create().eq("class_id", classDO.getId()));
            classDO.setUserCount(count);
        });
        List<SchoolClass> classes = ModelConverter.convert(dos, SchoolClass.class);
        return classes;
    }

    public Optional<SchoolClass> getClassById(Integer id) {
        return CrudRepositoryUtils.getItemByUnique(
            schoolClassMapper,
            SchoolClass.class,
            SchoolClassDO.class,
            "id",
            id);
    }

    public boolean updateClass(SchoolClass schoolClass) {
        SchoolClassDO schoolClassDO = ModelConverter.convert(schoolClass, SchoolClassDO.class);
        return schoolClassMapper.updateById(schoolClassDO) >= 1;
    }

}
