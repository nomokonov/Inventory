package inventory.atb.su.service;


import inventory.atb.su.models.FromExcelData;
import inventory.atb.su.models.InvMovings;
import inventory.atb.su.models.dto.DepartmentDTO;
import inventory.atb.su.repository.FromExcelDataRepository;
import inventory.atb.su.repository.InvMovingsRepository;
import inventory.atb.su.repository.impl.DepartmentDaoImpl;
import inventory.atb.su.repository.impl.MolDaoImpl;
import inventory.atb.su.util.WriteExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class FromExcelDataService {

    private FromExcelDataRepository fromExcelDataRepository;
    private InvMovingsRepository invMovingsRepository;
    private DepartmentDaoImpl departmentDao;
    private MolDaoImpl molDao;
    private WriteExcel writeExcel;

    @Autowired
    public FromExcelDataService(FromExcelDataRepository fromExcelDataRepositor,
                                InvMovingsRepository invMovingsRepository, DepartmentDaoImpl departmentDao,
                                MolDaoImpl molDao, WriteExcel writeExcel) {
        this.fromExcelDataRepository = fromExcelDataRepositor;
        this.invMovingsRepository = invMovingsRepository;
        this.departmentDao = departmentDao;
        this.molDao = molDao;
        this.writeExcel = writeExcel;
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
                if (fromExcelData.getInvMovings() != null) {
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

    public Page<InvMovings> getAllWithInvMovings(Integer page, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<InvMovings> pagedResult = invMovingsRepository.findAll(pageable);
        return pagedResult;
    }

    public List<String> GetDocsMovings() throws IOException {
        String newDep = "";
        String oldDep = "";
        String newDepName = "";
        String oldDepName = "";
        String newMol = "";
        String oldMol = "";
        List<String> result = new ArrayList<>();
        List<InvMovings> listForExcel = new ArrayList<>();
        ;
        List<InvMovings> movingsList = invMovingsRepository.findAll(Sort.by("codeDepartment", "FromExcelData.codeDepartment"));
        if (movingsList.isEmpty()) {
            return result;
        } else {
            newDep = movingsList.get(0).getCodeDepartment();
             oldDep = movingsList.get(0).getFromExcelData().getCodeDepartment();
             newDepName = movingsList.get(0).getNameDepartment();
             oldDepName = movingsList.get(0).getFromExcelData().getNameDepartment();
            newMol =  movingsList.get(0).getMol();
            oldMol =  movingsList.get(0).getFromExcelData().getMol();
        }
        for (InvMovings item : movingsList) {
//            if new department destination
            if (newDep.equals(item.getCodeDepartment()) && oldDep.equals(item.getFromExcelData().getCodeDepartment())) {
                listForExcel.add(item);
            } else {
                result.add(writeExcel.getDocument(listForExcel, oldDepName, newDepName, oldMol, newMol));
                listForExcel.clear();
                listForExcel.add(item);
                newDep = item.getCodeDepartment();
                oldDep = item.getFromExcelData().getCodeDepartment();
                newDepName = item.getNameDepartment();
                oldDepName = item.getFromExcelData().getNameDepartment();
                newMol = item.getMol();
                oldMol = item.getFromExcelData().getMol();
            }
        }
        result.add(writeExcel.getDocument(listForExcel, oldDepName, newDepName, oldMol, newMol));
        return result;

    }
}
