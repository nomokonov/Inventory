package inventory.atb.su.service;


import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.InvMovings;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.repository.FromExcelDataRepository;
import inventory.atb.su.repository.InvMovingsRepository;
import inventory.atb.su.repository.impl.DbUtilsDAO;
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
    private InvMovingsRepository invMovingsRepository;
    private DepartmentDaoImpl departmentDao;
    private MolDaoImpl molDao;
    private DbUtilsDAO dbUtilsDAO;

    @Autowired
    public FromExcelDataService(FromExcelDataRepository fromExcelDataRepositor, InvMovingsRepository invMovingsRepository, DepartmentDaoImpl departmentDao, MolDaoImpl molDao, DbUtilsDAO dbUtilsDAO) {
        this.fromExcelDataRepository = fromExcelDataRepositor;
        this.invMovingsRepository = invMovingsRepository;
        this.departmentDao = departmentDao;
        this.molDao = molDao;
        this.dbUtilsDAO = dbUtilsDAO;
    }

    public FromExcelData Save(FromExcelData fromExcelData) {
        return fromExcelDataRepository.save(fromExcelData);
    }

    public void deleteAll() {
        fromExcelDataRepository.deleteAll();
        dbUtilsDAO.sequencesRestart();
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

    public List<String> getAllMols() {
        return molDao.getAllMols();
    }

    public Optional<FromExcelData> getById(Long id) {
        return fromExcelDataRepository.findById(id);
    }

    public FromExcelData update(Long id, String mol, String codeDeparment) {
        Optional<FromExcelData> fromExcelDataFromDB = fromExcelDataRepository.findById(id);
        if (fromExcelDataFromDB.isPresent()) {
            FromExcelData fromExcelData = fromExcelDataFromDB.get();
            DepartmentDTO departmentByCode = departmentDao.getDepartmentByCode(codeDeparment);
            if (fromExcelData.getCodeDepartment().equals(departmentByCode.getCodeDepartment())
                    && fromExcelData.getMol().equals(mol)) {
                if (fromExcelData.getInvMovings() != null){
                    InvMovings invMovingsFromDB = invMovingsRepository.getOne(fromExcelData.getInvMovings().getId());
                    fromExcelData.setInvMovings(null);
                    invMovingsRepository.delete(invMovingsFromDB);

                    return fromExcelDataRepository.save(fromExcelData);
                }
                return fromExcelData;
            }
            if (fromExcelData.getInvMovings() == null) {
                fromExcelData.setInvMovings(new InvMovings(mol, departmentByCode, fromExcelData));

            } else {
                fromExcelData.getInvMovings().setMol(mol);
                fromExcelData.getInvMovings().setCodeDepartment(departmentByCode.getCodeDepartment());
                fromExcelData.getInvMovings().setNameDepartment(departmentByCode.getNameDepartment());
            }
            return fromExcelDataRepository.saveAndFlush(fromExcelData);
        } else {
            return null;
        }


    }

    public Optional<FromExcelData> getByInvNumber(String invnumber) {
        return fromExcelDataRepository.findByInvNumber(invnumber);
    }
}
