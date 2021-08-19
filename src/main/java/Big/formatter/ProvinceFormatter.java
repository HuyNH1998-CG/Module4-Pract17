package Big.formatter;

import Big.model.Province;
import Big.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class ProvinceFormatter implements Formatter<Province> {

    private IProvinceService iProvinceService;

    @Autowired
    public ProvinceFormatter(IProvinceService provinceService) {
        this.iProvinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Optional<Province> province = iProvinceService.findById(Long.parseLong(text));
        return province.orElse(null);
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}
