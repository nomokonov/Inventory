package inventory.atb.su.service;


import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.repository.FromExcelDataRepository;
import inventory.atb.su.repository.impl.DepartmentDaoImpl;
import inventory.atb.su.repository.impl.MolDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class FromExcelDataService {

    private FromExcelDataRepository fromExcelDataRepository;
    private DepartmentDaoImpl departmentDao;
    private MolDaoImpl molDao;

    @Autowired
    public FromExcelDataService(FromExcelDataRepository fromExcelDataRepositor, DepartmentDaoImpl departmentDao, MolDaoImpl molDao) {
        this.fromExcelDataRepository = fromExcelDataRepositor;
        this.departmentDao = departmentDao;
        this.molDao = molDao;
    }

    public FromExcelData Save(FromExcelData fromExcelData) {
        return fromExcelDataRepository.save(fromExcelData);
    }

    public void deleteAll() {
        fromExcelDataRepository.deleteAll();
    }

    public void saveAll(List<FromExcelData> fromExcelDataList) {
        fromExcelDataRepository.saveAll(fromExcelDataList);
    }

    public Page<FromExcelData> getAllByMol(String mol, Integer page, Integer pageSize, String sortBy, Optional<String> codeDepartment) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Specification<FromExcelData> fromExcelDataSpecification = null;
            if (codeDepartment.isPresent() && !codeDepartment.get().equals("?")) {
                fromExcelDataSpecification = where(fromExcelDataSpecification).and((root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("codeDepartment"), codeDepartment.get()));
            }
                fromExcelDataSpecification = where(fromExcelDataSpecification).and((root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("mol"), mol));
        Page<FromExcelData> pagedResult = fromExcelDataRepository.findAll(fromExcelDataSpecification, pageable);

        return pagedResult;

    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentDao.getAllDepartments();

    }

    public List<String> getAllMols(){
        return molDao.getAllMols();
    }
}
