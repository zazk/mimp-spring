package pe.gob.mimp.gis.service.impl;

import pe.gob.mimp.gis.dao.ExcelReaderDao;
import pe.gob.mimp.gis.entity.ExcelReader;
import pe.gob.mimp.gis.service.ExcelReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gian
 *
 */
@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {

    private static Logger log = LoggerFactory.getLogger(ExcelReaderServiceImpl.class);    
    
    @Autowired
    private ExcelReaderDao excelReaderDao;

    @Override
    public void insertar(ExcelReader excelReader) {
        log.info("Ingresamos el Excel a la BD");
        excelReaderDao.insertar(excelReader);
    }
}
