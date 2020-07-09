package inventory.atb.su.service;

import inventory.atb.su.repository.FromExcelDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromExcelDataService {

    private FromExcelDataRepository fromExcelDataRepository;

    @Autowired
    public FromExcelDataService(FromExcelDataRepository fromExcelDataRepositor) {
        this.fromExcelDataRepository = fromExcelDataRepositor;
    }


}
